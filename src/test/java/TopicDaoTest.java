
import com.omertex.support.dao.TopicDao;
import com.omertex.support.domain.Topic;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/beans.xml")
public class TopicDaoTest extends TestCase
{
    @Autowired
    private TopicDao topicDao;

    @Test
    public void testGetTopicAll() throws Exception
    {
        List<Topic> topicListTest = topicDao.getTopicAll();
        Assert.assertNotNull(topicListTest);
        Assert.assertTrue(topicListTest.size() > 0);
    }
}