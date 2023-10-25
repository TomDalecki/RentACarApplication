package pl.TomDal.RentACarApplication.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.TomDal.RentACarApplication.controllers.dto.TechnicalInspectionDTO;
import pl.TomDal.RentACarApplication.services.TechnicalInspectionService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.TomDal.RentACarApplication.controllers.rest.TechnicalInspectionRestController.*;

@WebMvcTest(value = TechnicalInspectionRestController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TechnicalInspectionRestControllerWebMvcTest {
    private final WebApplicationContext webApplicationContext;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @MockBean
    private TechnicalInspectionService technicalInspectionService;

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
    void thatUpdateTechInspectionDateWorksCorrectlyPutMethod() throws Exception {
        Integer carIdNumber = 1;

        when(technicalInspectionService.findInspectionDetailByCarId(carIdNumber))
                .thenReturn(TestDataFixtures.testTechnicalInspection1());

        mockMvc.perform(put(CAR_TO_RENT + CAR_TO_RENT_UPDATE_TECH_INSP_PUT, carIdNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inspectionExpiryDate\":\"2023-10-24\"}"))
                        .andExpect(status().isOk());
    }

    @Test
    void thatUpdateTechInspectionDateWorksCorrectlyPatchMethod() throws Exception {
        Integer carIdNumber = 1;
        LocalDate newInspDate = LocalDate.now();

        when(technicalInspectionService.findInspectionDetailByCarId(carIdNumber))
                .thenReturn(TestDataFixtures.testTechnicalInspection1());

        mockMvc.perform(patch(CAR_TO_RENT + CAR_TO_RENT_UPDATE_TECH_INSP_PATCH, carIdNumber)
                        .param("newInspDate", newInspDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}