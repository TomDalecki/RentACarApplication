package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
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
}
