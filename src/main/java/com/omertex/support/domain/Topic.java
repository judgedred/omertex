package com.omertex.support.domain;

import javax.persistence.*;

@Entity
@Table(name = "Topic")
public class Topic
{
    @Id
    @Column(name = "topic_id")
    @GeneratedValue
    private Integer topicId;

    @Column(name = "topic_name")
    private String topicName;

    public Integer getTopicId()
    {
        return topicId;
    }

    public void setTopicId(Integer topicId)
    {
        this.topicId = topicId;
    }

    public String getTopicName()
    {
        return topicName;
    }

    public void setTopicName(String topicName)
    {
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        Topic topic = (Topic) o;

        if(!topicId.equals(topic.topicId))
        {
            return false;
        }
        return topicName.equals(topic.topicName);

    }

    @Override
    public int hashCode()
    {
        return topicId.hashCode();
    }
}
