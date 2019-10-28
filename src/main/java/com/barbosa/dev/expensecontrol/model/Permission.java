package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.enums.RoleName;
import com.barbosa.dev.expensecontrol.util.BaseEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Permission extends BaseEntity<Long> {

    private static final long serialVersionUID = 2377503146917744119L;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
}
