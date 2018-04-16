package io.renren.modules.news.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.news.domain.NewsAttachmentRes;
import io.renren.modules.news.domain.NewsInfoReq;
import io.renren.modules.news.entity.NewsInfo;
import io.renren.modules.news.service.NewsInfoService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 资讯信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-30 13:22:02
 */
@RestController
@RequestMapping("generator/newsinfo")
public class NewsInfoController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsInfoService newsInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:newsinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        logger.debug("/list=======================================" + params);
        Query query = new Query(params);
        List<NewsInfo> list = newsInfoService.queryPage(query);
        int total = newsInfoService.countByExample(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{newsid}")
    @RequiresPermissions("generator:newsinfo:info")
    public R info(@PathVariable("newsid") Long newsid) {
        logger.debug("/info/{newsid}===============================" + newsid);
        NewsInfoReq req = newsInfoService.selectByPrimaryKey(newsid);

        return R.ok().put("newsInfo", req);
    }
//

    /**
     * 保存
     */
    @SysLog("新增资讯")
    @RequestMapping("/save")
    @RequiresPermissions("generator:newsinfo:save")
    public R save(@RequestBody NewsInfoReq domain) {
        logger.debug("/save============================================" + domain.toString());
        newsInfoService.insert(domain);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改资讯")
    @RequestMapping("/update")
    @RequiresPermissions("generator:newsinfo:update")
    public R update(@RequestBody NewsInfoReq domain) {
        logger.debug("/update============================================" + domain.toString());

        newsInfoService.updateById(domain);

        return R.ok();
    }

    /**
     * 上传
     */
    @RequestMapping("/uploadFile")
    @RequiresPermissions(value = {"generator:newsinfo:save", "generator:newsinfo:update"}, logical = Logical.OR)
    public R uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "type") String covertype, @RequestParam(value = "attaid") String attaid, @RequestParam(value = "sort") int sort) throws IOException {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        NewsAttachmentRes res = newsInfoService.uploadFile(file, covertype, attaid, sort);


        return R.ok().put("res", res);
    }

    /**
     * 删除图片
     */
    @RequestMapping("/deleteFile")
    @RequiresPermissions(value = {"generator:newsinfo:save", "generator:newsinfo:update"}, logical = Logical.OR)
    public R deleteFile(@RequestParam(value = "dataId") String dataId, @RequestParam(value = "attaid") String attaid) throws IOException {
        NewsAttachmentRes res = newsInfoService.deleteFile(dataId, attaid);


        return R.ok().put("res", res);
    }


//

    /**
     * 删除
     */
    @SysLog("删除资讯")
    @RequestMapping("/delete")
    @RequiresPermissions("generator:newsinfo:delete")
    public R delete(@RequestBody Long[] newsids) {

        newsInfoService.deleteBatchIds(Arrays.asList(newsids));

        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/queryTop/{newsid}")
    @RequiresPermissions(value = {"generator:newsinfo:save", "generator:newsinfo:update"}, logical = Logical.OR)
    public R queryTop(@PathVariable("newsid") String newsid) throws Exception {
        logger.debug("/queryTop/{newsid}===============================" + newsid);
        int i = newsInfoService.queryTop(newsid);
        if(i>1)
            throw new RRException("已有两篇被置顶的资讯！",1) ;
        return R.ok();
    }

}
