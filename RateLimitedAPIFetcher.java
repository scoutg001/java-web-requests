public class RateLimitedAPIFetcher implements APIFetcher{
    private APIFetcher fetcher;
    private static long throttle_duration=1000;
    private long lastCalled=0;

    public RateLimitedAPIFetcher(APIFetcher delegate){
        this.fetcher=delegate;
    }

    @Override
    public String getURL(String url){
        long currentTime=System.currentTimeMillis();
        if(currentTime-lastCalled>throttle_duration){
            return fetcher.getURL(url);
        }
        //wait((currentTime-lastCalled)-throttle_duration);
        return null;
    }
}
