import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Inquiry;
import com.omertex.support.domain.Topic;
import com.omertex.support.service.AttributeOfInquiryService;
import com.omertex.support.service.InquiryService;
import com.omertex.support.service.TopicService;
import com.omertex.support.web.InquiryWrapper;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/beans.xml")
public class InquiryControllerTest extends TestCase
{
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private AttributeOfInquiryService attributeOfInquiryService;

    @Autowired
    private TopicService topicService;

    @Test
    public void addInquiryTest() throws IOException, DaoException
    {
        Inquiry inquiry = new Inquiry();
        inquiry.setCustomerName("testCreateJson");
        Topic topic = topicService.getTopicAll().get(3);
        inquiry.setTopic(topic);
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.SEPTEMBER, 25);
        inquiry.setCreateDate(cal.getTime());
        inquiry.setDescription("testCreateJson");
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put("Город", "Минск");
        attributeMap.put("Телефон", "123");
        InquiryWrapper inquiryWrapped = new InquiryWrapper();
        inquiryWrapped.setInquiry(inquiry);
        inquiryWrapped.setAttributeMap(attributeMap);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String inquiryJson = ow.writeValueAsString(inquiryWrapped);
        StringEntity stringEntity = new StringEntity(inquiryJson, "UTF-8");
        stringEntity.setContentType("application/json");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8080/support/customers/tesCreateJson/inquiries");
        request.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(request);
        String customerNameExpected = inquiry.getCustomerName();
        Topic topicExpected = inquiry.getTopic();
        String createDateExpected = new SimpleDateFormat("yyyy-MM-dd").format(inquiry.getCreateDate());
        String descriptionExpected = inquiry.getDescription();
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        ObjectMapper mapper = new ObjectMapper();
        InquiryWrapper inquiryWrappedTest = mapper.readValue(br, InquiryWrapper.class);
        Assert.assertNotNull(inquiryWrappedTest);
        Inquiry inquiryTest = inquiryWrappedTest.getInquiry();
        Map<String, String> attributeMapTest = inquiryWrappedTest.getAttributeMap();
        String customerNameResult = inquiryTest.getCustomerName();
        Topic topicResult = inquiryTest.getTopic();
        String createDateResult = new SimpleDateFormat("yyyy-MM-dd").format(inquiryTest.getCreateDate());
        String descriptionResult = inquiryTest.getDescription();
        Assert.assertEquals(customerNameExpected, customerNameResult);
        Assert.assertEquals(topicExpected, topicResult);
        Assert.assertEquals(createDateExpected, createDateResult);
        Assert.assertEquals(descriptionExpected, descriptionResult);
        Assert.assertEquals(attributeMap, attributeMapTest);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();
    }

    @Test
    public void updateInquiryTest() throws IOException, DaoException
    {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryId(4);
        inquiry.setCustomerName("testUpdateJson");
        Topic topic = topicService.getTopicAll().get(3);
        inquiry.setTopic(topic);
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.SEPTEMBER, 25);
        inquiry.setCreateDate(cal.getTime());
        inquiry.setDescription("testUpdateJson");
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put("Город", "Минск");
        attributeMap.put("Телефон", "123");
        InquiryWrapper inquiryWrapped = new InquiryWrapper();
        inquiryWrapped.setInquiry(inquiry);
        inquiryWrapped.setAttributeMap(attributeMap);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String inquiryJson = ow.writeValueAsString(inquiryWrapped);
        StringEntity stringEntity = new StringEntity(inquiryJson, "UTF-8");
        stringEntity.setContentType("application/json");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut request = new HttpPut("http://localhost:8080/support/customers/testUpdateJson/inquiries/4");
        request.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(request);
        int idExpected = inquiry.getInquiryId();
        String customerNameExpected = inquiry.getCustomerName();
        Topic topicExpected = inquiry.getTopic();
        String createDateExpected = new SimpleDateFormat("yyyy-MM-dd").format(inquiry.getCreateDate());
        String descriptionExpected = inquiry.getDescription();
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        ObjectMapper mapper = new ObjectMapper();
        InquiryWrapper inquiryWrappedTest = mapper.readValue(br, InquiryWrapper.class);
        Assert.assertNotNull(inquiryWrappedTest);
        Inquiry inquiryTest = inquiryWrappedTest.getInquiry();
        Map<String, String> attributeMapTest = inquiryWrappedTest.getAttributeMap();
        int idResult = inquiryTest.getInquiryId();
        String customerNameResult = inquiryTest.getCustomerName();
        Topic topicResult = inquiryTest.getTopic();
        String createDateResult = new SimpleDateFormat("yyyy-MM-dd").format(inquiryTest.getCreateDate());
        String descriptionResult = inquiryTest.getDescription();
        Assert.assertEquals(idExpected, idResult);
        Assert.assertEquals(customerNameExpected, customerNameResult);
        Assert.assertEquals(topicExpected, topicResult);
        Assert.assertEquals(createDateExpected, createDateResult);
        Assert.assertEquals(descriptionExpected, descriptionResult);
        Assert.assertEquals(attributeMap, attributeMapTest);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();
    }

    @Test
    public void deleteInquiry() throws IOException, DaoException
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete("http://localhost:8080/support/customers/Тест/inquiries/5");
        HttpResponse response = httpClient.execute(request);
        Assert.assertNull(inquiryService.getInquiryById(5));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void getCustomerInquiriesTest() throws IOException, DaoException
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/support/customers/Олег/inquiries");
        HttpResponse response = httpClient.execute(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        ObjectMapper mapper = new ObjectMapper();
        List<Inquiry> inquiryListTest = mapper.readValue(br, mapper.getTypeFactory().constructCollectionType(List.class, Inquiry.class));
        Assert.assertNotNull(inquiryListTest);
        Assert.assertTrue(inquiryListTest.size() > 0);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();
    }

    @Test
    public void getCustomerInquiryTest() throws IOException, DaoException
    {
        Inquiry inquiry = inquiryService.getInquiryById(1);
        Map<String, String> attributeMap = attributeOfInquiryService.getAttributeMapById(inquiry.getInquiryId());
        int idExpected = inquiry.getInquiryId();
        String customerNameExpected = inquiry.getCustomerName();
        Topic topicExpected = inquiry.getTopic();
        String createDateExpected = new SimpleDateFormat("yyyy-MM-dd").format(inquiry.getCreateDate());
        String descriptionExpected = inquiry.getDescription();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/support/customers/Тест/inquiries/1");
        HttpResponse response = httpClient.execute(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        ObjectMapper mapper = new ObjectMapper();
        InquiryWrapper inquiryWrappedTest = mapper.readValue(br, InquiryWrapper.class);
        Assert.assertNotNull(inquiryWrappedTest);
        Inquiry inquiryTest = inquiryWrappedTest.getInquiry();
        Map<String, String> attributeMapTest = inquiryWrappedTest.getAttributeMap();
        int idResult = inquiryTest.getInquiryId();
        String customerNameResult = inquiryTest.getCustomerName();
        Topic topicResult = inquiryTest.getTopic();
        String createDateResult = new SimpleDateFormat("yyyy-MM-dd").format(inquiryTest.getCreateDate());
        String descriptionResult = inquiryTest.getDescription();
        Assert.assertEquals(idExpected, idResult);
        Assert.assertEquals(customerNameExpected, customerNameResult);
        Assert.assertEquals(topicExpected, topicResult);
        Assert.assertEquals(createDateExpected, createDateResult);
        Assert.assertEquals(descriptionExpected, descriptionResult);
        Assert.assertEquals(attributeMap, attributeMapTest);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();
    }

    @After
    public void cleanUp() throws DaoException
    {
        List<Inquiry> lst = inquiryService.getInquiryAll();
        for(Inquiry i : lst)
        {
            if(i.getDescription().equals("testCreateJson") || i.getDescription().equals("testUpdateJson"))
            {
                inquiryService.delete(i);
            }
        }
    }}
