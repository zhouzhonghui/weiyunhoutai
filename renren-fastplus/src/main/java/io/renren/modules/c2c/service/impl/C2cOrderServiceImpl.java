package io.renren.modules.c2c.service.impl;

import io.renren.common.eth.Consts;
import io.renren.modules.c2c.dao.C2cOrderDao;
import io.renren.modules.c2c.dao.VolunteerDao;
import io.renren.modules.c2c.entity.C2cOrderEntity;
import io.renren.modules.c2c.entity.VolunteerEntity;
import io.renren.modules.c2c.service.C2cOrderService;
import io.renren.modules.order.dao.OrderDao;
import io.renren.modules.order.entity.OrderEntity;
import io.renren.modules.transfer.service.TransferService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("c2cOrderService")
public class C2cOrderServiceImpl implements C2cOrderService {

    @Autowired
    private C2cOrderDao c2cOrderDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private VolunteerDao volunteerDao;

    @Autowired
    private TransferService transferService;
    @Override
    public int queryTotal(Map<String, Object> map) {
        return c2cOrderDao.queryTotal(map);
    }

    @Override
    public List<C2cOrderEntity> queryList(Map<String, Object> map) {
        List<C2cOrderEntity> c2cOrderEntityList = c2cOrderDao.queryList(map);
        for (C2cOrderEntity c2cOrderEntity : c2cOrderEntityList) {
            if (c2cOrderEntity.getState() != null && C2cOrderEntity.State.findByValue(c2cOrderEntity.getState()) != null) {
                c2cOrderEntity.setStateDesc(C2cOrderEntity.State.findByValue(c2cOrderEntity.getState()).getDesc());
            }
            if (c2cOrderEntity.getPaymentName() != null && C2cOrderEntity.PaymentMethod.valueOf(c2cOrderEntity.getPaymentName()) != null) {
                c2cOrderEntity.setPaymentName(C2cOrderEntity.PaymentMethod.valueOf(c2cOrderEntity.getPaymentName()).getDesc());
            }
        }
        return c2cOrderEntityList;
    }

    @Override
    public C2cOrderEntity selectById(Long id) {
        C2cOrderEntity c2cOrderEntity = c2cOrderDao.queryObject(id);
        return c2cOrderEntity;
    }

    @Override
    @Transactional
    public void save(C2cOrderEntity c2cOrder) {
        c2cOrderDao.save(c2cOrder);
    }

    @Override
    @Transactional
    public void update(C2cOrderEntity c2cOrder) {
        c2cOrderDao.update(c2cOrder);
    }

    @Override
    @Transactional
    public void deleteBatchByIds(Long[] ids) {
        c2cOrderDao.deleteBatch(ids);
    }

    @Override
    @Transactional
    public void updateState(Long id, Integer state){
        C2cOrderEntity c2cOrderEntity= c2cOrderDao.queryObject(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("state", OrderEntity.State.ygcancel.getValue());
        map.put("version", c2cOrderEntity.getVersion());
        c2cOrderDao.updateState(map);

        OrderEntity orderEntity=orderDao.queryObject(c2cOrderEntity.getOrderId());
        map = new HashMap<>();
        map.put("id", orderEntity.getId());
        map.put("state", OrderEntity.State.cancel.getValue());
        map.put("version", orderEntity.getVersion());
        orderDao.updateState(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String confirmPayment(Long id) throws Exception{

        C2cOrderEntity c2cOrderEntity= c2cOrderDao.queryObject(id);
        VolunteerEntity volunteerEntity=volunteerDao.queryObject(c2cOrderEntity.getVolunteerId());
        if(StringUtils.isBlank(volunteerEntity.getFromAddress())){

            throw new Exception("义工未绑定转币地址！");
        }
        Long orderId=c2cOrderEntity.getOrderId();


        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("state", C2cOrderEntity.State.payed.getValue());
        map.put("version", c2cOrderEntity.getVersion());
        c2cOrderDao.updateState(map);



        OrderEntity orderEntity=orderDao.queryObject(orderId);

        map = new HashMap<>();
        map.put("id", orderEntity.getId());
        map.put("fromAddress", volunteerEntity.getFromAddress());
        map.put("version", orderEntity.getVersion());
        System.out.println("version1="+orderEntity.getVersion());
        orderDao.updateFromAddress(map);

        //修改成功人数
        //volunteerDao.updateCompleteNumById(volunteerEntity.getId());
        //public int transfer(String fromAddress,String passWord, String toAddress, BigDecimal value,String walletPath,int transferType);

        String transfer = transferService.transfer(volunteerEntity.getFromAddress(), volunteerEntity.getWalletPwd(), orderEntity.getToAddress(), c2cOrderEntity.getNumber(), Consts.VOLUNTEER_DIRECTORY+volunteerEntity.getWalletPath(), 1);

        if(StringUtils.isBlank(transfer)){
            throw new RuntimeException("自动转账失败！");
        }
        try{
            C2cOrderEntity tempC2cOrderEntity= c2cOrderDao.queryObject(id);
            map = new HashMap<>();
            map.put("id", tempC2cOrderEntity.getId());
            map.put("transactionHash", transfer);
            map.put("version", tempC2cOrderEntity.getVersion());
            System.out.println("version2="+tempC2cOrderEntity.getVersion());
            int i = c2cOrderDao.updateTransactionHash(map);
            if (i<=0){
                System.out.println("没修改！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return transfer;

    }
}
