package com.barbosa.dev.expensecontrol.security;

import com.barbosa.dev.expensecontrol.model.UserSystem;
import com.barbosa.dev.expensecontrol.repository.UserSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by Bruno Barbosa - 30/05/2019
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserSystemRepository userSystemRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {
        UserSystem usuario = userSystemRepository.findByEmailOrLogin(loginOrEmail, loginOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com login ou email: " + loginOrEmail));
        return UserPrincipal.create(usuario);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        UserSystem user = userSystemRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com id: " + id));
        return UserPrincipal.create(user);
    }
}
