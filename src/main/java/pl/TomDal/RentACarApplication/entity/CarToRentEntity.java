package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_to_rent")
public class CarToRentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_rent_id")
    private Integer carToRentId;

    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "car_id_number", unique = true)
    private String carIdNumber;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "production_year")
    private Integer year;

    @Column(name = "color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_status")
    private CarStatus carStatus;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carToRent")
    private Set<RentalOrderEntity> rentalOrder;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CarToRentEntity that = (CarToRentEntity) o;
        return getCarToRentId() != null && Objects.equals(getCarToRentId(), that.getCarToRentId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
