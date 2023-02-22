package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TalkAdmin extends AuditingField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taaIdx;
    @ManyToOne
    @JoinColumn(name="pr_idx")
    private Profile profile;
    private String taaContent;

    protected TalkAdmin() {}

    public TalkAdmin(Long taaIdx, Profile profile, String taaContent) {
        this.taaIdx = taaIdx;
        this.profile = profile;
        this.taaContent = taaContent;
    }
}
