package io.renren.modules.product.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.product.entity.ProductEntity;
import io.renren.modules.product.service.ProductService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-10 12:22:45
 */
@RestController
@RequestMapping("manager/product")
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = productService.queryTotal(query);
        List<ProductEntity> productList = productService.queryList(query);
        PageUtils pageUtil = new PageUtils(productList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:product:info")
    public R info(@PathVariable("id") Long id) {
        ProductEntity product = productService.selectById(id);
        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @SysLog("新增Product")
    @RequestMapping("/save")
    @RequiresPermissions("manager:product:save")
    public R save(@RequestBody ProductEntity product) {
        productService.save(product);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改Product")
    @RequestMapping("/update")
    @RequiresPermissions("manager:product:update")
    public R update(@RequestBody ProductEntity product) {
        productService.update(product);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除Product")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:product:delete")
    public R delete(@RequestBody Long[] ids) {
        productService.deleteBatchByIds(ids);
        return R.ok();
    }
}
