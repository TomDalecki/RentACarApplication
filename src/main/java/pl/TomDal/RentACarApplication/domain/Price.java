package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Getter
@Value
@Builder
@EqualsAndHashCode
@ToString
public class Price {
    Integer priceId;
    BigDecimal price;
    OffsetDateTime priceDate;
    CarType carType;
}
