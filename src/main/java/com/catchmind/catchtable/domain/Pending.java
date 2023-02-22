package com.catchmind.catchtable.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Pending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penIdx;
    private String penBisName;
    private String penName;
    private String penHp;
    private String penRegion;
    private Long penBook;
    private String penFoodtype;

    public Pending(String penBisName, String penName, String penHp, String penRegion, Long penBook, String penFoodtype) {
        this.penBisName = penBisName;
        this.penName = penName;
        this.penHp = penHp;
        this.penRegion = penRegion;
        this.penBook = penBook;
        this.penFoodtype = penFoodtype;
    }

    protected Pending() {}

    public static Pending of(String penBisName, String penName, String penHp, String penRegion, Long penBook, String penFoodtype) {
        return new Pending(penBisName, penName, penHp, penRegion, penBook, penFoodtype);
    }
}


