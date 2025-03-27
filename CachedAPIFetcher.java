import java.util.HashMap;

public class CachedAPIFetcher implements APIFetcher{
    private APIFetcher fetcher;
    private HashMap<String, String>cache;

    public CachedAPIFetcher(APIFetcher delegate){
        this.fetcher=delegate;
    }

    @Override
    public String getURL(String url){
        if(cache.containsKey(url)){
            System.out.println("Cache hit!");
            return cache.get(url);
        }

        System.out.println("Cache miss!");
        String response=fetcher.getURL(url);
        if(response!=null){
            cache.put(url, response);
            return response;
        }

        return null;
    }
}
