import java.util.HashMap;

public class LoggedAPIFetcher implements APIFetcher{
    private APIFetcher fetcher;
    private HashMap<String, Integer>cache;

    public LoggedAPIFetcher(APIFetcher delegate){
        this.fetcher=delegate;this.cache=new HashMap<>();
    }

    @Override
    public FetcherResponse getURL(String url){
        String statusCode;
        if(cache.containsKey(url)){
            cache.replace(url, (cache.get(url)+1));
            statusCode="URL used "+cache.get(url)+" times: "+url;
        }else{
            statusCode="URL used 1 time: "+url;
        }

        FetcherResponse response=fetcher.getURL(url);
        if(response!=null){
            cache.put(url, 1);
            return new FetcherResponse(response, statusCode);
        }

        return new FetcherResponse(FetcherResponseStatus.ERROR, "Unknown internal error");
    }
}
