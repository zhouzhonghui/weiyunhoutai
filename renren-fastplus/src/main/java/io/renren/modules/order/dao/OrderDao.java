package io.renren.modules.order.dao;

import io.renren.modules.order.entity.OrderEntity;
import io.renren.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 13:53:24
 */
@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {

    OrderEntity queryOrder(Object id);

    int updateFromAddress(Map<String, Object> map);

    void updateState(Map<String, Object> map);


}
