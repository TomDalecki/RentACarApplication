package pl.TomDal.RentACarApplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.TomDal.RentACarApplication.domain.TechnicalInspection;
import pl.TomDal.RentACarApplication.entity.TechnicalInspectionEntity;
import pl.TomDal.RentACarApplication.repository.mapper.TechnicalInspectionEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.TechnicalInspectionDAO;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicalInspectionServiceTest {
    @InjectMocks
    TechnicalInspectionService technicalInspectionService;

    @Mock
    TechnicalInspectionDAO technicalInspectionDAO;

    @Mock
    TechnicalInspectionEntityMapper technicalInspectionEntityMapper;

    @Test
    void thatFindingInspectionDetailByCarIdWorksCorrectly() {
        //given
        Integer carToRentId = 1;
        TechnicalInspection technicalInspection = TestDataFixtures.testTechnicalInspection1();
        TechnicalInspectionEntity technicalInspectionEntity = TestDataFixtures.testTechnicalInspectionEntity1();

        when(technicalInspectionDAO.findInspectionDetailByCarId(carToRentId))
                .thenReturn(Optional.of(technicalInspectionEntity));

        when(technicalInspectionEntityMapper
                .mapFromEntity(technicalInspectionEntity))
                .thenReturn(technicalInspection);

        //when
        TechnicalInspection result = technicalInspectionService.findInspectionDetailByCarId(carToRentId);

        //then
        assertEquals(technicalInspection, result);
        verify(technicalInspectionDAO, times(1)).findInspectionDetailByCarId(carToRentId);
    }

    @Test
    void thatUpdatingExpiryDateWorksCorrectly() {
        //given
        Integer technicalInspectionId = 1;
        LocalDate newInspectionEndDate = LocalDate.now();

        //when
        technicalInspectionService.updateExpiryDate(technicalInspectionId, newInspectionEndDate);

        //then
        verify(technicalInspectionDAO, times(1))
                .updateExpiryDate(technicalInspectionId, newInspectionEndDate);
    }
}