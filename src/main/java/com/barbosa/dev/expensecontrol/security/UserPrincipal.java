package com.barbosa.dev.expensecontrol.security;

import com.barbosa.dev.expensecontrol.model.UserSystem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by Bruno Barbosa - 30/05/2019
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 5823795180116824868L;

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String nome;
    @JsonIgnore
    private String email;
    private Long imagem;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(UserSystem usuario) {
        List<GrantedAuthority> authorities = usuario.getPermissoes().stream().map(permissao -> new SimpleGrantedAuthority(permissao.getName().name())).collect(Collectors.toList());
        return UserPrincipal.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .password(usuario.getPassword())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .imagem(usuario.getImagem() != null ? usuario.getImagem().getId() : null)
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
