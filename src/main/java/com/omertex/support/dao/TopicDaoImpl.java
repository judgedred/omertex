package com.omertex.support.dao;

import com.omertex.support.domain.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getTopicAll() throws DaoException
    {
        try
        {
            session = sessionFactory.openSession();
            return (List<Topic>) session.createCriteria(Topic.class).list();
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
