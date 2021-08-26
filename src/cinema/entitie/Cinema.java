package cinema.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    @JsonProperty("total_rows")
    int totalRows;
    @JsonProperty("total_columns")
    int totalColumns;
    @JsonProperty("available_seats")
    private
    List<Seat> availableSeats = new ArrayList<>();
    @JsonIgnore
    private
    ArrayList<Ticket> soldTickets = new ArrayList<>();
    @JsonIgnore
    private
    ArrayList<Token> soldTokens = new ArrayList<>();
    @JsonIgnore
    private
    ArrayList<Ticket> returnedTickets = new ArrayList<>();


    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j, i <= 4 ? 10 : 8));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows( int totalRows ) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns( int totalColumns ) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats( List<Seat> availableSeats ) {
        this.availableSeats = availableSeats;
    }

    public ArrayList<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets( ArrayList<Ticket> soldTickets ) {
        this.soldTickets = soldTickets;
    }

    public ArrayList<Token> getSoldTokens() {
        return soldTokens;
    }

    public void setSoldTokens(ArrayList<Token> soldTokens ) {
        this.soldTokens = soldTokens;
    }

    public ArrayList<Ticket> getReturnedTickets() {
        return returnedTickets;
    }

    public void setReturnedTickets(ArrayList<Ticket> returnedTickets ) {
        this.returnedTickets = returnedTickets;
    }

}
