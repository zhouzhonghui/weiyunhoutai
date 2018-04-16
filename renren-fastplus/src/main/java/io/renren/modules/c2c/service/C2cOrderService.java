package io.renren.modules.c2c.service;

import io.renren.modules.c2c.entity.C2cOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 16:49:51
 */
public interface C2cOrderService {

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
    List<C2cOrderEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    C2cOrderEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param c2cOrder
     */
    void save(C2cOrderEntity c2cOrder);

    /**
     * 更新一条记录
     *
     * @param c2cOrder
     */
    void update(C2cOrderEntity c2cOrder);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatchByIds(Long[] ids);

    /**
     * 修改订单状态
     *
     * @param id
     */
    void updateState(Long id, Integer state);

    String confirmPayment(Long id) throws Exception;
}

