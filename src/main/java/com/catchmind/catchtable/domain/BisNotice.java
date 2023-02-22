package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
public class BisNotice extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long binIdx;
    @ManyToOne
    @JoinColumn(name= "resa_bis_name")
    @Setter
    private ResAdmin resAdmin;
    @Setter private String binTitle;
    @Setter private String binContent;
    protected BisNotice(){}
}

