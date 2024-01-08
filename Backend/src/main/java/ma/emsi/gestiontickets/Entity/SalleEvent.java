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
public class SalleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nbrPlaces;
    private String Name;
    private  String type;
    @OneToMany(mappedBy = "salleEvent")
    private Collection<Event> events;

    @OneToMany(mappedBy = "salleEvent")
    protected Collection<EventPlace> eventPlaces;
}
