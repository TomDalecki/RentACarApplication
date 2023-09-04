package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.OrderAndCarMapper;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.RentalOrderMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.CustomerService;
import pl.TomDal.RentACarApplication.services.PriceCalculationService;
import pl.TomDal.RentACarApplication.services.RentalOrderService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CustomerControllerMvcTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @MockBean
    private final ThymeleafViewResolver thymeleafViewResolver;
    @MockBean
    private final CarToRentService carToRentService;
    @MockBean
    private final CarToRentMapper carToRentMapper;
    @MockBean
    private final RentalOrderMapper rentalOrderMapper;
    @MockBean
    private final RentalOrderService rentalOrderService;
    @MockBean
    private final PriceCalculationService priceCalculationService;
    @MockBean
    private final CustomerService customerService;
    @MockBean
    private final OrderAndCarMapper orderAndCarMapper;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        UserDetails userDetails = User.withUsername("someTestUser")
                .password("password")
                .roles("USER")
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void thatCustomerControllerWorksCorrectly() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER))
                .andExpect(status().isOk())
                .andExpect(view().name("customer_panel"));
    }

    @Test
    void thatAvailableCarsAreLoadedCorrectlyToModel() throws Exception{

        CarToRent carToRent = TestDataFixtures.testCar1();

        List<CarToRentDTO> availableCarsToRentDTO = new ArrayList<>();
        availableCarsToRentDTO.add(carToRentMapper.mapToDTO(carToRent));

        when(carToRentService.findAvailableCars().stream()
                .map(carToRentMapper::mapToDTO).toList()).thenReturn(availableCarsToRentDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER))
                .andExpect(model().attribute("availableCarsToRentDTOs", availableCarsToRentDTO));
    }
}