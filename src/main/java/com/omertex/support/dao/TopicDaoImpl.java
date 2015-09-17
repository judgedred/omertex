package com.omertex.support.dao;

import com.omertex.support.domain.Topic;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getTopicAll()
    {
        return (List<Topic>) sessionFactory.getCurrentSession().createCriteria(Topic.class).list();
    }

}
