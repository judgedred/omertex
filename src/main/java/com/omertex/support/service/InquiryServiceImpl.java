package com.omertex.support.service;

import com.omertex.support.dao.DaoException;
import com.omertex.support.dao.InquiryDao;
import com.omertex.support.domain.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService
{
    @Autowired
    private InquiryDao inquiryDao;

    @Override
    public Inquiry create(Inquiry inquiry) throws DaoException
    {
        return inquiryDao.create(inquiry);
    }

    @Override
    public void update(Inquiry inquiry) throws DaoException
    {
        inquiryDao.update(inquiry);
    }

    @Override
    public void delete(Inquiry inquiry) throws DaoException
    {
        inquiryDao.delete(inquiry);
    }

    @Override
    public List<Inquiry> getInquiryAll() throws DaoException
    {
        return inquiryDao.getInquiryAll();
    }

    @Override
    public Inquiry getInquiryById(int id) throws DaoException
    {
        return inquiryDao.getInquiryById(id);
    }
}
