import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Main{
    private static final String WEATHER_API_URL="https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s";
    private static final long CACHE_DURATION=5*60*1000;
    //private static final long CACHE_DURATION=5*1000;
    private static final String CACHE_FILE="./cache/cache-%s-%s.json";
    private static final String CACHE_DIR="./cache/";
    private static APIFetcher fetcher=new SimpleAPIFetcher();
    public static void main(String[] args) {
        String openWeatherAPIKey="ab1f35c03fe7cef3e679fa33d50fdd86";
        String lat="42.1255";
        String lon="-80.0843";
        //String lon="80";

        String url=String.format(WEATHER_API_URL, lat, lon, openWeatherAPIKey);

        String json=fetcher.getURL(url);
    }

    private static void writeCache(String body, String lat, String lon){
        System.out.println("Saving cache to disk");
        try{
            Files.createDirectories(Paths.get(CACHE_DIR));
            FileWriter writer=new FileWriter(String.format(CACHE_FILE, lat, lon));
            writer.write(body);
            writer.flush();
            writer.close();
            System.out.println("Saved cache to disk");
        }catch(Exception e){
            System.out.println("Error while writing cache to disk:\n"+e.getMessage());
        }
    }

    private static String getCache(String lat, String lon){
        cleanup();
        System.out.println("Fetching cache from disk if it exists");
        try{
            File cacheFile=new File(String.format(CACHE_FILE, lat, lon));
            if(cacheFile.exists()){
                long lastModified=cacheFile.lastModified();
                long currentTime=System.currentTimeMillis();
                if(currentTime-lastModified<CACHE_DURATION){
                    System.out.println("Cache exists and is valid");
                    return Files.readString(Paths.get(String.format(CACHE_FILE, lat, lon)));
                }
            }
        }catch(Exception e){
            System.out.println("Error while getting cache:\n"+e.getMessage());
        }
        System.out.println("Cache does not exist or is not valid");
        return null;
    }

    private static void cleanup(){
        int randomNumber=new Random().nextInt(100);
        if(randomNumber<5){
            System.out.println("Cleaning house");
            File cacheDir=new File("./cache/");
            long cutoffTime=2*24*60*60*1000;
            //long cutoffTime=1000;
            File[]files=cacheDir.listFiles(
                file -> file.isFile()&&
                        file.getName().startsWith("cache")&&
                        file.getName().endsWith(".json")&&
                        System.currentTimeMillis()-file.lastModified()>cutoffTime
            );
            for(File file:files){
                System.out.println("Deleting: "+file.getName());
                file.delete();
            }
        }
    }
}