package smg.emgem.ticket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import smg.emgem.ticket.data.Ticket;
import smg.emgem.ticket.service.TicketService;


/**
 * Controller to provide basic RESTfull resource operations for Ticket entity
 */

@RestController
public class TicketController {

	 	@Autowired
	    private TicketService ticketService;
	
	    Logger log = LoggerFactory.getLogger(TicketController.class);
	    
	    @RequestMapping(value = "/ticket/list")
	    public ResponseEntity<List<Ticket>> listTickets() {
	        log.info("List tickets called");
	        return new ResponseEntity<>(ticketService.list(), HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
	    public ResponseEntity<Ticket> getNextTicket() {
     	    log.info("getting ticket with next number");
     	    Ticket result = ticketService.create();
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }

	    @RequestMapping(value = "/ticket", method = RequestMethod.DELETE)
	    public ResponseEntity<Ticket> deleteLatestTicket() {
	        log.info("deleting latest ticket");
	        Ticket removed = ticketService.deleteLatest();
	        return new ResponseEntity<>(removed, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/ticket/active", method = RequestMethod.GET)
	    public ResponseEntity<Ticket> getActiveTicket() {
	        return new ResponseEntity<>(ticketService.getActive(), HttpStatus.OK);
	    }

	    @RequestMapping(value = "/ticket/active/next", method = RequestMethod.GET)
	    public ResponseEntity<Ticket> moveToNextActiveTicket() {
	        Ticket newActive = ticketService.moveActive();
	        return new ResponseEntity<>(newActive, HttpStatus.OK);
	    }


	    
}
