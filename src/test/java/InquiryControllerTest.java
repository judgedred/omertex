import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Inquiry;
import com.omertex.support.domain.Topic;
import com.omertex.support.service.AttributeOfInquiryService;
import com.omertex.support.service.InquiryService;
import com.omertex.support.service.TopicService;
import com.omertex.support.web.InquiryWrapper;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        inquiry.setDescription("тестJson");
        InquiryWrapper inquiryWrapped = new InquiryWrapper();
        inquiryWrapped.setInquiry(inquiry);
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
        Inquiry inquiryTest = mapper.readValue(br, Inquiry.class);
        Assert.assertNotNull(inquiryTest);

        String customerNameResult = inquiryTest.getCustomerName();
        Topic topicResult = inquiryTest.getTopic();
        String createDateResult = new SimpleDateFormat("yyyy-MM-dd").format(inquiryTest.getCreateDate());
        String descriptionResult = inquiryTest.getDescription();

        Assert.assertEquals(customerNameExpected, customerNameResult);
        Assert.assertEquals(topicExpected, topicResult);
        Assert.assertEquals(createDateExpected, createDateResult);
        Assert.assertEquals(descriptionExpected, descriptionResult);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();
    }
}
