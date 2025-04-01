import java.util.HashMap;

public class CachedAPIFetcher implements APIFetcher{
    private static final long CACHE_DURATION=5*60*1000;
    //private static final long CACHE_DURATION=5*1000;

    private APIFetcher fetcher;
    private HashMap<String, CacheObject<String>>cache;

    public CachedAPIFetcher(APIFetcher delegate){
        this.fetcher=delegate;this.cache=new HashMap<>();
    }

    @Override
    public String getURL(String url){
        CacheObject<String>CachedObj=cache.get(url);

        if(CachedObj!=null&&CachedObj.isValid(CACHE_DURATION)){
            System.out.println("Cache hit!");
            return CachedObj.getValue();
        }

        System.out.println("Cache miss!");
        String response=fetcher.getURL(url);
        if(response!=null){
            cache.put(url, new CacheObject<String>(response));
            return response;
        }

        return null;
    }
}
