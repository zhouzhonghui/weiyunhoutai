package io.renren.modules.c2c.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.c2c.entity.VolunteerEntity;
import io.renren.modules.c2c.service.VolunteerService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GuanYang
 * @email
 * @date 2018-04-04 15:05:52
 */
@RestController
@RequestMapping("manager/c2c/volunteer")
public class VolunteerController extends AbstractController {

    @Autowired
    private VolunteerService volunteerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = volunteerService.queryTotal(query);
        List<VolunteerEntity> volunteerList = volunteerService.queryList(query);
        PageUtils pageUtil = new PageUtils(volunteerList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("manager:c2c:volunteer:info")
    public R info(@PathVariable("id") Long id) {
        VolunteerEntity volunteer = volunteerService.selectById(id);
        return R.ok().put("volunteer", volunteer);
    }

    /**
     * 保存
     */
    @SysLog("新增Volunteer")
    @RequestMapping("/save")
    @RequiresPermissions("manager:c2c:volunteer:save")
    public R save(@RequestBody VolunteerEntity volunteer) {
        volunteer.setManagerId(getUserId().longValue());
        volunteerService.save(volunteer);
        return R.ok();
    }

    /**
     * 上传
     */
    @RequestMapping("/uploadFile")
    @RequiresPermissions(value = {"manager:c2c:volunteer:save"}, logical = Logical.OR)
    public R uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        String imgPath = volunteerService.uploadFile(file) ;
        return R.ok().put("imgPath", imgPath);
    }

    /**
     * 修改
     */
    @SysLog("修改Volunteer")
    @RequestMapping("/update")
    @RequiresPermissions("manager:c2c:volunteer:update")
    public R update(@RequestBody VolunteerEntity volunteer) {
        volunteerService.update(volunteer);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除Volunteer")
    @RequestMapping("/delete")
    @RequiresPermissions("manager:c2c:volunteer:delete")
    public R delete(@RequestBody Long[] ids) {
        volunteerService.deleteBatchByIds(ids);
        return R.ok();
    }

    /**
     * 修改义工在线状态
     *
     * @param id
     * @param onLine
     * @return
     */
    @SysLog("修改义工在线状态")
    @RequestMapping("/modifyOnLine")
    @RequiresPermissions("manager:c2c:volunteer:modifyOnLine")
    public R modifyOnLine(Long id, Integer onLine) {
        volunteerService.modifyOnLine(id, onLine);
        return R.ok();
    }
}
