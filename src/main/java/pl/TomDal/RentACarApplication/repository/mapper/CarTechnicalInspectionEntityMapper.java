package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.CarTechnicalInspection;
import pl.TomDal.RentACarApplication.entity.CarTechnicalInspectionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarTechnicalInspectionEntityMapper {

    CarTechnicalInspection mapFromEntity(CarTechnicalInspectionEntity entity);
}
