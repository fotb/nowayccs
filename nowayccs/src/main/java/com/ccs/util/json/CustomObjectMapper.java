package com.ccs.util.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.ccs.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {  
    
    
	private static final long serialVersionUID = -891889947116740050L;

	private boolean camelCaseToLowerCaseWithUnderscores = false;  
    private String dateFormatPattern;  
  
    public void setCamelCaseToLowerCaseWithUnderscores(boolean camelCaseToLowerCaseWithUnderscores) {  
        this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;  
    }  
  
    public void setDateFormatPattern(String dateFormatPattern) {  
        this.dateFormatPattern = dateFormatPattern;  
    }  
  
    public void init() {  
        // 排除值为空属性  
        setSerializationInclusion(JsonInclude.Include.NON_NULL);  
        // 进行缩进输出  
        configure(SerializationFeature.INDENT_OUTPUT, true);  
        // 将驼峰转为下划线  
        if (camelCaseToLowerCaseWithUnderscores) {  
            setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);  
        }  
        // 进行日期格式化  
        if (!StringUtil.isNull(dateFormatPattern)) {  
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);  
            setDateFormat(dateFormat);  
        }  
    }  
}  