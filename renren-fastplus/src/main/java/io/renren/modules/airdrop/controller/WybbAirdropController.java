package io.renren.modules.airdrop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

import io.renren.common.eth.AirdropperToken;
import io.renren.common.eth.Consts;
import io.renren.common.eth.EthAirDropperUtil;
import io.renren.common.eth.Web3JClient;
import io.renren.common.utils.*;
import io.renren.modules.airdrop.entity.SheEntity;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.airdrop.entity.WybbAirdropEntity;
import io.renren.modules.airdrop.service.WybbAirdropService;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-27 09:46:31
 */
@RestController
@RequestMapping("sys/airdrop/wybbairdrop")
public class WybbAirdropController {
    @Autowired
    private WybbAirdropService wybbAirdropService;

    @RequestMapping("/queryShe")
    public R queryShe(){
        SheEntity sheEntity=new SheEntity();
        sheEntity.setSheAddress(Consts.SHETOKEN_ADDR);
        try {
            sheEntity.setSheAmount(EthAirDropperUtil.queryAmountBySheAddress(Consts.SHETOKEN_ADDR).divide(new BigInteger("100000000000000000")).toString());
            sheEntity.setEthAmount(EthAirDropperUtil.queryAmountBySheAddress(Consts.ETH_ADDR).divide(new BigInteger("100000000000000000")).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheEntity.setEthAddress(Consts.ETH_ADDR);
        List list=new ArrayList();
        list.add(sheEntity);
        PageUtils pageUtil = new PageUtils(list, 1, 0,0);
        return R.ok().put("page",pageUtil);

    }


    @RequestMapping("/queryByDateAndName")
    public R queryByConditionList(@RequestParam Map<String, Object> params){
        if(params.get("date").toString()!=null&&!"".equals(params.get("date"))){
            params.put("date",params.get("date"));
        }
        if(params.get("filename").toString()!=null&&!"".equals(params.get("filename"))){
            params.put("filename",params.get("filename"));
        }
        Query query = new Query(params);
        List<WybbAirdropEntity> jobList = wybbAirdropService.queryByDateAndName(params);
        int total = wybbAirdropService.queryTotal(query);
        queryShe();
        PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
         * 列表
         */
        @RequestMapping("/list")
        public R list(@RequestParam Map<String, Object> params){
            //查询列表数据
            Query query = new Query(params);
            List<WybbAirdropEntity> jobList = wybbAirdropService.queryList(query);
            int total = wybbAirdropService.queryTotal(query);
            queryShe();
            PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
            return R.ok().put("page", pageUtil);
    }

    //导入
    @RequestMapping("/batchImport")
    public R batchImportUserKnowledge(@RequestParam(value="file") MultipartFile file,
                                           HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        List<String[]> list=ExcelDataUtil.getExcelData(file);
        List<String> addressDest=new ArrayList<>();//目的地址集合
        List<BigInteger> amountDest=new ArrayList<>();//转账金额
        List<String> amountDest1=new ArrayList<>();//转账金额
        List<String> name=new ArrayList<>();//姓名集合
        for (int i=0;i<list.size();i++){
            String [] str=list.get(i);
            name.add(str[0]);
            if(str[1].indexOf("-")<0&&str[2].indexOf("0x")<0){
                return R.error("请检查excel中的数据是否合法！");
            }else {
                amountDest1.add(str[1]);
                amountDest.add(EthAirDropperUtil.getAmount(str[1]));
                addressDest.add(str[2]);
            }
        }
        //发送空投交易
        TransactionReceipt transactionReceipt=EthAirDropperUtil.sendAirDropper(addressDest,amountDest);
        for (int i=0;i<addressDest.size();i++){
            WybbAirdropEntity wybbAirdropEntity=new WybbAirdropEntity();
            wybbAirdropEntity.setAddress(addressDest.get(i));
            wybbAirdropEntity.setAmount(amountDest1.get(i).toString());
            wybbAirdropEntity.setBlocknum(transactionReceipt.getBlockNumber().toString());
            wybbAirdropEntity.setTranhash(transactionReceipt.getTransactionHash());
            wybbAirdropEntity.setCreatedate(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            wybbAirdropEntity.setRemark1(name.get(i).toString());
            wybbAirdropEntity.setRemark2(file.getOriginalFilename());
            wybbAirdropService.insert(wybbAirdropEntity);
        }
        return R.ok();
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
			WybbAirdropEntity wybbAirdrop = wybbAirdropService.selectById(id);

        return R.ok().put("wybbAirdrop", wybbAirdrop);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WybbAirdropEntity wybbAirdrop){
			wybbAirdropService.insert(wybbAirdrop);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WybbAirdropEntity wybbAirdrop){
			wybbAirdropService.updateById(wybbAirdrop);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody WybbAirdropEntity wybbAirdrop){
			wybbAirdropService.deleteBatchIds(wybbAirdrop);

        return R.ok();
    }

    public static void main(String[] args) throws Exception{
        List<String> addressList=new ArrayList<>();
        List<BigInteger> valueList=new ArrayList<>();
        Web3j web3j = Web3JClient.getClient();
//        Credentials credentials = WalletUtils.loadCredentials("123456", Consts.PATH);
//        AirdropperToken airdropperToken=new AirdropperToken(Consts.TOKEN_ADDR,web3j,credentials,Consts.GAS_PRICE,Consts.GAS_LIMIT);
//        TransactionReceipt send = airdropperToken.multisend(Consts.TOKEN_ADDR, addressList, valueList).send();
        System.out.println(Consts.PATH);
    }
}
