package com.omertex.support.service;


import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Inquiry;

import java.util.List;

public interface InquiryService
{
    public Inquiry create(Inquiry inquiry) throws DaoException;
    public void update(Inquiry inquiry) throws DaoException;
    public void delete(Inquiry inquiry) throws DaoException;
    public List<Inquiry> getInquiryAll() throws DaoException;
    public Inquiry getInquiryById(int id) throws DaoException;
}
