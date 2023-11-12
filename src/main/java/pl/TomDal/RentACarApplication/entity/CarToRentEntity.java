package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Set;

@Getter
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carToRent", cascade = CascadeType.REMOVE)
    private Set<RentalOrderEntity> rentalOrders;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carToRent", cascade = CascadeType.REMOVE)
    private Set<CarInsuranceEntity> carInsuranceEntities;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "carToRent", cascade = CascadeType.REMOVE)
    private TechnicalInspectionEntity technicalInspectionEntity;
}
