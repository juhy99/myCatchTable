package com.catchmind.catchtable.domain;

import com.catchmind.catchtable.domain.type.ReservationType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

//@Table(name = "reserve")
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class Reserve extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resIdx;    // 예약 번호
    @ManyToOne(optional = false)
    @JoinColumn(name = "resa_bis_name")
    private ResAdmin resAdmin;   // 식당 이름 FK
    //    private String resaBisName;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bis_idx")
    private BistroInfo bistroInfo;  // 식당 번호 FK bis_idx
    //    private Long bisIdx;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_idx")
    private Profile profile;    // 예약 번호 FK pr_idx
    //    private Long prIdx;
    private String prName;  // 예약자 이름
    private String resHp;   // 예약자 전화번호
    private String resRequest;  // 예약 요청사항
    private String visitName;   // 방문자 이름
    private String visitHp; // 방문자 전화번호
    private String resMonth; // 예약 달
    private String resDay;   // 예약 일
    private String resReason;   // 취소 사유
    private Long resPerson;  // 예약 인원수
    private String resTime; // 예약 시간
    @Enumerated(EnumType.STRING)
    private ReservationType resStatus;  // 예약 상태
    @ManyToOne(optional = false)
    @JoinColumn(name = "bd_idx")
    private BistroDetail bistroDetail;  // 예약 상세 정보 Fk bd_idx

    private boolean revStatus;      // 예약 상태 default 0(false)


    protected Reserve() {}

    @Builder
    public Reserve(Long resIdx, ResAdmin resAdmin, BistroInfo bistroInfo, Profile profile, String prName, String resHp, String resRequest, String visitName, String visitHp, String resMonth, String resDay, String resReason, Long resPerson, String resTime, ReservationType resStatus, BistroDetail bistroDetail, boolean revStatus) {
        this.resIdx = resIdx;
        this.resAdmin = resAdmin;
        this.bistroInfo = bistroInfo;
        this.profile = profile;
        this.prName = prName;
        this.resHp = resHp;
        this.resRequest = resRequest;
        this.visitName = visitName;
        this.visitHp = visitHp;
        this.resMonth = resMonth;
        this.resDay = resDay;
        this.resReason = resReason;
        this.resPerson = resPerson;
        this.resTime = resTime;
        this.resStatus = resStatus;
        this.bistroDetail = bistroDetail;
        this.revStatus = revStatus;
    }


    public Reserve(Long resIdx) {
        this.resIdx = resIdx;
    }

    public Reserve(ResAdmin resAdmin, String prName, String resHp, String resRequest,
                   Long resPerson, String resTime, String resMonth, String resDay,
                   ReservationType resStatus,
                   BistroInfo bistroInfo,
                   BistroDetail bistroDetail,
                   Profile profile) {
        this.resAdmin = resAdmin;
        this.prName = prName;
        this.resHp = resHp;
        this.resRequest = resRequest;
        this.resPerson = resPerson;
        this.resTime = resTime;
        this.resMonth = resMonth;
        this.resDay = resDay;
        this.resStatus = resStatus.PLANNED;
        this.bistroInfo = bistroInfo;
        this.bistroDetail = bistroDetail;
        this.profile = profile;
    }

    public static Reserve of(Long resIdx) {
        return new Reserve(resIdx);
    }



    public static Reserve ofKakao(ResAdmin resAdmin, String prName, String resHp, String resRequest, Long resPerson, String resTime, String resMonth, String resDay,ReservationType resStatus, BistroInfo bistroInfo, BistroDetail bistroDetail, Profile profile){
        return new Reserve(resAdmin,prName,resHp,resRequest,resPerson,resTime,resMonth,resDay,resStatus, bistroInfo, bistroDetail, profile);
    }


    public Reserve(boolean revStatus) {
        this.revStatus = revStatus;
    }

    public static Reserve of(boolean revStatus) {
        return new Reserve(revStatus);
    }

}
