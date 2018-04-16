package io.renren.modules.order.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.order.entity.OrderEntity;
import io.renren.modules.order.service.OrderService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-03-31 13:53:24
 */
@RestController
@RequestMapping("manager/order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = orderService.queryTotal(query);
        List<OrderEntity> jobList = orderService.queryList(query);
        PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);

    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:order:info")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.selectById(id);
        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @SysLog("新增订单")
    @RequestMapping("/save")
    @RequiresPermissions("manager:order:save")
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改订单信息")
    @RequestMapping("/update")
    @RequiresPermissions("manager:order:update")
    public R update(@RequestBody OrderEntity order) {
        orderService.update(order);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除订单")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:order:delete")
    public R delete(@RequestBody Long[] ids) {
        orderService.deleteBatch(ids);
        return R.ok();
    }

}
