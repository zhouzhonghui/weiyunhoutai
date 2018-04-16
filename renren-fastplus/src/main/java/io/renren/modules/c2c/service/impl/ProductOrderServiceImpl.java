package io.renren.modules.c2c.service.impl;

import io.renren.modules.c2c.dao.ProductOrderDao;
import io.renren.modules.c2c.entity.ProductOrderEntity;
import io.renren.modules.c2c.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return productOrderDao.queryTotal(map);
    }

    @Override
    public List<ProductOrderEntity> queryList(Map<String, Object> map) {
        List<ProductOrderEntity> productOrderEntityList = productOrderDao.queryList(map);
        return productOrderEntityList;
    }

    @Override
    public ProductOrderEntity selectById(Long id) {
        ProductOrderEntity productOrderEntity = productOrderDao.queryObject(id);
        return productOrderEntity;
    }

    @Override
    @Transactional
    public void save(ProductOrderEntity productOrder) {
        productOrderDao.save(productOrder);
    }

    @Override
    @Transactional
    public void update(ProductOrderEntity productOrder) {
        productOrderDao.update(productOrder);
    }

    @Override
    @Transactional
    public void deleteBatchByIds(Long[] ids) {
        productOrderDao.delete(ids);
    }
}
