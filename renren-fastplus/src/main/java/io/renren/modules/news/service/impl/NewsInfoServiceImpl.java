package io.renren.modules.news.service.impl;

import com.alibaba.fastjson.JSON;
import io.renren.common.utils.DateUtil;
import io.renren.modules.news.dao.NewsInfoDao;
import io.renren.modules.news.domain.ImgReq;
import io.renren.modules.news.domain.NewsAttachmentRes;
import io.renren.modules.news.domain.NewsInfoReq;
import io.renren.modules.news.entity.NewsAttachment;
import io.renren.modules.news.entity.NewsInfo;
import io.renren.modules.news.entity.NewsInfoExample;
import io.renren.modules.news.service.NewsInfoService;
import io.renren.modules.oss.cloud.OSSFactory;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;


@Service("newsInfoService")
public class NewsInfoServiceImpl implements NewsInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private NewsInfoDao newsInfoDao;

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private ListOperations listOperations;


    @Override
    public List<NewsInfo> queryPage(Map<String, Object> params) {
        NewsInfoExample example = new NewsInfoExample();
        example.setLimit((Integer) params.get("limit"));
        example.setOffset((Integer) params.get("offset"));
        example.setOrderByClause("publishtime desc");
        return newsInfoDao.selectByExampleWithBLOBs(example);
    }

    @Override
    public int countByExample(Map<String, Object> params) {
        NewsInfoExample example = new NewsInfoExample();
        return newsInfoDao.countByExample(example);
    }

    @Override
    public int updateById(NewsInfoReq domain) {
        NewsInfo newsInfo = new NewsInfo();
        BeanUtils.copyProperties(domain, newsInfo);
        newsInfo.setPublishtime(DateUtil.parse(domain.getPublishtime(), DateUtil.YYYYMMDDHHMM));
        long userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        newsInfo.setModifyuser(userId);
        newsInfo.setModityat(new Date());

        /**
         * 图片类
         */
        if ("1".equals(domain.getNewstype())) {
            storePic(domain, true);
        }
        /**
         * 视频类
         */
        if ("2".equals(domain.getNewstype())) {
            storeVideo(domain, true);
        }

        return newsInfoDao.updateByPrimaryKeyWithBLOBs(newsInfo);
    }


    private void storeVideo(NewsInfoReq domain, boolean isUpdate) {
        if (isUpdate) {
            listOperations.leftPop(domain.getAttaid() + "video");
        }

        NewsAttachment attachment = new NewsAttachment();
        attachment.setAttaid(domain.getAttaid());
        attachment.setUrlpath(domain.getVideoUrl());
        attachment.setAttatype("2");
        attachment.setContent(domain.getVideoDesc());
        attachment.setId(valueOperations.increment("newsAttachement", 1l));
        listOperations.rightPush(attachment.getAttaid() + "video", JSON.toJSONString(attachment));
    }

    @Override
    public int insert(NewsInfoReq domain) {
        NewsInfo newsInfo = new NewsInfo();
        BeanUtils.copyProperties(domain, newsInfo);
        newsInfo.setPublishtime(DateUtil.parse(domain.getPublishtime(), DateUtil.YYYYMMDDHHMM));
        long userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        newsInfo.setCreateuser(userId);
        newsInfo.setCreateat(new Date());

        /**
         * 图片类
         */
        if ("1".equals(domain.getNewstype())) {
            storePic(domain, false);
        }
        /**
         * 视频类
         */
        else if ("2".equals(domain.getNewstype())) {
            storeVideo(domain, false);
        }

        return newsInfoDao.insert(newsInfo);
    }

    private void storePic(NewsInfoReq domain, boolean isUpdate) {
        List<ImgReq> list = domain.getPicList();

        if (isUpdate) {
            long size = listOperations.size(domain.getAttaid() + "pic");

            logger.debug("before leftPop===================" + size);
            for (int i = 0; i < size; i++)
                listOperations.leftPop(domain.getAttaid() + "pic");

            logger.debug("after leftPop====================" + listOperations.size(domain.getAttaid() + "pic"));
        }

        list.forEach(imgReq -> {
            NewsAttachment attachment = new NewsAttachment();
            attachment.setAttaid(domain.getAttaid());
            attachment.setUrlpath(imgReq.getImgSrc());
            attachment.setAttatype("1");
            attachment.setId(Long.valueOf(imgReq.getDataId()));
            attachment.setContent(imgReq.getTextarea());

            listOperations.rightPush(attachment.getAttaid() + "pic", JSON.toJSONString(attachment));
        });
    }

    @Override
    public NewsAttachmentRes uploadFile(MultipartFile file, String type, String attaid, int sort) throws IOException {
        NewsAttachmentRes res = new NewsAttachmentRes();

        if (StringUtils.isBlank(attaid) || "0".equals(attaid)) {
            Long l = valueOperations.increment("newsAttachement", 1l);
            attaid = String.format("%08d", l);
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        NewsAttachment attachment = new NewsAttachment();
        attachment.setAttaid(attaid);
        attachment.setUrlpath(url);
        attachment.setAttatype(type);
        attachment.setId(valueOperations.increment("newsAttachement", 1l));

        /**
         * 封面图
         */
        if ("0".equals(type)) {
            long size = listOperations.size(attachment.getAttaid() + "cover");
            Object o = listOperations.index(attachment.getAttaid() + "cover", sort);
            if (null == o)
                listOperations.rightPush(attachment.getAttaid() + "cover", JSON.toJSONString(attachment));
            else
                listOperations.set(attachment.getAttaid() + "cover", sort, JSON.toJSONString(attachment));
        }
        /**
         * 图片
         */
        else if ("1".equals(type)) {
//            listOperations.rightPush(attachment.getAttaid() + "pic", JSON.toJSONString(attachment));
        }
        /**
         * 视频
         */
        else {

        }

        BeanUtils.copyProperties(attachment, res);
        return res;
    }

    @Override
    public NewsAttachmentRes deleteFile(String dataId, String attaid) {
        NewsAttachmentRes attachmentRes = new NewsAttachmentRes();

        List<String> list = listOperations.range(attaid + "pic", 0, 20);

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.indexOf(":" + dataId + ",") > 0)
                listOperations.remove(attaid + "pic", i, s);
        }

        return attachmentRes;
    }

    @Override
    public NewsInfoReq selectByPrimaryKey(Long newsid) {
        NewsInfoReq req = new NewsInfoReq();

        NewsInfo info = newsInfoDao.selectByPrimaryKey(newsid);
        BeanUtils.copyProperties(info, req);

        /**
         * 获取封面图
         */
        List<String> list = listOperations.range(req.getAttaid() + "cover", 0, 20);
        req.setCoverList(list);

        /**
         * 图片类
         */
        if ("1".equals(info.getNewstype())) {
            List<String> picRedisList = listOperations.range(info.getAttaid() + "pic", 0, 20);
            List<ImgReq> picList = new ArrayList();
            picRedisList.forEach(str -> {
                NewsAttachment attachment = JSON.parseObject(str, NewsAttachment.class);
                ImgReq imgReq = new ImgReq();
                imgReq.setDataId(attachment.getId().toString());
                imgReq.setImgSrc(attachment.getUrlpath());
                imgReq.setTextarea(attachment.getContent());
                picList.add(imgReq);
            });

            req.setPicList(picList);
        }
        /**
         * 视频类
         */
        else if ("2".equals(info.getNewstype())) {
            List<String> videoList = listOperations.range(info.getAttaid() + "video", 0, 3);
            String str = videoList.get(0);
            NewsAttachment attachment = JSON.parseObject(str, NewsAttachment.class);
            req.setVideoDesc(attachment.getContent());
            req.setVideoUrl(attachment.getUrlpath());
        }

        req.setPublishtime(DateUtil.format(info.getPublishtime(), DateUtil.YYYYMMDDHHMM));

        return req;
    }

    @Override
    public void deleteBatchIds(List<Long> list) {
        NewsInfoExample example = new NewsInfoExample();
        NewsInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNewsidIn(list);

        newsInfoDao.deleteByExample(example);
    }

    @Override
    public int queryTop(String newsid) throws Exception {
        NewsInfoExample example = new NewsInfoExample();
        example.createCriteria().andIstopEqualTo("1").andNewsidNotEqualTo(Long.valueOf(newsid));
        return newsInfoDao.countByExample(example);
    }


}
