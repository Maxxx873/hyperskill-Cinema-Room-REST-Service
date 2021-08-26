package cinema.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    @JsonIgnore
    Token token;
    @JsonProperty("token")
    UUID uuid;
    @JsonProperty("ticket")
    Seat seat;
    @JsonIgnore
    boolean available;

    public Ticket() {
    }

    public Ticket(Seat seat) {
        this.seat = seat;
        this.token = new Token();
        this.uuid = this.token.getToken();
        this.available = false;
    }

    public Token getToken() {
        return token;
    }

    public void setToken( Token token ) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat( Seat seat ) {
        this.seat = seat;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable( boolean available ) {
        this.available = available;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
