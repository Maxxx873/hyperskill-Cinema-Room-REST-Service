package cinema.entitie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {
    @JsonProperty("returned_ticket")
    Seat seat;

    public ReturnedTicket() {
    }

    public ReturnedTicket(Ticket ticket) {
        this.seat = ticket.getSeat();
    }
}
