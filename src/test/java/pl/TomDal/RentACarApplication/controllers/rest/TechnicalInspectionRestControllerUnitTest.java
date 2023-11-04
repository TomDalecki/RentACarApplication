package pl.TomDal.RentACarApplication.controllers.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.TomDal.RentACarApplication.domain.TechnicalInspection;
import pl.TomDal.RentACarApplication.services.TechnicalInspectionService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicalInspectionRestControllerUnitTest {

    @Mock
    private TechnicalInspectionService technicalInspectionService;

    @InjectMocks
    private TechnicalInspectionRestController technicalInspectionRestController;

    @Test
    void updateTechInspectionDate() {
       //given
        Integer carIdNumber = 1;
        TechnicalInspection technicalInspection = TestDataFixtures.testTechnicalInspection1();
        when(technicalInspectionService.findInspectionDetailByCarId(carIdNumber)).thenReturn(technicalInspection);
        LocalDate newInspDate = LocalDate.now();


        //when
        ResponseEntity<?> responseEntity = technicalInspectionRestController
                .updateTechInspectionDate(carIdNumber, newInspDate);

        //then
        verify(technicalInspectionService, times(1))
                .updateExpiryDate(technicalInspection.getTechnicalInspectionId(), newInspDate);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateTechInspectionDate_whenCarNotFound() {
        //given
        Integer carIdNumber = 1;
        when(technicalInspectionService.findInspectionDetailByCarId(carIdNumber)).thenReturn(null);
        LocalDate newInspDate = LocalDate.now();

        //when
        ResponseEntity<?> responseEntity = technicalInspectionRestController
                .updateTechInspectionDate(carIdNumber, newInspDate);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}