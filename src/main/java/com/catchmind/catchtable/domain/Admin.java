package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString(callSuper = true)
public class Admin extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adIdx;         // 관리자 번호
    private String adUserid;    // 관리자 아이디
    private String adUserpw;    // 관리자 비밀번호
    private String adName;      // 관리자 이름
    private String adHp;        // 관리자 전화번호
    private String adEmail;     // 관리자 이메일

    protected Admin(){}

    public Admin(Long adIdx, String adUserid, String adUserpw, String adName, String adHp, String adEmail) {
        this.adIdx = adIdx;
        this.adUserid = adUserid;
        this.adUserpw = adUserpw;
        this.adName = adName;
        this.adHp = adHp;
        this.adEmail = adEmail;
    }
}
