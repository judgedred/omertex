package com.omertex.support.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Inquiry")
public class Inquiry
{
    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue
    private Integer inquiryId;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "customer_name")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Integer getInquiryId()
    {
        return inquiryId;
    }

    public void setInquiryId(Integer inquiryId)
    {
        this.inquiryId = inquiryId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public Topic getTopic()
    {
        return topic;
    }

    public void setTopic(Topic topic)
    {
        this.topic = topic;
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

        Inquiry inquiry = (Inquiry) o;

        if(!inquiryId.equals(inquiry.inquiryId))
        {
            return false;
        }
        if(!description.equals(inquiry.description))
        {
            return false;
        }
        if(!createDate.equals(inquiry.createDate))
        {
            return false;
        }
        if(!customerName.equals(inquiry.customerName))
        {
            return false;
        }
        return topic.equals(inquiry.topic);

    }

    @Override
    public int hashCode()
    {
        return inquiryId.hashCode();
    }
}
