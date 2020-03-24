package com.example.demo.common.util;

import org.apache.commons.collections.MapUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplate工具类，只支持LoadBalanced
 * 
 * @author wenbin.dai
 *
 */
public class RestTemplateHelperV2 {

	public  static MultiValueMap postForm(Map<String, String> map) throws RestClientException {
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		if (MapUtils.isNotEmpty(map)) {
			map.forEach(requestEntity::set);
		}
		return requestEntity;
	}


	public  static MultiValueMap postForm1(Map<String, Object> map1) throws RestClientException {
		Map<String,String> map=new HashMap<>();
		String date=JsonHelper.parseToJson(map1);
		map=JsonHelper.parseToMap(date);
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		if (MapUtils.isNotEmpty(map)) {
			map.forEach(requestEntity::set);
		}
		return requestEntity;
	}


	public static String getForm(String url, Map<String, String> map) throws RestClientException {
		if (MapUtils.isNotEmpty(map)) {
			StringBuilder sb = new StringBuilder(url);
			sb.append("?");
			map.forEach((key, value) -> {
				sb.append(key).append("=").append(value).append("&");
			});
			url = sb.toString();
		}
		return url;
	}

}
