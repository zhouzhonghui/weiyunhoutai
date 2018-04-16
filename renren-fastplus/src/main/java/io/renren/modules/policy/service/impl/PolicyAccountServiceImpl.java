package io.renren.modules.policy.service.impl;

import io.renren.modules.policy.dao.PolicyAccountDao;
import io.renren.modules.policy.entity.PolicyAccountEntity;
import io.renren.modules.policy.service.PolicyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("policyAccountService")
public class PolicyAccountServiceImpl implements PolicyAccountService {

    @Autowired
    private PolicyAccountDao policyAccountDao;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return policyAccountDao.queryTotal(map);
    }

    @Override
    public List<PolicyAccountEntity> queryList(Map<String, Object> map) {
        List<PolicyAccountEntity> policyAccountEntityList = policyAccountDao.queryList(map);
        return policyAccountEntityList;
    }

    @Override
    public PolicyAccountEntity selectById(Long id) {
        PolicyAccountEntity policyAccountEntity = policyAccountDao.queryObject(id);
        return policyAccountEntity;
    }

    @Override
    @Transactional
    public void save(PolicyAccountEntity policyAccount) {
        policyAccountDao.save(policyAccount);
    }

    @Override
    @Transactional
    public void update(PolicyAccountEntity policyAccount) {
        policyAccountDao.update(policyAccount);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        policyAccountDao.deleteBatch(ids);
    }
}
