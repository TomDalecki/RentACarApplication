package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.controllers.dto.CredentialDetailsDTO;
import pl.TomDal.RentACarApplication.domain.CredentialDetails;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CredentialDetailsMapper {

    CredentialDetails mapFromDTO(final CredentialDetailsDTO credentialDetailsDTO);
}
