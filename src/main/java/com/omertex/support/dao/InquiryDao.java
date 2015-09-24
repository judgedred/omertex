package com.omertex.support.dao;

import com.omertex.support.domain.Inquiry;
import java.util.List;


public interface InquiryDao
{
    public Inquiry create(Inquiry inquiry) throws DaoException;
    public Inquiry update(Inquiry inquiry) throws DaoException;
    public void delete(Inquiry inquiry) throws DaoException;
    public List<Inquiry> getInquiryAll() throws DaoException;
    public Inquiry getInquiryById(int id) throws DaoException;
}
