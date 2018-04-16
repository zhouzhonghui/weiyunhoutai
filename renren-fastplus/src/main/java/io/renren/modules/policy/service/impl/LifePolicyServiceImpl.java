package io.renren.modules.policy.service.impl;

import io.renren.modules.policy.dao.LifePolicyDao;
import io.renren.modules.policy.entity.LifePolicyEntity;
import io.renren.modules.policy.service.LifePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("lifePolicyService")
public class LifePolicyServiceImpl implements LifePolicyService {

    @Autowired
    private LifePolicyDao lifePolicyDao;

    @Override
    public List<LifePolicyEntity> queryList(Map<String, Object> map) {
        List<LifePolicyEntity> lifePolicyEntityList = lifePolicyDao.queryList(map);
        return lifePolicyEntityList;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return lifePolicyDao.queryTotal(map);
    }

    @Override
    public LifePolicyEntity selectById(Long id) {
        LifePolicyEntity lifePolicyEntity = lifePolicyDao.queryObject(id);
        return lifePolicyEntity;
    }

    @Override
    @Transactional
    public void save(LifePolicyEntity policy) {
        lifePolicyDao.save(policy);
    }

    @Override
    @Transactional
    public void update(LifePolicyEntity policy) {
        lifePolicyDao.update(policy);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        lifePolicyDao.deleteBatch(ids);
    }
}
