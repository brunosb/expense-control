package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Cartao extends BaseEntity<Long> {

    private static final long serialVersionUID = 2115573597467150779L;

    @Column
    private String nome;

    @Column
    private String bandeira;

    @Column
    private Integer diaVencimento;

    @OneToMany
    @JoinColumn(name = "movimentacao_id")
    private List<CartaoMovimentacao> movimentacao;

    @Column
    private boolean debito;

    @Column
    private boolean credito;

    @Column
    private BigDecimal limite;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;
}
