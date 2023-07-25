package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.PriceList;
import pl.TomDal.RentACarApplication.entity.PriceListEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceListEntityMapper {

    PriceList mapFromEntity(PriceListEntity entity);
}
