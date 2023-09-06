package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
