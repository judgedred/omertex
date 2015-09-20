package com.omertex.support.dao;


import com.omertex.support.domain.AttributeOfInquiry;
import com.omertex.support.domain.Inquiry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class AttributeOfInquiryDaoImpl implements AttributeOfInquiryDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    public AttributeOfInquiry create(AttributeOfInquiry attribute) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(attribute);
            session.flush();
            Integer lastId = ((BigInteger) session.createSQLQuery("Select last_insert_id()").uniqueResult()).intValue();
            attribute = (AttributeOfInquiry) session.load(AttributeOfInquiry.class, lastId);
            session.getTransaction().commit();
            return attribute;
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
    public void update(AttributeOfInquiry attribute) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(attribute);
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
    public void delete(AttributeOfInquiry attribute) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(attribute);
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
    public List<AttributeOfInquiry> getAttributeAll() throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            return (List<AttributeOfInquiry>) session.createCriteria(AttributeOfInquiry.class).list();
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
    public AttributeOfInquiry getAttributeById(int id) throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            AttributeOfInquiry attribute = (AttributeOfInquiry)session.get(AttributeOfInquiry.class, id);
            if(attribute != null)
            {
                return attribute;
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
