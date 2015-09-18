package com.omertex.support.service;

import com.omertex.support.dao.DaoException;
import com.omertex.support.dao.TopicDao;
import com.omertex.support.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService
{
    @Autowired
    private TopicDao topicDao;

    @Override
    @Transactional
    public List<Topic> getTopicAll() throws DaoException
    {
        try
        {
            return topicDao.getTopicAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
