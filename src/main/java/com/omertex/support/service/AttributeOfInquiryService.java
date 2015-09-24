package com.omertex.support.service;


import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.AttributeOfInquiry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface AttributeOfInquiryService
{
    public AttributeOfInquiry create(AttributeOfInquiry attribute) throws DaoException;
    public void update(AttributeOfInquiry attribute) throws DaoException;
    public void delete(AttributeOfInquiry attribute) throws DaoException;
    public List<AttributeOfInquiry> getAttributeAll() throws DaoException;
    public Map<String, String> getAttributeMapById(int inquiryId) throws DaoException;
    public List<AttributeOfInquiry> getAttributeAllById(int inquiryId) throws DaoException;
}
