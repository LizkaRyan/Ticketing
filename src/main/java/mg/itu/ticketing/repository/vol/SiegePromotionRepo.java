package mg.itu.ticketing.repository.vol;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mg.itu.ticketing.entity.avion.SiegePromotion;
import mg.itu.ticketing.entity.vol.Reservation;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SiegePromotionRepo extends GenericRepository<SiegePromotion,String> {

    private final AnnulationReservationRepo annulationReservationRepo = new AnnulationReservationRepo();

    public List<SiegePromotion> findSiegePromotion(LocalDate date, EntityManager em){
        return this.findRequest("select s from SiegePromotion s join fetch s.reservations where s.dateFin < :date order by s.dateFin asc",em,typedQuery -> {
            typedQuery.setParameter("date",date);
        });
    }

    public void updateData(List<SiegePromotion> siegePromotions,EntityManager em){
        for(SiegePromotion siegePromotion:siegePromotions){
            List<Reservation> reservations = siegePromotion.getReservations().stream()
                    .sorted(Comparator.comparing(Reservation::getDateReservation)) // tri croissant
                    .collect(Collectors.toList());
            int nbNonPaye = 0;
            for (int i = 0; i < reservations.size();i++){
                if(reservations.get(i).getPaye()==0){
                    nbNonPaye++;
                    annulationReservationRepo.save(reservations.get(i).getIdReservation(),em);
                }
            }
            if(nbNonPaye!=0 && siegePromotion.getNext()!=null){
                siegePromotion.setNombre(siegePromotion.getNombre()-nbNonPaye);
                this.save(siegePromotion,em);
                SiegePromotion next = siegePromotion.getNext();
                next.setNombre(next.getNombre()+nbNonPaye);
                this.save(next,em);
            }
        }
    }

    public void updateAllVol(LocalDate date){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        try {
            List<SiegePromotion> siegePromotions = this.findSiegePromotion(date,em);
            em.getTransaction().begin();
            updateData(siegePromotions,em);
            em.getTransaction().commit();
        }
        catch (Exception ex){
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }
    }
}
