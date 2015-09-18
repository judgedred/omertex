package com.omertex.support.dao;

import com.omertex.support.domain.Topic;

import java.util.List;

public interface TopicDao
{
    public List<Topic> getTopicAll() throws DaoException;
}
