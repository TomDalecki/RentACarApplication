package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.repository.jpa.CarToRentJpaRepository;
import pl.TomDal.RentACarApplication.repository.jpa.RentalOrderJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.RentalOrderEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDAO;

import java.util.List;
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
    public void changeOrderStatus(Integer rentOrderId, OrderStatus orderStatus) {
        rentalOrderJpaRepository.updateOrderStatusByRentalOrderId(orderStatus, rentOrderId);
    }

    @Override
    @Transactional
    public void changeCarToRentStatus(Integer carToRentId, CarStatus carStatus) {
        carToRentJpaRepository.updateCarStatusByCarToRentId(carToRentId, carStatus);
    }
}
