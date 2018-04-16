package io.renren.common.eth;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author littleredhat
 */
public class EthUtils {

    /**
     * 创建账户
     * @throws Exception
     */
    public static String createAccount(String pwd) throws Exception {
        String fileName = WalletUtils.generateNewWalletFile(pwd, new File(Consts.DIRECTORY), true);
        return fileName;
    }

    /**
     * 获取用户凭证
     * @param pwd
     * @param filepath
     * @return
     * @throws Exception
     */
    public static Credentials getCredentials(String pwd, String filepath) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials(pwd, filepath);
        return credentials;
    }


    //合约转账
    public static void  contactTransferAsync(String erc20contarct, Credentials credentials,String to,BigInteger value){
        Web3j web3j = Web3JClient.getClient();
        Consts.getReloadP();
        SheToken contract = new SheToken(erc20contarct,web3j,credentials,Consts.GAS_PRICE,Consts.GAS_LIMIT);
        //TransactionReceipt tr = contract.transfer(to,value).send();
        CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = contract.transfer(to, value).sendAsync();

    }

    //合约转账
    public static String contactTransfer(String erc20contarct, Credentials credentials,String to,BigInteger value) throws Exception{
        Web3j web3j = Web3JClient.getClient();
        Consts.getReloadP();
        SheToken contract = new SheToken(erc20contarct,web3j,credentials,Consts.GAS_PRICE,Consts.GAS_LIMIT);
        TransactionReceipt tr = contract.transfer(to,value).send();
        if(tr.getBlockNumber()!=null&&tr.getTransactionHash()!=null&&tr.getBlockHash()!=null){
            return tr.getTransactionHash();
        }else{
            return null;
        }

    }

    //根据区块数获取区块
    public static EthBlock ethGetBlockByNumber(Long blockNumber) throws Exception{
        Web3j web3j = Web3JClient.getClient();
        EthBlock send = web3j.ethGetBlockByNumber(new DefaultBlockParameterNumber(blockNumber), false).send();

        return send;
    }

    /**
     * eth同步转账
     * @param toAddress
     * @param value
     * @throws Exception
     */
    public static String ethTransfer(Credentials credentials, String toAddress, BigDecimal value) throws Exception {
    	Web3j web3 = Web3JClient.getClient();
        Consts.getReloadP();
        TransactionReceipt tr = Transfer.sendFunds(web3, credentials, toAddress, value, Convert.Unit.ETHER).send();
        if(tr.getBlockNumber()!=null&&tr.getTransactionHash()!=null&&tr.getBlockHash()!=null){
            return tr.getTransactionHash();
        }else{
            return null;
        }
    }

    /**
     * eth异步转账
     * @param toAddress
     * @param value
     * @throws Exception
     */
    public static void ethTransferAsync(Credentials credentials, String toAddress, BigDecimal value) throws Exception {
        Web3j web3 = Web3JClient.getClient();
        Consts.getReloadP();
        CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = Transfer.sendFunds(web3, credentials, toAddress, value, Convert.Unit.ETHER).sendAsync();
    }

    public static void main(String[] args){
        try {
//            Credentials c = EthUtils.getCredentials("123456","F:/store/UTC--2018-01-11T06-30-42.760536343Z--13b3620e947e5a1f69779a10979b4434c374b30d.json");
//            EthUtils.contactTransfer("0x1ddaa07c79a52814b21d23f3c305de8a8e0e5091",c,"0xded8e424bf8ac027e22e27a9aee0266618294c6a","5000000000");
            //ethGetBlockByNumber(1l);
            EthBlock ethBlock2 = EthUtils.ethGetBlockByNumber(1362l);
            HashMap<String,String> blockMap=new HashMap<>();
            // List<EthBlock.TransactionResult> transactions = ethBlock2.getResult().getTransactions();
            for (int i = 0; i <=1 ; i++) {
                //根据区块数查询交易区块
                EthBlock ethBlock = EthUtils.ethGetBlockByNumber(new Long(i));
                List<EthBlock.TransactionResult> transactions = ethBlock.getResult().getTransactions();
                StringBuffer sb=new StringBuffer();
                for (EthBlock.TransactionResult transactionResult: transactions) {
                    sb.append(transactionResult.get().toString()+",");
                }
                blockMap.put(i+"",sb.toString());
                //blockMap.put("",ethBlock.)

            }
            System.out.println(blockMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //合约转账确认

    //转账

    //转账确认



}
