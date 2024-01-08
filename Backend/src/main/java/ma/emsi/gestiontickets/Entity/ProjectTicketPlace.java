package ma.emsi.gestiontickets.Entity;

@org.springframework.data.rest.core.config.Projection(name = "ticketProj",types = {Ticket.class})
public interface ProjectTicketPlace {

    public int getId();
    public String getNomCLient();
    public double getPrix();
    public Integer getCodePayement();
    public boolean isReserve();
    public Place getPlace();

}
