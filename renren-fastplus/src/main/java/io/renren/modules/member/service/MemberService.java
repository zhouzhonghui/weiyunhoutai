package io.renren.modules.member.service;

import io.renren.modules.member.entity.MemberEntity;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-30 13:40:55
 */
public interface MemberService {

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询列表
     */
    List<MemberEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    MemberEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param member
     */
    void save(MemberEntity member);

    /**
     * 更新一条记录
     *
     * @param member
     */
    void update(MemberEntity member);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(Long[] ids);

}

