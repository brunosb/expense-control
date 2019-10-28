package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
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
public class UserBanco extends BaseEntity<Long> {
    private static final long serialVersionUID = -2838495355736792552L;

    @Column
    private String nome;

    @OneToMany
    @JoinColumn(name = "cartao_id")
    private List<Cartao> cartoes;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;
}
