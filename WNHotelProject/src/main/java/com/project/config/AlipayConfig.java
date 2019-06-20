package com.project.config;

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
	public static String app_id = "2016092900622157";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEWBtSpIz39cQCBquBQeFtPEJ1w4XJ20gC3mwjSeqopS+jRr9kaausYfcgiiF7eM5UANb7cGSydPj45iKyhdFLxjrlM/bABMb5W7Bm03O3zi0SVK4aC0BgbOFcYFO4LEwWiHuvISAScNrFhE5u3YKr0vEHIo5IoMynPxrqJDFLvYPJ17qduipknG4rsv3wXv8ewmk0vrZ9cyWatnhql3J+2Irbjt+wr6KQdJs9NH+FAaDlC/WP6H2uG/z7ysbyFjjujL5Z3AzM3yPv2LbwvZy416oIjspIFmWMjiWj5C/bELCE0Z37pvRzqnQ+v5IfSwnsVDpDy6n6TP+9XTVKXlADAgMBAAECggEAGsG9dG30WkWEBWzXy+BH9U/Tl/+Z0v/UhU/2yQOuv7317armEyUSIl4EGr4SmuYHwRASZu+ITFQ96gSNS06mzA0d5y0kXBobKFsTRRbHNIgosKYSNQ3FTDl3LrgC9R9vmrapWvT1iQTsFjbzrJJ4UsjpFio0Xha0ibPGe0Qp+0fdUvXV5KyjcXp7He4S/LiPcTDalmmgkuRLKjT06ALg3IY6wJfLR6N5fOgc0rMcr3i5hXYiMV1lI4Re7HSvCsIkHQEQop4JQppu5S6cTnclzY3WeeVVdYHjIm1z7vmtniGPHBCJErR/BWjemaGc36DWgheta8/vTg8WIn2dDaAiEQKBgQD9bbro4PSEka2sc0n6aKOum4GOrXl7abW6o8/hs1tiwPkUrmMurIuOc0gVYhQC7ou+rUFM1or2ucjeZ9Tyc3xAFL985Y0XBTNK6vDBwHVgAxZ7PjUF5QHwaVjkYXleXm0OucABXhuWukpE7iS4W1uoTvJoTPAK8wSsGaw5O3ow/wKBgQCFr91uQ6jHv7qx8ll2UZqLGRlxjbgrkmPYgOMgzUOyFhIEyU952NkNuxIizVVIaRFvaRD0ZNNfwRhz/5WfVahAqclIVrIxn5E7wXKTImG8QIXx26CIPp1t583/cdaAMFaOjpoIcJzWXV9VozdgrXOIWq7rXJMllAvFkBD6msAc/QKBgQDNcY78CqEudwpbqnpmTT2j5tx4VZkNuvxfeTyks0l6HQXPBi/RPn9D06Cz9PZUCh2vTbSKHcI5kRKNeY7fnU80g51YGp7t8oFKUnsqFmMQmnA3KI7DYIrZ4gz11y8UmWe/cEV+1lN3NT3P815BNY5wOs+4Teaqeadn/Mfo/ZZo8QKBgDKSjNmhjr/BsAaQ/3ba9/OW0yE502noPw0v9Lv76pf78WUOIpqdhb7D/eBcfN6foJDc1iUd3zhh+7YmAZv882FC2XocEdzj2DUawRUX9JEad5E+3EOuLC/fePYhQlvL7XKQT4TR1QLi36Wt0M0p0ILMi09N/fxBPRLHpSSoVfShAoGAVrpuNIXHz64rtR3U7Led0jp7j2/o8etodu7UJaYXhL9vE0A17FPNLNJMKF0UPqdj7DJrHLpCT2h09BzVDG2J+lfnXIJldVpYGwfBMHLchoLPc0wIVAXI+4KEdQWZmkFj/aQD8PVLmN9k2wX+lRSShgmGW0SARouJs0nsa3VpR9U=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmsKbMKnGjqpAmZDC+4fcJr3B6Ju/E9CrC9F4RPo44mc61F12Dxkv5SKdpRTMD7YiI8zjTJ2wro77fVqeLHVCuS2viIK1f3GgxN+ax8DTA/pJQDDHIH4NjqSqg8s1Tep8uaxfko/wDugg6TMBT+6LjY55CtvabIUgpM/fCYtT1AmkLG8BI/nDEsGrIBpgqQM/geqzW+06ACBVcHSLhUMl08W2eINp/XZytu/9xXEW9K8vy/ItOtvls7Xp2v+F0ftcg0q/xbBT5f3+/8d7Qxho4MLQW+DrNayuKE/8X0MTkCRZCrHuU942lV4Qk38Dq7Zoky9eScUssbyL7rZdTaSMXQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://2481g0u862.wicp.vip:47453/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://2481g0u862.wicp.vip:47453/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

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

