package pl.TomDal.RentACarApplication.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = CarToRentRestController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CarToRentRestControllerWebMvcTest {
    private final WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    private CarToRentService carToRentService;

    @MockBean
    private CarToRentMapper carToRentMapper;

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
    void thatAllCarsCanBeRetrieved() throws Exception {
        //given
        CarToRent carToRent = TestDataFixtures.testCar1();
        CarToRentDTO carToRentDTO = TestDtoDataFixtures.testCarDTO1();

        when(carToRentService.findAllCars()).thenReturn(List.of(carToRent));
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO);


        //when
        String endpoint = CarToRentRestController.CARTORENT;
        mockMvc.perform(get(endpoint)).andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carsToRent[0].carToRentId", is(carToRentDTO.getCarToRentId())))
                .andExpect(jsonPath("$.carsToRent[0].vin", is(carToRentDTO.getVin())))
                .andExpect(jsonPath("$.carsToRent[0].carIdNumber", is(carToRentDTO.getCarIdNumber())))
                .andExpect(jsonPath("$.carsToRent[0].carType", is(carToRentDTO.getCarType().toString())))
                .andExpect(jsonPath("$.carsToRent[0].brand", is(carToRentDTO.getBrand())))
                .andExpect(jsonPath("$.carsToRent[0].model", is(carToRentDTO.getModel())))
                .andExpect(jsonPath("$.carsToRent[0].year", is(carToRentDTO.getYear())))
                .andExpect(jsonPath("$.carsToRent[0].color", is(carToRentDTO.getColor())))
                .andExpect(jsonPath("$.carsToRent[0].carStatus", is(carToRentDTO.getCarStatus().toString())));
    }

    @Test
    void carsToRentList() {
    }

    @Test
    void carDetails() {
    }

    @Test
    void addCarToRent() {
    }
}