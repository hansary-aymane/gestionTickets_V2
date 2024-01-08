package ma.emsi.gestiontickets.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numero;
    private int prix;
    private String type;
    @OneToMany(mappedBy = "eventPlace")
    private Collection<TicketEvent> ticketEvents;
    @ManyToOne
    private SalleEvent salleEvent;

}
