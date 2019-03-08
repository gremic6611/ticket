package smg.emgem.ticket.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import smg.emgem.ticket.data.Ticket;

/**
 * by default in memory DB
 */
public interface TicketRepo extends CrudRepository<Ticket, Long> {

	@Query("SELECT t FROM Ticket t where t.created = (SELECT MAX(t1.created) FROM Ticket t1)")
	public Ticket getLatestTicket();
	
	public Ticket findByNumber(int number);
	
	public Ticket findByActive(boolean active);
	
}
