package cinema.entitie;

import java.util.UUID;

public class Token {
    UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public Token( UUID token ) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
