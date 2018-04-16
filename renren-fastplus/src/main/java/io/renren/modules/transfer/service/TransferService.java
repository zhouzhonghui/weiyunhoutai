package io.renren.modules.transfer.service;


import io.renren.modules.airdrop.entity.WybbAirdropEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-03-27 09:46:31
 */
public interface TransferService {
    /**
     * 自动生成转账
     * @param fromAddress 从哪个地址
     * @param toAddress 转到某个地址
     * @param value 转币的数量
     * @param walletPath 钱包路径
     * @param transferType 交易类别0为eth，1为she
     * @return null失败hash存在为成功
     */
    public String transfer(String fromAddress,String passWord, String toAddress, BigDecimal value,String walletPath,int transferType);

    /**
     * 异步自动生成转账
     * @param fromAddress 从哪个地址
     * @param toAddress 转到某个地址
     * @param value 转币的数量
     * @param walletPath 钱包路径
     * @param transferType 交易类别0为eth，1为she
     * @return null失败hash存在为成功
     */
    public void transferAsync(String fromAddress,String passWord, String toAddress, BigDecimal value,String walletPath,int transferType);

}

