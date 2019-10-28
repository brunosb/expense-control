package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.enums.FormaDespesa;
import com.barbosa.dev.expensecontrol.util.BaseEntity;
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
public class Despesa extends BaseEntity<Long> {

    private static final long serialVersionUID = 1106134325786102895L;

    @Column
    private String descricao;

    @Column
    private BigDecimal valor;

    @Column
    @Enumerated(EnumType.STRING)
    private FormaDespesa formaDespesa;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column
    private Integer numCheque;

    @OneToOne
    @JoinColumn(name = "cartao_movimentacao_id")
    private CartaoMovimentacao cartaoMovimentacao;

    @Column
    private Calendar dataDespesa;
}
