package com.ennuova.util;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**
 * jimmy
 * json工具类
 * 
 * @author 
 */
public class JsonUtils extends ObjectMapper{
	private static Logger logger = Logger.getLogger(JsonUtils.class);

	/**
	 * 通用的json处理工具
	 */
	private static final ObjectMapper NORMAL_OBJECT_MAPPER;
	private static final ObjectMapper WRAP_ROOT_NAME_OBJECT_MAPPER;
	/**
	 * 日期格式化
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final DateFormat ROOT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	static {
		NORMAL_OBJECT_MAPPER = new ObjectMapper();
		// 日期格式化为 yyyy-MM-dd HH:mm:ss
		NORMAL_OBJECT_MAPPER.setDateFormat(ROOT_DATE_FORMAT);
		// 只包含属性不为空的值，去掉为null和""的数据
		//NORMAL_OBJECT_MAPPER.setSerializationInclusion(Inclusion.NON_EMPTY);
		//NORMAL_OBJECT_MAPPER.configure(Feature.WRITE_NULL_MAP_VALUES, true);
		/*
		 * 设置NULL或空的数据为""
		 */
		NORMAL_OBJECT_MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
        {

            public void serialize(
                    Object value,
                    JsonGenerator jg,
                    SerializerProvider sp) throws IOException, JsonProcessingException
            {
                jg.writeString("");
            }
        });
		//StdSerializerProvider sp = new StdSerializerProvider();  
		//sp.setNullValueSerializer(NullStringSerializer.instance);  
		//NORMAL_OBJECT_MAPPER.setSerializerProvider(sp);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		NORMAL_OBJECT_MAPPER.configure(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NORMAL_OBJECT_MAPPER.configure(Feature.FAIL_ON_EMPTY_BEANS, true);

		WRAP_ROOT_NAME_OBJECT_MAPPER = new ObjectMapper();
		// 日期格式化为 yyyy-MM-dd HH:mm:ss
		WRAP_ROOT_NAME_OBJECT_MAPPER.setDateFormat(DATE_FORMAT);
		// 只包含属性不为空的值，去掉为null和""的数据
		WRAP_ROOT_NAME_OBJECT_MAPPER.setSerializationInclusion(Inclusion.NON_EMPTY);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(Feature.WRITE_NULL_MAP_VALUES, true);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(Feature.FAIL_ON_EMPTY_BEANS, true);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(Feature.WRAP_ROOT_VALUE, true);
		WRAP_ROOT_NAME_OBJECT_MAPPER.configure(
				org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
		

	}

	public static <T> T toObject(String json, Class<T> clazz) {
		try {
			return NORMAL_OBJECT_MAPPER.readValue(json, clazz);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> String toJson(T entity) {
		try {
			return NORMAL_OBJECT_MAPPER.writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> T toCollection(String json, TypeReference<T> typeReference) {
		try {
			return NORMAL_OBJECT_MAPPER.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> T toObjectWithRootName(String json, Class<T> clazz) {
		try {
			if (StringUtils.isNotEmpty(json)) {
				return WRAP_ROOT_NAME_OBJECT_MAPPER.readValue(json, clazz);
			}
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> String toJsonWithRootName(T entity) {
		try {
			return WRAP_ROOT_NAME_OBJECT_MAPPER.writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> T toCollectionWithRootName(String json, TypeReference<T> typeReference) {
		try {
			return WRAP_ROOT_NAME_OBJECT_MAPPER.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 获取泛型的Collection Type
	 * @param jsonStr json字符串
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类型
	 */
	public static <T> T toCollection(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
	       JavaType javaType = NORMAL_OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	       return NORMAL_OBJECT_MAPPER.readValue(jsonStr, javaType);
	}
}
