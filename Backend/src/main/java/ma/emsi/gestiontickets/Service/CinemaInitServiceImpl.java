package ma.emsi.gestiontickets.Service;

import ma.emsi.gestiontickets.Entity.*;
import ma.emsi.gestiontickets.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CategoryRepository categorieRepository;
    @Autowired
    private ProjectionRepository projectionRepository;


    @Override
    public void initVille() {
        Stream.of("Casablanca","Marrakech","Tanger").forEach(name->{
            Ville ville = new Ville();
            ville.setNom(name);
            villeRepository.save(ville);});
    }
    @Override
    public void initCinema() {
        villeRepository.findAll().forEach(ville->{
            Stream.of("Megarama","IMAX","SALAM","ABCD").forEach(name->{
                Cinema cinema = new Cinema();
                cinema.setNom(name);
                cinema.setVille(ville);
                cinema.setNbrsalles(3 + (int)(Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema->{
            for(int i =0;i<cinema.getNbrsalles();i++){
                Salle salle = new Salle();
                salle.setName("Salle"+(i+1));
                salle.setCinema(cinema);
                salle.setNbrPlaces(20 + (int)(Math.random()*20) );
                salleRepository.save(salle);
                System.out.println("########## i: "+i);
            }
            System.out.println("########## 1");
        });
        System.out.println("########## 2");
    }
    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i =0;i<salle.getNbrPlaces();i++){
                Place place =new Place();
                place.setSalle(salle);
                place.setNumero(i+1);
                placeRepository.save(place);
            }
        });

    }

    /*@Override
    public void initEvent(){
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        stadiumSalleRepository.findAll().forEach(stadiumSalle -> {
            Stream.of("wac vs rca","rca vs irt","rca vs ock").forEach(event-> {
                Event event1 = new Event();
                event1.setTitre(event);
                event1.setPhoto(event.replaceAll(" ", "") + ".jpg");
                Stream.of("12:00", "15:00", "17:00", "19:00").forEach(s -> {
                    try {
                        event1.setHeureDebut(dateFormat.parse(s));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
                event1.setStadiumSalle(stadiumSalle);
                eventRepository.save(event1);
            });
        });*/
    /*
        List<Event> eventsList = new ArrayList<>();
        List<StadiumSalle> stadiumSalles = stadiumSalleRepository.findAll();
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Random random = new Random();
        Stream.of("wac vs rca","rca vs irt","rca vs ock").forEach(event->{
            Event event1 = new Event();
            event1.setTitre(event);
            event1.setPhoto(event.replaceAll(" ","")+".jpg");
            Stream.of("12:00","15:00","17:00","19:00").forEach(s->{
                try {
                    event1.setHeureDebut(dateFormat.parse(s));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
            int randomIndex = random.nextInt(stadiumSalles.size());
            StadiumSalle randomStadiumSalle = stadiumSalles.get(randomIndex);
            // Ajoutez la StadiumSalle à l'événement
            event1.setStadiumSalle(randomStadiumSalle);
            eventRepository.save(event1);
            eventsList.add(event1);
        });
        }
*/
    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00").forEach(s->{
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

    }
    @Override
    public void initCategories() {
        Stream.of("Action","Fiction","Drama").forEach(cat->{
            Categorie categorie = new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);
        });

    }
    @Override
    public void initFilm() {
        double[] durees = new double[]{2,3,2.5,2};
        List<Categorie> categories = categorieRepository.findAll();
        Stream.of("El camino","pursuit of Happyness","The IrishMan","King Arthur","The Gentlemen","Fight Club").forEach(film->{
            Film film1 = new Film();
            film1.setTitre(film);
            film1.setDuree(durees[new Random().nextInt(durees.length)]);
            film1.setPhoto(film.replaceAll(" ","")+".jpg");
            film1.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film1);
        });

    }
    @Override
    public void initProjections() {
        double[] prix = new double[] {30,55,70,90};
        List<Film> films=filmRepository.findAll();
        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema->{
                cinema.getSalles().forEach(salle->{
                    int index=new Random().nextInt(films.size());
                    Film film =films.get(index);
                        seanceRepository.findAll().forEach(seance ->{
                            Projection proj = new Projection();
                            proj.setDateProj(new Date());
                            proj.setFilm(film);
                            proj.setPrix(prix[new Random().nextInt(prix.length)]);
                            proj.setSalle(salle);
                            proj.setSeance(seance);
                            projectionRepository.save(proj);
                        });
                });
            });
        });
    }
    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setProjection(projection);
                ticket.setPrix(projection.getPrix());
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });
    }

}
