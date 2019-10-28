package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserSystem extends BaseEntity<Long> {

    private static final long serialVersionUID = -1475669764106632744L;
    @Column(unique = true)
    private String login;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String nome;

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permission> permissoes = new HashSet<>();

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;
}
