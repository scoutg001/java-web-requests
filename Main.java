import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main{
    private static final long CACHE_DURATION=5*60*1000;
    private static final String CACHE_FILE="cache.json";
    public static void main(String[] args) {
        //System.out.println("Hello World");
        System.out.println(System.currentTimeMillis());

        String openWeatherAPIKey="ab1f35c03fe7cef3e679fa33d50fdd86";
        String lat="42.1255";
        String lon="-80.0843";

        HttpClient client=HttpClient.newHttpClient();

        //HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://scoutg.tech/final-project")).GET().build();

        //HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://forecast.weather.gov/MapClick.php?lat=42.1255&lon=-80.0843&unit=0&lg=english&FcstType=text&TextType=1")).GET().build();

        HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+openWeatherAPIKey)).GET().build();

        try{
            String body=getCache();

            if(body==null){
                HttpResponse<String>response=client.send(request, HttpResponse.BodyHandlers.ofString());

                if(response.statusCode()==200)
                    writeCache(response.body());

                System.out.println("Status code: "+response.statusCode());
                System.out.println("Response body: "+response.body());

                body=response.body();
            }

            Weather weather=new OpenWeatherMapAPIParser(body);

            System.out.println(weather.getTempurature(TempuratureUnit.FAHRENHEIT));
            System.out.println(weather.getCondition());
            //System.out.println(weatherData.toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void writeCache(String body){
        System.out.println("Saving cache to disk");
        try{
            FileWriter writer=new FileWriter(CACHE_FILE);
            writer.write(body);
            writer.flush();
            System.out.println("Saved cache to disk");
        }catch(Exception e){
            System.out.println("Error while writing cache to disk:\n"+e.getMessage());
        }
    }

    private static String getCache(){
        System.out.println("Fetching cache from disk if it exists");
        try{
            File cacheFile=new File(CACHE_FILE);
            if(cacheFile.exists()){
                long lastModified=cacheFile.lastModified();
                long currentTime=System.currentTimeMillis();
                if(currentTime-lastModified<CACHE_DURATION){
                    return Files.readString(Paths.get(CACHE_FILE));
                }
            }
        }catch(Exception e){
            System.out.println("Error while getting cache:\n"+e.getMessage());
        }
        return null;
    }
}