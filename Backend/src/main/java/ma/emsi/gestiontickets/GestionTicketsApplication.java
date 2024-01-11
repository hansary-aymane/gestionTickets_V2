package ma.emsi.gestiontickets;

import ma.emsi.gestiontickets.Entity.Film;
import ma.emsi.gestiontickets.Entity.Salle;
import ma.emsi.gestiontickets.Entity.Ticket;
import ma.emsi.gestiontickets.Service.ICinemaInitService;
import ma.emsi.gestiontickets.Service.ITheatreStadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class GestionTicketsApplication implements CommandLineRunner {

	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private ITheatreStadiumService TheatreStadiumService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(GestionTicketsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class); // inclure id dans json
		restConfiguration.exposeIdsFor(Salle.class); // inclure id dans json
		restConfiguration.exposeIdsFor(Ticket.class); // inclure id dans json
		cinemaInitService.initVille();
		cinemaInitService.initCinema();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategories();
		cinemaInitService.initFilm();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();

		TheatreStadiumService.initStadium();
		TheatreStadiumService.initTheatre();
		TheatreStadiumService.initSallesEvent();
		TheatreStadiumService.initEventPlaces();
		TheatreStadiumService.initEvent();
		TheatreStadiumService.initTicketsEvent();
	}
}