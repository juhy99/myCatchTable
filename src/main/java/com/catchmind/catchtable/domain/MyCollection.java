package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Data
@ToString (callSuper = true)
@Entity
@EqualsAndHashCode(callSuper = true)
public class MyCollection extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_idx")
    private Long colIdx;
    private String colTitle;
    private String colContent;
    private boolean colLock;
    @ManyToOne(optional = false)
    @JoinColumn(name = "prIdx")
    private Profile profile;
    private String bisNames;

    protected MyCollection() {}
    private MyCollection(String colTitle, String colContent, boolean colLock, Profile profile) {
        this.colTitle = colTitle;
        this.colContent = colContent;
        this.colLock = colLock;
        this.profile = profile;
    }
    private MyCollection(Long colIdx) {
        this.colIdx = colIdx;
    }

    public static MyCollection of(String colTitle, String colContent, boolean colLock, Profile profile) {
        return new MyCollection(colTitle,colContent,colLock, profile);
    }
    public static MyCollection ofIdx(Long colIdx ) {
        return new MyCollection(colIdx);
    }

}