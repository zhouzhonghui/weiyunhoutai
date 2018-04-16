package io.renren.modules.wybbscalp.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtil;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import io.renren.modules.wybbscalp.dao.WybbScalpDao;
import io.renren.modules.wybbscalp.entity.WybbScalpEntity;
import io.renren.modules.wybbscalp.service.WybbScalpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;

@Service("wybbScalpService")
public class WybbScalpServiceImpl implements WybbScalpService {

    @Autowired
    private WybbScalpDao wybbScalpDao;

    @Override
    public List<WybbScalpEntity> queryList(Map<String, Object> map) {
        return wybbScalpDao.queryList(map);
    }
    @Autowired
    @Qualifier("scalpQueue")
    private Queue queue ;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    @Transactional(timeout = 5000,rollbackFor = RRException.class)
    public void insert(List<WybbScalpEntity> user) {
            for (int i=0;i<user.size();i++) {
                WybbScalpEntity wybbScalpEntity=user.get(i);
                String jobtime=wybbScalpEntity.getJobtime().substring(0,wybbScalpEntity.getJobtime().length()-2);
                try {
                    if (jobtime.compareTo(DateUtil.format(new Date(), "yyyyMMddHHmmss")) < 0) {//如果定时时间小于等于当前时间那么就即时投递消息
                        jmsTemplate.convertAndSend(this.queue, JSONObject.toJSONString(wybbScalpEntity));
                    } else {
                        //按时间算出来进行投递
                        jmsTemplate.send(this.queue, new MessageCreator() {
                            @Override
                            public Message createMessage(Session session) throws JMSException {
                                TextMessage textMessage = session.createTextMessage();
                                textMessage.setText(JSONObject.toJSONString(wybbScalpEntity));
                                Date date1 = DateUtil.parse(DateUtil.format(new Date(), "yyyyMMddHHmmss"), "yyyyMMddHHmmss");
                                Date date2 = DateUtil.parse(jobtime,
                                        "yyyyMMddHHmmss");
                                long diff = date2.getTime() - date1.getTime();
                                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, diff);
                                return textMessage;
                            }
                        });
                    }
                    wybbScalpEntity.setRemark1("0");
                } catch (Exception jms) {
                    jms.printStackTrace();
                    wybbScalpEntity.setRemark1("1");//出现异常后把remark做一个标记
                }
                wybbScalpEntity.setJobtime(jobtime);
                wybbScalpDao.save(wybbScalpEntity);
            }

    }

    @Override
    public void updateById(WybbScalpEntity user) {
            wybbScalpDao.update(user);
    }

    @Override
    public void deleteById(WybbScalpEntity user) {
            wybbScalpDao.delete(user);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return wybbScalpDao.queryTotal(map);
    }

}
