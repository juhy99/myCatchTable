package com.catchmind.catchtable.domain;

import com.catchmind.catchtable.domain.type.MemberRole;
import com.catchmind.catchtable.dto.network.request.ProfileRequest;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


//@Table(name = "profile")
@ToString(callSuper = true)
@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prIdx;
    private String prNick;
    private String prName;
    private String prIntro;
    private String prRegion;
    private String prHp;
    private String prUserpw;
    private String prGender;
    private String prBirth;
    private String prMemo;
    private int prReview;
    private int prNoshow;
    private boolean prBlock;
    private int prPoint;
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public Profile(){}

    @Builder
    public Profile(String prNick, String prName, String prIntro, String prRegion, String prHp, String prUserpw, String prGender, String prBirth, String prMemo, int prReview, int prNoshow, boolean prBlock, int prPoint, MemberRole role) {

        this.prNick = prNick;
        this.prName = prName;
        this.prIntro = prIntro;
        this.prRegion = prRegion;
        this.prHp = prHp;
        this.prUserpw = prUserpw;
        this.prGender = prGender;
        this.prBirth = prBirth;
        this.prMemo = prMemo;
        this.prReview = prReview;
        this.prNoshow = prNoshow;
        this.prBlock = prBlock;
        this.prPoint = prPoint;
        this.role = role;
    }


    public static Profile createMember(ProfileRequest request, PasswordEncoder passwordEncoder) {
        Profile profile = Profile.builder()
                .prNick(request.prNick())
                .prName(request.prName())
                .prUserpw(passwordEncoder.encode(request.prUserpw()))  //암호화처리
                .prIntro(request.prIntro())
                .role(MemberRole.USER)
                .prGender(request.prGender())
                .prRegion(request.prRegion())
                .prBirth(request.prBirth())
                .prHp(request.prHp())
                .build();
        return profile;
    }

    public static Profile of(String prNick, String prName, String prIntro, String prRegion, String prHp, String prUserpw, String prGender, String prBirth, String prMemo, int prReview, int prNoshow, boolean prBlock, int prPoint, MemberRole role) {
        return new Profile(prNick, prName, prIntro, prRegion, prHp, prUserpw, prGender, prBirth, prMemo, prReview, prNoshow, prBlock, prPoint, role);
    }

    public Profile(Long prIdx){
        this.prIdx = prIdx;
    }

    public static Profile ofIdx(Long prIdx) {
        return new Profile(prIdx);
    }

}
