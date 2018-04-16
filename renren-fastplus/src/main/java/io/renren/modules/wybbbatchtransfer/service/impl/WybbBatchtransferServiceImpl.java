package io.renren.modules.wybbbatchtransfer.service.impl;

import io.renren.common.eth.Consts;
import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.redis.SysConfigRedis;
import io.renren.modules.sys.service.SysConfigService;
import io.renren.modules.transfer.service.TransferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import io.renren.modules.wybbbatchtransfer.dao.WybbBatchtransferDao;
import io.renren.modules.wybbbatchtransfer.entity.WybbBatchtransferEntity;
import io.renren.modules.wybbbatchtransfer.service.WybbBatchtransferService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("wybbBatchtransferService")
public class WybbBatchtransferServiceImpl implements WybbBatchtransferService {

    @Autowired
    private WybbBatchtransferDao wybbBatchtransferDao;

    @Autowired
    private TransferService transferService;

    @Autowired
    private SysConfigDao sysConfigDao;


    @Override
    public List<WybbBatchtransferEntity> queryList(Map<String, Object> map) {
        return wybbBatchtransferDao.queryList(map);
    }

    @Override
    public void insert(List<WybbBatchtransferEntity> user) {
            for (int i=0;i<user.size();i++){
                WybbBatchtransferEntity wybbBatchtransferEntity=user.get(i);
                SysConfigEntity sysConfigEntity=sysConfigDao.queryByKey(wybbBatchtransferEntity.getPayaddress());
                System.out.println(sysConfigEntity.getValue());
                try {
                    String hash = transferService.transfer(wybbBatchtransferEntity.getPayaddress(),"99Weiyun",
                            wybbBatchtransferEntity.getGatheraddress(),new BigDecimal(wybbBatchtransferEntity.getAmount()),
                            Consts.BATCHDIRECTORY+sysConfigEntity.getValue(),Integer.parseInt(wybbBatchtransferEntity.getFlag()));
                    if (!"".equals(hash) && null != hash) {
                        wybbBatchtransferEntity.setStatus("1");
                    } else {
                        wybbBatchtransferEntity.setStatus("0");
                    }
                }catch (Exception e){
                    //转账失败
                    wybbBatchtransferEntity.setStatus("2");
                }
                wybbBatchtransferDao.save(wybbBatchtransferEntity);
            }

    }

    @Override
    public void updateById(WybbBatchtransferEntity user) {
            wybbBatchtransferDao.update(user);
    }

    @Override
    public void deleteById(WybbBatchtransferEntity user) {
            wybbBatchtransferDao.delete(user);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return wybbBatchtransferDao.queryTotal(map);
    }

}
