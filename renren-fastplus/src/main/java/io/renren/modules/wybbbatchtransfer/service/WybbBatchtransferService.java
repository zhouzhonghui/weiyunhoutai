package io.renren.modules.wybbbatchtransfer.service;

import io.renren.modules.wybbbatchtransfer.entity.WybbBatchtransferEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author shunshine
 * @email shunshine@99weiyun.com
 * @date 2018-04-10 09:46:58
 */
public interface WybbBatchtransferService {

    /**
    * 查询
    * @param map
    * @return
    */
    List<WybbBatchtransferEntity> queryList(Map<String, Object> map);

    /**
    * 插入一条记录
    * @param user
    */
    void insert(List<WybbBatchtransferEntity> user);

    /**
     * 更新一条记录
     * @param user
     */
    void updateById(WybbBatchtransferEntity user);
    /**
     * 批量删除
     * @param user
     */
    void deleteById(WybbBatchtransferEntity user);
    /**
    * 查询总数
    */
    int queryTotal(Map<String, Object> map);
}

