package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Receita extends BaseEntity<Long> {

    private static final long serialVersionUID = 5509619120637019208L;

    @Column
    private Calendar dataReceita;

    @Column
    private String origem;

    @Column
    private BigDecimal valorBruto;

    @Column
    private BigDecimal valorDespesa;

    @OneToOne
    @JoinColumn(name = "banco_movimentacao_id")
    private BancoMovimentacao bancoMovimentacao; // O valor da Movimentação será o líquido (bruto-despesa)
}
