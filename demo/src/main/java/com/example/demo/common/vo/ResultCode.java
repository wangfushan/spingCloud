package com.example.demo.common.vo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class ResultCode {

	@Sub(name = "SUCCESS")
	public static String RESULT_SUCCESS = "0000";

	@Sub(name = "FAILED : ")
	public static String RESULT_FAILED = "0101";

	@Sub(name = "系统内部错误！")
	public static String RESULT__ERROR_2 = "0102";

	@Sub(name = "Save Failed, please check the params")
	public static String RESULT__ERROR_3 = "0103";

	@Sub(name = "Failed, current carType has no subsidy in this city")
	public static String RESULT__ERROR_4 = "0104";

	@Sub(name = "该城市正在使用中, 请勿禁用!")
	public static String RESULT__ERROR_5 = "0105";

	@Sub(name = "该车款正在使用中, 请勿删除或禁用!")
	public static String RESULT__ERROR_6 = "0106";

	@Sub(name = "该城市已存在, 请勿重复添加")
	public static String RESULT__ERROR_7 = "0107";

	@Sub(name = "该年份车款已存在, 请勿重复添加")
	public static String RESULT__ERROR_8 = "0108";

	@Sub(name = "该补贴详情已存在, 请勿重复添加")
	public static String RESULT__ERROR_9 = "0109";
	
	@Sub(name = "参数错误")
	public static String RESULT_ERROR_10 = "0110";
	
	@Sub(name = "账号已被锁定")
	public static String RESULT_ERROR_11 = "0111";
	
	@Sub(name = "用户不存在")
	public static String RESULT_ERROR_12 = "0112";
	
	@Sub(name = "密码错误")
	public static String RESULT_ERROR_13 = "0113";
	
	@Sub(name = "账户被删除或禁用!")
	public static String RESULT_ERROR_14 = "0114";
	
	@Sub(name = "登陆失败!")
	public static String RESULT_ERROR_15 = "0115";
	
	@Sub(name = "数据已存在!")
	public static String RESULT_ERROR_16 = "0116";
	
	@Sub(name = "保存失败!")
	public static String RESULT_ERROR_17 = "0117";
	
	@Sub(name = "更新失败!")
	public static String RESULT_ERROR_18 = "0118";
	
	@Sub(name = "删除失败!")
	public static String RESULT_ERROR_19 = "0119";

	@Sub(name = "数据不存在!")
	public static String RESULT_ERROR_20 = "0120";

	@Sub(name="该车款没有匹配电池容量")
	public static String RESULT_ERROR_21 = "0121";
	
	@Sub(name = "该车款不存在, 请前往补贴车款页面进行添加!")
	public static String RESULT__ERROR_22 = "0122";

    @Sub(name = "该车款已存在最新国补")
    public static String RESULT__ERROR_23 = "0123";
    
    @Sub(name = "该补贴是默认补贴，不能禁用")
    public static String RESULT__ERROR_24 = "0124";
	
	@Retention(RUNTIME)
	@Target({ FIELD })
	public @interface Sub {
		public String name();
	}

	public static String getValueByKey(String key) {
		ResultCode mySub = new ResultCode();

		if (key == null || key.equals("")) {
			key = ResultCode.RESULT_SUCCESS;
		}

		String result = "Success";

		Field[] fields = mySub.getClass().getDeclaredFields();
		for (Field field : fields) {

			Annotation ano = field.getAnnotation(Sub.class);
			try {
				if (key.equals(field.get(mySub).toString()) && ano != null) {
					Sub sub = (Sub) ano;
					result = (String) sub.name();
					break;
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

}
