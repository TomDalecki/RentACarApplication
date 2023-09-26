package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_technical_inspection")
public class TechnicalInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_technical_inspection_id")
    private Integer technicalInspectionId;

    @Column(name = "inspection_expiry_date")
    private LocalDate inspectionExpiryDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_to_rent_id")
    private CarToRentEntity carToRent;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TechnicalInspectionEntity that = (TechnicalInspectionEntity) o;
        return getTechnicalInspectionId() != null && Objects.equals(getTechnicalInspectionId(), that.getTechnicalInspectionId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
