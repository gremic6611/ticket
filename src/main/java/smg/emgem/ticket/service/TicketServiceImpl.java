package smg.emgem.ticket.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smg.emgem.ticket.data.Ticket;
import smg.emgem.ticket.repo.TicketRepo;

/**
 * Ticket service implementation
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	Logger log = LoggerFactory.getLogger(TicketService.class);
	
	//are initialized from repo - see init()
	private int latestNumber = 0;
	private int firstActiveNumber = 0;
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Override
	public Ticket create() {
		Ticket newTicket = new Ticket();
		newTicket.setCreated(new Date());
		newTicket.setActive(latestNumber==firstActiveNumber);
		newTicket.setNumber(latestNumber+1);
		latestNumber++;
		
		ticketRepo.save(newTicket);
		log.info("Created:"+newTicket);
		
		return newTicket;
	}

	@Override
	public Ticket get(long id) {
		return ticketRepo.findById(id).orElse(null);
	}

	@Override
	public Ticket getActive() {
		return ticketRepo.findByActive(true);
	}

	@Override
	public List<Ticket> list() {
		return StreamSupport.stream(ticketRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Ticket deleteLatest() {
		Ticket latest = ticketRepo.findByNumber(latestNumber);
		ticketRepo.delete(latest);
		
		log.info("Deleted latest ticket:"+latest);
		
		return latest;
	}

	@Override
	public void update(Ticket ticket) {
		ticketRepo.save(ticket);
	}

	@Override
	public Ticket moveActive() {
		Ticket active = ticketRepo.findByActive(true);
		if (active==null)
			throw new RuntimeException("No active ticket");
		
		Ticket nextActive = ticketRepo.findByNumber(active.getNumber()+1);
		if (nextActive == null)
			throw new RuntimeException("Latest ticket must cannot me moved to inactive");
		
		active.setActive(false);
		nextActive.setActive(true);
		
		log.info("moving active ticket from: "+active +" to:"+nextActive);
		ticketRepo.save(active);
		ticketRepo.save(nextActive);
		
		return nextActive;
	}
	
	@PostConstruct
	private void init() {
		Ticket latest = ticketRepo.getLatestTicket();
		if (latest!=null) { //if null- DB is empty and default 0 is good
			this.latestNumber = latest.getNumber();
		}
		Ticket active = ticketRepo.findByActive(true);
		if (active!=null) { //if null- DB is empty and default 0 is good
			this.firstActiveNumber = active.getNumber();
		}
	}


}
