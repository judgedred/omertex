package com.omertex.support.web;

import com.omertex.support.domain.Topic;
import com.omertex.support.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class TopicController
{
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public @ResponseBody List<Topic> listTopics() throws Exception
    {
        return topicService.getTopicAll();
    }
}
