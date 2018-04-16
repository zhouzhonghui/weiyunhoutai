package io.renren.modules.member.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-30 13:40:55
 */
@RestController
@RequestMapping("manager/member")
public class MemberController extends AbstractController {

    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<MemberEntity> jobList = memberService.queryList(query);
        int total = memberService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:member:info")
    public R info(@PathVariable("id") Long id) {
        MemberEntity member = memberService.selectById(id);
        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @SysLog("保存会员")
    @RequestMapping("/save")
    @RequiresPermissions("manager:member:save")
    public R save(@RequestBody MemberEntity member) {
        memberService.save(member);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改会员信息")
    @RequestMapping("/update")
    @RequiresPermissions("manager:member:update")
    public R update(@RequestBody MemberEntity member) {
        memberService.update(member);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @SysLog("删除会员")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:member:delete")
    public R delete(@RequestBody Long[] ids) {
        memberService.deleteBatch(ids);
        return R.ok();
    }
}