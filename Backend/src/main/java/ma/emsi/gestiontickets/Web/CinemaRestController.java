package ma.emsi.gestiontickets.Web;

import ma.emsi.gestiontickets.Entity.Event;
import ma.emsi.gestiontickets.Entity.Film;
import ma.emsi.gestiontickets.Entity.Ticket;
import ma.emsi.gestiontickets.Repository.EventRepository;
import ma.emsi.gestiontickets.Repository.FilmRepository;
import ma.emsi.gestiontickets.Repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name="id") int id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName=f.getPhoto();
        File file = new File(System.getProperty("user.home")+"/OneDrive/Bureau/JEE Project/GestionTickets/movies/"+photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @GetMapping(path="/imageEvent/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageEvent(@PathVariable(name="id") int id) throws Exception{
        Event e = eventRepository.findById(id).get();
        String photoName=e.getPhoto();
        File file = new File(System.getProperty("user.home")+"/OneDrive/Bureau/JEE Project/GestionTickets/event/"+photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/payerTickets")
    @Transactional
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm){
        List<Ticket> ListTickets = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomCLient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketForm.getCodePayement());
            ticketRepository.save(ticket);
            ListTickets.add(ticket);
        });
        return ListTickets;
    }
}

@Data
class TicketForm{
    private String nomClient;
    private Integer codePayement;
    private List<Integer> tickets= new ArrayList<>();
}
