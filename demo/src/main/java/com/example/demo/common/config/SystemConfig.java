package com.example.demo.common.config;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 系统设置型配置
 * 广州市领课网络科技有限公司：www.roncoo.net
 *
 * @author：WuShuicheng .
 */

public class SystemConfig {

    private static final Logger LOG = LogManager.getLogger(SystemConfig.class);

    /**
     * 支付网关请求地址
     **/
    public static String GATEWAY_URL = "";

    /**
     * H5请求地址
     **/
    public static String H5_URL = "";

    /**
     * 微信支付APPID
     **/
    public static String WEIXIN_PAY_APP_ID = "";
    /**
     * 微信支付APP_SECRET
     **/
    public static String WEIXIN_PAY_APP_SECRET = "";
    /**
     * 微信支付REDIRECT_URL
     **/
    public static String WEIXIN_PAY_REDIRECT_URL = "";

    /**
     * 微信公众号APPID
     **/
    public static String WEIXIN_GZH_APP_ID = "";
    /**
     * 微信公众号APP_SECRET
     **/
    public static String WEIXIN_GZH_APP_SECRET = "";
    /**
     * 微信公众号REDIRECT_URL
     **/
    public static String WEIXIN_GZH_REDIRECT_URL = "";
    /**
     * 微信公众号推送模版id
     **/
    public static String WEIXIN_GZH_TEMPLATEID = "";
    /**
     * 银联二维码
     */
    public static String UNIONPAY_REDIRECT_URL = "";

    /**
     * FTP字符编码
     **/
    public static String FTP_CHARSET = "utf-8";
    /**
     * FTP主机地址
     **/
    public static String FTP_HOST = "";
    /**
     * FTP端口号
     **/
    public static int FTP_PORT = 21;
    /**
     * FTP用户名
     **/
    public static String FTP_USERNAME = "";
    /**
     * FTP密码
     **/
    public static String FTP_PASSWORD = "";
    /**
     * FTP根目录
     **/
    public static String FTP_PROJECT = "";
    /**
     * FTP访问地址
     **/
    public static String FTP_DEST_PATH = "";
    /**
     * 文件夹根目录地址
     */
    public static String RES_DIR = "";
    /**
     * 图片文件夹目录地址
     */
    public static String IMAGE_DIR = "";
    /**
     * 文件夹访问路径
     */
    public static String RES_DEST_PATH = "";
    /**
     * 二级商户进件文件夹地址
     */
    public static String RESULT_DIR = "";

    /**
     * 二维码字符编码(默认utf-8)
     **/
    public static String QRCODE_CHARSET = "utf-8";
    /**
     * 二维码图片格式(默认jpg)
     **/
    public static String QRCODE_FORMAT = "jpg";
    /**
     * 二维码内容(请求路径)
     **/
    public static String QRCODE_CONTENT = "";
    /**
     * 二维码尺寸(默认300)
     **/
    public static int QRCODE_SIZE = 300;
    /**
     * 二维码LOGO宽度(默认100)
     **/
    public static int QRCODE_LOGO_WIDTH = 100;
    /**
     * 二维码LOGO高度(默认100)
     **/
    public static int QRCODE_LOGO_HEIGHT = 100;
    /**
     * 二维码LOGO路径
     **/
    public static String QRCODE_LOGO_PATH = "";
    public static String QRCODE_LOGO_PATH_NULL = "";
    public static String QRCODE_LOGO_BACKGROUND_PATH = "";
    /**
     * 二维码尺寸
     */
    public static int QRCODE_WIDTH_COMMOM = 635;
    public static int QRCODE_WIDTH_HIGH_DEFINITION = 1024;
    public static int QRCODE_HEIGHT_COMMOM = 658;
    public static int QRCODE_HEIGHT_HIGH_DEFINITION = 1038;

    /**
     * 退款结果异步通知（后台通知）地址
     **/
    public static String REFUND_RESULT_NOTIFY_URL = "";
    /**
     * 支付结果异步通知（后台通知）地址
     **/
    public static String PAY_RESULT_NOTIFY_URL = "";
    /**
     * 支付结果同步通知（前台通知）地址
     */
    public static String PAY_RESULT_RETURN_URL = "";
    /**
     * 代付支付结算结果通知地址
     */
    public static String PROXY_PAY_RESULT_NOTIFY_URL = "";
    /**
     * 厦门银行进件结果回调
     */
    public static String XIAMEN_MER_CHECK = "";
    /**
     * 支付宝 WAP 支付
     **/
    public static String PAY_ALI_WAP_URL = "";

    /**
     * qq WAP 支付
     **/
    public static String PAY_QQ_WAP_URL = "";

    /**
     * 微信 WAP 支付
     **/
    public static String PAY_WEIXIN_WAP_URL = "";

    /**
     * 支付完成跳转地址
     **/
    public static String PAY_COMPLETE_URL = "";

    /**
     * 支付完成跳转地址
     **/
    public static String PAY_RESULT_ORDER_QUERY = "";

    /**
     * 对账文件存放目录
     */
    public static String RECONCILIATION_FILE_DIR = "";

    /**
     * 对账文件下载目录
     */
    public static String RECONCILIATION_DOWNLOAD_DIR = "";

    /**
     * 对账文件上传目录
     */
    public static String RECONCILIATION_UPLOAD_DIR = "";

    /**
     * jwt 私钥
     */
    public static String JWT_RSA_PRIVATEKEY = "";

    /**
     * jwt 公钥
     */
    public static String JWT_RSA_PUBLICKEY = "";

    /**
     * 阿里大鱼请求地址
     */
    public static String ALIBABA_MSG_URL = "";
    /**
     * 阿里大鱼key
     */
    public static String ALIBABA_APP_KEY = "";
    /**
     * 阿里大鱼secret
     */
    public static String ALIBABA_APP_SECRET = "";
    /**
     * 阿里大鱼template
     */
    public static String ALIBABA_MSG_TEMPLATE_ID = "";

    /**
     * 阿里云短信 状态
     */
    public static String ALIYUN_SMS_STATUS = "";
    /**
     * 阿里云短信key
     */
    public static String ALIYUN_SMS_KEY = "";
    /**
     * 阿里云短信secret
     */
    public static String ALIYUN_SMS_SECRET = "";
    /**
     * 阿里云短信template
     */
    public static String ALIYUN_SMS_TEMPLATE_ID = "";
    /**
     * 阿里云短信签名
     */
    public static String ALIYUN_SMS_SIGNNAME = "";
    /**
     * 阿里云短信endpoint
     */
    public static String ALIYUN_SMS_ENDPOINT = "";

    //杉德短信服务配置
    //渠道号
    public static String SAND_SMS_CHANNEL_ID = "";
    //后台服务网址
    public static String SAND_SMS_TRANS_URL = "";
    //#验证私钥
    public static String SAND_SMS_PRIVATE_KEY = "";
    //验证公钥
    public static String SAND_SMS_PUBLIC_KEY = "";

    /**
     * 极光推送
     */
    public static String SAND_JPUSH_MASTER_SECRET = "";
    public static String SAND_JPUSH_APP_KEY = "";
    public static Integer SAND_JPUSH_TIME_LIVE = 0;
    public static Boolean SAND_JPUSH_ISPRODUCT = false;

    /**
     * 系统环境
     */
    public static String SYSTEM_CONDITIONS = "";

    /**
     * 系统所属
     */
    public static String SYSTEM_OWNER = "roncoo";

    public static String SYSTEM_JSP_STYLE_VERSION = "";

    /**
     * 是否发送记账通知
     */
    public static Boolean PAY_ACCOUNT_NOTIFY = false;

    /**
     * 记账是否为测试模式
     */
    public static Boolean PAY_ACCOUNT_ISTEST = false;

    public static String PAY_ACCOUNT_CASH_REQ_URL = "";

    public static String PAY_ACCOUNT_QUERY_COMFIRM_REQ_URL = "";

    public static String PAY_ACCOUNT_CASH_PRIVATE_KEY = "";

    /**
     * 系统代理
     */
    public static Boolean PROXY_ENABLE = false;
    public static String PROXY_IP = "";
    public static Integer PROXY_PORT = 0;

    /**
     * 银联专线代理
     */

    public static Boolean UNIONPAY_PROXY_ENABLE = false;
    public static String UNIONPAY_PROXY_IP = "";
    public static Integer UNIONPAY_PROXY_PORT = 0;


    /**
     * 系统版本：区分金，银
     */
    public static String SYSTEM_VERSION = "";

    public static String BANKORDERNO_PREFIX = "";

    public static String TRXNO_PREFIX = "";

    /**
     * 代理商分润计算标识 1 同步计算 2 同步不计算 其他 同步计算
     */
    public static String ACCOUNT_AGENT_PROFIT = "";

    public static String RES_DOMAIN;


    /**
     * 数据中心代理白名单添加地址
     */
    public static String DC_AGENT_URL = "";

    /**
     * 阿里云手机号码归属地请求地址
     */
    public static String ALIYUN_MOBILE_HOST = "";

    /**
     * 阿里云手机号码归属地请求路径
     */
    public static String ALIYUN_MOBILE_PATH = "";

    /**
     * 阿里云身份证归属地请求地址
     */
    public static String ALIYUN_IDCARD_HOST = "";

    /**
     * 阿里云手机号码归属地请求路径
     */
    public static String ALIYUN_IFCARD_PATH = "";

    /**
     * 阿里云appCode
     */
    public static String ALIYUN_APPCODE = "";

    public static boolean CACHE_ENABLE = true;


    public static String TRADE_PRECREATE_WECHAT_PAYMODE = "";


    public static String TRADE_UNION_ALIPAY_URL = "";
    public static String TRADE_UNION_WECHAT_URL = "";
    public static String TRADE_UNION_UNIONPAY_URL = "";
    public static String TRADE_SANDBAO_URL = "";
    /*强生rresa2私钥*/
    public static String DRIVER_PRIVATE_KEY = "";
    /*强生rresa2公钥*/
    public static String DRIVER_PUBLIC_KEY = "";

    /*强生司机信息增加接口URL*/
    public static String DRIVER_UPDATE_ADD_URL = "";

    /*强生司机主信息查询接口URL*/
    public static String DRIVER_SELECT_INFO_URL = "";

    /*强生司机修改信息接口URL*/
    public static String DRIVER_SELECT_UPDATE_URL = "";

    /**
     * 只加载一次.
     */
    static {
        try {
            LOG.info("=== load system_config.properties and init sys param");
            /*InputStream proFile = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("main/java/com/example/demo/common/config/config.properties");*/

            InputStream proFile = SystemConfig.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");
            Properties props = new Properties();
            props.load(proFile);
            init(props);
            LOG.info("=== load end");
        } catch (Exception e) {
            LOG.error("=== load and init system_config.properties exception:" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据配置文件初始化静态变量值.
     *
     * @param props
     */
    private static void init(Properties props) {
        String gateway_url = props.getProperty("gateway.url");
        if (StringUtils.isNotBlank(gateway_url)) {
            GATEWAY_URL = gateway_url;
        } else {
            throw new RuntimeException("支付网关请求地址为空");
        }

        String h5_url = props.getProperty("h5.url");
        if (StringUtils.isNotBlank(h5_url)) {
            H5_URL = h5_url;
        } else {
            throw new RuntimeException("H5请求地址为空");
        }

        String weixin_pay_app_id = props.getProperty("weixin.pay.app.id");
        if (StringUtils.isNotBlank(weixin_pay_app_id)) {
            WEIXIN_PAY_APP_ID = weixin_pay_app_id;
        } else {
            throw new RuntimeException("微信支付APPID为空");
        }

        String weixin_pay_app_secret = props.getProperty("weixin.pay.app.secret");
        if (StringUtils.isNotBlank(weixin_pay_app_secret)) {
            WEIXIN_PAY_APP_SECRET = weixin_pay_app_secret;
        } else {
            throw new RuntimeException("微信支付APP_SECRET为空");
        }

        String weixin_pay_redirect_url = props.getProperty("weixin.pay.redirect.url");
        if (StringUtils.isNotBlank(weixin_pay_redirect_url)) {
            WEIXIN_PAY_REDIRECT_URL = weixin_pay_redirect_url;
        } else {
            throw new RuntimeException("微信支付REDIRECT_URL为空");
        }

        String weixin_gzh_app_id = props.getProperty("weixin.gzh.app.id");
        if (StringUtils.isNotBlank(weixin_gzh_app_id)) {
            WEIXIN_GZH_APP_ID = weixin_gzh_app_id;
        } else {
            throw new RuntimeException("微信公众号APPID为空");
        }

        String weixin_gzh_app_secret = props.getProperty("weixin.gzh.app.secret");
        if (StringUtils.isNotBlank(weixin_gzh_app_secret)) {
            WEIXIN_GZH_APP_SECRET = weixin_gzh_app_secret;
        } else {
            throw new RuntimeException("微信公众号APP_SECRET为空");
        }

        String weixin_gzh_redirect_url = props.getProperty("weixin.gzh.redirect.url");
        if (StringUtils.isNotBlank(weixin_gzh_redirect_url)) {
            WEIXIN_GZH_REDIRECT_URL = weixin_gzh_redirect_url;
        } else {
            throw new RuntimeException("微信公众号REDIRECT_URL为空");
        }

        String weixin_gzh_templateId = props.getProperty("weixin.gzh.templateId");
        if (StringUtils.isNotBlank(weixin_gzh_templateId)) {
            WEIXIN_GZH_TEMPLATEID = weixin_gzh_templateId;
        } else {
            throw new RuntimeException("微信公众号推送模版id为空");
        }

        String unionpay_redirect_url = props.getProperty("unionpay.redirect.url");
        if (StringUtils.isNotBlank(weixin_gzh_redirect_url)) {
            UNIONPAY_REDIRECT_URL = unionpay_redirect_url;
        } else {
            throw new RuntimeException("微信公众号REDIRECT_URL为空");
        }

        String ftp_charset = props.getProperty("ftp.charset");
        if (StringUtils.isNotBlank(ftp_charset)) {
            FTP_CHARSET = ftp_charset;
        } else {
            throw new RuntimeException("FTP字符编码为空");
        }

        String ftp_host = props.getProperty("ftp.host");
        if (StringUtils.isNotBlank(ftp_host)) {
            FTP_HOST = ftp_host;
        } else {
            throw new RuntimeException("FTP主机地址为空");
        }

        String ftp_port = props.getProperty("ftp.port");
        if (StringUtils.isNotBlank(ftp_port)) {
            FTP_PORT = Integer.valueOf(ftp_port);
        } else {
            throw new RuntimeException("FTP端口号为空");
        }

        String ftp_username = props.getProperty("ftp.username");
        if (StringUtils.isNotBlank(ftp_username)) {
            FTP_USERNAME = ftp_username;
        } else {
            throw new RuntimeException("FTP用户名为空");
        }

        String ftp_password = props.getProperty("ftp.password");
        if (StringUtils.isNotBlank(ftp_password)) {
            FTP_PASSWORD = ftp_password;
        } else {
            throw new RuntimeException("FTP密码为空");
        }

        String ftp_project = props.getProperty("ftp.project");
        if (StringUtils.isNotBlank(ftp_project)) {
            FTP_PROJECT = ftp_project;
        } else {
            throw new RuntimeException("FTP根目录为空");
        }

        String ftp_dest_path = props.getProperty("ftp.dest.path");
        if (StringUtils.isNotBlank(ftp_dest_path)) {
            FTP_DEST_PATH = ftp_dest_path;
        } else {
            throw new RuntimeException("FTP访问地址为空");
        }

        String res_dir = props.getProperty("res.dir");
        if (StringUtils.isNotBlank(res_dir)) {
            RES_DIR = res_dir;
        } else {
            throw new RuntimeException("文件夹根目录设置为空");
        }

        String image_dir = props.getProperty("image.dir");
        if (StringUtils.isNotBlank(image_dir)) {
            IMAGE_DIR = image_dir;
        } else {
            throw new RuntimeException("图片文件目录设置为空");
        }

        String result_dir = props.getProperty("result.dir");
        if (StringUtils.isNotBlank(result_dir)) {
            RESULT_DIR = result_dir;
        } else {
            throw new RuntimeException("进件结果文件目录设置为空");
        }

        String res_dest_path = props.getProperty("res.dest.path");
        if (StringUtils.isNotBlank(res_dest_path)) {
            RES_DEST_PATH = res_dest_path;
        } else {
            throw new RuntimeException("文件夹访问路径设置为空");
        }

        String res_domain = props.getProperty("res.domain");
        if (StringUtils.isNotBlank(res_dest_path)) {
            RES_DOMAIN = res_domain;
        } else {
            throw new RuntimeException("图片REFERER白名单设置为空");
        }

        String qrcode_charset = props.getProperty("qrcode.charset");
        if (StringUtils.isNotBlank(qrcode_charset)) {
            QRCODE_CHARSET = qrcode_charset;
        } else {
            throw new RuntimeException("二维码字符编码为空");
        }

        String qrcode_format = props.getProperty("qrcode.format");
        if (StringUtils.isNotBlank(qrcode_format)) {
            QRCODE_FORMAT = qrcode_format;
        } else {
            throw new RuntimeException("二维码图片格式为空");
        }

        String qrcode_content = props.getProperty("qrcode.content");
        if (StringUtils.isNotBlank(qrcode_content)) {
            QRCODE_CONTENT = qrcode_content;
        } else {
            throw new RuntimeException("二维码内容(请求路径)为空");
        }

        String qrcode_size = props.getProperty("qrcode.size");
        if (StringUtils.isNotBlank(qrcode_size)) {
            QRCODE_SIZE = Integer.valueOf(qrcode_size);
        } else {
            throw new RuntimeException("二维码尺寸为空");
        }

        String qrcode_logo_width = props.getProperty("qrcode.logo.width");
        if (StringUtils.isNotBlank(qrcode_logo_width)) {
            QRCODE_LOGO_WIDTH = Integer.valueOf(qrcode_logo_width);
        } else {
            throw new RuntimeException("二维码LOGO宽度为空");
        }

        String qrcode_logo_height = props.getProperty("qrcode.logo.height");
        if (StringUtils.isNotBlank(qrcode_logo_height)) {
            QRCODE_LOGO_HEIGHT = Integer.valueOf(qrcode_logo_height);
        } else {
            throw new RuntimeException("二维码LOGO高度为空");
        }

        String qrcode_logo_path = props.getProperty("qrcode.logo.path");
        if (StringUtils.isNotBlank(qrcode_logo_path)) {
            QRCODE_LOGO_PATH = qrcode_logo_path;
        } else {
            throw new RuntimeException("二维码内容(请求路径)为空");
        }

        String qrcode_logo_path_null = props.getProperty("qrcode.logo.path.null");
        if (StringUtils.isNotBlank(qrcode_logo_path_null)) {
            QRCODE_LOGO_PATH_NULL = qrcode_logo_path_null;
        } else {
            throw new RuntimeException("二维码空白logo(请求路径)为空");
        }

        String qrcode_logo_background_path = props.getProperty("qrcode.logo.background.path");
        if (StringUtils.isNotBlank(qrcode_logo_background_path)) {
            QRCODE_LOGO_BACKGROUND_PATH = qrcode_logo_background_path;
        } else {
            throw new RuntimeException("二维码背景(请求路径)为空");
        }

        String qrcode_width_high_definition = props.getProperty("qrcode.width.high.definition");
        if (StringUtils.isNotBlank(qrcode_width_high_definition)) {
            QRCODE_WIDTH_HIGH_DEFINITION = Integer.valueOf(qrcode_width_high_definition);
        } else {
            throw new RuntimeException("高清二维码宽为空");
        }

        String qrcode_height_high_definition = props.getProperty("qrcode.height.high.definition");
        if (StringUtils.isNotBlank(qrcode_height_high_definition)) {
            QRCODE_HEIGHT_HIGH_DEFINITION = Integer.valueOf(qrcode_height_high_definition);
        } else {
            throw new RuntimeException("高清二维码高为空");
        }

        String qrcode_width_commom = props.getProperty("qrcode.width.commom");
        if (StringUtils.isNotBlank(qrcode_width_commom)) {
            QRCODE_WIDTH_COMMOM = Integer.valueOf(qrcode_width_commom);
        } else {
            throw new RuntimeException("普通二维码宽为空");
        }

        String qrcode_height_commom = props.getProperty("qrcode.height.commom");
        if (StringUtils.isNotBlank(qrcode_height_commom)) {
            QRCODE_HEIGHT_COMMOM = Integer.valueOf(qrcode_height_commom);
        } else {
            throw new RuntimeException("普通二维码高为空");
        }

        String refund_result_notify_url = props.getProperty("refund.result.notify.url");
        if (StringUtils.isNotBlank(refund_result_notify_url)) {
            REFUND_RESULT_NOTIFY_URL = refund_result_notify_url;
        } else {
            throw new RuntimeException("退款结果异步通知（后台通知）地址为空");
        }

        String pay_result_notify_url = props.getProperty("pay.result.notify.url");
        if (StringUtils.isNotBlank(pay_result_notify_url)) {
            PAY_RESULT_NOTIFY_URL = pay_result_notify_url;
        } else {
            throw new RuntimeException("支付结果异步通知（后台通知）地址为空");
        }

        String pay_ali_wap_url = props.getProperty("pay.ali.wap.url");
        if (StringUtils.isNotBlank(pay_ali_wap_url)) {
            PAY_ALI_WAP_URL = pay_ali_wap_url;
        } else {
            throw new RuntimeException("支付宝 wap支付请求地址为空");
        }

        String pay_qq_wap_url = props.getProperty("pay.qq.wap.url");
        if (StringUtils.isNotBlank(pay_qq_wap_url)) {
            PAY_QQ_WAP_URL = pay_qq_wap_url;
        } else {
            throw new RuntimeException("QQ wap支付请求地址为空");
        }

        String pay_weixin_wap_url = props.getProperty("pay.weixin.wap.url");
        if (StringUtils.isNotBlank(pay_weixin_wap_url)) {
            PAY_WEIXIN_WAP_URL = pay_weixin_wap_url;
        } else {
            throw new RuntimeException("微信 wap支付请求地址为空");
        }

        String pay_complete_url = props.getProperty("pay.complete.url");
        if (StringUtils.isNotBlank(pay_complete_url)) {
            PAY_COMPLETE_URL = pay_complete_url;
        } else {
            throw new RuntimeException("支付完成跳转地址为空");
        }

        String pay_result_order_query = props.getProperty("pay.result.order.query");
        if (StringUtils.isNotBlank(pay_result_order_query)) {
            PAY_RESULT_ORDER_QUERY = pay_result_order_query;
        } else {
            throw new RuntimeException("支付结果查询地址为空");
        }

        String pay_result_return_url = props.getProperty("pay.result.return.url");
        if (StringUtils.isNotBlank(pay_result_return_url)) {
            PAY_RESULT_RETURN_URL = pay_result_return_url;
        } else {
            throw new RuntimeException("支付结果同步通知（前台通知）地址为空");
        }

        String proxy_pay_result_notify_url = props.getProperty("proxy.pay.result.notify.url");
        if (StringUtils.isNotBlank(proxy_pay_result_notify_url)) {
            PROXY_PAY_RESULT_NOTIFY_URL = proxy_pay_result_notify_url;
        } else {
            throw new RuntimeException("代付支付结算结果通知地址为空");
        }

        String xiamen_mer_check = props.getProperty("xiamen.mer.check");
        if (StringUtils.isNotBlank(xiamen_mer_check)) {
            XIAMEN_MER_CHECK = xiamen_mer_check;
        } else {
            throw new RuntimeException("厦门银行进件结果回调地址为空");
        }

        String reconciliation_file_dir = props.getProperty("reconciliation.file.dir");
        if (StringUtils.isNotBlank(reconciliation_file_dir)) {
            RECONCILIATION_FILE_DIR = reconciliation_file_dir;
        } else {
            throw new RuntimeException("对账文件存放目录为空");
        }

        String reconciliation_download_dir = props.getProperty("reconciliation.download.dir");
        if (StringUtils.isNotBlank(reconciliation_download_dir)) {
            RECONCILIATION_DOWNLOAD_DIR = reconciliation_download_dir;
        } else {
            throw new RuntimeException("对账文件下载目录为空");
        }

        String reconciliation_upload_dir = props.getProperty("reconciliation.upload.dir");
        if (StringUtils.isNotBlank(reconciliation_download_dir)) {
            RECONCILIATION_UPLOAD_DIR = reconciliation_upload_dir;
        } else {
            throw new RuntimeException("对账文件上传目录为空");
        }

        String jwt_rsa_privateKey = props.getProperty("jwt.rsa.privateKey");
        if (StringUtils.isNotBlank(jwt_rsa_privateKey)) {
            JWT_RSA_PRIVATEKEY = jwt_rsa_privateKey;
        } else {
            throw new RuntimeException("JWT私钥为空");
        }

        String jwt_rsa_publicKey = props.getProperty("jwt.rsa.publicKey");
        if (StringUtils.isNotBlank(jwt_rsa_publicKey)) {
            JWT_RSA_PUBLICKEY = jwt_rsa_publicKey;
        } else {
            throw new RuntimeException("JWT公钥为空");
        }

        String alibaba_msg_url = props.getProperty("alibaba.msg.url");
        if (StringUtils.isNotBlank(alibaba_msg_url)) {
            ALIBABA_MSG_URL = alibaba_msg_url;
        } else {
            throw new RuntimeException("阿里大鱼请求地址为空");
        }

        String alibaba_app_key = props.getProperty("alibaba.app.key");
        if (StringUtils.isNotBlank(alibaba_app_key)) {
            ALIBABA_APP_KEY = alibaba_app_key;
        } else {
            throw new RuntimeException("阿里大鱼KEY为空");
        }

        String alibaba_app_secret = props.getProperty("alibaba.app.secret");
        if (StringUtils.isNotBlank(alibaba_app_secret)) {
            ALIBABA_APP_SECRET = alibaba_app_secret;
        } else {
            throw new RuntimeException("阿里大鱼SECRET为空");
        }

        String alibaba_msg_template_id = props.getProperty("alibaba.msg.template.id");
        if (StringUtils.isNotBlank(alibaba_msg_template_id)) {
            ALIBABA_MSG_TEMPLATE_ID = alibaba_msg_template_id;
        } else {
            throw new RuntimeException("阿里大鱼TEMPLATE_ID为空");
        }

        String aliyun_sms_status = props.getProperty("aliyun.sms.status");
        if (StringUtils.isNotBlank(aliyun_sms_status)) {
            ALIYUN_SMS_STATUS = aliyun_sms_status;
        } else {
            throw new RuntimeException("阿里云短信状态为空");
        }

        String aliyun_sms_key = props.getProperty("aliyun.sms.key");
        if (StringUtils.isNotBlank(aliyun_sms_key)) {
            ALIYUN_SMS_KEY = aliyun_sms_key;
        } else {
            throw new RuntimeException("阿里云短信KEY为空");
        }

        String aliyun_sms_secret = props.getProperty("aliyun.sms.secret");
        if (StringUtils.isNotBlank(aliyun_sms_secret)) {
            ALIYUN_SMS_SECRET = aliyun_sms_secret;
        } else {
            throw new RuntimeException("阿里云短信SECRET为空");
        }

        String aliyun_sms_template_id = props.getProperty("aliyun.sms.template.id");
        if (StringUtils.isNotBlank(aliyun_sms_template_id)) {
            ALIYUN_SMS_TEMPLATE_ID = aliyun_sms_template_id;
        } else {
            throw new RuntimeException("阿里云短信TEMPLATE_ID为空");
        }

        String aliyun_sms_signname;
        try {
            aliyun_sms_signname = new String(props.getProperty("aliyun.sms.signname").getBytes("ISO-8859-1"), "UTF-8");
            if (StringUtils.isNotBlank(aliyun_sms_signname)) {
                ALIYUN_SMS_SIGNNAME = aliyun_sms_signname;
            } else {
                throw new RuntimeException("阿里云签名为空");
            }
        } catch (UnsupportedEncodingException e) {
            LOG.info("获取阿里云签名异常", e);
            throw new RuntimeException("阿里云签名为空");
        }

        String aliyun_sms_endpoint;
        try {
            aliyun_sms_endpoint = new String(props.getProperty("aliyun.sms.endpoint").getBytes("ISO-8859-1"), "UTF-8");
            if (StringUtils.isNotBlank(aliyun_sms_endpoint)) {
                ALIYUN_SMS_ENDPOINT = aliyun_sms_endpoint;
            } else {
                throw new RuntimeException("阿里云endpoint为空");
            }
        } catch (UnsupportedEncodingException e) {
            LOG.info("获取阿里云endpoint异常", e);
            throw new RuntimeException("阿里云endpoint为空");
        }


        String sand_sms_channel_id = props.getProperty("sand.sms.channel.id");
        if (StringUtils.isNotBlank(sand_sms_channel_id)) {
            SAND_SMS_CHANNEL_ID = sand_sms_channel_id;
        } else {
            throw new RuntimeException("渠道号sand.sms.channel.id为空");
        }

        String sand_sms_trans_url = props.getProperty("sand.sms.trans.url");
        if (StringUtils.isNotBlank(sand_sms_trans_url)) {
            SAND_SMS_TRANS_URL = sand_sms_trans_url;
        } else {
            throw new RuntimeException("后台服务网址sand.sms.trans.url为空");
        }

        String sand_sms_private_key = props.getProperty("sand.sms.private.key");
        if (StringUtils.isNotBlank(sand_sms_private_key)) {
            SAND_SMS_PRIVATE_KEY = sand_sms_private_key;
        } else {
            throw new RuntimeException("验证私钥sand.sms.private.key为空");
        }

        String sand_sms_public_key = props.getProperty("sand.sms.public.key");
        if (StringUtils.isNotBlank(sand_sms_public_key)) {
            SAND_SMS_PUBLIC_KEY = sand_sms_public_key;
        } else {
            throw new RuntimeException("验证公钥sand.sms.public.key为空");
        }

        /**
         * 极光推送
         */
        String sand_jpush_master_secret = props.getProperty("sand.jpush.master.secret");
        if (StringUtils.isNotBlank(sand_jpush_master_secret)) {
            SAND_JPUSH_MASTER_SECRET = sand_jpush_master_secret;
        } else {
            throw new RuntimeException("极光主密钥sand_jpush_master_secret为空");
        }
        String sand_jpush_app_key = props.getProperty("sand.jpush.app.key");
        if (StringUtils.isNotBlank(sand_jpush_app_key)) {
            SAND_JPUSH_APP_KEY = sand_jpush_app_key;
        } else {
            throw new RuntimeException("极光应用密钥sand_jpush_app_key为空");
        }

        String sand_jpush_time_live = props.getProperty("sand.jpush.time.live");
        if (StringUtils.isNotBlank(sand_jpush_time_live)) {
            SAND_JPUSH_TIME_LIVE = Integer.parseInt(sand_jpush_time_live);
        }

        String sand_jpush_isproduct = props.getProperty("sand.jpush.isproduct");
        if (StringUtils.isNotBlank(sand_jpush_isproduct)) {
            SAND_JPUSH_ISPRODUCT = Boolean.parseBoolean(sand_jpush_isproduct);
        } else {
            throw new RuntimeException("极光推送环境sand_jpush_isproduct为空");
        }


        String system_conditions = props.getProperty("system.conditions");
        if (StringUtils.isNotBlank(system_conditions)) {
            SYSTEM_CONDITIONS = system_conditions;
        } else {
            throw new RuntimeException("系统环境配置为空");
        }

        String system_owner = props.getProperty("system.owner");
        if (StringUtils.isNotBlank(system_owner)) {
            SYSTEM_OWNER = system_owner;
        } else {
            throw new RuntimeException("系统所属配置为空");
        }

        String system_jsp_style_version = props.getProperty("system.jsp.style.version");
        if (StringUtils.isNotBlank(system_jsp_style_version)) {
            SYSTEM_JSP_STYLE_VERSION = system_jsp_style_version;
        } else {
            throw new RuntimeException("系统所属配置为空");
        }


        String pay_account_notify = props.getProperty("pay.account.notify");
        if (StringUtils.isNotBlank(pay_account_notify)) {
            PAY_ACCOUNT_NOTIFY = Boolean.parseBoolean(pay_account_notify);
        }

        String pay_account_istest = props.getProperty("pay.account.istest");
        if (StringUtils.isNotBlank(pay_account_istest)) {
            PAY_ACCOUNT_ISTEST = Boolean.parseBoolean(pay_account_istest);
        }

        String pay_account_cash_request_url = props.getProperty("pay.account.cash.request.url");
        if (StringUtils.isNotBlank(pay_account_cash_request_url)) {
            PAY_ACCOUNT_CASH_REQ_URL = pay_account_cash_request_url;
        }

        String pay_account_query_comfirm_request_url = props.getProperty("pay.account.query.comfirm.request.url");
        if (StringUtils.isNotBlank(pay_account_query_comfirm_request_url)) {
            PAY_ACCOUNT_QUERY_COMFIRM_REQ_URL = pay_account_query_comfirm_request_url;
        }

        String pay_account_cash_privatekey = props.getProperty("pay.account.cash.privatekey");
        if (StringUtils.isNotBlank(pay_account_cash_privatekey)) {
            PAY_ACCOUNT_CASH_PRIVATE_KEY = pay_account_cash_privatekey;
        }

        String proxy_enable = props.getProperty("proxy.enable");
        if (StringUtils.isNotBlank(proxy_enable)) {
            PROXY_ENABLE = Boolean.parseBoolean(proxy_enable);
        }

        String proxy_ip = props.getProperty("proxy.ip");
        if (StringUtils.isNotBlank(proxy_enable)) {
            PROXY_IP = proxy_ip;
        }

        String proxy_port = props.getProperty("proxy.port");
        if (StringUtils.isNotBlank(proxy_enable)) {
            PROXY_PORT = Integer.parseInt(proxy_port);
        }

        String unionpay_proxy_enable = props.getProperty("unionpay.proxy.enable");
        if (StringUtils.isNotBlank(proxy_enable)) {
            UNIONPAY_PROXY_ENABLE = Boolean.parseBoolean(unionpay_proxy_enable);
        }

        String unionpay_proxy_ip = props.getProperty("unionpay.proxy.ip");
        if (StringUtils.isNotBlank(proxy_enable)) {
            UNIONPAY_PROXY_IP = unionpay_proxy_ip;
        }

        String unionpay_proxy_port = props.getProperty("unionpay.proxy.port");
        if (StringUtils.isNotBlank(proxy_enable)) {
            UNIONPAY_PROXY_PORT = Integer.parseInt(unionpay_proxy_port);
        }

        String system_version = props.getProperty("system.version");
        if (StringUtils.isNotBlank(system_version)) {
            SYSTEM_VERSION = system_version;
        } else {
            throw new RuntimeException("系统平台参数为空");
        }

        String bankorderno_prefix = props.getProperty("id.bankorderno.prefix");
        if (StringUtils.isNotBlank(bankorderno_prefix)) {
            BANKORDERNO_PREFIX = bankorderno_prefix;
        } else {
            throw new RuntimeException("银行订单号前缀为空");
        }

        String trxno_prefix = props.getProperty("id.trxno.profix");
        if (StringUtils.isNotBlank(trxno_prefix)) {
            TRXNO_PREFIX = trxno_prefix;
        } else {
            throw new RuntimeException("平台流水号前缀为空");
        }

        String accountAgentProfit = props.getProperty("account.agent.profit");
        if (StringUtils.isNotBlank(accountAgentProfit)) {
            ACCOUNT_AGENT_PROFIT = accountAgentProfit;
        } else {
            throw new RuntimeException("代理商分润计算标识为空");
        }


        String dc_agent_url = props.getProperty("dc.agent.url");
        if (StringUtils.isNotBlank(dc_agent_url)) {
            DC_AGENT_URL = dc_agent_url;
        } else {
            throw new RuntimeException("数据中心代理白名单添加地址为空");
        }

        String aliyun_mobile_host = props.getProperty("aliyun.mobile.host");
        if (StringUtils.isNotBlank(aliyun_mobile_host)) {
            ALIYUN_MOBILE_HOST = aliyun_mobile_host;
        } else {
            throw new RuntimeException("阿里云手机号码归属地请求地址为空");
        }
        String aliyun_mobile_path = props.getProperty("aliyun.mobile.path");
        if (StringUtils.isNotBlank(aliyun_mobile_path)) {
            ALIYUN_MOBILE_PATH = aliyun_mobile_path;
        } else {
            throw new RuntimeException("阿里云手机号码归属地请求路径为空");
        }
        String aliyun_idcard_host = props.getProperty("aliyun.idCard.host");
        if (StringUtils.isNotBlank(aliyun_idcard_host)) {
            ALIYUN_IDCARD_HOST = aliyun_idcard_host;
        } else {
            throw new RuntimeException("阿里云身份证归属地请求地址为空");
        }
        String aliyun_ifcard_path = props.getProperty("aliyun.ifCard.path");
        if (StringUtils.isNotBlank(aliyun_ifcard_path)) {
            ALIYUN_IFCARD_PATH = aliyun_ifcard_path;
        } else {
            throw new RuntimeException("阿里云手机号码归属地请求路径为空");
        }
        String aliyun_appcode = props.getProperty("aliyun.appCode");
        if (StringUtils.isNotBlank(aliyun_appcode)) {
            ALIYUN_APPCODE = aliyun_appcode;
        } else {
            throw new RuntimeException("阿里云appCode为空");
        }

        String cacheEnable = props.getProperty("cache.enable");
        if (StringUtils.isNotBlank(cacheEnable)) {
            CACHE_ENABLE = Boolean.parseBoolean(cacheEnable);
        }

        String tradePrecreateWechatPaymode = props.getProperty("trade.precreate.wechat.paymode");
        if (StringUtils.isNotBlank(tradePrecreateWechatPaymode)) {
            TRADE_PRECREATE_WECHAT_PAYMODE = tradePrecreateWechatPaymode;
        }

        String tradeUnionAlipayUrl = props.getProperty("trade.union.alipay.url");
        if (StringUtils.isNotBlank(tradeUnionAlipayUrl)) {
            TRADE_UNION_ALIPAY_URL = tradeUnionAlipayUrl;
        } else {
            throw new RuntimeException("银联渠道支付宝地址为空");
        }

        String tradeUnionWechatUrl = props.getProperty("trade.union.wechat.url");
        if (StringUtils.isNotBlank(tradeUnionWechatUrl)) {
            TRADE_UNION_WECHAT_URL = tradeUnionWechatUrl;
        } else {
            throw new RuntimeException("银联渠道微信地址为空");
        }

        String tradeUnionUnionpayUrl = props.getProperty("trade.union.unionpay.url");
        if (StringUtils.isNotBlank(tradeUnionUnionpayUrl)) {
            TRADE_UNION_UNIONPAY_URL = tradeUnionUnionpayUrl;
        } else {
            throw new RuntimeException("银联渠道银联二维码地址为空");
        }

        String tradeSandbaoUrl = props.getProperty("trade.sandbao.url");
        if (StringUtils.isNotBlank(tradeSandbaoUrl)) {
            TRADE_SANDBAO_URL = tradeSandbaoUrl;
        } else {
            throw new RuntimeException("杉德宝渠道地址为空");
        }


        String driverPublic = props.getProperty("driver.public.key");
        if (StringUtils.isNotBlank(driverPublic)) {
            DRIVER_PUBLIC_KEY = driverPublic;
        } else {
            throw new RuntimeException("RSA2公钥为空");
        }
        String driverPrivate = props.getProperty("driver.private.key");
        if (StringUtils.isNotBlank(driverPrivate)) {
            DRIVER_PRIVATE_KEY = driverPrivate;
        } else {
            throw new RuntimeException("RSA2私钥为空");
        }

        String driverUpdateAddUrl = props.getProperty("dviver.update.add.url");
        if (StringUtils.isNotBlank(driverUpdateAddUrl)) {
            DRIVER_UPDATE_ADD_URL = driverUpdateAddUrl;
        } else {
            throw new RuntimeException("强生司机修改保存接口UPL为空");
        }

        String driverSelectInfoUrl = props.getProperty("dviver.select.info.url");
        if (StringUtils.isNotBlank(driverSelectInfoUrl)) {
            DRIVER_SELECT_INFO_URL = driverSelectInfoUrl;
        } else {
            throw new RuntimeException("强生司机查询信息接口UPL为空");
        }

        String driverSelectUpdateUrl = props.getProperty("dviver.select.update.url");
        if (StringUtils.isNotBlank(driverSelectUpdateUrl)) {
            DRIVER_SELECT_UPDATE_URL = driverSelectUpdateUrl;
        } else {
            throw new RuntimeException("强生司机查询修改信息接口UPL为空");
        }
    }
}