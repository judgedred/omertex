import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class TopicControllerTest
{
    public static void main(String[] args) throws IOException
    {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build())
        {
            HttpGet request = new HttpGet("http://localhost:8080/support/topics");
            HttpResponse response = httpClient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while((line = rd.readLine()) != null)
            {
                System.out.println(response.getStatusLine());
                System.out.println(line);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
