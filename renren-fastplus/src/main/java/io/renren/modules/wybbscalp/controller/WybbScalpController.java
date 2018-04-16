package io.renren.modules.wybbscalp.controller;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.renren.common.utils.*;
import org.apache.activemq.ScheduledMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.wybbscalp.entity.WybbScalpEntity;
import io.renren.modules.wybbscalp.service.WybbScalpService;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.*;
import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 *
 * @author shunshine
 * @email shunshine@99weiyun.com
 * @date 2018-04-03 10:29:11
 */
@RestController
@RequestMapping("wybbscalp/wybbscalp")
public class WybbScalpController {
    @Autowired
    private WybbScalpService wybbScalpService;



    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wybbscalp:wybbscalp:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        String jobstartDate= (String) params.get("jobstartDate");//定时任务开始时间
        String jobendDate=(String) params.get("jobendDate");//定时任务结束时间
        String importstartDate= (String) params.get("importstartDate");//导入开始时间
        String importendDate=(String) params.get("importendDate");//导入结束时间
        String status=(String) params.get("status");//状态
        if(!"".equals(jobstartDate)&&null!=jobstartDate){
            query.put("jobstartDate",jobstartDate.replace("-","")+"000000");
        }
        if(!"".equals(jobendDate)&&null!=jobendDate){
            query.put("jobendDate",jobendDate.replace("-","")+"000000");
        }
        if(!"".equals(importstartDate)&&null!=importstartDate){
            query.put("importstartDate",importstartDate.replace("-","")+"000000");
        }
        if(!"".equals(importendDate)&&null!=importendDate){
            query.put("importendDate",importendDate.replace("-","")+"000000");
        }
        if(!"".equals(status)&&null!=status){
            query.put("status",status);
        }
        List<WybbScalpEntity> jobList = wybbScalpService.queryList(query);
        int total = wybbScalpService.queryTotal(query);
        PageUtils page = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", page);
    }

    //导入
    @RequestMapping("/batchImport")
    @Transactional(timeout = 50000,rollbackFor = Exception.class)
    public R batchImportUserKnowledge(@RequestParam(value="file") MultipartFile file,
                                      HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<String[]> list= ExcelDataUtil.getExcelData(file);
        List<WybbScalpEntity> wybbScalpEntityList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String [] line=list.get(i);
            StringBuilder sb=new StringBuilder();
            StringBuilder sb1=new StringBuilder();
            sb.append("Excel中的第"+(i+1)+"行");
            WybbScalpEntity wybbScalpEntity=new WybbScalpEntity();
            if("".equals(line[0])||null==line[0]){
                sb1.append("手机号数据格式非法\t");
            }
            wybbScalpEntity.setMobile(line[0]);
            if("".equals(line[1])||null==line[1]){
                sb1.append("证件号码数据格式非法\t");
            }
            wybbScalpEntity.setIdno(line[1]);
            if("".equals(line[2])||null==line[2]){
                sb1.append("用户姓名数据格式非法\t");
            }
            wybbScalpEntity.setUsername(line[2]);
            if("".equals(line[3])||null==line[3]){
                sb1.append("用户地址数据格式非法\t");
            }
            wybbScalpEntity.setUseraddress(line[3]);
            if("".equals(line[4])||null==line[4]){
                sb1.append("紧急联系人姓名数据格式非法\t");
            }
            wybbScalpEntity.setUcname(line[4]);
            if("".equals(line[5])||null==line[5]){
                sb1.append("紧急联系人电话数据格式非法\t");
            }
            wybbScalpEntity.setUcmobile(line[5]);
            if("".equals(line[6])||null==line[6]){
                sb.append("紧急联系人邮箱数据格式非法\t");
            }
            wybbScalpEntity.setUcemail(line[6]);
            if("".equals(line[7])||null==line[7]){
                sb1.append("定时任务执行时间数据格式非法\t");
            }
            sb.append(sb1);
            wybbScalpEntity.setJobtime(line[7]);
            wybbScalpEntity.setCreatetime(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
            wybbScalpEntity.setStatus("0");//最开始的状态为0
            wybbScalpEntity.setRemark3(UUID.randomUUID().toString().replace("-",""));
            wybbScalpEntity.setRemark2(JSONObject.toJSONString(wybbScalpEntity));//json字符串
            wybbScalpEntityList.add(wybbScalpEntity);
            if(sb1.length()>0){
                wybbScalpEntityList.clear();
                return R.error(sb.toString());
            }
        }
        if(wybbScalpEntityList.size()>0){
            wybbScalpService.insert(wybbScalpEntityList);
        }
        return R.ok();
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wybbscalp:wybbscalp:save")
    public R save(@RequestBody List<WybbScalpEntity> wybbScalp){
			wybbScalpService.insert(wybbScalp);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wybbscalp:wybbscalp:update")
    public R update(@RequestBody WybbScalpEntity wybbScalp){
			wybbScalpService.updateById(wybbScalp);

        return R.ok();
    }

    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString().replace("-",""));
        String str=DateUtil.format(new Date(),"yyyyMMddHHmmss");
        Date date1=DateUtil.parse(str,"yyyyMMddHHmmss");
        System.out.println(date1);
        String str1="0x7d67fb5a7835074920de0c78d608ee00bbb71436";
        System.out.println(str1.startsWith("0x"));
        String str3="2018-04-08";
        System.out.println(str3.replace("-",""));
    }
}
