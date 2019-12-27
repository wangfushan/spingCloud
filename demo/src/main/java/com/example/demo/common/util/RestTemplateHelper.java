package com.example.demo.common.util;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplate工具类，只支持LoadBalanced
 * 
 * @author wenbin.dai
 *
 */
public class RestTemplateHelper {

	private static RestTemplate balancedRestTemplate;
	private static HttpHeaders applicaiontJsonHeaders = new HttpHeaders();

	static {
		balancedRestTemplate = BeanUtils.getBean(RestTemplate.class);
		applicaiontJsonHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}

	public static String postForm(String url, Map<String, String> map) throws RestClientException {
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		if (MapUtils.isNotEmpty(map)) {
			map.forEach(requestEntity::set);
		}
		return balancedRestTemplate.postForObject(url, requestEntity, String.class);
	}
	
	public static String postForm2(String url, Map<String, Object> map) throws RestClientException {
		MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
		if (MapUtils.isNotEmpty(map)) {
			map.forEach(requestEntity::set);
		}
		return balancedRestTemplate.postForObject(url, requestEntity, String.class);
	}

	public static <T> String postJson(String url, T object) throws RestClientException {
		HttpEntity<T> entity = new HttpEntity<T>(object, applicaiontJsonHeaders);
		return balancedRestTemplate.postForEntity(url, entity, String.class).getBody();
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
		return balancedRestTemplate.getForObject(url, String.class);
	}

}
