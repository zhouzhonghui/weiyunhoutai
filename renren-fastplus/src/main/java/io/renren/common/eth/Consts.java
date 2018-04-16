package io.renren.common.eth;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author littleredhat
 */
public class Consts {

	private static Properties p;

	// 初始化配置
	static {
		p = new Properties();
		InputStream in = Consts.class.getResourceAsStream("/config/config.properties");
		InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
		try {
			p.load(r);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  static void getReloadP(){
		p = new Properties();
		InputStream in = Consts.class.getResourceAsStream("/config/config.properties");
		InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
		try {
			p.load(r);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GAS_PRICE = new BigInteger(p.getProperty("gas_price"));
		GAS_LIMIT = new BigInteger(p.getProperty("gas_limit"));
	}

	// GAS价格
	public static String URL = p.getProperty("url");

	// GAS价格
	public static BigInteger GAS_PRICE =new BigInteger(p.getProperty("gas_price"));
	// GAS上限
	public static BigInteger GAS_LIMIT =new BigInteger(p.getProperty("gas_limit"));
	// ETHER以太币
	//public static BigInteger ETHER = new BigInteger("1000000000000000000");

	// 钱包密码
	public static String PASSWORD = p.getProperty("password");
	// 钱包路径
	public static String PATH = p.getProperty("path");
	// 钱包目录
	public static String DIRECTORY = p.getProperty("directory");
	// 合约地址
	public static String HELLOWORLD_ADDR = p.getProperty("helloworldAddr");

	// 合约地址
	public static String TOKEN_ADDR = p.getProperty("tokenAddr");

	//add by zhouzh begin
	//空投合约地址
	public static String AIRTOKEN_ADDR=p.getProperty("airtokenAddr");

	//空投she合约地址

	public static String SHETOKEN_ADDR=p.getProperty("shetokenAddr");

	public static String ETH_ADDR=p.getProperty("ethAddr");


	//义工钱包路径
	public static String VOLUNTEER_DIRECTORY=p.getProperty("volunteer_directory");
	//批量转账钱包路径
	public static String BATCHDIRECTORY=p.getProperty("batchdirectory");
	// add by zhouzh end


}
