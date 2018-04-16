package io.renren.modules.transfer.service.impl;

import io.renren.common.eth.Consts;
import io.renren.common.eth.EthUtils;
import io.renren.modules.airdrop.dao.WybbAirdropDao;
import io.renren.modules.airdrop.entity.WybbAirdropEntity;
import io.renren.modules.transfer.service.TransferService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@Service("transferService")
public class TransferServiceImpl implements TransferService {
    @Autowired
    private WybbAirdropDao wybbAirdropDao;


//    public  void test1(){
//        int transfer = tempWalletService.transfer(address, "99Weiyun", product.getTokenAddress(), product.getPrice());
//    }



    @Override
    public String transfer(String fromAddress, String passWord,String toAddress, BigDecimal value,String walletDirectory,int transferType) {
        try {
            if(StringUtils.isBlank(fromAddress)||StringUtils.isBlank(passWord)||StringUtils.isBlank(toAddress)||value==null){
                throw new RuntimeException("参数有误！");
            }
            if(transferType==0){
                return ethTransfer(fromAddress, passWord, toAddress, value, walletDirectory,0);
            }else if(transferType==1){
                return sheTransfer(fromAddress, passWord, toAddress, value, walletDirectory,0);
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            //logger.error("转账失败，错误原因为：{}",e);
            throw new RuntimeException("转账失败，错误原因为："+e.getMessage());
        }

    }


    @Override
    public void transferAsync(String fromAddress, String passWord,String toAddress, BigDecimal value,String walletDirectory,int transferType) {
        try {
            if(StringUtils.isBlank(fromAddress)||StringUtils.isBlank(passWord)||StringUtils.isBlank(toAddress)||value==null){
                throw new RuntimeException("参数有误！");
            }
            if(transferType==0){
                ethTransfer(fromAddress, passWord, toAddress, value, walletDirectory,1);
            }else if(transferType==1){
                sheTransfer(fromAddress, passWord, toAddress, value, walletDirectory,1);
            }else{
                throw new RuntimeException("交易类别占时不支持");
            }
        }catch (Exception e){
            e.printStackTrace();
            //logger.error("转账失败，错误原因为：{}",e);
            throw new RuntimeException("转账失败，错误原因为："+e.getMessage());
        }

    }
    private String sheTransfer(String fromAddress, String passWord,String toAddress, BigDecimal value,String walletPath,int isAsync) {
        try {
            if(StringUtils.isBlank(fromAddress)||StringUtils.isBlank(passWord)||StringUtils.isBlank(toAddress)||value==null){
                throw new RuntimeException("参数有误！");
            }
            BigDecimal weiUnit=new BigDecimal("1000000000000000000");
            BigInteger bigValue = value.multiply(weiUnit).toBigInteger();
            Credentials credentials = WalletUtils.loadCredentials(passWord, walletPath);

            //EthUtils.contactTransferAsync(Consts.TOKEN_ADDR, credentials, toAddress, bigValue);
            //return 1;
            System.out.println("++++++++"+Consts.TOKEN_ADDR);
            String transactionHash=null;
            if(isAsync==0){
                transactionHash = EthUtils.contactTransfer(Consts.TOKEN_ADDR, credentials, toAddress, bigValue);
            }else{
                EthUtils.contactTransferAsync(Consts.TOKEN_ADDR, credentials, toAddress, bigValue);
            }

            System.out.println("-------------"+transactionHash);
            return transactionHash;
        }catch (Exception e){
            e.printStackTrace();
            //logger.error("转账失败，错误原因为：{}",e);
            throw new RuntimeException("she转账失败，错误原因为："+e.getMessage());
        }

    }


    private String ethTransfer(String fromAddress, String passWord,String toAddress, BigDecimal value,String walletDirectory,int isAsync) {
        try {
            if(StringUtils.isBlank(fromAddress)||StringUtils.isBlank(passWord)||StringUtils.isBlank(toAddress)||value==null){
                throw new RuntimeException("参数有误！");
            }
            BigDecimal weiUnit=new BigDecimal("1000000000000000000");
            BigInteger bigValue = value.multiply(weiUnit).toBigInteger();
            Credentials credentials = WalletUtils.loadCredentials(passWord, walletDirectory);

            String transactionHash=null;
            if(isAsync==0){
                transactionHash = EthUtils.ethTransfer(credentials, toAddress, value);
            }else{
                EthUtils.ethTransferAsync(credentials, toAddress, value);
            }
            return transactionHash;
        }catch (Exception e){
            e.printStackTrace();
            //logger.error("转账失败，错误原因为：{}",e);
            throw new RuntimeException("she转账失败，错误原因为："+e.getMessage());
        }

    }
}

