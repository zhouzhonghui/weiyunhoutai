package io.renren.modules.wybbbatchtransfer.controller;

import java.util.*;

import io.renren.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.wybbbatchtransfer.entity.WybbBatchtransferEntity;
import io.renren.modules.wybbbatchtransfer.service.WybbBatchtransferService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 *
 * @author shunshine
 * @email shunshine@99weiyun.com
 * @date 2018-04-10 09:46:58
 */
@RestController
@RequestMapping("wybbbatchtransfer/wybbbatchtransfer")
public class WybbBatchtransferController {
    @Autowired
    private WybbBatchtransferService wybbBatchtransferService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wybbbatchtransfer:wybbbatchtransfer:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        String createstartDate= (String) params.get("createstartDate");//创建任务开始时间
        String createendDate=(String) params.get("createendDate");//创建任务结束时间
        String status=(String) params.get("status");//转账状态
        String flag=(String) params.get("flag");//转账类型
        if(!"".equals(createstartDate)&&null!=createstartDate){
            query.put("transferstartDate",createstartDate.replace("-","")+"000000");
        }
        if(!"".equals(createendDate)&&null!=createendDate){
            query.put("transferendDate",createendDate.replace("-","")+"000000");
        }
        if(!"".equals(status)&&null!=status){
            query.put("status",status);
        }
        if(!"".equals(flag)&&null!=flag){
            query.put("flag",flag);
        }
        List<WybbBatchtransferEntity> jobList = wybbBatchtransferService.queryList(query);
        int total = wybbBatchtransferService.queryTotal(query);
        PageUtils page = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", page);
    }


    //导入
    @RequestMapping("/batchImport")
    @Transactional(timeout = 50000,rollbackFor = Exception.class)
    public R batchImportUserKnowledge(@RequestParam(value="file") MultipartFile file,
                                      HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<String[]> list= ExcelDataUtil.getExcelData(file);
        List<WybbBatchtransferEntity> wybbBatchtransferEntityArrayList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String [] line=list.get(i);
            StringBuilder sb=new StringBuilder();
            StringBuilder sb1=new StringBuilder();
            sb.append("Excel中的第"+(i+1)+"行");
            WybbBatchtransferEntity wybbScalpEntity=new WybbBatchtransferEntity();
            if("".equals(line[0])||null==line[0]){
                sb1.append("付款地址数据格式非法\t");
            }
            wybbScalpEntity.setPayaddress(line[0]);
            if("".equals(line[1])||null==line[1]){
                sb1.append("收款地址数据格式非法\t");
            }
            wybbScalpEntity.setGatheraddress(line[1]);
            if("".equals(line[2])||null==line[2]){
                sb1.append("转账金额数据格式非法\t");
            }
            wybbScalpEntity.setAmount(line[2]);
            if("".equals(line[3])||null==line[3]){
                sb1.append("转账类型数据格式非法\t");
            }
            wybbScalpEntity.setFlag(line[3]);
            sb.append(sb1);
            wybbScalpEntity.setCreatedate(DateUtil.format(new Date(),"yyyyMMddHHmmss"));//转账时间
            wybbBatchtransferEntityArrayList.add(wybbScalpEntity);
            if(sb1.length()>0){
                wybbBatchtransferEntityArrayList.clear();
                return R.error(sb.toString());
            }
        }
        if(wybbBatchtransferEntityArrayList.size()>0){
            wybbBatchtransferService.insert(wybbBatchtransferEntityArrayList);
        }
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wybbbatchtransfer:wybbbatchtransfer:save")
    public R save(@RequestBody List<WybbBatchtransferEntity> wybbBatchtransfer){
			wybbBatchtransferService.insert(wybbBatchtransfer);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wybbbatchtransfer:wybbbatchtransfer:update")
    public R update(@RequestBody WybbBatchtransferEntity wybbBatchtransfer){
			wybbBatchtransferService.updateById(wybbBatchtransfer);

        return R.ok();
    }



}
