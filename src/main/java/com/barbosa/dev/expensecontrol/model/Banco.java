package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Banco extends BaseEntity<Long> {
    private static final long serialVersionUID = -3418955276086129302L;

    @Column
    private String nome;

    @ManyToMany
    @JoinTable(name = "banco_usuario", joinColumns = @JoinColumn(name = "banco_id"), inverseJoinColumns = @JoinColumn(name = "usuario_banco_id"))
    private List<UserBanco> usersBanco;

    @OneToMany
    @JoinColumn(name = "movimentacao_id")
    @JsonManagedReference
    private List<BancoMovimentacao> movimentacoes;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;
}
