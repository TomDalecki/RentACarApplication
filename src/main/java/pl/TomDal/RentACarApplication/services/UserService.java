package pl.TomDal.RentACarApplication.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.domain.Role;
import pl.TomDal.RentACarApplication.domain.User;
import pl.TomDal.RentACarApplication.entity.enums.UserRole;
import pl.TomDal.RentACarApplication.repository.mapper.RoleEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.RoleDAO;
import pl.TomDal.RentACarApplication.services.dao.UserDAO;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final RoleEntityMapper roleEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String email, String password, UserRole userRole) {
        Role role = roleDAO.createRole(role(userRole));
        User newUser = user(email, password, role);
        userDAO.saveUser(newUser);
    }

    private Role role(UserRole userRole) {
        return Role.builder()
                .userRole(userRole)
                .build();
    }

    private User user(String email, String password, Role role) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Set.of(roleEntityMapper.mapToEntity(role)))
                .active(true)
                .build();
    }
}
