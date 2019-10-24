package com.barbosa.dev.expensecontrol.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Create by Bruno Barbosa - 30/05/2019
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity<PK extends Serializable> implements Serializable {

    private static final long serialVersionUID = -9033073735760048812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private PK id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Calendar criado;

    @LastModifiedDate
    @Column(nullable = false)
    @JsonIgnore
    private Calendar atualizado;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    private String criadoBy;

    @LastModifiedBy
    @JsonIgnore
    private String atualizadoBy;
}
