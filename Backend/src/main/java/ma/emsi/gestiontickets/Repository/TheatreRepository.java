package ma.emsi.gestiontickets.Repository;

import ma.emsi.gestiontickets.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
//  restricts web pages from making requests to a different domain than the one that served the page
@CrossOrigin("*")
public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
}
