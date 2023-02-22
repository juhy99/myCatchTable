package com.catchmind.catchtable.dto.security;


import com.catchmind.catchtable.dto.ProfileDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record CatchPrincipal(
        Long prIdx,
        String prNick,
        String prName,
        String prIntro,
        String prRegion,
        String prHp,
        String prUserpw,
        Collection<? extends GrantedAuthority> authorities,
        String prGender,
        String prBirth,
        String prMemo,
        int prReview,
        int prNoshow,
        boolean prBlock,
        int prPoint

) implements UserDetails {

    public static CatchPrincipal of(Long prIdx, String prNick, String prName, String prIntro, String prRegion, String prHp, String prUserpw, String prGender, String prBirth, String prMemo, int prReview, int prNoshow, boolean prBlock, int prPoint){
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new CatchPrincipal(
                prIdx,
                prNick,
                prName,
                prIntro,
                prRegion,
                prHp,
                prUserpw,
                roleTypes.stream().map(RoleType::getPrHp)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                prGender,
                prBirth,
                prMemo,
                prReview,
                prNoshow,
                prBlock,
                prPoint
        );
    }

    public static CatchPrincipal from(ProfileDto dto){
        return CatchPrincipal.of(
                dto.prIdx(),
                dto.prNick(),
                dto.prName(),
                dto.prIntro(),
                dto.prRegion(),
                dto.prHp(),
                dto.prUserpw(),
                dto.prGender(),
                dto.prBirth(),
                dto.prMemo(),
                dto.prReview(),
                dto.prNoshow(),
                dto.prBlock(),
                dto.prPoint()
        );
    }

    public ProfileDto toDto(){
        return ProfileDto.of(
                prIdx,
                prNick,
                prName,
                prIntro,
                prRegion,
                prHp,
                prUserpw,
                prGender,
                prBirth,
                prMemo,
                prReview,
                prNoshow,
                prBlock,
                prPoint
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return prUserpw;
    }

    @Override
    public String getUsername() {
        return prName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum RoleType {
        USER("ROLE_USER");

        @Getter
        private final String prHp;

        RoleType(String prHp){
            this.prHp = prHp;
        }
    }
}
