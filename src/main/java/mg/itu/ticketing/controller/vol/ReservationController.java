package mg.itu.ticketing.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.Validator;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.method.Post;
import mg.itu.prom16.winter.annotation.method.RestMethod;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.prom16.winter.authentication.Authenticate;
import mg.itu.prom16.winter.authentication.NormalAuthenticator;
import mg.itu.ticketing.authentication.ConnectedAuthentication;
import mg.itu.ticketing.controller.Dispatcher;
import mg.itu.ticketing.dto.reservation.ReservationDTO;
import mg.itu.ticketing.dto.reservation.ReservationPdfData;
import mg.itu.ticketing.entity.personnel.Utilisateur;
import mg.itu.ticketing.repository.vol.ReservationRepository;
import mg.itu.ticketing.validation.reservation.ReservationValidation;

import java.time.LocalDate;

@Controller(mapping = "/reservation")
@Authenticate(ConnectedAuthentication.class)
public class ReservationController {

    Session session;
    private final ReservationRepository reservationRepository=new ReservationRepository();

    public ReservationController(Session session){
        this.session=session;
    }
    @Get("/form")
    public ModelAndView form(@Param(name = "idVol")String idVol){
        return new Dispatcher("reservation/form",session)
                .addObject("tranchesAge",reservationRepository.getPassePortRepo().getTrancheAgeRepo().findAll())
                .addObject("typeSieges",reservationRepository.getTypeSiegeRepository().findAll())
                .addObject("idVol",idVol);
    }

    @Get
    public ModelAndView list(@Param(name = "message")String message){
        return new Dispatcher("reservation/index",session).addObject("reservations",reservationRepository.findReservationByIdUser(((Utilisateur)session.get("utilisateur")).getIdUtilisateur()))
                .addObject("message",message);
    }

    @Post
    public Object insert(@Param(name = "reservation") @ReservationValidation ReservationDTO reservation, Validator validator) throws Exception {
        if(!validator.isValid()){
            Dispatcher dispatcher = (Dispatcher) new Dispatcher("reservation/form",session)
                    .addObject("tranchesAge",reservationRepository.getPassePortRepo().getTrancheAgeRepo().findAll())
                    .addObject("typeSieges",reservationRepository.getTypeSiegeRepository().findAll())
                    .addObject("idVol",reservation.getIdVol())
                    .addObject("error.reservation",reservation);
            return validator.setError(dispatcher);
        }
        return reservationRepository.save(reservation,session);
    }

    @Get("/paye")
    public String insert(@Param(name = "idReservation") String idReservation) throws Exception {
        reservationRepository.pay(idReservation);
        return "redirect:/Ticketing/reservation";
    }

    @RestMethod
    @Get("pdf")
    @Authenticate(NormalAuthenticator.class)
    public ReservationPdfData findPdfReservation(@Param(name = "idReservation") String idReservation){
        return reservationRepository.findReservationById(idReservation).getPdfData();
    }
}
