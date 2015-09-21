import com.omertex.support.dao.AttributeOfInquiryDao;
import com.omertex.support.dao.DaoException;
import com.omertex.support.dao.InquiryDao;
import com.omertex.support.domain.AttributeOfInquiry;
import com.omertex.support.domain.Inquiry;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/beans.xml")
public class AttributeOfInquiryDaoTest extends TestCase
{
    @Autowired
    private AttributeOfInquiryDao attributeOfInquiryDao;

    @Autowired
    private InquiryDao inquiryDao;

    @Test
    public void testGetAttributeById() throws DaoException
    {
        AttributeOfInquiry attributeTest = attributeOfInquiryDao.getAttributeById(1);
        Assert.assertNotNull(attributeTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        AttributeOfInquiry attribute = new AttributeOfInquiry();
        attribute.setAttributeName("testCreatePassed");
        attribute.setAttributeValue("testCreatePassed");
        Inquiry inquiry = inquiryDao.getInquiryById(1);
        attribute.setInquiry(inquiry);
        String attributeNameExpected = attribute.getAttributeName();
        String attributeValueExpected = attribute.getAttributeValue();
        Inquiry inquiryExpected = attribute.getInquiry();
        AttributeOfInquiry attributeTest = attributeOfInquiryDao.create(attribute);
        Assert.assertNotNull(attributeTest);
        String attributeNameResult = attributeTest.getAttributeName();
        String attributeValueResult = attributeTest.getAttributeValue();
        Inquiry inquiryResult = attributeTest.getInquiry();
        Assert.assertEquals(attributeNameExpected, attributeNameResult);
        Assert.assertEquals(attributeValueExpected, attributeValueResult);
        Assert.assertEquals(inquiryExpected, inquiryResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        AttributeOfInquiry attribute = new AttributeOfInquiry();
        attribute.setAttributeId(2);
        attribute.setAttributeName("testUpdatePassed");
        attribute.setAttributeValue("testUpdatePassed");
        Inquiry inquiry = inquiryDao.getInquiryById(1);
        attribute.setInquiry(inquiry);
        String attributeNameExpected = attribute.getAttributeName();
        String attributeValueExpected = attribute.getAttributeValue();
        Inquiry inquiryExpected = attribute.getInquiry();
        attributeOfInquiryDao.update(attribute);
        AttributeOfInquiry attributeTest = attributeOfInquiryDao.getAttributeById(attribute.getAttributeId());
        Assert.assertNotNull(attributeTest);
        String attributeNameResult = attributeTest.getAttributeName();
        String attributeValueResult = attributeTest.getAttributeValue();
        Inquiry inquiryResult = attributeTest.getInquiry();
        Assert.assertEquals(attributeNameExpected, attributeNameResult);
        Assert.assertEquals(attributeValueExpected, attributeValueResult);
        Assert.assertEquals(inquiryExpected, inquiryResult);
    }

    @Test
    public void testDelete() throws DaoException
    {
        AttributeOfInquiry attribute = attributeOfInquiryDao.getAttributeById(3);
        attributeOfInquiryDao.delete(attribute);
        Assert.assertNull(attributeOfInquiryDao.getAttributeById(attribute.getAttributeId()));
    }

    @Test
    public void testGetInquiryAll() throws DaoException
    {
        List<AttributeOfInquiry> listAttributeTest = attributeOfInquiryDao.getAttributeAll();
        Assert.assertNotNull(listAttributeTest);
        Assert.assertTrue(listAttributeTest.size() > 0);
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<AttributeOfInquiry> lst = attributeOfInquiryDao.getAttributeAll();
        for(AttributeOfInquiry a : lst)
        {
            if(a.getAttributeName().equals("testCreatePassed") || a.getAttributeName().equals("testUpdatePassed"))
            {
                attributeOfInquiryDao.delete(a);
            }
        }
    }
}
