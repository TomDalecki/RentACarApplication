package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.RentOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental_history")
public class RentalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_history_id")
    private Integer rentalHistoryId;

    @Column(name = "rental_start_date")
    private LocalDate rentalStartDate;

    @Column(name = "rental_end_date")
    private LocalDate rentalEndDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "rent_order_status")
    private RentOrderStatus rentOrderStatus;

    @ManyToOne
    @JoinColumn(name = "car_to_rent_id")
    private CarToRentEntity carToRent;

    @ManyToOne
    @JoinColumn(name = "rental_order_id")
    private RentalOrderEntity rentalOrder;



}
