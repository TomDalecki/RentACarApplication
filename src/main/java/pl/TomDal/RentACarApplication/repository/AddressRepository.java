package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.Address;
import pl.TomDal.RentACarApplication.repository.jpa.AddressJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.AddressEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.AddressDAO;

import java.util.List;

@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {

    private final AddressJpaRepository addressJpaRepository;
    private final AddressEntityMapper addressEntityMapper;

    public void saveAddress(Address address) {
        addressJpaRepository.save(addressEntityMapper.mapToEntity(address));
    }

    public List<Address> findAll() {
        return addressJpaRepository.findAll().stream()
                .map(addressEntityMapper::mapFromEntity)
                .toList();
    }
}
