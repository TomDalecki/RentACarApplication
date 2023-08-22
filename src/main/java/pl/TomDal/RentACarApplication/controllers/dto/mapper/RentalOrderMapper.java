package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerRentalOrderDTO;
import pl.TomDal.RentACarApplication.controllers.dto.RentalOrderDTO;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.services.CustomerService;
import pl.TomDal.RentACarApplication.services.PriceCalculationService;

import java.time.OffsetDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentalOrderMapper {

    RentalOrderDTO mapToDTO(final RentalOrder rentalOrder);

    default RentalOrder mapFromDTO(CustomerRentalOrderDTO customerRentalOrderDTO,
                                   PriceCalculationService priceCalculationService,
                                   CustomerService customerService)
    {
        return RentalOrder.builder()
                .rentNumber(UUID.randomUUID().toString())
                .receivedDateTime(OffsetDateTime.now())
                .rentalStartDate(customerRentalOrderDTO.getRentalStartDate())
                .rentalEndDate(customerRentalOrderDTO.getRentalEndDate())
                .totalPrice(priceCalculationService.calculateTotalPriceService(
                        customerRentalOrderDTO.getRentalStartDate(),
                        customerRentalOrderDTO.getRentalEndDate(),
                        customerRentalOrderDTO.getCarType()))
                .orderStatus(OrderStatus.NEW_ORDER)
                .customer(customerService.findCustomerByEmail(customerRentalOrderDTO.getCustomerEmail()).orElse(null))
                .carToRent(CarToRent.builder().carToRentId(customerRentalOrderDTO.getSelectedCarToRentId()).build())
                .build();
    }
}
