package com.omertex.support.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "AttributeOfInquiry")
public class AttributeOfInquiry
{
    @Id
    @Column(name ="attributeOfInquiry_id")
    @GeneratedValue
    private Integer attributeId;

    @Column(name = "attribute_name")
    private String attributeName;

    @Column(name = "attribute_value")
    private String attributeValue;

    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;


    public Integer getAttributeId()
    {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId)
    {
        this.attributeId = attributeId;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public void setAttributeName(String attributeName)
    {
        this.attributeName = attributeName;
    }

    public String getAttributeValue()
    {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue)
    {
        this.attributeValue = attributeValue;
    }

    public Inquiry getInquiry()
    {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry)
    {
        this.inquiry = inquiry;
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

        AttributeOfInquiry that = (AttributeOfInquiry) o;

        if(attributeId != null ? !attributeId.equals(that.attributeId) : that.attributeId != null)
        {
            return false;
        }
        if(attributeName != null ? !attributeName.equals(that.attributeName) : that.attributeName != null)
        {
            return false;
        }
        if(attributeValue != null ? !attributeValue.equals(that.attributeValue) : that.attributeValue != null)
        {
            return false;
        }
        return !(inquiry != null ? !inquiry.equals(that.inquiry) : that.inquiry != null);

    }

    @Override
    public int hashCode()
    {
        int result = attributeId != null ? attributeId.hashCode() : 0;
        result = 31 * result + (attributeName != null ? attributeName.hashCode() : 0);
        result = 31 * result + (attributeValue != null ? attributeValue.hashCode() : 0);
        result = 31 * result + (inquiry != null ? inquiry.hashCode() : 0);
        return result;
    }
}
