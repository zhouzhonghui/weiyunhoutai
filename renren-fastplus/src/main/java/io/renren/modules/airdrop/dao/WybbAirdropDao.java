package io.renren.modules.airdrop.dao;

import io.renren.modules.airdrop.entity.WybbAirdropEntity;
import io.renren.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;


@Mapper
public interface WybbAirdropDao extends BaseDao<WybbAirdropEntity> {
    List<WybbAirdropEntity> queryListByCondition(Map<String, Object> map);
}
