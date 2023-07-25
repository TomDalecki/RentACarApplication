//package pl.TomDal.RentACarApplication.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.proxy.HibernateProxy;
//
//import java.math.BigDecimal;
//import java.time.OffsetDateTime;
//import java.util.Objects;
//import java.util.Set;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "rental_history")
//public class RentalHistoryEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "rental_history_id")
//    private Integer rentalHistoryId;
//
//    @ManyToOne
//    @JoinColumn(name = "car_to_rent_id")
//    private CarToRentEntity carToRent;
//
//    @ManyToOne
//    @JoinColumn(name = "rental_order_id")
//    private RentalOrderEntity rentalOrder;





//    @Column(name = "rental_start_date")
//    private OffsetDateTime rentalStartDate;
//
//    @Column(name = "rental_end_date")
//    private OffsetDateTime rentalEndDate;
//
//    @Column(name = "total_price")
//    private BigDecimal totalPrice;


//}
