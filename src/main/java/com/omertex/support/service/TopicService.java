package com.omertex.support.service;


import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Topic;

import java.util.List;

public interface TopicService
{
    public List<Topic> getTopicAll() throws DaoException;

}
