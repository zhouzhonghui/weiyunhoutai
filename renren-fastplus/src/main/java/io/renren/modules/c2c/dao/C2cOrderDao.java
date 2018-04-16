package io.renren.modules.c2c.dao;

import io.renren.modules.c2c.entity.C2cOrderEntity;
import io.renren.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 16:49:51
 */
@Mapper
public interface C2cOrderDao extends BaseDao<C2cOrderEntity> {

    void updateState(Map<String, Object> map);
    int updateTransactionHash(Map<String, Object> map);
}
