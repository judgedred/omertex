package com.omertex.support.service;

import com.omertex.support.domain.Inquiry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Form
{
    private Inquiry inquiry;
    private Map<String, String> attributeOfInquiryMap;

    public Map<String, String> getAttributeOfInquiryMap()
    {
        return attributeOfInquiryMap;
    }

    public void setAttributeOfInquiryMap(Map<String, String> attributeOfInquiryMap)
    {
        this.attributeOfInquiryMap = attributeOfInquiryMap;
    }

    public Inquiry getInquiry()
    {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry)
    {
        this.inquiry = inquiry;
    }
}
