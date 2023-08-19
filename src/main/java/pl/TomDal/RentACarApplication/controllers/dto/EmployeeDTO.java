package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.domain.RentalOrder;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    Integer employeeId;
    String name;
    String surname;
    String pesel;
    String email;
}
