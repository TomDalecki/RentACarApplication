package pl.TomDal.RentACarApplication.controllers.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerRentalOrderDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerUnitTest {

    @Mock
    private CarToRentService carToRentService;

    @Mock
    private CarToRentMapper carToRentMapper;

    @Mock
    private CustomerRentalOrderDTO customerRentalOrderDTO;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void thatRetrievingAvailableCarsToRentWorksCorrectly(){
        //given
        LocalDate rentalStartDate = null;
        LocalDate rentalEndDate = null;
        List<CarToRentDTO> availableCarsToRentDTOs = new ArrayList<>();
        availableCarsToRentDTOs.add(carToRentMapper.mapToDTO(TestDataFixtures.testCar1()));

        List<CarToRent> availableCarsToRent = new ArrayList<>(List.of(TestDataFixtures.testCar1()));

        ExtendedModelMap model = new ExtendedModelMap();

        Mockito.when(carToRentService.findAvailableCarsByStartEndDate(rentalStartDate, rentalEndDate))
                .thenReturn(availableCarsToRent);

        //when
        ModelAndView result = customerController.availableCarsToRentInDate(customerRentalOrderDTO, model);

        //then
        // Assert that the method returns the expected ModelAndView
        Assertions.assertThat(result.getViewName()).isEqualTo("choosing_panel");

        // Assert that the model attributes have been added correctly
        Assertions.assertThat(model.getAttribute("availableCarsToRentInDateDTOs")).isEqualTo(availableCarsToRentDTOs);
    }
}