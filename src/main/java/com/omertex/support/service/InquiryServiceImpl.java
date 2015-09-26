package com.omertex.support.service;

import com.omertex.support.dao.AttributeOfInquiryDao;
import com.omertex.support.dao.DaoException;
import com.omertex.support.dao.InquiryDao;
import com.omertex.support.domain.AttributeOfInquiry;
import com.omertex.support.domain.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService
{
    @Autowired
    private InquiryDao inquiryDao;

    @Autowired
    private AttributeOfInquiryDao attributeOfInquiryDao;

    @Override
    public Inquiry create(Inquiry inquiry) throws DaoException
    {
        return inquiryDao.create(inquiry);
    }

    @Override
    public Inquiry update(Inquiry inquiry) throws DaoException
    {
        return inquiryDao.update(inquiry);
    }

    @Override
    public void delete(Inquiry inquiry) throws DaoException
    {
        List<AttributeOfInquiry> attributeList = attributeOfInquiryDao.getAttributeAll();
        if(attributeList != null)
        {
            for(AttributeOfInquiry a : attributeList)
            {
                if(a.getInquiry().equals(inquiry))
                {
                    attributeOfInquiryDao.delete(a);
                }
            }
        }
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
