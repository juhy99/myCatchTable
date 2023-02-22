package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phIdx;
    @ManyToOne
    @JoinColumn(name="resa_bis_name")
    @Setter private ResAdmin resAdmin;
    @Setter private String orgNm;
    @Setter private String savedNm;
    @Setter private String savedPath;
    @Setter private String type;


    protected Photo(){}
    public Photo(String savedPath) {
        this.savedPath = savedPath;
    }

    public static Photo of1(String savedPath) {
        return new Photo(savedPath);
    }
}
//ph_idx int auto_increment primary key,
//    org_nm varchar(1000) not null,
//    saved_nm varchar(1000) not null,
//    saved_path varchar(1000) not null,
//    type varchar(100) not null,
//    resa_bis_name varchar(100) not null,
//	foreign key (resa_bis_name) references res_admin (resa_bis_name) on update cascade on delete cascade