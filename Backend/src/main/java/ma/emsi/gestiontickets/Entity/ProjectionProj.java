package ma.emsi.gestiontickets.Entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

// pour specifier les donnees envoyer localhost:8080/projections/1?projection=p1
// personaliser spring data rest
@Projection(name = "p1",types = {ma.emsi.gestiontickets.Entity.Projection.class})
public interface ProjectionProj {
    public int getId();
    public double getPrix();
    public Date getDateProj();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}
