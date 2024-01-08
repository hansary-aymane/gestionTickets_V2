package ma.emsi.gestiontickets.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Nom;
    @ManyToOne
    private Ville ville;
    @OneToOne
    @JoinColumn(name="stadium_salle_id")
    private SalleEvent salleEvent;
}
