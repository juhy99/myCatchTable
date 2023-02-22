package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
public class Notice extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noIdx;             // 공지사항 번호
    private String noTitle;         // 공지사항 제목
    private String noContent;       // 공지사항 내용

    protected Notice() {}

    public Notice(String noTitle, String noContent) {
        this.noTitle = noTitle;
        this.noContent = noContent;
    }

    public static Notice of(String noTitle, String noContent){
        return new Notice(noTitle, noContent);
    }

    @Override
    public int hashCode() { return Objects.hash(noIdx); }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Notice notice)) return false;
        return noIdx != null && noIdx.equals(notice.noIdx);
    }
}
