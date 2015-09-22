package com.omertex.support.web;


import com.omertex.support.domain.Topic;
import org.springframework.core.convert.converter.Converter;

public class StringToTopic implements Converter<String, Topic>
{
    @Override
    public Topic convert(String source)
    {
        Topic t = new Topic();
        if(source != null && !source.equals(""))
        {
            t.setTopicId(Integer.parseInt(source));
            return t;
        }
        else
        {
            return null;
        }
    }
}
