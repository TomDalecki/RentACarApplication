package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class PriceList {
    Integer priceListId;
    BigDecimal price;
    OffsetDateTime priceDate;
    CarType carType;
}
