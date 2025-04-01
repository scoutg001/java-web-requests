public class CacheObject<T> {
    private T value;
    private long timestamp;

    public CacheObject(T value){
        this.value=value;this.timestamp=System.currentTimeMillis();
    }

    public CacheObject(T value, long timestamp){
        this.value=value;this.timestamp=timestamp;
    }

    public T getValue(){return this.value;}
    public long getTimestamp(){return this.timestamp;}

    public boolean isValid(long cacheDuration){
        long currentTime=System.currentTimeMillis();
        return currentTime-this.timestamp <=cacheDuration;
    }

    public boolean isExpired(long cacheDuration){
        return !isValid(cacheDuration);
    }
}
