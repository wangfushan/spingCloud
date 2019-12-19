/**
 * Project Name:finance-admin
 * File Name:DateJsonDeserialize.java
 * Package Name:com.nio.common.jackson
 * Date:2017年8月15日下午2:10:34
 * Copyright (c) 2017, China Link Communications LTD All Rights Reserved.
 *
 */

package com.example.demo.common.jackson;

import com.example.demo.common.util.DateTimeHelper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: DateJsonDeserialize <br/>
 * Date: 2017年8月15日 下午2:10:34 <br/>
 * Description: 日期从json反序列化 <br/>
 *
 * @author wenbin.dai
 * @version
 * @see
 */
public class DateJsonDeserialize {

	/**
	 * ClassName: All_Pattern <br/>
	 * Date: 2017年8月15日 下午2:53:20 <br/>
	 * Description: 将所有pattern集合 <br/>
	 *
	 * @author wenbin.dai
	 * @version DateJsonDeserialize
	 * @see
	 */
	public static class All_Pattern extends JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonParser p, DeserializationContext context)
				throws IOException, JsonProcessingException {
			if (StringUtils.isNotBlank(p.getText())) {
				String value = p.getText().trim();
				Date date = null;
				try {
					date = DateTimeHelper.stringToDateShort(value, DateTimeHelper.YYYY_MM_DD);
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDate(value, DateTimeHelper.YYYY_MM_DD_HH_MM);
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDate(value, DateTimeHelper.YYYY_MM_DD_HH_MM_SS);
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDate(value, DateTimeHelper.YYYY_MM_DD_HH_MM_SS_SSS);
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDate(value, DateTimeHelper.YYYY_MM_DD_HH_MM_SS + ".S");
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDateShort(value, "yyyyMMdd");
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDate(value, "yyyyMMddHHmm");
					return date;
				} catch (Exception exception) {
				}
				try {
					date = DateTimeHelper.stringToDate(value, "yyyyMMddHHmmss");
					return date;
				} catch (Exception exception) {
				}
				try {
					date = new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(value);
					return date;
				} catch (Exception exception) {
				}
			}
			return null;
		}
	}

	public static class TimeMillis extends JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonParser p, DeserializationContext context)
				throws IOException, JsonProcessingException {
			if (StringUtils.isNotBlank(p.getText())) {
				return new Date(Long.parseLong(p.getText().trim()));
			}
			return null;
		}
	}

}
