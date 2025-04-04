public class RateLimitedAPIFetcher implements APIFetcher{
    private APIFetcher fetcher;
    private static long throttle_duration=1000;
    private long lastCalled=0;

    public RateLimitedAPIFetcher(APIFetcher delegate){
        this.fetcher=delegate;
    }

    @Override
    public FetcherResponse getURL(String url){
        long currentTime=System.currentTimeMillis();
        if(currentTime-lastCalled>throttle_duration){
            lastCalled=currentTime;
            return fetcher.getURL(url);
        }
        
        return new FetcherResponse(FetcherResponseStatus.WAIT);
    }
}
