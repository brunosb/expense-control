package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CartaoMovimentacao extends BaseEntity<Long> {

    private static final long serialVersionUID = -9183823736912321759L;

    @Column
    private String estabelecimento;

    @Column
    private String descricao;

    @Column
    private Integer numParcela;

    @Column
    private Integer mesParcelaInicial;

    @Column
    private Calendar dataMov;

    @Column
    private BigDecimal valor;
}
