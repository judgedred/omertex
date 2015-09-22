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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.*;

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
        /*Map<Object, String> topicMap = new HashMap<>();
        for(Topic t : topicList)
        {
            topicMap.put(t.getTopicId(), t.getTopicName());
        }*/
        mav.addObject("topicList", topicList);
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

    @RequestMapping("/update/{inquiryId}/test")
    public ModelAndView updateInquiry2(@PathVariable Integer inquiryId) throws DaoException
    {
        Inquiry inquiry = inquiryService.getInquiryById(inquiryId);
        Map<String, String> attributeMap = attributeOfInquiryService.getAttributeMapById(inquiryId);
        form.setAttributeOfInquiryMap(attributeMap);
        List<Topic> topicList = topicService.getTopicAll();
        Map<Object, String> topicMap = new HashMap<>();
        for(Topic t : topicList)
        {
            topicMap.put(t.getTopicId(), t.getTopicName());
        }
        ModelAndView mav = new ModelAndView("inquiryUpdate2");
        mav.addObject("topicList", topicMap);
        mav.addObject("inquiry", inquiry);
        mav.addObject("form", form);
        mav.addObject("attribute", new AttributeOfInquiry());
        return mav;
    }

    @RequestMapping("/inquiryForm1")
    public ModelAndView inquiryForm1() throws DaoException
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

    @RequestMapping(value = "/addInquiry1", method = RequestMethod.POST)
    public String addInquiryForm1(@ModelAttribute("form") Form form, BindingResult result) throws DaoException
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

    @RequestMapping(value = "/addInquiry", method = RequestMethod.POST)
    public String addInquiryForm(@RequestParam MultiValueMap<String, String> params) throws Exception
    {

        Inquiry inquiry = new Inquiry();
        Topic topic = new Topic();
        AttributeOfInquiry attribute;
        List<AttributeOfInquiry> attributeList = new LinkedList<>();
        for(Map.Entry<String, List<String>> entry : params.entrySet())
        {
            if(entry.getKey().equals("customerName"))
            {
                inquiry.setCustomerName(entry.getValue().get(0));
            }
            else if(entry.getKey().equals("createDate") && !entry.getValue().get(0).equals(""))
            {
                inquiry.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(entry.getValue().get(0)));
            }
            else if(entry.getKey().equals("description"))
            {
                inquiry.setDescription(entry.getValue().get(0));
            }
            else if(entry.getKey().equals("topic") && !entry.getValue().get(0).equals(""))
            {
                topic.setTopicId(Integer.parseInt(entry.getValue().get(0)));
                inquiry.setTopic(topic);
            }
            else if(entry.getKey().equals("attributeName"))
            {
                for(String s : entry.getValue())
                {
                    attribute = new AttributeOfInquiry();
                    attribute.setAttributeName(s);
                    attributeList.add(attribute);
                }
            }
            else if(entry.getKey().equals("attributeValue"))
            {
                for(String s : entry.getValue())
                {
                    for(AttributeOfInquiry a : attributeList)
                    {
                        if(a.getAttributeValue() == null)
                        {
                            a.setAttributeValue(s);
                            break;
                        }
                    }
                }
            }
        }
        Inquiry inquiryCreated = inquiryService.create(inquiry);
        for(AttributeOfInquiry a : attributeList)
        {
            a.setInquiry(inquiryCreated);
            attributeOfInquiryService.create(a);
        }


        return "redirect: /inquiryForm";
    }

    @RequestMapping("/inquiryForm")
    public ModelAndView inquiryForm() throws DaoException
    {
        ModelAndView mav = new ModelAndView("inquiryAdd");
        List<Topic> topicList = topicService.getTopicAll();
//        Map<Object, String> topicMap = new HashMap<>();
//        Map<String, String> attributeMap = new HashMap<>();
//        attributeMap.put("town", "Minsk");
//        form.setAttributeOfInquiryMap(attributeMap);
        /*for(Topic t : topicList)
        {
            topicMap.put(t.getTopicId(), t.getTopicName());
        }*/
        mav.addObject("topicList", topicList);
        mav.addObject("topic", new Topic());

        return mav;
    }

    @RequestMapping("/update/{inquiryId}/form")
    public ModelAndView updateInquiryForm(@PathVariable Integer inquiryId) throws DaoException
    {
        Inquiry inquiry = inquiryService.getInquiryById(inquiryId);
        HashMap<String, String> attributeMap = attributeOfInquiryService.getAttributeMapById(inquiryId);
        List<Topic> topicList = topicService.getTopicAll();
        ModelAndView mav = new ModelAndView("inquiryUpdateForm");
        mav.addObject("topicList", topicList);
        mav.addObject("inquiry", inquiry);
        mav.addObject("attributeMap", attributeMap);

        return mav;
    }

    @RequestMapping("/update/{inquiryId}")
    public ModelAndView updateInquiry(@PathVariable Integer inquiryId, @ModelAttribute Inquiry inquiry, BindingResult result,
                                      @RequestParam Map<String, String> attributeMap) throws DaoException
    {
        if(result.hasErrors())
        {

        }
        inquiryService.update(inquiry);
        List<AttributeOfInquiry> attributeList = attributeOfInquiryService.getAttributeAllById(inquiryId);
        for(Map.Entry<String, String> entry : attributeMap.entrySet())
        {
            if(entry.getKey().contains("attributeMap"))
            {
                for(AttributeOfInquiry a : attributeList)
                {
                    if(entry.getKey().contains(a.getAttributeName()))
                    {
//                        a.setAttributeName(entry.getKey());
                        a.setAttributeValue(entry.getValue());
                        attributeOfInquiryService.update(a);
                        attributeList.remove(a);
                        break;
                    }
                }
            }
        }


        ModelAndView mav = new ModelAndView(new RedirectView("redirect: update/{inquiryId}/form"));

        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
        binder.registerCustomEditor(Topic.class, new TopicEditor());
    }
}
