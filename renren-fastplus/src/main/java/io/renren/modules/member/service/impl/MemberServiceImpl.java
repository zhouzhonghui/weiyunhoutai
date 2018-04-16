package io.renren.modules.member.service.impl;

import io.renren.modules.member.dao.MemberDao;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("wybbMemberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public int queryTotal(Map<String, Object> map) {
        return memberDao.queryTotal(map);
    }

    @Override
    public List<MemberEntity> queryList(Map<String, Object> map) {
        return memberDao.queryList(map);
    }

    @Override
    public MemberEntity selectById(Long id) {
        MemberEntity memberEntity = memberDao.queryObject(id);
        if (memberEntity.getGender() != null && MemberEntity.Gender.findById(memberEntity.getGender()) != null) {
            memberEntity.setGenderDesc(MemberEntity.Gender.findById(memberEntity.getGender()).getDesc());
        }
        if (!StringUtils.isBlank(memberEntity.getFromSource()) && MemberEntity.FromSource.findById(Integer.parseInt(memberEntity.getFromSource())) != null) {
            memberEntity.setFromSource(MemberEntity.FromSource.findById(Integer.parseInt(memberEntity.getFromSource())).getDesc());
        }
        if (memberEntity.getState() != null && MemberEntity.State.findById(memberEntity.getState()) != null) {
            memberEntity.setStateDesc(MemberEntity.State.findById(memberEntity.getState()).getDesc());
        }
        return memberEntity;
    }

    @Override
    @Transactional
    public void save(MemberEntity user) {
        memberDao.save(user);
    }

    @Override
    @Transactional
    public void update(MemberEntity user) {
        memberDao.update(user);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        memberDao.deleteBatch(ids);
    }
}
