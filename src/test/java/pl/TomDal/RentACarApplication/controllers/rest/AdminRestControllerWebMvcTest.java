package pl.TomDal.RentACarApplication.controllers.rest;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminRestController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AdminRestControllerWebMvcTest {
    private MockMvc mockMvc;
    private final WebApplicationContext webApplicationContext;

    @MockBean
    private CarToRentService carToRentService;

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
    void thatCarCanBeDeleted() throws Exception {
        //given
        String endpoint = AdminRestController.ADMIN + AdminRestController.ADMIN_DELETE_CAR;
        String vin = "someVin";
        CarToRent existingCar = TestDataFixtures.testCar1().withVin(vin);
        when(carToRentService.findByVin(vin)).thenReturn(Optional.ofNullable(existingCar));
        //when, then
        mockMvc.perform(delete(endpoint, vin))
                .andExpect(status().isOk());

        verify(carToRentService, times(1)).deleteCarByVin(vin);
    }


    @Test
    void thatCarCanBeDeleted_carNotFound() throws Exception {
        //given
        String endpoint = AdminRestController.ADMIN + AdminRestController.ADMIN_DELETE_CAR;
        String vin = "someWrongVin";
        when(carToRentService.findByVin(vin)).thenReturn(Optional.empty());

        //when, then
        mockMvc.perform(delete(endpoint, vin))
                .andExpect(status().isNotFound());

        verify(carToRentService, times(0)).deleteCarByVin(vin);
    }
}