package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.repository.jpa.CarToRentJpaRepository;
import pl.TomDal.RentACarApplication.repository.jpa.RentalOrderJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.RentalOrderEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RentalOrderRepository implements RentalOrderDAO {
    private final CarToRentJpaRepository carToRentJpaRepository;
    private final RentalOrderJpaRepository rentalOrderJpaRepository;
    private final RentalOrderEntityMapper rentalOrderEntityMapper;


    @Override
    @Transactional
    public void saveRentalOrder(RentalOrder rentalOrder) {
        RentalOrderEntity rentalOrderEntity = rentalOrderEntityMapper.mapToEntity(rentalOrder);
        rentalOrderJpaRepository.saveAndFlush(rentalOrderEntity);
    }

    @Override
    public List<RentalOrder> findOpenRentalOrdersByEmail(String email) {
        return rentalOrderJpaRepository.findOpenRentalOrdersByEmail(email).stream()
                .map(rentalOrderEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeOrderStatusByOrderId(Integer rentOrderId, OrderStatus orderStatus) {
        rentalOrderJpaRepository.updateOrderStatusByRentalOrderId(orderStatus, rentOrderId);
    }

    // UWAGA TA METODA POWINNA TRAFIĆ DO CarToRentJPA
    @Override
    @Transactional
    public void changeCarToRentStatus(Integer carToRentId, CarStatus carStatus) {
        carToRentJpaRepository.updateCarStatusByCarToRentId(carToRentId, carStatus);
    }

    @Override
    public List<OrderAndCar> findOrdersByStatusJoinedWithCars(OrderStatus orderStatus) {
        return rentalOrderJpaRepository.findOrdersByStatusJoinedWithCars(orderStatus);
    }

    @Override
    public Optional<OrderAndCar> findOrderByRentalOrderIdJoinedWithCar(String rentNumber) {
        return rentalOrderJpaRepository.findOrderByRentalOrderIdJoinedWithCar(rentNumber);
    }

    @Override
    public void changeRentalPeriodByOrderId(Integer rentalOrderId, LocalDate newRentalStartDate, LocalDate newRentalEndDate) {
        rentalOrderJpaRepository.updateRentalStartDateAndRentalEndDateByRentalOrderId(newRentalStartDate,newRentalEndDate, rentalOrderId);

    }
}
