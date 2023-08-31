package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.Price;
import pl.TomDal.RentACarApplication.entity.PriceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceEntityMapper {

    Price mapFromEntity(PriceEntity entity);

    PriceEntity mapToEntity(Price price);
}
