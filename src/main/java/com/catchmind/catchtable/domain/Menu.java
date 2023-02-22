package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class Menu extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long meIdx;
    @ManyToOne
    @JoinColumn(name= "resa_bis_name")
    @Setter private ResAdmin resAdmin;
    @Setter private String meName;
    @Setter private String meContent;
    @Setter private String mePrice;

    protected Menu(){}
}

//me_idx	메뉴 번호	int
//bis_name	식당 이름	varchar
//me_name	메뉴 이름	varchar
//me_content	메뉴 설명	varchar
//me_price	메뉴 가격	varchar
//reg_date	등록날짜	datetime
//update_date	수정날짜	datetime