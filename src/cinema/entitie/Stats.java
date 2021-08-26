package cinema.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    @JsonIgnore
    String password = "super_secret";
    @JsonProperty("current_income")
    int currentIncome;
    @JsonProperty("number_of_available_seats")
    int numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    int numberOfPurchasedTickets;

    public Stats() {

    }

    public Stats(Cinema cinema) {
        for (Ticket ticket : cinema.getSoldTickets()) {
            this.currentIncome += ticket.getSeat().getPrice();
        }
        this.numberOfAvailableSeats = cinema.getAvailableSeats().size() - cinema.getSoldTickets().size();
        this.numberOfPurchasedTickets = cinema.getSoldTickets().size();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome( int currentIncome ) {
        this.currentIncome = currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats( int numberOfAvailableSeats ) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets( int numberOfPurchasedTickets ) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }
}
