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
    public Inquiry update(Inquiry inquiry) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(inquiry);
            session.flush();
            inquiry = (Inquiry) session.load(Inquiry.class, inquiry.getInquiryId());
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
    public void delete(Inquiry inquiry) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(inquiry);
            session.getTransaction().commit();
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
    @SuppressWarnings("unchecked")
    public List<Inquiry> getInquiryAll() throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            return (List<Inquiry>) session.createCriteria(Inquiry.class).list();
        }
        catch(Exception e)
        {
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
    public Inquiry getInquiryById(int id) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            Inquiry inquiry = (Inquiry)session.get(Inquiry.class, id);
            if(inquiry != null)
            {
                return inquiry;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
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
}
