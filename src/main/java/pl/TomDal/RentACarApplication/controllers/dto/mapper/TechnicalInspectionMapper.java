package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.controllers.dto.TechnicalInspectionDTO;
import pl.TomDal.RentACarApplication.domain.TechnicalInspection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnicalInspectionMapper {

    TechnicalInspectionDTO mapToDTO(final TechnicalInspection technicalInspection);

}
