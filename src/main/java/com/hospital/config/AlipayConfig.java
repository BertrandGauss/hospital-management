package com.hospital.config;

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
    public static String app_id = "2021000118685787";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDDNS9Mmoia/SuuTk2UORzaL8QpX5FpgwfX9nJpSMQEsaFsWh+ey6ayvMhSRjKPHII6c9QsXiyb7p87HJ6UOLflVU59oDKcYsmb4dCUNfdvN4G8yGwgsy1oorvBm2kGC4Cjlq0rpRKH26KEnUr6S1rtdNfnKOWMS0WxHtH2PYduB55GmZnKVhpZ3tsOgsuj9z2SERQBC3nZ6wzuSaL4lpp4Dhpg2M72n0oaKOB/Dr/XyH50nhJfhl4QpWWsUgf3xnGGDn6T3KvbTskrPtSCAg7DHb/CIfF3qiPK7IXxcVuRA3xA8/Ke2nP+27o2yT8hleWyNenw3ZOjJlA6ux06Ae4NAgMBAAECggEBAKZDyoGorem08VuJfjJdYuHEBYRnzebcn/WmB1T9kVeeJCuxp3UNaHKcsNLCz5VtZs56kaEopT47RIyIl9kPZjKDMlN1+yWQTr/i2lEilhm4YVx2KQLBGlAhK2KACDgJ3At57w1ZkCr/O7FQd+gv/sZ52tsczkdsZXOzGIpvZcakYVXIUvCRWHFy6Z4WDZSo6EWRYtuDs6mzwCaxQz3OX8nqNdSpMOzMbVK1iXvEE3AR+G6lo3c2Uco0iXm+ZbXnUcVNgg2lzx8g6G+mkmWTX9kS279AkePiqot3J3KP9zAHxE4zezNcLpBqxbmm4yan/DGmNR3eHVjR6/+APYdUYVECgYEA6Vx7ZdLXrZfENQ87BGUMWQDoRN+dKFFagL+yPJNegj0Yxqm2aR1dZxMe/yTojXOJzEPebWDd6/gn0vs2+VUFeuXDWKqxTgKvYPJqOlvNozVLAuzicojdH2N8WCuNQOsTYsO2GOD34Wa4z6w0YJA53KMRCvRcSes7uWbrMjpfsHMCgYEA1iUpcemkbi0hQb0q7HRmM5WlcN8aaeg3a66vkcQ4A5KnsYkFNEWvwwBr4G3Ou6oFiB+M1qGt0Oq6bHzcUiSOVWRT/kDk8BXuMxMsY/CmBa8WiSoqAkwGpSFfQGvzI4I5wH17JuxRecoemGkGuyyBIjPEcFADdyUNXe8bo9wgx38CgYEAvZl9TMKcp5b6PF3uhmiPOrUpAeRo7LwEezzE1MCrdcesHKUA5rCMFdYMRiK3q02nNMYjrtyYEkOiH6IqDTQN6sdJCv5MZmR6F5t9wYKbNjJziqHqbkJS3lDXU0+3SXp1WrZGejo2S/VIzLpOJ/KmgGQg0bSuUwSDluROzZVt748CgYEAvT5CDgTn5uXDChFeKXAEEXohpknDUeyBbYwGY87/oUitIVtxWexrkuyAaOkhVP6/vLgFAngG3yVQcic1nPHpRtVO1NYMe48l3cUH6Zn1XfiI9dZpIXaGOM/xGUFMsQiYeN9rQc59gh94QkAuTqLZy5vr8UUEivoXgfeYeYpC/YkCgYB9v+yav7HTWfLzmVo+6rj3Bu9unIfA7KEUe7TVUYmRWnto2xawl5KtYPcFQDC/gZa1EeQ+dfqT+UowRzSC1bxx47x0zdj8fIkU4nZk6ybCRjWHRCnC0LcXV299dY/waYM02zvEvpuNtCt+eRs4MLYhDMQ/x76X2uUECPE3vcNjZw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0A6O3p6YbPI+oUotUMTr/e+fUfTlz+TIoG0FgZsU4IiJaR3GX6YqeQqeQWtW+Rcj0dVWoWMcOJFPq4n4bm+YEc9bhF5n2CCs+jd5vs/3RXXUcavxcIGlyk8OFedClhYc3BMTfLchioEgmCc6F3nNW/vGI6SdBwMsw06V/+DDRh2PU3k7gkX7996rA1J7xZ0eYsP7sj6k/ckNK8VLtgSx00dKUhmxEAfiVuZUV7L0zpDViulHXElwgcyrg7/7+gQ4o7pG36+J0MiQvPqjMjqswdVZRJJL2h3dQbDHdgOpor/aubSXZaA1ibwVS22CAHN4B17dzNfbqcgmxVVBKfw/XwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/expense/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/expense/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

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

