import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{
    public static void main(String[] args) {
        //System.out.println("Hello World");

        String openWeatherAPIKey="ab1f35c03fe7cef3e679fa33d50fdd86";
        String lat="42.1255";
        String lon="-80.0843";

        HttpClient client=HttpClient.newHttpClient();

        //HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://scoutg.tech/final-project")).GET().build();

        //HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://forecast.weather.gov/MapClick.php?lat=42.1255&lon=-80.0843&unit=0&lg=english&FcstType=text&TextType=1")).GET().build();

        HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+openWeatherAPIKey)).GET().build();

        try{
            HttpResponse<String>response=client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code: "+response.statusCode());
            //System.out.println("Response body: "+response.body());

            Weather weather=new OpenWeatherMapAPIParser(response.body());

            System.out.println(weather.getTempurature(TempuratureUnit.FAHRENHEIT));
            System.out.println(weather.getCondition());
            //System.out.println(weatherData.toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}