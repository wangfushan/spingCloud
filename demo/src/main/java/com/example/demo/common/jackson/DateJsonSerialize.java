/**
 * Project Name:nio-pay
 * File Name:DateJsonSerialize.java
 * Package Name:com.nio.common.jackson
 * Date:2017年8月23日下午4:09:56
 * Copyright (c) 2017, China Link Communications LTD All Rights Reserved.
 *
 */

package com.example.demo.common.jackson;

import com.example.demo.common.util.DateTimeHelper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * ClassName: DateJsonSerialize <br/>
 * Date: 2017年8月23日 下午4:09:56 <br/>
 * Description: TODO
 *
 * @author wenbin.dai
 * @version
 * @see
 */
public class DateJsonSerialize {

	public static class Date_YYYYMMDDHHMMSS extends JsonSerializer<Date> {

		@Override
		public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException, JsonProcessingException {
			if (value != null) {
				gen.writeString(DateTimeHelper.dateToString(value, DateTimeHelper.YYYY_MM_DD_HH_MM_SS));
			}
		}

	}

}
