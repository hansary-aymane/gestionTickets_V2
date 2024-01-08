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

public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Nom;
    private  double longitude,latitude,altitude;
    @OneToMany(mappedBy = "ville")
    private Collection<Cinema> cinemas;
    @OneToMany(mappedBy = "ville")
    private Collection<Stadium> stadiums;
    @OneToMany(mappedBy = "ville")
    private Collection<Theatre> theatres;

}
