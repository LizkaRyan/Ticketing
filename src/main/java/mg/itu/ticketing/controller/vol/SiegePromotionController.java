package mg.itu.ticketing.controller.vol;

import mg.itu.prom16.winter.ModelAndView;
import mg.itu.prom16.winter.Session;
import mg.itu.prom16.winter.annotation.method.Get;
import mg.itu.prom16.winter.annotation.parameter.Param;
import mg.itu.prom16.winter.annotation.type.Controller;
import mg.itu.ticketing.controller.Dispatcher;
import mg.itu.ticketing.repository.vol.SiegePromotionRepo;

import java.time.LocalDate;

@Controller(mapping = "siege-promotion")
public class SiegePromotionController {

    private final SiegePromotionRepo siegePromotionRepo = new SiegePromotionRepo();

    Session session;

    public SiegePromotionController(Session session){
        this.session=session;
    }

    @Get("/update")
    public String update(@Param(name = "date")String date){
        siegePromotionRepo.updateAllVol(LocalDate.parse(date));
        return "redirect:/Ticketing/siege-promotion/form";
    }

    @Get("/form")
    public ModelAndView form(){
        return new Dispatcher("update",session);
    }
}
