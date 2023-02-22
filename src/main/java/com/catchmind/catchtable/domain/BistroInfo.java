package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class BistroInfo extends AuditingField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bisIdx;    // 식당 번호
    @ManyToOne(optional = false)
    @JoinColumn(name = "resa_bis_name")
    private ResAdmin resAdmin;   // 식당 이름 FK
    private String bisDesc; // 식당 설명
    private String bisCategory; // 음식 카테고리
    private String bisRegion;   // 식당이 있는 지역
    private String bisLunch;    // 점심 시간대 가격
    private String bisDinner;   // 저녁 시간대 가격
    // 테이블에서 빠짐 -> 편의시설 테이블로
//    private String bisConvenience;  // 편의시설

    @ManyToOne
    @JoinColumn(name="ph_idx")
    @Setter
    private Photo photo;
    protected BistroInfo() {}
    public BistroInfo(Long bisIdx){
        this.bisIdx = bisIdx;
    }
    public static BistroInfo ofBisIdx(Long bisIdx) {
        return new BistroInfo(bisIdx);
    }
}
