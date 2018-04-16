package io.renren.modules.news.dao;


import io.renren.modules.news.entity.NewsAttachment;
import io.renren.modules.news.entity.NewsAttachmentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NewsAttachmentDao {
    int countByExample(NewsAttachmentExample example);

    int deleteByExample(NewsAttachmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NewsAttachment record);

    int insertSelective(NewsAttachment record);

    List<NewsAttachment> selectByExample(NewsAttachmentExample example);

    NewsAttachment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NewsAttachment record, @Param("example") NewsAttachmentExample example);

    int updateByExample(@Param("record") NewsAttachment record, @Param("example") NewsAttachmentExample example);

    int updateByPrimaryKeySelective(NewsAttachment record);

    int updateByPrimaryKey(NewsAttachment record);
}