package mg.itu.ticketing.entity.avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.entity.vol.Reservation;
import mg.itu.ticketing.entity.vol.Vol;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "siege_promotion")
public class SiegePromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_siege_promotion")
    private String idSiegePromotion;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "prix")
    private double prix;

    @Column(name = "nombre")
    private int nombre;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "siegePromotion")
    @Where(clause = "id_reservation not in (select a.id_reservation from annulation_reservation a)")
    public List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "next") // FK vers id_siege_promotion
    private SiegePromotion next;
}
