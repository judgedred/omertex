package com.omertex.support.dao;

import com.omertex.support.domain.Inquiry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class InquiryDaoImpl implements InquiryDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    public Inquiry create(Inquiry inquiry) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(inquiry);
            session.flush();
            Integer lastId = ((BigInteger) session.createSQLQuery("Select last_insert_id()").uniqueResult()).intValue();
            inquiry = (Inquiry) session.load(Inquiry.class, lastId);
            session.getTransaction().commit();
            return inquiry;
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            throw new DaoException(e);
        }
        finally
        {
            if(session != null && session.isOpen())
            {
                session.close();
            }
        }
    }

    @Override
    public void update(Inquiry inquiry) throws DaoException
    {

    }

    @Override
    public void delete(Inquiry inquiry) throws DaoException
    {

    }

    @Override
    public List<Inquiry> getInquiryAll() throws DaoException
    {
       return null;
    }

    @Override
    public Inquiry getInquiryById(int id) throws DaoException
    {
        return null;
    }
}
