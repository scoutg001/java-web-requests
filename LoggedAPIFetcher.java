import java.util.HashMap;

public class LoggedAPIFetcher implements APIFetcher{
    private APIFetcher fetcher;
    private HashMap<String, Integer>cache;

    public LoggedAPIFetcher(APIFetcher delegate){
        this.fetcher=delegate;this.cache=new HashMap<>();
    }

    @Override
    public String getURL(String url){
        if(cache.containsKey(url)){
            cache.replace(url, (cache.get(url)+1));
            System.out.println("URL used "+cache.get(url)+" times: "+url);
        }else{
            System.out.println("URL used 1 time: "+url);
        }

        String response=fetcher.getURL(url);
        if(response!=null){
            cache.put(url, 1);
            return response;
        }

        return null;
    }
}
