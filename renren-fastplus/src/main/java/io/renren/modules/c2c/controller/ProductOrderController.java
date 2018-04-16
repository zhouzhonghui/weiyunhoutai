package io.renren.modules.c2c.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.c2c.entity.ProductOrderEntity;
import io.renren.modules.c2c.service.ProductOrderService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-09 20:18:01
 */
@RestController
@RequestMapping("manager/c2c/productOrder")
public class ProductOrderController extends AbstractController {

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = productOrderService.queryTotal(query);
        List<ProductOrderEntity> productOrderList = productOrderService.queryList(query);
        PageUtils pageUtil = new PageUtils(productOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:c2c:productOrder:info")
    public R info(@PathVariable("id") Long id) {
        ProductOrderEntity productOrder = productOrderService.selectById(id);
        return R.ok().put("productOrder", productOrder);
    }

    /**
     * 保存
     */
    @SysLog("新增ProductOrder")
    @RequestMapping("/save")
    @RequiresPermissions("manager:c2c:productOrder:save")
    public R save(@RequestBody ProductOrderEntity productOrder) {
        productOrderService.save(productOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改ProductOrder")
    @RequestMapping("/update")
    @RequiresPermissions("manager:c2c:productOrder:update")
    public R update(@RequestBody ProductOrderEntity productOrder) {
        productOrderService.update(productOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除ProductOrder")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:c2c:productOrder:delete")
    public R delete(@RequestBody Long[] ids) {
        productOrderService.deleteBatchByIds(ids);
        return R.ok();
    }
}
