package ma.emsi.gestiontickets.Repository;

import ma.emsi.gestiontickets.Entity.TicketEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

// Rest Api
@RepositoryRestResource
//  restricts web pages from making requests to a different domain than the one that served the page
@CrossOrigin("*")
public interface TicketEventRepository extends JpaRepository<TicketEvent,Integer> {
}
