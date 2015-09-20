package com.omertex.support.web;

import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.AttributeOfInquiry;
import com.omertex.support.domain.Inquiry;
import com.omertex.support.domain.Topic;
import com.omertex.support.service.AttributeOfInquiryService;
import com.omertex.support.service.Form;
import com.omertex.support.service.InquiryService;
import com.omertex.support.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/customers/{customerName}/inquiries")
public class InquiryController
{
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private AttributeOfInquiryService attributeOfInquiryService;

    @Autowired
    private TopicService topicService;

    private Inquiry inquiry;
    private Map<String, String> attributeMap;

    @Autowired
    private Form form;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry, BindingResult result) throws DaoException
    {
        if(result.hasErrors())
        {

        }
        this.inquiry = inquiryService.create(inquiry);

        if(attributeMap != null && !attributeMap.isEmpty())
        {
            for(Map.Entry<String, String> entry : attributeMap.entrySet())
            {
                AttributeOfInquiry attribute = new AttributeOfInquiry();
                attribute.setInquiry(this.inquiry);
                attribute.setAttributeName(entry.getKey());
                attribute.setAttributeValue(entry.getValue());
                attributeOfInquiryService.create(attribute);
            }
            attributeMap.clear();
        }
        return "redirect: /inquiries";
    }

    @RequestMapping(value = "/addAttribute", method = RequestMethod.POST)
    public String addAttribute(@ModelAttribute("attribute")AttributeOfInquiry attribute, BindingResult result) throws DaoException
    {
        if(attributeMap == null)
        {
            attributeMap = new HashMap<>();
        }
        attributeMap.put(attribute.getAttributeName(), attribute.getAttributeValue());

        return "redirect: /inquiries";

    }

    @RequestMapping("/inquiries")
    public ModelAndView home() throws DaoException
    {
//        model.addAttribute("inquiry", new Inquiry());
//        model.addAttribute("attribute", new AttributeOfInquiry());
//        attributeMap.clear();
//        return "inquiry";
        ModelAndView mav = new ModelAndView("inquiry");
        List<Topic> topicList = topicService.getTopicAll();
        Map<Object, String> topicMap = new HashMap<>();
        for(Topic t : topicList)
        {
            topicMap.put(t.getTopicId(), t.getTopicName());
        }
        mav.addObject("topicList", topicMap);
        mav.addObject("inquiry", new Inquiry());
        mav.addObject("attribute", new AttributeOfInquiry());
        mav.addObject("inquiryList", inquiryService.getInquiryAll());
        return mav;
    }

    @RequestMapping("/delete/{inquiryId}")
    public View deleteInquiry(@PathVariable Integer inquiryId) throws DaoException
    {
        Inquiry inquiry = inquiryService.getInquiryById(inquiryId);
        if(inquiry != null)
        {
            List<AttributeOfInquiry> attributeList = attributeOfInquiryService.getAttributeAll();
            for(AttributeOfInquiry a : attributeList)
            {
                if(a.getInquiry().getInquiryId().equals(inquiryId))
                {
                    attributeOfInquiryService.delete(a);
                }
            }
            inquiryService.delete(inquiry);
        }
        return new RedirectView("/support/inquiries");
    }

    @RequestMapping("/update/{inquiryId}")
    public ModelAndView updateInquiry(@PathVariable Integer inquiryId) throws DaoException
    {
        Inquiry inquiry = inquiryService.getInquiryById(inquiryId);
        Map<String, String> attributeMap = attributeOfInquiryService.getAttributeOfInquiryById(inquiryId);
        form.setAttributeOfInquiryMap(attributeMap);
        List<Topic> topicList = topicService.getTopicAll();
        Map<Object, String> topicMap = new HashMap<>();
        for(Topic t : topicList)
        {
            topicMap.put(t.getTopicId(), t.getTopicName());
        }
        ModelAndView mav = new ModelAndView("inquiryUpdate");
        mav.addObject("topicList", topicMap);
        mav.addObject("inquiry", inquiry);
        mav.addObject("form", form);
        mav.addObject("attribute", new AttributeOfInquiry());
        return mav;
    }

    @RequestMapping("/inquiryForm")
    public ModelAndView inquiryForm() throws DaoException
    {
        ModelAndView mav = new ModelAndView("inquiryForm");
        List<Topic> topicList = topicService.getTopicAll();
        Map<Object, String> topicMap = new HashMap<>();
//        Map<String, String> attributeMap = new HashMap<>();
//        attributeMap.put("town", "Minsk");
//        form.setAttributeOfInquiryMap(attributeMap);
        for(Topic t : topicList)
        {
            topicMap.put(t.getTopicId(), t.getTopicName());
        }
        mav.addObject("topicList", topicMap);
        mav.addObject("form", form);
        return mav;
    }

    @RequestMapping(value = "/addInquiry", method = RequestMethod.POST)
    public String addInquiryForm(@ModelAttribute("form") Form form, BindingResult result) throws DaoException
    {
        if(result.hasErrors())
        {

        }
        this.inquiry = inquiryService.create(form.getInquiry());

        if(form.getAttributeOfInquiryMap() != null && !form.getAttributeOfInquiryMap().isEmpty())
        {
            for(Map.Entry<String, String> entry : form.getAttributeOfInquiryMap().entrySet())
            {
                AttributeOfInquiry attribute = new AttributeOfInquiry();
                attribute.setInquiry(this.inquiry);
                attribute.setAttributeName(entry.getKey());
                attribute.setAttributeValue(entry.getValue());
                attributeOfInquiryService.create(attribute);
            }
            form.getAttributeOfInquiryMap().clear();
        }
        return "redirect: /inquiryForm";
    }
}
