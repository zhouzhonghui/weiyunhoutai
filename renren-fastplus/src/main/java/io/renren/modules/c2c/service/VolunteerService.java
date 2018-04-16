package io.renren.modules.c2c.service;

import io.renren.modules.c2c.entity.VolunteerEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 15:05:52
 */
public interface VolunteerService {

    /**
     * 查询总数
     *
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    List<VolunteerEntity> queryList(Map<String, Object> map);

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    VolunteerEntity selectById(Long id);

    /**
     * 插入一条记录
     *
     * @param volunteer
     */
    void save(VolunteerEntity volunteer);

    String uploadFile(MultipartFile file) throws IOException;

    /**
     * 更新一条记录
     *
     * @param volunteer
     */
    void update(VolunteerEntity volunteer);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatchByIds(Long[] ids);

    /**
     * 更新在线状态
     *
     * @param id
     * @param onLine
     */
    void modifyOnLine(Long id, Integer onLine);
}

