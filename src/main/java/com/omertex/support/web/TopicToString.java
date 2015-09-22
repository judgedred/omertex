package com.omertex.support.web;


import com.omertex.support.domain.Topic;
import org.springframework.core.convert.converter.Converter;

public class TopicToString implements Converter<Topic, String>
{
    @Override
    public String convert(Topic source)
    {
        return source.getTopicName();
    }
}
