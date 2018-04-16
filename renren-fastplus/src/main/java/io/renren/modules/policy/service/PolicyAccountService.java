package io.renren.modules.policy.service;

import io.renren.modules.policy.entity.PolicyAccountEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 14:41:28
 */
public interface PolicyAccountService {
    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询列表
     */
    List<PolicyAccountEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    PolicyAccountEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param policyAccount
     */
    void save(PolicyAccountEntity policyAccount);

    /**
     * 更新一条记录
     *
     * @param policyAccount
     */
    void update(PolicyAccountEntity policyAccount);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(Long[] ids);

}

