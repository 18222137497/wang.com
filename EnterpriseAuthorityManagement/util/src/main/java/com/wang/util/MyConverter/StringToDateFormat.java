package com.wang.util.MyConverter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器：字符串转date
 */
public class StringToDateFormat implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source==null){
            throw new RuntimeException("执行com.wang.util.MyConverter.StringToDateFormat,传入参数为空");
        }else {
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                return dateFormat.parse(source);
            } catch (ParseException e) {
                throw new RuntimeException("执行com.wang.util.MyConverter.StringToDateFormat,类型转换异常");
            }
        }
    }
}
