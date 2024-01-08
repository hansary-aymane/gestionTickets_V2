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
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class ITheatreStadiumServiceImpl implements ITheatreStadiumService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private SalleEventRepository salleEventRepository;
    @Autowired
    private EventPlaceRepository eventPlaceRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketEventRepository ticketEventRepository;
    @Override
    public void initStadium() {
        villeRepository.findAll().forEach(ville->{
            Stream.of("Stad1","Stad2","Stad3","Stad4").forEach(name->{
                Stadium stadium = new Stadium();
                stadium.setNom(name);
                stadium.setVille(ville);
                stadiumRepository.save(stadium);
            });
        });
    }
    @Override
    public void initTheatre() {
        villeRepository.findAll().forEach(ville->{
            Stream.of("Theatre1","Theatre2","Theatre3","Theatre4").forEach(name->{
                Theatre theatre = new Theatre();
                theatre.setNom(name);
                theatre.setVille(ville);
                theatreRepository.save(theatre);
            });
        });
    }
    @Override
    public void initSallesEvent() {
        stadiumRepository.findAll().forEach(stadium->{
            SalleEvent salleEvent = new ma.emsi.gestiontickets.Entity.SalleEvent();
            salleEvent.setName("Salle Stadim");
            salleEvent.setNbrPlaces(20 + (int)(Math.random()*20) );
            salleEvent.setType("sport");
            salleEventRepository.save(salleEvent);
            stadium.setSalleEvent(salleEvent);
            stadiumRepository.save(stadium);
        });
        theatreRepository.findAll().forEach(theatre->{
            SalleEvent salleEvent = new ma.emsi.gestiontickets.Entity.SalleEvent();
            salleEvent.setName("Salle Theatre");
            salleEvent.setNbrPlaces(20 + (int)(Math.random()*20) );
            salleEvent.setType("theatre");
            salleEventRepository.save(salleEvent);
            theatre.setSalleEvent(salleEvent);
            theatreRepository.save(theatre);
        });
    }
    @Override
    public void initEventPlaces(){
        salleEventRepository.findAll().forEach(stadiumSalle -> {
            for(int i=0;i<stadiumSalle.getNbrPlaces();i++){
                EventPlace eventPlace = new EventPlace();
                eventPlace.setSalleEvent((stadiumSalle));
                eventPlace.setNumero(i+1);
                eventPlace.setPrix(getRandomPlaceprix());
                eventPlaceRepository.save(eventPlace);
            }
        });
    }
    private int getRandomPlaceprix() {
        List<Integer> PlacePrix = Arrays.asList(10,15,20,25);
        Random random = new Random();
        return PlacePrix.get(random.nextInt(PlacePrix.size()));
    }
    @Override
    public void initEvent() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        List<SalleEvent> salleEvents = salleEventRepository.findAll();
        salleEvents.forEach(salleEvent -> {
            String randomEventSportTitle = getRandomEventSportTitle();
            String randomEventTheatreTitle = getRandomEventTheatretTitle();
            Event event1 = new Event();
            if(salleEvent.getType().equals("sport")){
                // Select a random event from the list
                event1.setTitre(randomEventSportTitle);
                event1.setPhoto(randomEventSportTitle.replaceAll(" ", "") + ".jpg");
                event1.setType("sport");
                // Associate the StadiumSalle with the event
                event1.setSalleEvent(salleEvent);
            }
            if(salleEvent.getType().equals("theatre")){
                // Select a random event from the list
                event1.setTitre(randomEventTheatreTitle);
                event1.setPhoto(randomEventTheatreTitle.replaceAll(" ", "") + ".jpg");
                // Associate the StadiumSalle with the event
                event1.setSalleEvent(salleEvent);
                event1.setType("theatre");
            }
            // Select a random time
            try {
                event1.setHeureDebut(dateFormat.parse(getRandomTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            event1.setPrix(getRandomEventprix());
            // Save the event
            eventRepository.save(event1);
        });
    }
    // Helper method to get a random event title
    private String getRandomEventSportTitle() {
        List<String> events = Arrays.asList("wac vs rca", "rca vs irt", "rca vs ock","wac vs ock", "wac vs irt", "irt vs ock","far vs rca", "far vs irt", "far vs ock","ocs vs rca", "ocs vs irt", "ocs vs ock");
        Random random = new Random();
        return events.get(random.nextInt(events.size()));
    }
    private String getRandomEventTheatretTitle() {
        List<String> events = Arrays.asList("la boite a merveille", "les miserables", "antigone","event1", "event2", "event3","event4", "event4", "event5","event6", "event7", "event8");
        Random random = new Random();
        return events.get(random.nextInt(events.size()));
    }
    private int getRandomEventprix() {
        List<Integer> eventsPrix = Arrays.asList(30,40,50,60);
        Random random = new Random();
        return eventsPrix.get(random.nextInt(eventsPrix.size()));
    }

    // Helper method to get a random time
    private String getRandomTime() {
        List<String> times = Arrays.asList("12:00", "15:00", "17:00", "19:00");
        Random random = new Random();
        return times.get(random.nextInt(times.size()));
    }
    @Override
    public void initTicketsEvent() {
        eventRepository.findAll().forEach(event -> {
            SalleEvent salleEvent = event.getSalleEvent();
            System.out.println("#############"+event.getSalleEvent().getName());
            salleEvent.getEventPlaces().forEach(eventPlace -> {
                TicketEvent ticketEvent = new TicketEvent();
                ticketEvent.setEventPlace(eventPlace);
                ticketEvent.setEvent(event);
                ticketEvent.setReserve(false);
                ticketEvent.setPrix(event.getPrix()+ eventPlace.getPrix());
                ticketEventRepository.save(ticketEvent);
            });
        });
    }
}
