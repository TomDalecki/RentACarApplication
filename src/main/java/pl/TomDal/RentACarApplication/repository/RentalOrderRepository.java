package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.repository.jpa.CarToRentJpaRepository;
import pl.TomDal.RentACarApplication.repository.jpa.RentalOrderJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.RentalOrderEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RentalOrderRepository implements RentalOrderDTO {
    private final CarToRentJpaRepository carToRentJpaRepository;
    private final RentalOrderJpaRepository rentalOrderJpaRepository;
    private final RentalOrderEntityMapper rentalOrderEntityMapper;


    @Override
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
    public void changeOrderStatus(Integer rentOrderId, OrderStatus orderStatus) {
        rentalOrderJpaRepository.updateOrderStatusByRentalOrderId(orderStatus, rentOrderId);
    }

    @Override
    public void changeCarToRentStatus(Integer carToRentId) {
        carToRentJpaRepository.updateCarStatusByCarToRentId(carToRentId);
    }
}
