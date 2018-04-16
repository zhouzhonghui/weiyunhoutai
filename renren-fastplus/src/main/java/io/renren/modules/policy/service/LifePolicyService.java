package io.renren.modules.policy.service;


import io.renren.modules.policy.entity.LifePolicyEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-30 17:18:27
 */
public interface LifePolicyService {
    /**
     * 查询列表
     */
    List<LifePolicyEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    LifePolicyEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param policy
     */
    void save(LifePolicyEntity policy);

    /**
     * 更新一条记录
     *
     * @param policy
     */
    void update(LifePolicyEntity policy);

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteBatch(Long[] ids);


}

