package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101500695891";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNMdb5elwvX6NxwB7FetFqAXI3KE3fzWrm600tP89NRl9OgiBlqIr2APmGqZYlfw+N95dsBkNN/GuWoKl1jhqZdEoVb5lub1uwNO0FH7NdaL6rAomTdNlw0znf4axO+OAPGbIZORlZMEd42DAYnvw0JOID6ypMRkxoxoPiE7Offr0pjMwSaZxwtUNjinsZ7S3sBs4wRHV0rZq+11Abv9I6llWFcSWJ01+4TOQB2UAr/MOoilnFc/W+YFKiPj0CC7VavCM85SolQORTxb/NiRojDJHGRrPqa/S+jQac9vHa+xuT9a32ZOg5YFOB/rPvuWcWxHI3qiTDmgeXdbykfVYrAgMBAAECggEAJXYPv2lDhB4v00JR57tKTIHNt+ytnK93qYTMmTDQ44PETaQgzFgHZnWjvqLH/3LSwavrNPIEcXCDaxd9Xn8xd31vGnjdkHtjLEEekblzcVkVHXIy5jieaFzigg/yE5HcgKUkp6SKoSs6C0nwmNe1um/+t2NxeYM9Sp9A5n6rrakfXwYWWwAQYPBlijOARpSSnvldyFxTo6HrhH17UU0lmImO0CwIMlo0eAydL4VLws4hYl7rE+UOaRRUAoPGQNoMt8w+GPG0TmnLaHBmxv9SbHrWlguRwnE+opNweY36tqhHMDYOFGXAusovetWajHRRolwFS9Zp0Q+iJGepFFQzIQKBgQDspsm7pI+CuxOjxTNXpnTX6+WANys19vZTVNHaEZiFfHiukXnWEC3yr+5zQSOqJHNm77JHOXXSyXdHicLTdlNKt5+46JEBo13ohQ8YlIQM/SXbE+c7c/8Ba0jNRtHUKNRZ0UBPF67/A9vQqcSbMYh6XumL695ojqiy1fAVuarQvwKBgQCYvRoGsjBimh4kUi3CqYUJ63WgKOMgg9FSRgYrrZHZpPtUAjwXBbaFjp/32OCHPwsJrZdjexbHLDpMEdSfAn/bn0TYf1J9qYxjAsau4y8yT7IFEWQ+XcPMk9bvlxDafd7w++6yZoQhnms2n9okfam9GDxPiWZ6FGnVjOfcVOzplQKBgEo4PQi2obO0jIJOdL3GnNqjR0UhCFJSdArKjU5BOn6S/R5yza1PQbndZ4GKKm9QI0ljX5xq7wL+4IP1Nr8vWwwR7uwFNIU5AVjNcmCZI5kXQ+ojod/Jj26YgSqwTVqmu4K31912ZhHg2e3lcJ3juRP39HNHtGdCZrI/2p5VrrbtAoGAPT6+e+bhnfgclAXb0Y22Cgc/cExU3sncmnf3q3X64OHV4ZbPz49748wP+wsc5T5zR5sy9DH8LTtf2PbhYi1XZZJUwdc0Fa1x3JXVY3U3jJ4GTR/vKU7h31t8uS/DE0mbptfum9ydqPlkFlA84Zr+ABQCwwXecJ9U3MuWgXQQV40CgYAaI8HEmTyQG4Fyv3sSCTh7840WJV3aHonrDt96zSTuINp8q2/6Hg6DRTz7gzVoCLnCX2MhaOxcpmVDxJbW8dqomMZMmmCEwtaZDpR2uTGnQNev9FGF28Aa5foeewbG8zZGHm5bBXPhBlmKM1F8LZc9kQOyGavrGDybe07PIZz+sg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApjiVFiErB8G8I3jsBm3H1Ixk1JpLFF7Pw+dz5MucjYuAAa+86R/Ho9GqxpAR5hiD1KtQlvE3GIy8jMZ83YUNYMMfopD43VIKSYJcHuxbXq6t1QtwOgg2DquE5vFjueI2bysHzGjXrWfJYlvqKGN5/aNaEhyXvdr7n7vhRQB0Pl417PPED9gnCs/byiLS8eyEWe1YD+Q+uziIzEMW5qtI8wFZOXt+8ecpRH3fUHFJnO+beYJ4NbLOf3DaWECbOp0QST2BMYWuGaEcaBYEJ3t1V01EzmN9M2C/sYW9iaXtJfORhKvDy3b61xZgVNiXXdunhsoXmAjOhLY/LcW2uDOKZwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

