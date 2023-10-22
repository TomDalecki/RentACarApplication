package pl.TomDal.RentACarApplication.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = CarToRentRestController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CarToRentRestControllerWebMvcTest {
    private final WebApplicationContext webApplicationContext;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    @MockBean
    private CarToRentService carToRentService;
    @MockBean
    private CarToRentMapper carToRentMapper;
    @MockBean
    private CarToRentRestController.CarToRentRestDTO carToRentRestDTO;

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


        //when, then
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
    void thatAllCarsCanBeRetrievedByCarStatus() throws Exception {
        //given
        CarToRent carToRent = TestDataFixtures.testCar1();
        CarStatus carStatus = CarStatus.TO_RENT;
        CarToRentDTO carToRentDTO = TestDtoDataFixtures.testCarDTO1();

        when(carToRentService.findCarsToRentByCarStatus(carStatus)).thenReturn(List.of(carToRent));
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO);

        //when, then
        String endpoint = CarToRentRestController.CARTORENT + CarToRentRestController.CARTORENT_CAR_STATUS;
        mockMvc.perform(get(endpoint, carStatus)).andExpect(content()
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
    void thatAllCarsCanBeRetrievedByCarStatus_wrongStatus() throws Exception {
        //given
        String carStatus = "someWrongStatus";

        //when, then
        String endpoint = CarToRentRestController.CARTORENT + CarToRentRestController.CARTORENT_CAR_STATUS;
        mockMvc.perform(get(endpoint, carStatus))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void thatCarDetailsCanBeRetrievedByCarIdNumber() throws Exception {
        //given
        String carIdNumber = TestDataFixtures.testCar1().getCarIdNumber();
        CarToRent carToRent = TestDataFixtures.testCar1();
        CarToRentDTO carToRentDTO = TestDtoDataFixtures.testCarDTO1();

        when(carToRentService.findByCarIdNumber(carIdNumber)).thenReturn(carToRent);
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO);

        //when, then
        String endpoint = CarToRentRestController.CARTORENT + CarToRentRestController.CARTORENT_CAR_ID_NUMBER;
        mockMvc.perform(get(endpoint, carIdNumber)).andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carToRentId", is(carToRentDTO.getCarToRentId())))
                .andExpect(jsonPath("$.vin", is(carToRentDTO.getVin())))
                .andExpect(jsonPath("$.carIdNumber", is(carToRentDTO.getCarIdNumber())))
                .andExpect(jsonPath("$.carType", is(carToRentDTO.getCarType().toString())))
                .andExpect(jsonPath("$.brand", is(carToRentDTO.getBrand())))
                .andExpect(jsonPath("$.model", is(carToRentDTO.getModel())))
                .andExpect(jsonPath("$.year", is(carToRentDTO.getYear())))
                .andExpect(jsonPath("$.color", is(carToRentDTO.getColor())))
                .andExpect(jsonPath("$.carStatus", is(carToRentDTO.getCarStatus().toString())));
    }

    @Test
    void thatCarDetailsCanBeRetrievedByCarIdNumber_wrongIdNumber() throws Exception {
        //given
        String carIdNumber = "someWrongIdNumber";
        when(carToRentService.findByCarIdNumber(carIdNumber)).thenThrow(new EntityNotFoundException());

        //when, then
        String endpoint = CarToRentRestController.CARTORENT + CarToRentRestController.CARTORENT_CAR_ID_NUMBER;
        mockMvc.perform(get(endpoint, carIdNumber))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void thatCarToRentIsSavedCorrectly() throws Exception {
        //given
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO = new CarToRentRestController.CarToRentRestDTO();
        CarToRentDTO carToRentDTO = TestDtoDataFixtures.testCarDTO1();
        carToRentRestDTO.setCarToRentDTO(carToRentDTO);
        carToRentRestDTO.setTechnicalInspectionDate(LocalDate.now());

        when(carToRentService.saveCar(any(), any())).thenReturn(carToRentDTO);

        //when, then
        String endpoint = CarToRentRestController.CARTORENT;
        mockMvc.perform(post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.registerModule(new JavaTimeModule()).toString())
                        .content(objectMapper.writeValueAsString(carToRentRestDTO)))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}