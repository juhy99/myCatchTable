package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class Facility extends AuditingField {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //increment
    private Long facIdx;

    @ManyToOne //fk
    @JoinColumn(name="resa_bis_name")
    @Setter
    private ResAdmin resAdmin; //엔티티

    @ManyToOne
    @JoinColumn(name="bis_idx")
    @Setter
    private BistroInfo bistroInfo;

    @Setter private boolean facParking;
    @Setter private boolean facValet;
    @Setter private boolean facCorkage;
    @Setter private boolean facNokid;
    @Setter private boolean facAnimal;
    @Setter private boolean facHandi;

    protected Facility(){}


}

//fac_idx	편의시설 번호	int
//bis_idx	식당정보번호	int
//bis_name	식당 이름	varchar
//fac_parking	주차 가능 여부	boolean
//fac_valet	발렛 가능 여부	boolean
//fac_corkage	콜키지 가능 여부	boolean
//fac_nokid	노키즈존 여부	boolean
//fac_animal	반려동물 동반 가능 여부	boolean
//fac_handi	장애인 편의시설 유무	boolean
//reg_date	등록날짜	datetime