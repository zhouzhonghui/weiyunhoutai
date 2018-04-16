package io.renren.modules.order.service.impl;

import io.renren.modules.order.dao.OrderDao;
import io.renren.modules.order.entity.OrderEntity;
import io.renren.modules.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        List<OrderEntity> orderEntityList = orderDao.queryList(map);
        for (OrderEntity orderEntity : orderEntityList) {
            if (orderEntity.getOrderType() != null && OrderEntity.OrderType.findByValue(orderEntity.getOrderType()) != null) {
                orderEntity.setOrderTypeDesc(OrderEntity.OrderType.findByValue(orderEntity.getOrderType()).getDesc());
            }
            if (orderEntity.getState() != null && OrderEntity.State.findByValue(orderEntity.getState()) != null) {
                orderEntity.setStateDesc(OrderEntity.State.findByValue(orderEntity.getState()).getDesc());
            }
        }
        return orderEntityList;
    }

    @Override
    public OrderEntity selectById(Long id) {
        OrderEntity orderEntity = orderDao.queryObject(id);
        if (orderEntity.getOrderType() != null && OrderEntity.OrderType.findByValue(orderEntity.getOrderType()) != null) {
            orderEntity.setOrderTypeDesc(OrderEntity.OrderType.findByValue(orderEntity.getOrderType()).getDesc());
        }
        if (orderEntity.getState() != null && OrderEntity.State.findByValue(orderEntity.getState()) != null) {
            orderEntity.setStateDesc(OrderEntity.State.findByValue(orderEntity.getState()).getDesc());
        }
        return orderEntity;
    }

    @Override
    @Transactional
    public void save(OrderEntity order) {
        orderDao.save(order);
    }

    @Override
    @Transactional
    public void update(OrderEntity order) {
        orderDao.update(order);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        orderDao.deleteBatch(ids);
    }
}
