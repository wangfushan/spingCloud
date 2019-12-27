package com.example.demo.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * (C) Shanghai Sand Information Technology System Co., Ltd.
 * All Rights Reserved.
 * <p>
 * Description: 封装Jackson工具类
 * <p>
 * Modification History: <p>
 * ============================================================================= <p>
 * Author         Date          Modification Description <p>
 * ------------ ---------- --------------------------------------------------- <p>
 * Wu.WQ         2017/5/15       Create <p>
 * ============================================================================= <p>
 */
public class JacksonUtil {
    public static final Logger LOG = LogManager.getLogger(JacksonUtil.class);

    private static final ObjectMapper sObjectMapper = new ObjectMapper();
    private static final XmlMapper sXmlMapper = new XmlMapper();

    static {
        sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//忽略unknown
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)//字典序
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);//忽略null
        sXmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)//忽略unknown
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)//字典序
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);//忽略null
    }

    /**
     * java bean to json
     *
     * @param obj Object
     * @return xml string
     */
    public static String obj2json(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOG.error("object convert to json error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java bean to map
     *
     * @param obj Object
     * @return Map
     */
    public static <K, V> Map<K, V> bean2map(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, new TypeReference<Map<K, V>>() {
            });
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java obj to bean
     *
     * @param obj Object
     * @return T
     */
    public static <T> T obj2bean(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, clazz);
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java bean to map
     *
     * @param obj Object
     * @return Map
     */
    public static Map<String, Object> obj2map(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * java bean to map
     *
     * @param obj Object
     * @return Map
     */
    public static Map<String, String> obj2MapStr(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return sObjectMapper.convertValue(obj, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            LOG.error("object convert to map error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return sObjectMapper.readValue(json, clazz);
        } catch (Exception e) {
            LOG.error("json string convert to object error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(String json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        }
        try {
            return sObjectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            LOG.error("json string convert to object error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(InputStream in, Class<T> clazz) {
        try {
            return sObjectMapper.readValue(in, clazz);
        } catch (IOException e) {
            LOG.error("stream convert to object error,{}", e.getMessage());
        }
        return null;
    }

    public static <T> T json2obj(Reader src, Class<T> clazz) {
        try {
            return sObjectMapper.readValue(src, clazz);
        } catch (IOException e) {
            LOG.error("stream convert to object error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * json string convert to map
     */
    public static <K, V> Map<K, V> json2map(String jsonStr) {
        if (jsonStr == null) {
            return null;
        }
        try {
            return sObjectMapper.readValue(jsonStr, new TypeReference<Map<K, V>>() {
            });
        } catch (Exception e) {
            LOG.error("json string convert to map error,{}", e.getMessage());
        }
        return null;
    }

    /**
     * json string convert to List
     */
    public static <T> List<T> json2list(String jsonStr, Class<T> clazz) {
        if (jsonStr == null) {
            return null;
        }
        try {
            //CollectionType javaType = sObjectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            //return sObjectMapper.readValue(jsonStr,javaType);
            //速度快
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + clazz.getName() + ";");
            return Arrays.asList(sObjectMapper.readValue(jsonStr, arrayClass));
        } catch (Exception e) {
            LOG.error("json string convert to map error,{}", e.getMessage());
        }
        return null;
    }

    public static String obj2Xml(Object obj) throws JsonProcessingException {
        return sXmlMapper.writeValueAsString(obj);
    }

    public static <T> T xml2obj(String xmlStr, Class<T> clazz) throws IOException {
        return sXmlMapper.readValue(xmlStr, clazz);
    }

    public static <K, V> Map<K, V> xml2Map(String xmlStr) throws IOException {
        return sXmlMapper.readValue(xmlStr, new TypeReference<Map<K, V>>() {
        });
    }
}