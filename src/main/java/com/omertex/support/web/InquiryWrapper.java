package com.omertex.support.web;


import com.omertex.support.domain.Inquiry;

import java.util.Map;

public class InquiryWrapper
{
    private Inquiry inquiry;
    private Map<String, String> attributeMap;

    public Inquiry getInquiry()
    {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry)
    {
        this.inquiry = inquiry;
    }

    public Map<String, String> getAttributeMap()
    {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, String> attributeMap)
    {
        this.attributeMap = attributeMap;
    }
}
