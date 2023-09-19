package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.TechnicalInspection;
import pl.TomDal.RentACarApplication.entity.TechnicalInspectionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnicalInspectionEntityMapper {

    TechnicalInspection mapFromEntity(TechnicalInspectionEntity entity);
}
