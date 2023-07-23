package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_technical_inspection")
public class CarTechnicalInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_technical_inspection_id")
    private Integer carTechnicalInspectionId;

    @Column(name = "inspection_expiry_date")
    private OffsetDateTime inspectionExpiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_rent_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CarToRentEntity carToRent;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CarTechnicalInspectionEntity that = (CarTechnicalInspectionEntity) o;
        return getCarTechnicalInspectionId() != null && Objects.equals(getCarTechnicalInspectionId(), that.getCarTechnicalInspectionId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
