package cinema.entitie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("error")
    private String message;

    public Response() {
    }

    public Response( String message ) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
}
