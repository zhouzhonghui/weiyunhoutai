package io.renren.modules.policy.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.policy.entity.LifePolicyEntity;
import io.renren.modules.policy.service.LifePolicyService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-30 17:18:27
 */
@RestController
@RequestMapping("manager/policy/lifePolicy")
public class LifePolicyController extends AbstractController {

    @Autowired
    private LifePolicyService lifePolicyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<LifePolicyEntity> policyVOList = lifePolicyService.queryList(query);
        int total = lifePolicyService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(policyVOList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:policy:lifePolicy:info")
    public R info(@PathVariable("id") Long id) {
        LifePolicyEntity lifePolicy = lifePolicyService.selectById(id);
        return R.ok().put("lifePolicy", lifePolicy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @SysLog("保存保单")
    @RequiresPermissions("manager:policy:lifePolicy:save")
    public R save(@RequestBody LifePolicyEntity lifePolicy) {
        lifePolicyService.save(lifePolicy);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改保单信息")
    @RequestMapping("/update")
    @RequiresPermissions("manager:policy:lifePolicy:update")
    public R update(@RequestBody LifePolicyEntity lifePolicy) {
        lifePolicyService.update(lifePolicy);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除保单")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:policy:lifePolicy:delete")
    public R delete(@RequestBody Long[] ids) {
        lifePolicyService.deleteBatch(ids);
        return R.ok();
    }
}
