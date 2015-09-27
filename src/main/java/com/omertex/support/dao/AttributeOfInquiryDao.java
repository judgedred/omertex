package com.omertex.support.dao;

import com.omertex.support.domain.AttributeOfInquiry;

import java.util.List;

public interface AttributeOfInquiryDao
{
    public AttributeOfInquiry create(AttributeOfInquiry attribute) throws DaoException;
    public void update(AttributeOfInquiry attribute) throws DaoException;
    public void delete(AttributeOfInquiry attribute) throws DaoException;
    public List<AttributeOfInquiry> getAttributeAll() throws DaoException;
    public AttributeOfInquiry getAttributeById(int id) throws DaoException;
}
