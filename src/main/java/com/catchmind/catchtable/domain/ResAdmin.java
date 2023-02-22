package com.catchmind.catchtable.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@ToString
@Entity
public class ResAdmin extends AuditingField{
    @Id
    private String resaBisName;
    @Setter @Column(length = 100, unique = true)
    private String resaUserid;
    @Setter @Column(length = 100, nullable = false)
    private String resaUserpw;
    @Setter @Column(length = 100, nullable = false)
    private String resaName;
    @Setter @Column(length = 100, nullable = false)
    private String resaHp;
    @Setter @Column(length = 500, nullable = false)
    private String resaRegion;

    protected ResAdmin() {}

    @Builder
    public ResAdmin(String resaBisName, String resaUserid, String resaUserpw, String resaName, String resaHp, String resaRegion) {
        this.resaBisName = resaBisName;
        this.resaUserid = resaUserid;
        this.resaUserpw = resaUserpw;
        this.resaName = resaName;
        this.resaHp = resaHp;
        this.resaRegion = resaRegion;
    }

    public static ResAdmin of(String resaBisName, String resaUserid, String resaUserpw, String resaName, String resaHp, String resaRegion) {
        return new ResAdmin(resaBisName, resaUserid, resaUserpw, resaName, resaHp, resaRegion);
    }

    public ResAdmin(String resaBisName) {
        this.resaBisName = resaBisName;
    }
    public static ResAdmin of1(String resaBisName) {
        return new ResAdmin(resaBisName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resaBisName);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof ResAdmin resAdmin)) return false;
        return resaBisName != null && resaBisName.equals(resAdmin.resaBisName);
    }


}
