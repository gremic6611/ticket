package smg.emgem.ticket.service;

import java.util.List;

import smg.emgem.ticket.data.Ticket;

/**
 * Service for basic operation for Ticket entity
 * @author Michal Grega
 *
 */
public interface TicketService {

	public List<Ticket> list();
	
	public Ticket create();
	
	public Ticket deleteLatest();
	
	public Ticket get(long id);
	
	public Ticket getActive();
	
	public void update(Ticket ticket);
	
	public Ticket moveActive();
	
	
}
