package ma.emsi.gestiontickets.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String photo;

    private String type; // stadium theatre
    private int prix;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @ManyToOne
    private SalleEvent salleEvent;
    @OneToMany(mappedBy = "event")
    private Collection<TicketEvent> ticketEvents;

}
