import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Topic;
import com.omertex.support.service.TopicService;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/beans.xml")
public class TopicControllerTest extends TestCase
{
    @Autowired
    private TopicService topicService;

    @Test
    public void listTopicsTest() throws IOException, DaoException
    {
        List<Topic> topicList = topicService.getTopicAll();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/support/topics");
        HttpResponse response = httpClient.execute(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        ObjectMapper mapper = new ObjectMapper();
        List<Topic> topicListTest = mapper.readValue(br, mapper.getTypeFactory().constructCollectionType(List.class, Topic.class));
        Assert.assertNotNull(topicListTest);
        Assert.assertEquals(topicList, topicListTest);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        httpClient.close();
    }
}
