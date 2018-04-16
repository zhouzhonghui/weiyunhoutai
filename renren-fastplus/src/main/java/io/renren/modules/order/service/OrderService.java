package io.renren.modules.order.service;

import io.renren.modules.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 13:53:24
 */
public interface OrderService {

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询列表
     */
    List<OrderEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    OrderEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param order
     */
    void save(OrderEntity order);

    /**
     * 更新一条记录
     *
     * @param order
     */
    void update(OrderEntity order);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(Long[] ids);

}

