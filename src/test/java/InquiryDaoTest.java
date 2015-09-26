import com.omertex.support.dao.DaoException;
import com.omertex.support.dao.InquiryDao;
import com.omertex.support.dao.TopicDao;
import com.omertex.support.domain.Inquiry;
import com.omertex.support.domain.Topic;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/beans.xml")
public class InquiryDaoTest extends TestCase
{
    @Autowired
    private InquiryDao inquiryDao;

    @Autowired
    private TopicDao topicDao;

    @Test
    public void testGetInquiryById() throws DaoException
    {
        Inquiry inquiryTest = inquiryDao.getInquiryById(1);
        Assert.assertNotNull(inquiryTest);
    }

    @Test
    public void testCreate() throws DaoException
    {
        Inquiry inquiry = new Inquiry();
        inquiry.setDescription("testCreatePassed");
        inquiry.setCustomerName("testCreatePassed");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.SEPTEMBER, 21, 12, 00);
        inquiry.setCreateDate(cal.getTime());
        Topic topic = topicDao.getTopicAll().get(1);
        inquiry.setTopic(topic);
        String descriptionExpected = inquiry.getDescription();
        String customerNameExpected = inquiry.getCustomerName();
        String createDateExpected = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(inquiry.getCreateDate());
        Topic topicExpected = inquiry.getTopic();
        Inquiry inquiryTest = inquiryDao.create(inquiry);
        Assert.assertNotNull(inquiryTest);
        String descriptionResult = inquiryTest.getDescription();
        String customerNameResult = inquiryTest.getCustomerName();
        String createDateResult = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(inquiryTest.getCreateDate());
        Topic topicResult = inquiryTest.getTopic();
        Assert.assertEquals(descriptionExpected, descriptionResult);
        Assert.assertEquals(customerNameExpected, customerNameResult);
        Assert.assertEquals(createDateExpected, createDateResult);
        Assert.assertEquals(topicExpected, topicResult);
    }

    @Test
    public void testUpdate() throws DaoException
    {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryId(2);
        inquiry.setDescription("testUpdatePassed");
        inquiry.setCustomerName("testUpdatePassed");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.SEPTEMBER, 21, 12, 00);
        inquiry.setCreateDate(cal.getTime());
        Topic topic = topicDao.getTopicAll().get(3);
        inquiry.setTopic(topic);
        int idExpceted = inquiry.getInquiryId();
        String descriptionExpected = inquiry.getDescription();
        String customerNameExpected = inquiry.getCustomerName();
        String createDateExpected = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(inquiry.getCreateDate());
        Topic topicExpected = inquiry.getTopic();
        Inquiry inquiryTest = inquiryDao.update(inquiry);
        Assert.assertNotNull(inquiryTest);
        int idResult = inquiryTest.getInquiryId();
        String descriptionResult = inquiryTest.getDescription();
        String customerNameResult = inquiryTest.getCustomerName();
        String createDateResult = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(inquiryTest.getCreateDate());
        Topic topicResult = inquiryTest.getTopic();
        Assert.assertEquals(idExpceted, idResult);
        Assert.assertEquals(descriptionExpected, descriptionResult);
        Assert.assertEquals(customerNameExpected, customerNameResult);
        Assert.assertEquals(createDateExpected, createDateResult);
        Assert.assertEquals(topicExpected, topicResult);
    }

    @Test
    public void testDelete() throws DaoException
    {
        Inquiry inquiry = inquiryDao.getInquiryById(3);
        inquiryDao.delete(inquiry);
        Assert.assertNull(inquiryDao.getInquiryById(inquiry.getInquiryId()));
    }

    @Test
    public void testGetInquiryAll() throws DaoException
    {
        List<Inquiry> listInquiryTest = inquiryDao.getInquiryAll();
        Assert.assertNotNull(listInquiryTest);
        Assert.assertTrue(listInquiryTest.size() > 0);
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<Inquiry> lst = inquiryDao.getInquiryAll();
        for(Inquiry i : lst)
        {
            if(i.getDescription().equals("testCreatePassed") || i.getDescription().equals("testUpdatePassed"))
            {
                inquiryDao.delete(i);
            }
        }
    }



}
