package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.CarTechnicalInspection;
import pl.TomDal.RentACarApplication.entity.CarTechnicalInspectionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarTechnicalInspectionEntityMapper {
    @Mapping(target = "carToRent", ignore = true)
    CarTechnicalInspection mapFromEntity(CarTechnicalInspectionEntity entity);
}
