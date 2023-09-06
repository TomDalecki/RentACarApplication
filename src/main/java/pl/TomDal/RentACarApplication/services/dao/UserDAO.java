package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.User;
import pl.TomDal.RentACarApplication.entity.UserEntity;

public interface UserDAO {
    void saveUser(User user);

    UserEntity findByEmail(String userEmail);
}
