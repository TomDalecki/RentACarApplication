package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceCompanies;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceType;

import java.time.LocalDate;
import java.util.Objects;
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_insurance")
public class CarInsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_insurance_id")
    private Integer carInsuranceId;

    @Column(name = "insurance_company")
    @Enumerated(EnumType.STRING)
    private InsuranceCompanies insuranceCompany;

    @Column(name = "insurance_type")
    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;

    @Column(name = "insurance_number", unique = true)
    private String insuranceNumber;

    @Column(name = "insurance_start_date")
    private LocalDate insuranceStartDate;

    @Column(name = "insurance_end_date")
    private LocalDate insuranceEndDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_to_rent_id")
    @EqualsAndHashCode.Exclude
    private CarToRentEntity carToRent;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CarInsuranceEntity that = (CarInsuranceEntity) o;
        return getCarInsuranceId() != null && Objects.equals(getCarInsuranceId(), that.getCarInsuranceId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
