package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.controllers.dto.CarInsuranceDTO;
import pl.TomDal.RentACarApplication.domain.CarInsurance;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarInsuranceMapper {

    CarInsurance mapFromDTO(CarInsuranceDTO carInsuranceDTO);
}
