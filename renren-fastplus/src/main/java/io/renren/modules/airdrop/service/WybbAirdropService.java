package io.renren.modules.airdrop.service;


import io.renren.modules.airdrop.entity.WybbAirdropEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-27 09:46:31
 */
public interface WybbAirdropService  {

    /**
     *
     */
    List<WybbAirdropEntity> queryList(Map<String, Object> map);

    /**
     *
     */
    List<WybbAirdropEntity> queryByDateAndName(Map<String, Object> map);
    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    WybbAirdropEntity selectById(Long id);

    /**
     * 插入一条记录
     * @param user
     */
    void insert(WybbAirdropEntity user);

    /**
     * 更新一条记录
     * @param user
     */
    void updateById(WybbAirdropEntity user);

    /**
     * 批量删除
     * @param user
     */
    void deleteBatchIds(WybbAirdropEntity user);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);
}

