package mg.itu.ticketing.entity.avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "siege_type")
public class SiegeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_siege_type")
    private String idSiegeType;

    @Column(name = "siege_type")
    private String siegeType;
}
