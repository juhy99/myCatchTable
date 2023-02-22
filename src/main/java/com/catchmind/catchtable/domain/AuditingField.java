package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)  // JpaConfig에서 사용한 것을 알려주기 위한 어노테이션
@MappedSuperclass   // 부모클래스로 매핑
public abstract class AuditingField {
    // 공통적으로 들어가는 regDate와 updateDate를 한번에 관리하기 위한 추상 클래스

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // 날짜 포맷
    @CreatedDate    //해당 엔티티가 생성될 때, 생성하는 시각을 자동으로 삽입해준다.
    @Column(nullable = false)
    private LocalDateTime regDate; // 생성일시


}
