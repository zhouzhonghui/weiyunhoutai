package io.renren.modules.news.service;


import io.renren.modules.news.domain.NewsAttachmentRes;
import io.renren.modules.news.domain.NewsInfoReq;
import io.renren.modules.news.entity.NewsInfo;
import io.renren.modules.news.entity.NewsInfoExample;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 资讯信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-30 13:22:02
 */
public interface NewsInfoService {

    List<NewsInfo> queryPage(Map<String, Object> params);

    int insert(NewsInfoReq domain);

    NewsAttachmentRes uploadFile(MultipartFile file, String covertype, String attaid, int sort) throws IOException;

    NewsAttachmentRes deleteFile(String dataId, String attaid);

    NewsInfoReq selectByPrimaryKey(Long newsid);

    int countByExample(Map<String, Object> params);

    int updateById(NewsInfoReq domain);

    void deleteBatchIds(List<Long> longs);

    int queryTop(String newsid) throws Exception;
}

