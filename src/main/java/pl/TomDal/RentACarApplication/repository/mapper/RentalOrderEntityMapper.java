package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentalOrderEntityMapper {

    RentalOrder mapFromEntity(RentalOrderEntity rentalOrderEntity);

    RentalOrderEntity mapToEntity(RentalOrder rentalOrder);
}
