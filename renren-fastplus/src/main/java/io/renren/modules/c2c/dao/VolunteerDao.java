package io.renren.modules.c2c.dao;

import io.renren.modules.c2c.entity.VolunteerEntity;
import io.renren.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 15:05:52
 */
@Mapper
public interface VolunteerDao extends BaseDao<VolunteerEntity> {

    void modifyOnLine(Map<String, Object> map);

    int  updateCompleteNumById(Object id);
}
