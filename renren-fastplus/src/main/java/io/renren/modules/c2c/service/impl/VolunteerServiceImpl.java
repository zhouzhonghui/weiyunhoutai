package io.renren.modules.c2c.service.impl;

import io.renren.modules.c2c.dao.PaymentMethodDao;
import io.renren.modules.c2c.dao.VolunteerDao;
import io.renren.modules.c2c.entity.PaymentMethodEntity;
import io.renren.modules.c2c.entity.VolunteerEntity;
import io.renren.modules.c2c.service.VolunteerService;
import io.renren.modules.oss.cloud.OSSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private PaymentMethodDao paymentMethodDao;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return volunteerDao.queryTotal(map);
    }

    @Override
    public List<VolunteerEntity> queryList(Map<String, Object> map) {
        List<VolunteerEntity> volunteerEntityList = volunteerDao.queryList(map);
        for (VolunteerEntity volunteerEntity : volunteerEntityList) {
            if (volunteerEntity.getOnline() != null && VolunteerEntity.OnLine.findByValue(volunteerEntity.getOnline()) != null) {
                volunteerEntity.setOnlineDesc(VolunteerEntity.OnLine.findByValue(volunteerEntity.getOnline()).getDesc());
            }
        }
        return volunteerEntityList;
    }

    @Override
    public VolunteerEntity selectById(Long id) {
        VolunteerEntity volunteerEntity = volunteerDao.queryObject(id);
        return volunteerEntity;
    }

    @Override
    @Transactional
    public void save(VolunteerEntity volunteer) {

        Map<String, Object> map = new HashMap<>();
        map.put("volunteer", volunteer);
        volunteerDao.save(volunteer);

        for (PaymentMethodEntity pm : volunteer.getPaymentMethodList()) {
            pm.setVolunteerId(volunteer.getId());
        }

        Map<String, Object> pmMap = new HashMap<>();
        pmMap.put("pmList", volunteer.getPaymentMethodList());
        paymentMethodDao.save(pmMap);
    }

    @Override
    public  String uploadFile(MultipartFile file) throws IOException {
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String imgPath = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);
        return imgPath;
    }

    @Override
    @Transactional
    public void update(VolunteerEntity volunteer) {
        Map<String, Object> map = new HashMap<>();
        map.put("volunteer", volunteer);
        volunteerDao.update(map);

        List<PaymentMethodEntity> addList = new ArrayList<>();
        List<PaymentMethodEntity> modifyList = new ArrayList<>();

        for (PaymentMethodEntity pm : volunteer.getPaymentMethodList()) {
            if (pm.getId() == null) {
                pm.setVolunteerId(volunteer.getId());
                pm.setCreatedAt(new Date());
                pm.setState(1);
                addList.add(pm);
            } else {
                modifyList.add(pm);
            }
        }

        if (addList.size() > 0) {
            Map<String, Object> pmAddMap = new HashMap<>();
            pmAddMap.put("pmList", addList);
            paymentMethodDao.save(pmAddMap);
        }

        Map<String, Object> pmModifyMap = new HashMap<>();
        pmModifyMap.put("paymentMethodList", modifyList);
        paymentMethodDao.update(pmModifyMap);
    }

    @Override
    @Transactional
    public void deleteBatchByIds(Long[] ids) {
        volunteerDao.deleteBatch(ids);
    }

    @Override
    @Transactional
    public void modifyOnLine(Long id, Integer onLine) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("onLine", onLine);
        volunteerDao.modifyOnLine(map);
    }
}
