import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleAPIFetcher implements APIFetcher{
    @Override
    public String getURL(String url){
        HttpClient client=HttpClient.newHttpClient();

        HttpRequest request=HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        try{
            HttpResponse<String>response=client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200){
                return response.body();
            }
        }catch(Exception e){
            System.err.println("Error fetching weather data: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
}
