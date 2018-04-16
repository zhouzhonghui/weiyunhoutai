package io.renren.modules.product.service.impl;

import io.renren.modules.product.dao.ProductDao;
import io.renren.modules.product.entity.ProductEntity;
import io.renren.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return productDao.queryTotal(map);
    }

    @Override
    public List<ProductEntity> queryList(Map<String, Object> map) {
        List<ProductEntity> productEntityList = productDao.queryList(map);
        return productEntityList;
    }

    @Override
    public ProductEntity selectById(Long id) {
        ProductEntity productEntity = productDao.queryObject(id);
        return productEntity;
    }

    @Override
    @Transactional
    public void save(ProductEntity product) {
        productDao.save(product);
    }

    @Override
    @Transactional
    public void update(ProductEntity product) {
        productDao.update(product);
    }

    @Override
    @Transactional
    public void deleteBatchByIds(Long[] ids) {
        productDao.deleteBatch(ids);
    }
}
