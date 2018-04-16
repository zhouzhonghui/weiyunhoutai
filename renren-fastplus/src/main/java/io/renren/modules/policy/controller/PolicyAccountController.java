package io.renren.modules.policy.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.policy.entity.PolicyAccountEntity;
import io.renren.modules.policy.service.PolicyAccountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 14:41:28
 */
@RestController
@RequestMapping("manager/policy/policyAccount")
public class PolicyAccountController {

    @Autowired
    private PolicyAccountService policyAccountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = policyAccountService.queryTotal(query);
        List<PolicyAccountEntity> jobList = policyAccountService.queryList(query);
        PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:policy:policyAccount:info")
    public R info(@PathVariable("id") Long id) {
        PolicyAccountEntity policyAccount = policyAccountService.selectById(id);
        return R.ok().put("policyAccount", policyAccount);
    }

    /**
     * 保存
     */
    @SysLog("新增")
    @RequestMapping("/save")
    @RequiresPermissions("manager:policy:policyAccount:save")
    public R save(@RequestBody PolicyAccountEntity policyAccount) {
        policyAccountService.save(policyAccount);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @RequestMapping("/update")
    @RequiresPermissions("manager:policy:policyAccount:update")
    public R update(@RequestBody PolicyAccountEntity policyAccount) {
        policyAccountService.update(policyAccount);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:policy:policyAccount:delete")
    public R delete(@RequestBody Long[] ids) {
        policyAccountService.deleteBatch(ids);
        return R.ok();
    }

}
