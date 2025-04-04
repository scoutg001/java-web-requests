public class FetcherResponse {
    private String response;
    private FetcherResponseStatus status;
    private String statusCode;

    public FetcherResponse(String response){
        this.response=response;
        this.status=FetcherResponseStatus.OK;
    }

    public FetcherResponse(String response, String statusCode){
        this.response=response;
        this.status=FetcherResponseStatus.OK;
        this.statusCode=statusCode;
    }

    public FetcherResponse(String response, FetcherResponseStatus status){
        this.response=response;
        this.status=status;
    }

    public FetcherResponse(String response, FetcherResponseStatus status, String statusCode){
        this.response=response;
        this.status=status;
        this.statusCode=statusCode;
    }

    public FetcherResponse(FetcherResponseStatus status){
        this.status=status;
    }

    public FetcherResponse(FetcherResponseStatus status, String statusCode){
        this.status=status;
        this.statusCode=statusCode;
    }

    public FetcherResponse(FetcherResponse response, String statusCode){
        this.response=response.getResponse();
        this.status=response.getStatus();
        this.statusCode=response.getStatusCode()+"\n"+statusCode;
    }

    public String getResponse(){return this.response;}
    public FetcherResponseStatus getStatus(){return this.status;}
    public String getStatusCode(){return this.statusCode;}
}
