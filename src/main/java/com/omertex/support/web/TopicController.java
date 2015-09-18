package com.omertex.support.web;

import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Topic;
import com.omertex.support.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class TopicController
{
    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public String listTopics(Model model) throws DaoException
    {

//        map.put("topic", new Topic());
//        map.put("topicList", topicService.getTopicAll());
        model.addAttribute("topicList", topicService.getTopicAll());

        return "topic";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }
}
