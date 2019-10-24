package com.barbosa.dev.expensecontrol.model;

import com.barbosa.dev.expensecontrol.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.sql.Blob;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Imagem extends BaseEntity<Long> {

    private static final long serialVersionUID = 8836432164111627388L;

    @Column(nullable = false)
    private String nome;

    @Lob
    @Type(type = "org.hibernate.type.BlobType")
    @JsonIgnore
    private Blob image;

    @Column
    private String extensao;
}
