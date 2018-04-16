package io.renren.modules.wybbscalp.service;

import io.renren.modules.wybbscalp.entity.WybbScalpEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author shunshine
 * @email shunshine@99weiyun.com
 * @date 2018-04-03 10:29:11
 */
public interface WybbScalpService {

    /**
    * 查询
    * @param map
    * @return
    */
    List<WybbScalpEntity> queryList(Map<String, Object> map);

    /**
    * 插入一条记录
    * @param user
    */
    void insert(List<WybbScalpEntity> user);

    /**
     * 更新一条记录
     * @param user
     */
    void updateById(WybbScalpEntity user);
    /**
     * 批量删除
     * @param user
     */
    void deleteById(WybbScalpEntity user);
    /**
    * 查询总数
    */
    int queryTotal(Map<String, Object> map);
}

