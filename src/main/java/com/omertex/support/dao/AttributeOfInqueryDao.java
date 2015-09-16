package com.omertex.support.dao;

import com.omertex.support.domain.AttributeOfInquiry;
import java.util.List;

public interface AttributeOfInqueryDao
{
    public AttributeOfInquiry create(AttributeOfInquiry attributeOfInquiry) throws DaoException;
    public void update(AttributeOfInquiry attributeOfInquiry) throws DaoException;
    public void delete(AttributeOfInquiry attributeOfInquiry) throws DaoException;
    public List<AttributeOfInquiry> getAttributeOfInqueryAll() throws DaoException;
    public AttributeOfInquiry getAttributeOfInqueryById(int id) throws DaoException;
}
