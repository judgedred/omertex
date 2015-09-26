package com.omertex.support.web;

import com.omertex.support.domain.AttributeOfInquiry;
import com.omertex.support.domain.Inquiry;
import com.omertex.support.service.AttributeOfInquiryService;
import com.omertex.support.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class InquiryController
{
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private AttributeOfInquiryService attributeOfInquiryService;


    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.DELETE)
    public void deleteInquiry(@PathVariable Integer inquiryId, HttpServletResponse response) throws Exception
    {
        try
        {
            Inquiry inquiry = inquiryService.getInquiryById(inquiryId);
            if(inquiry != null)
            {
                inquiryService.delete(inquiry);
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }
        catch(Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/customers/{customerName}/inquiries", method = RequestMethod.POST)
    public @ResponseBody InquiryWrapper addInquiry(@RequestBody InquiryWrapper inquiryWrapped) throws Exception
    {
        Inquiry inquiry = inquiryWrapped.getInquiry();
        if(inquiry != null && !inquiry.getCustomerName().equals("") && !inquiry.getDescription().equals("")
                && inquiry.getCreateDate() != null && inquiry.getTopic() != null)
        {
            Inquiry inquiryCreated = inquiryService.create(inquiry);
            Map<String, String> attributeMap = inquiryWrapped.getAttributeMap();
            if(attributeMap != null)
            {
                for(Map.Entry<String, String> entry : attributeMap.entrySet())
                {
                    AttributeOfInquiry attribute = new AttributeOfInquiry();
                    attribute.setInquiry(inquiryCreated);
                    attribute.setAttributeName(entry.getKey());
                    attribute.setAttributeValue(entry.getValue());
                    attributeOfInquiryService.create(attribute);
                }
                attributeMap = attributeOfInquiryService.getAttributeMapById(inquiryCreated.getInquiryId());
                inquiryWrapped.setAttributeMap(attributeMap);
            }
            inquiryWrapped.setInquiry(inquiryCreated);
            return inquiryWrapped;
        }
        else
        {
            return null;
        }
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.PUT)
    public @ResponseBody InquiryWrapper updateInquiry(@PathVariable Integer inquiryId,
                                               @RequestBody InquiryWrapper inquiryWrapped) throws Exception
    {
        Inquiry inquiry = inquiryWrapped.getInquiry();
        if(inquiry != null && inquiry.getInquiryId() != null && !inquiry.getCustomerName().equals("")
                && !inquiry.getDescription().equals("")  && inquiry.getCreateDate() != null && inquiry.getTopic() != null)
        {
            Inquiry inquiryUpdated = inquiryService.update(inquiry);
            Map<String, String> attributeMap = inquiryWrapped.getAttributeMap();
            List<AttributeOfInquiry> attributeList = attributeOfInquiryService.getAttributeAllById(inquiryId);
            if(attributeList != null && attributeMap != null)
            {
                for(Map.Entry<String, String> entry : attributeMap.entrySet())
                {
                    for(AttributeOfInquiry a : attributeList)
                    {
                        a.setAttributeName(entry.getKey());
                        a.setAttributeValue(entry.getValue());
                        attributeOfInquiryService.update(a);
                        attributeList.remove(a);
                        break;
                    }
                }
                attributeMap = attributeOfInquiryService.getAttributeMapById(inquiryUpdated.getInquiryId());
                inquiryWrapped.setAttributeMap(attributeMap);
            }
            inquiryWrapped.setInquiry(inquiryUpdated);
            return inquiryWrapped;
        }
        else
        {
            return null;
        }
    }

    @RequestMapping(value = "/customers/{customerName}/inquiries", method = RequestMethod.GET)
    public @ResponseBody List<Inquiry> getCustomerInquiries(@PathVariable String customerName) throws Exception
    {
        if(customerName != null)
        {
            List<Inquiry> inquiryList = inquiryService.getInquiryAll();
            List<Inquiry> filteredList = new ArrayList<>();

            for(Inquiry i : inquiryList)
            {
                if(i.getCustomerName().equals(customerName))
                {
                    filteredList.add(i);
                }
            }
            return filteredList;
        }
        else
        {
            return null;
        }
    }

    @RequestMapping(value =  "/customers/{customerName}/inquiries/{inquiryId}", method = RequestMethod.GET )
    public @ResponseBody InquiryWrapper getCustomerInquiry(@PathVariable int inquiryId) throws Exception
    {
        Inquiry inquiry = inquiryService.getInquiryById(inquiryId);
        if(inquiry != null)
        {
            InquiryWrapper inquiryWrapped = new InquiryWrapper();
            inquiryWrapped.setInquiry(inquiry);
            Map<String, String> attributeMap = attributeOfInquiryService.getAttributeMapById(inquiryId);
            if(attributeMap != null)
            {
                inquiryWrapped.setAttributeMap(attributeMap);
            }
            return inquiryWrapped;
        }
        else
        {
            return null;
        }
    }
}
