package io.renren.modules.c2c.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.eth.Consts;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.c2c.entity.C2cOrderEntity;
import io.renren.modules.c2c.service.C2cOrderService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.transfer.service.TransferService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 16:49:51
 */
@RestController
@RequestMapping("manager/c2c/order")
public class C2cOrderController extends AbstractController {
    public static String CONFIRM_PAYMENT_KEY="confirmPayment:c2c:id:";
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private TransferService transferService;
    @Autowired
    private C2cOrderService c2cOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = c2cOrderService.queryTotal(query);
        List<C2cOrderEntity> c2cOrderList = c2cOrderService.queryList(query);
        PageUtils pageUtil = new PageUtils(c2cOrderList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:c2c:order:info")
    public R info(@PathVariable("id") Long id) {
        C2cOrderEntity c2cOrder = c2cOrderService.selectById(id);
        return R.ok().put("c2cOrder", c2cOrder);
    }

    /**
     * 保存
     */
    @SysLog("新增C2cOrder")
    @RequestMapping("/save")
    @RequiresPermissions("manager:c2c:order:save")
    public R save(@RequestBody C2cOrderEntity c2cOrder) {
        c2cOrderService.save(c2cOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改C2cOrder")
    @RequestMapping("/update")
    @RequiresPermissions("manager:c2c:order:update")
    public R update(@RequestBody C2cOrderEntity c2cOrder) {
        c2cOrderService.update(c2cOrder);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除C2cOrder")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:c2c:order:delete")
    public R delete(@RequestBody Long[] ids) {
        c2cOrderService.deleteBatchByIds(ids);
        return R.ok();
    }

    /**
     * 买家未付款
     *
     * @param id
     * @return
     */
    @SysLog("买家未付款, 修改c2c订单状态")
    @RequestMapping("/unPaid")
    @RequiresPermissions("manager:c2c:order:unPaid")
    public R unPaid(Long id, Integer state) {
        try {
            c2cOrderService.updateState(id, state);
        } catch (Exception e) {
            logger.info("支付确认:{}", e);

            return R.error(2, "操作异常");
        }

        return R.ok();
    }

    /**
     * 支付确认
     *
     * @param id
     * @return
     */
    @SysLog("支付确认, 修改c2c订单状态")
    @RequestMapping("/confirmPayment")
    @RequiresPermissions("manager:c2c:order:confirmPayment")
    public R confirmPayment(Long id) {
        Boolean ok=false;
        try {
            ok = redisTemplate.opsForValue().setIfAbsent(CONFIRM_PAYMENT_KEY + id, "ok");
            if(ok){
                redisTemplate.expire(CONFIRM_PAYMENT_KEY + id, 300, TimeUnit.SECONDS);
                c2cOrderService.confirmPayment(id);
            }else{
                return R.error(2, "此订单正在处理中！！！");
            }

        } catch (Exception e) {
            logger.info("支付确认:{}", e);
            try {
                if(ok){
                    redisTemplate.delete(CONFIRM_PAYMENT_KEY + id);
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }

            return R.error(2, e.getMessage());
        }
        return R.ok();
    }

    /**
     * 支付确认
     *
     * @param fromAddress
     * @param passWord
     * @param toAddress
     * @param value
     * @param walletPath
     * @param transferType
     * @return
     */
    @RequestMapping("/testTransfer")
    public R testTransfer(String fromAddress, String passWord, String toAddress, BigDecimal value, String walletPath, int transferType) {
        try {
            System.out.println("--------------测试开始");
            String transfer = transferService.transfer(fromAddress, passWord, toAddress, value, Consts.VOLUNTEER_DIRECTORY + walletPath, transferType);
            System.out.println("---------------" + transfer);
            System.out.println("--------------测试结束");
        } catch (Exception e) {
            logger.info("测试异常:{}", e);

            return R.error(2, e.getMessage());
        }
        return R.ok();
    }

}
