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

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/inquiries")
    public ModelAndView home() throws DaoException
    {
        ModelAndView mav = new ModelAndView("inquiries");
        List<Topic> topicList = topicService.getTopicAll();
        mav.addObject("topicList", topicList);
        mav.addObject("inquiry", new Inquiry());
        mav.addObject("attribute", new AttributeOfInquiry());
        mav.addObject("inquiryList", inquiryService.getInquiryAll());
        return mav;
    }

    @RequestMapping(value = "customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.DELETE)
    public ModelAndView deleteInquiry(@PathVariable Integer inquiryId, String customerName, HttpServletResponse response) throws Exception
    {
        try
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
            response.setStatus(HttpServletResponse.SC_OK);
            return new ModelAndView(new RedirectView("/support/inquiries"));
        }
        catch(Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries", method = RequestMethod.POST)
    @ResponseBody
    public Inquiry addInquiry(@RequestBody Inquiry inquiry,
                                 BindingResult result,
                                 @RequestParam MultiValueMap<String, String> params) throws Exception
    {
        if(result.hasErrors())
        {

        }
        if(inquiry != null && !inquiry.getCustomerName().equals("") && !inquiry.getDescription().equals(""))
        {
            Inquiry inquiryCreated = inquiryService.create(inquiry);
            AttributeOfInquiry attribute;
            List<AttributeOfInquiry> attributeList = new LinkedList<>();
            for(Map.Entry<String, List<String>> entry : params.entrySet())
            {
                if(entry.getKey().equals("attributeName"))
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
                            a.setAttributeValue(s);
                            a.setInquiry(inquiryCreated);
                            attributeOfInquiryService.create(a);
                            attributeList.remove(a);
                            break;
                        }
                    }
                }
            }
        }

        return inquiry;
    }

    @RequestMapping("/inquiryAddForm/{customerName}")
    public ModelAndView inquiryForm(@PathVariable String customerName) throws DaoException
    {
        ModelAndView mav = new ModelAndView("inquiryAddForm");
        List<Topic> topicList = topicService.getTopicAll();
        Inquiry inquiry = new Inquiry();
        inquiry.setCustomerName(customerName);
        mav.addObject("topicList", topicList);
        mav.addObject("topic", new Topic());
        mav.addObject("inquiry", inquiry);

        return mav;
    }

    @RequestMapping("/update/{inquiryId}")
    public ModelAndView updateInquiry(@PathVariable Integer inquiryId, @ModelAttribute Inquiry inquiry, BindingResult result,
                                      @RequestParam Map<String, String> attributeMap) throws Exception
    {
        if(result.hasErrors())
        {

        }
        if(inquiry != null && inquiry.getCustomerName() != null && inquiry.getDescription() != null)
        {
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
                            a.setAttributeValue(entry.getValue());
                            attributeOfInquiryService.update(a);
                            attributeList.remove(a);
                            break;
                        }
                    }
                }
            }
        }
        inquiry = inquiryService.getInquiryById(inquiryId);
        attributeMap = attributeOfInquiryService.getAttributeMapById(inquiryId);
        List<Topic> topicList = topicService.getTopicAll();
        ModelAndView mav = new ModelAndView("inquiryUpdateForm");
        mav.addObject("topicList", topicList);
        mav.addObject("inquiry", inquiry);
        mav.addObject("attributeMap", attributeMap);

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
