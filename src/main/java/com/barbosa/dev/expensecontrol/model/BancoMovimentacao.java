package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.enums.MovBanco;
import com.barbosa.dev.expensecontrol.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BancoMovimentacao extends BaseEntity<Long> {
    private static final long serialVersionUID = -7003854496475345066L;

    @Column
    private String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    private MovBanco movimentacaoBanco;

    @Column
    private Calendar dataMov;

    @Column
    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "banco_id")
    @JsonBackReference
    private Banco banco;
}
