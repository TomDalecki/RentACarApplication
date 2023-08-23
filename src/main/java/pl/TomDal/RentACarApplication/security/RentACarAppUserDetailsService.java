package pl.TomDal.RentACarApplication.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RentACarAppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) {
        final UserEntity user = userRepository.findByEmail(userEmail);
        if (user == null) {
            log.warn("user not found: {}", userEmail);
            throw new UsernameNotFoundException("User " + userEmail + " not found");
        }
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .distinct()
                .collect(Collectors.toList());
    }

    private UserDetails buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorities
        );
    }
}

