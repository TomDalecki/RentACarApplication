package pl.TomDal.RentACarApplication.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.TomDal.RentACarApplication.domain.CarInsurance;
import pl.TomDal.RentACarApplication.entity.CarInsuranceEntity;
import pl.TomDal.RentACarApplication.repository.mapper.CarInsuranceEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.InsuranceDAO;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsuranceServiceTest {
    @InjectMocks
    InsuranceService insuranceService;

    @Mock
    private InsuranceDAO insuranceDAO;

    @Mock
    private CarInsuranceEntityMapper carInsuranceEntityMapper;

    @Mock
    private CarToRentService carToRentService;

    @Test
    void thatSavingInsuranceWorksCorrectly_WhenInsuranceTypeIsOC() {
        //given
        CarInsurance carOCInsurance = TestDataFixtures.testOCInsurance1();
        CarInsuranceEntity carOCInsuranceEntity = carInsuranceEntityMapper.mapToEntity(carOCInsurance);

        List<CarInsurance> carInsuranceList = new ArrayList<>();
        carInsuranceList.add(carOCInsurance);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsuranceList);
        //doNothing().when(insuranceDAO).saveInsurance(carOCInsuranceEntity);

        //when
        insuranceService.saveInsurance(carOCInsurance);

        //then
        verify(insuranceDAO, Mockito.times(1)).saveInsurance(carOCInsuranceEntity);
        verify(insuranceDAO, Mockito.times(1)).findCarInsuranceByCarId(any());
    }

    @Test
    void thatSavingInsuranceWorksCorrectly_WhenInsuranceTypeIsAC() {
        //given
        CarInsurance carACInsurance = TestDataFixtures.testACInsurance1();
        CarInsuranceEntity carACInsuranceEntity = carInsuranceEntityMapper.mapToEntity(carACInsurance);


        List<CarInsurance> carInsuranceList = new ArrayList<>();
        carInsuranceList.add(carACInsurance);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsuranceList);

        //when
        insuranceService.saveInsurance(carACInsurance);

        //then
        verify(insuranceDAO, Mockito.times(1)).saveInsurance(carACInsuranceEntity);
        verify(insuranceDAO, Mockito.times(1)).findCarInsuranceByCarId(any());
    }

    @Test
    void thatSavingInsuranceWorksCorrectlyAndShouldNotChangeCarStatus_whenInsuranceListEqualsOne() {
        //given
        CarInsurance carACInsurance = TestDataFixtures.testACInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));

        List<CarInsurance> carInsuranceList = new ArrayList<>();
        carInsuranceList.add(carACInsurance);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsuranceList);

        //when
        insuranceService.saveInsurance(carACInsurance);

        //then
        verify(carToRentService, times(0)).changeCarStatusByCarId(any(), any());
    }

    @Test
    void thatSavingInsuranceWorksCorrectlyAndShouldChangeCarStatus_whenInsuranceListHigherThanOne() {
        //given
        CarInsurance carOCInsurance = TestDataFixtures.testOCInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));
        CarInsurance carACInsurance = TestDataFixtures.testACInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));

        CarInsuranceEntity carOCInsuranceEntity = carInsuranceEntityMapper.mapToEntity(carOCInsurance);

        List<CarInsurance> carInsuranceList = new ArrayList<>();
        carInsuranceList.add(carOCInsurance);
        carInsuranceList.add(carACInsurance);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsuranceList);

        //when
        insuranceService.saveInsurance(carOCInsurance);

        //then
        verify(carToRentService, times(1)).changeCarStatusByCarId(any(), any());
    }

    @Test
    void thatFindingInsurancesWorksCorrectly_bothOCAndACInsurances(){
        //given
        CarInsurance carInsuranceOC = TestDataFixtures.testOCInsurance1();
        CarInsurance carInsuranceAC = TestDataFixtures.testACInsurance1();
        List<CarInsurance> carInsurancesByCarId = new ArrayList<>();
        carInsurancesByCarId.add(carInsuranceOC);
        carInsurancesByCarId.add(carInsuranceAC);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsurancesByCarId);

        //when
        List<CarInsurance> result = insuranceService.findInsurances(any());

        //then
        assertEquals(2, result.size());
    }

    @Test
    void thatFindingInsurancesWorksCorrectly_twoOCInsurances(){
        //given
        CarInsurance carInsuranceOC1 = TestDataFixtures.testOCInsurance1();
        CarInsurance carInsuranceOC2 = TestDataFixtures.testOCInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));
        List<CarInsurance> carInsurancesByCarId = new ArrayList<>();
        carInsurancesByCarId.add(carInsuranceOC1);
        carInsurancesByCarId.add(carInsuranceOC2);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsurancesByCarId);

        //when
        List<CarInsurance> result = insuranceService.findInsurances(any());

        //then
        assertEquals(1, result.size());
        assertEquals(carInsuranceOC2, result.get(0));
    }

    @Test
    void thatFindingInsurancesWorksCorrectly_twoACInsurances(){
        //given
        CarInsurance carInsuranceAC1 = TestDataFixtures.testACInsurance1();
        CarInsurance carInsuranceAC2 = TestDataFixtures.testACInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));
        List<CarInsurance> carInsurancesByCarId = new ArrayList<>();
        carInsurancesByCarId.add(carInsuranceAC1);
        carInsurancesByCarId.add(carInsuranceAC2);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsurancesByCarId);

        //when
        List<CarInsurance> result = insuranceService.findInsurances(any());

        //then
        assertEquals(1, result.size());
        assertEquals(carInsuranceAC2, result.get(0));
    }

    @Test
    void thatFindingInsurancesWorksCorrectly_multipleACAndOCInsurancesWithDifferentEndDates(){
        //given
        CarInsurance carInsuranceAC1 = TestDataFixtures.testACInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));
        CarInsurance carInsuranceAC2 = TestDataFixtures.testACInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(2));
        CarInsurance carInsuranceOC1 = TestDataFixtures.testOCInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(1));
        CarInsurance carInsuranceOC2 = TestDataFixtures.testOCInsurance1()
                .withInsuranceEndDate(LocalDate.now().plusDays(2));

        List<CarInsurance> carInsurancesByCarId = new ArrayList<>();
        carInsurancesByCarId.add(carInsuranceAC1);
        carInsurancesByCarId.add(carInsuranceAC2);
        carInsurancesByCarId.add(carInsuranceOC1);
        carInsurancesByCarId.add(carInsuranceOC2);

        when(insuranceDAO.findCarInsuranceByCarId(any())).thenReturn(carInsurancesByCarId);

        //when
        List<CarInsurance> result = insuranceService.findInsurances(any());

        //then
        assertEquals(2, result.size());
        assertEquals(carInsuranceAC2, result.get(0));
        assertEquals(carInsuranceOC2, result.get(1));
    }

    @Test
    void thatUpdateInsuranceEndDateWorksCorrectly(){
        //given
        Integer insuranceId = 1;
        LocalDate newInsuranceEndDate = LocalDate.now();

        //when
        insuranceService.updateUpdateInsuranceEndDate(insuranceId, newInsuranceEndDate);

        //then
        verify(insuranceDAO).updateInsuranceEndDate(insuranceId, newInsuranceEndDate);
        verify(insuranceDAO, times(1)).updateInsuranceEndDate(insuranceId, newInsuranceEndDate);
    }
}