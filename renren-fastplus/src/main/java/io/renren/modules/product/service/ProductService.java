package io.renren.modules.product.service;

import io.renren.modules.product.entity.ProductEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-10 12:22:45
 */
public interface ProductService {

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
    List<ProductEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    ProductEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param product
     */
    void save(ProductEntity product);

    /**
     * 更新一条记录
     *
     * @param product
     */
    void update(ProductEntity product);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatchByIds(Long[] ids);
}

