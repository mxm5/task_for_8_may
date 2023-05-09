package io.mxm.testers.config.security;

import io.mxm.testers.domains.Identity;
import io.mxm.testers.repository.IdentityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService implements UserDetailsService {

    @Autowired
    IdentityRepository identityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return identityRepository.findByUsername(username);
    }

    public Identity findUserBySubject(String sub) {
        return identityRepository.findBySubject(sub);
    }
}
