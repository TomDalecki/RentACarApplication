package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.User;
import pl.TomDal.RentACarApplication.entity.UserEntity;
import pl.TomDal.RentACarApplication.repository.jpa.UserJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.UserEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.UserDAO;

@Repository
@AllArgsConstructor
public class UserRepository implements UserDAO {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        userJpaRepository.save(userEntityMapper.mapToEntity(user));
    }

    @Override
    public UserEntity findByEmail(String userEmail) {
        return userJpaRepository.findByEmail(userEmail);
    }

}
