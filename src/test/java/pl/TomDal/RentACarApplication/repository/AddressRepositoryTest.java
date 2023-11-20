package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.TomDal.RentACarApplication.domain.Address;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.util.List;


@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AddressRepositoryTest {
    private AddressRepository addressRepository;

    @Test
    void thatSavingAddressWorksCorrectly() {
        //given
        Address address1 = TestDataFixtures.someCustomer1().getAddress();
        Address address2 = TestDataFixtures.someCustomer2().getAddress();

        //when
        addressRepository.saveAddress(address1);
        addressRepository.saveAddress(address2);
        List<Address> result = addressRepository.findAll();

        //then
        Assertions.assertEquals(result.size(), 2);
    }
}