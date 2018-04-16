package io.renren.modules.c2c.service;


import io.renren.modules.c2c.entity.ProductOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-09 20:18:01
 */
public interface ProductOrderService {

    /**
     * 查询总数
     *
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    List<ProductOrderEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    ProductOrderEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param productOrder
     */
    void save(ProductOrderEntity productOrder);

    /**
     * 更新一条记录
     *
     * @param productOrder
     */
    void update(ProductOrderEntity productOrder);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatchByIds(Long[] ids);
}

