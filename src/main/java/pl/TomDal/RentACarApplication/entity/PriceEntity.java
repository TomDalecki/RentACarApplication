package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price_list")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "price_date")
    private OffsetDateTime priceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;
}
