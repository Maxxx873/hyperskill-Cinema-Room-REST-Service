package cinema.controller;

import cinema.entitie.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
public class TicketController {
    Cinema cinema = new Cinema(9,9);

    @GetMapping("/seats")
    public Cinema getCinema() {
       return cinema;
   }

    @PostMapping("/purchase")
    public Ticket ticketStatus(@RequestBody Seat order) {

        if (order.getRow() <= 0 || order.getColumn() <= 0) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        if (order.getRow() > cinema.getTotalRows() || order.getColumn() > cinema.getTotalColumns()) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        }

        for (Seat seat : cinema.getAvailableSeats()) {
            if (order.getRow() == seat.getRow() && order.getColumn() == seat.getColumn()) {
                if (seat.isPurchased()) {
                    throw new SeatNotFoundException("The ticket has been already purchased!");
                } else {
                    seat.setPurchased(true);
                    Ticket ticket = new Ticket(seat);
                    cinema.getSoldTickets().add(ticket);
                    cinema.getSoldTokens().add(ticket.getToken());
                    return ticket;
                }
            }
        }
        return new Ticket();
    }

    @PostMapping("/return")
    public ReturnedTicket ticketReturn(@RequestBody Token token) {

        if (cinema.getSoldTokens().size() == 0 || cinema.getSoldTokens().indexOf(token) == 0) {
            throw new SeatNotFoundException("Wrong token!");
        }
        if (token.getToken() == null) {
            throw new SeatNotFoundException("Wrong token!");
        }
        if (token.getToken().equals("")) {
            throw new SeatNotFoundException("Wrong token!");
        }

        Iterator<Ticket> ticketIterator = cinema.getSoldTickets().iterator();
        while(ticketIterator.hasNext()) {
            Ticket nextTicket = ticketIterator.next();
            if (nextTicket.getUuid().equals(token.getToken())) {
                cinema.getReturnedTickets().add(nextTicket);
                ticketIterator.remove();
                cinema.getSoldTokens().remove(token.getToken());
            }
        }


        if(cinema.getReturnedTickets().size() == 0) {
            throw new SeatNotFoundException("Wrong token!");
        }
            ReturnedTicket returnedTicket = new ReturnedTicket(cinema.getReturnedTickets()
                    .get(cinema.getReturnedTickets().size() - 1));

    return returnedTicket;
    }

    @RequestMapping(value="/stats")
    public Stats cinemaStats(@RequestParam(required=false, value="password") String password) {
        if (password == null) {
            throw new SeatNotFoundException("The password is wrong!");
        }
        Stats stats = new Stats();
        if (!password.equals(stats.getPassword())) {
            throw new SeatNotFoundException("The password is wrong!");
        }
        stats = new Stats(cinema);
        return stats;
    }

    @ResponseStatus()
    class SeatNotFoundException extends RuntimeException {
        public SeatNotFoundException(String cause) {
            super(cause);
        }
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<Response> handleException(SeatNotFoundException e) {
      Response response = new Response(e.getMessage());
      HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
      if (e.getMessage().equals("The password is wrong!")) {
          httpStatus = HttpStatus.UNAUTHORIZED;
      }
      return new ResponseEntity<>(response, httpStatus);
    }

}



