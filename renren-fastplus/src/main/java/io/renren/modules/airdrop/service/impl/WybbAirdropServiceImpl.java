package io.renren.modules.airdrop.service.impl;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import io.renren.modules.airdrop.dao.WybbAirdropDao;
import io.renren.modules.airdrop.entity.WybbAirdropEntity;
import io.renren.modules.airdrop.service.WybbAirdropService;


@Service("wybbAirdropService")
public class WybbAirdropServiceImpl  implements WybbAirdropService {
    @Autowired
    private WybbAirdropDao wybbAirdropDao;


    @Override
    public List<WybbAirdropEntity> queryList(Map<String, Object> map) {
        return wybbAirdropDao.queryList(map);
    }

    @Override
    public List<WybbAirdropEntity> queryByDateAndName(Map<String, Object> map) {
        return wybbAirdropDao.queryListByCondition(map);
    }

    @Override
    public WybbAirdropEntity selectById(Long id) {
        return wybbAirdropDao.queryObject(id);
    }

    @Override
    public void insert(WybbAirdropEntity user) {
        wybbAirdropDao.save(user);
    }

    @Override
    public void updateById(WybbAirdropEntity user) {
        wybbAirdropDao.update(user);
    }

    @Override
    public void deleteBatchIds(WybbAirdropEntity user) {
        wybbAirdropDao.delete(user);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return wybbAirdropDao.queryTotal(map);
    }
}
