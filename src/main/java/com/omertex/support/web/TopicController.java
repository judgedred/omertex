package com.omertex.support.web;

import com.omertex.support.domain.Topic;
import com.omertex.support.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class TopicController
{
    @Autowired
    private TopicService topicService;

    @RequestMapping("/index")
    public String listTopics(Map<String, Object> map) {

        map.put("topic", new Topic());
        map.put("topicList", topicService.getTopicAll());

        return "inquiry";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }
}
