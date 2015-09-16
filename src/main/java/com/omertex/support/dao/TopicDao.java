package com.omertex.support.dao;

import com.omertex.support.domain.Topic;

import java.util.List;

public interface TopicDao
{
    public Topic create(Topic topic) throws DaoException;
    public void update(Topic topic) throws DaoException;
    public void delete(Topic topic) throws DaoException;
    public List<Topic> getTopicAll() throws DaoException;
    public Topic getTopicById(int id) throws DaoException;
}
