package com.omertex.support.service;


import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.AttributeOfInquiry;
import java.util.List;
import java.util.Map;


public interface AttributeOfInquiryService
{
    public AttributeOfInquiry create(AttributeOfInquiry attribute) throws DaoException;
    public void update(AttributeOfInquiry attribute) throws DaoException;
    public void delete(AttributeOfInquiry attribute) throws DaoException;
    public Map<String, String> getInquiryAttributeMap(int inquiryId) throws DaoException;
    public List<AttributeOfInquiry> getInquiryAttributeAll(int inquiryId) throws DaoException;
}
