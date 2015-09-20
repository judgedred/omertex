package com.omertex.support.service;


import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.AttributeOfInquiry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AttributeOfInquiryService
{
    public AttributeOfInquiry create(AttributeOfInquiry attribute) throws DaoException;
    public void update(AttributeOfInquiry attribute) throws DaoException;
    public void delete(AttributeOfInquiry attribute) throws DaoException;
    public List<AttributeOfInquiry> getAttributeAll() throws DaoException;
    public AttributeOfInquiry getAttributeById(int id) throws DaoException;
    public Map<String, String> getAttributeOfInquiryById(int inquiryId) throws DaoException;
}
