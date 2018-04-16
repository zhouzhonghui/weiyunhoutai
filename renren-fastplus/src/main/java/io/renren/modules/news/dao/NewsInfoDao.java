package io.renren.modules.news.dao;

import io.renren.modules.news.entity.NewsInfo;
import io.renren.modules.news.entity.NewsInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NewsInfoDao {
    int countByExample(NewsInfoExample example);

    int deleteByExample(NewsInfoExample example);

    int deleteByPrimaryKey(Long newsid);

    int insert(NewsInfo record);

    int insertSelective(NewsInfo record);

    List<NewsInfo> selectByExampleWithBLOBs(NewsInfoExample example);

    List<NewsInfo> selectByExample(NewsInfoExample example);

    NewsInfo selectByPrimaryKey(Long newsid);

    int updateByExampleSelective(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    int updateByExample(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    int updateByPrimaryKeySelective(NewsInfo record);

    int updateByPrimaryKeyWithBLOBs(NewsInfo record);

    int updateByPrimaryKey(NewsInfo record);
}