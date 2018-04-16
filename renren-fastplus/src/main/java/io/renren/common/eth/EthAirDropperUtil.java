package io.renren.common.eth;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.ReadonlyTransactionManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Eth的工具类
 */
public class EthAirDropperUtil {

    public static TransactionReceipt sendAirDropper( List<String> addressDest,List<BigInteger> amountDest)throws Exception{
        Web3j web3j = Web3JClient.getClient();
        Credentials credentials = WalletUtils.loadCredentials("123456", Consts.PATH);
        AirdropperToken airdropperToken=new AirdropperToken(Consts.AIRTOKEN_ADDR,web3j,credentials,Consts.GAS_PRICE,Consts.GAS_LIMIT);
        CompletableFuture<TransactionReceipt> send = airdropperToken.multisend(Consts.SHETOKEN_ADDR, addressDest, amountDest).sendAsync();
        return send.get();
    }

    /**
     * 对金额进行格式化
     * @param amount
     * @return
     */
    public static BigInteger getAmount(String amount){
        DecimalFormat decimalFormat=new DecimalFormat();
        decimalFormat.applyPattern("#.000000000000000000");
        double d=Double.parseDouble(amount);
        String str=decimalFormat.format(d).replace(".","");
        return new BigInteger(str);
    }

    /**
     * 根据she地址查询金额
     * @param address
     * @return
     */
    public static BigInteger queryAmountBySheAddress(String address) throws Exception{
        Web3j web3j = Web3JClient.getClient();
        ReadonlyTransactionManager rtm = new ReadonlyTransactionManager(web3j,address);
        SheToken contract = new SheToken(Consts.TOKEN_ADDR,web3j,rtm,Consts.GAS_PRICE,Consts.GAS_LIMIT);
        BigInteger bigInteger=contract.balanceOf(address).send();
        return bigInteger;
    }

    /**
     * 根据ETH的地址查询ETH剩余的余额
     * @param address
     * @return
     */
    public static BigInteger queryAmountByEthAddress(String address) throws Exception{
        Web3j web3j = Web3JClient.getClient();
        EthGetBalance ethGetBalance1 = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).send();
        return ethGetBalance1.getBalance();
    }

    public static void main(String[] args) throws Exception{
//        List<String> dest=new ArrayList<>();
//        dest.add("0x69e646f7c51a1f55157dc6a2f39162879d91eb71");
//        dest.add("0xb7b7e64cb69bc0a37f3201a08467fa3d49b12650");
//        List<BigInteger> amount=new ArrayList<>();
//        amount.add(new BigInteger("3000000000000000000"));
//        amount.add(new BigInteger("3000000000000000000"));
//        TransactionReceipt transactionReceipt=EthAirDropperUtil.sendAirDropper(dest,amount);
//        System.out.println(transactionReceipt.getBlockNumber()+"-----"+transactionReceipt.getTransactionHash());
//        String str="0x-1234";
//       int i= str.indexOf("0x");
//        System.out.println(i);
        BigInteger bigInteger=EthAirDropperUtil.queryAmountByEthAddress("0x13b3620e947e5a1f69779a10979b4434c374b30d");
        System.out.println(bigInteger);
        BigInteger bigInteger1=EthAirDropperUtil.queryAmountBySheAddress("0x806a521ec6e8bf59623a5e2f00bbf639b3e4cfd2");
        System.out.println(bigInteger1.divide(new BigInteger("1000000000000000000"))+"@@@@@@@@@@@@");



    }
}
