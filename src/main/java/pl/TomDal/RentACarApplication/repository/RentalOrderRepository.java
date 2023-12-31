package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.EmployeeEntity;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.repository.jpa.RentalOrderJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.EmployeeEntityMapper;
import pl.TomDal.RentACarApplication.repository.mapper.RentalOrderEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RentalOrderRepository implements RentalOrderDAO {

    private final RentalOrderJpaRepository rentalOrderJpaRepository;
    private final RentalOrderEntityMapper rentalOrderEntityMapper;
    private final EmployeeEntityMapper employeeEntityMapper;


    @Override
    @Transactional
    public void saveRentalOrder(RentalOrder rentalOrder) {
        RentalOrderEntity rentalOrderEntity = rentalOrderEntityMapper.mapToEntity(rentalOrder);
        rentalOrderJpaRepository.save(rentalOrderEntity);
    }

    @Override
    public List<RentalOrder> findOpenRentalOrdersByEmail(String email) {
        return rentalOrderJpaRepository.findOpenRentalOrdersByEmail(email).stream()
                .map(rentalOrderEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeOrderStatusByOrderId(Integer rentOrderId, OrderStatus orderStatus, Employee employee) {
        EmployeeEntity employeeEntity = employeeEntityMapper.mapToEntity(employee);
        rentalOrderJpaRepository.updateOrderStatusByRentalOrderId(orderStatus, rentOrderId, employeeEntity);
    }

    @Override
    public List<OrderAndCar> findOrdersByStatusJoinedWithCars(OrderStatus orderStatus) {
        return rentalOrderJpaRepository.findOrdersByStatusJoinedWithCars(orderStatus);
    }

    @Override
    public Optional<OrderAndCar> findOrderByRentalOrderNumberJoinedWithCar(String rentNumber) {
        return rentalOrderJpaRepository.findOrderByRentalOrderNumberJoinedWithCar(rentNumber);
    }

    @Override
    public void changeRentalPeriodByOrderId(Integer rentalOrderId, LocalDate newRentalStartDate,
                                            LocalDate newRentalEndDate) {
        rentalOrderJpaRepository.updateRentalStartDateAndRentalEndDateByRentalOrderId(newRentalStartDate,
                newRentalEndDate, rentalOrderId);

    }

    public List<RentalOrder> findAll() {
        return rentalOrderJpaRepository.findAll().stream()
                .map(rentalOrderEntityMapper::mapFromEntity).collect(Collectors.toList());
    }

    public Optional<RentalOrder> findRentalOrderById(Integer rentalOrderId) {
        return rentalOrderJpaRepository.findById(rentalOrderId).map(rentalOrderEntityMapper::mapFromEntity);
    }
}
