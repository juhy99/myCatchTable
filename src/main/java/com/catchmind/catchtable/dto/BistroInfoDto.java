package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.BistroInfo;
import com.catchmind.catchtable.domain.Review;

import java.time.LocalDateTime;
import java.util.List;

public record BistroInfoDto(
        Long bisIdx,
        ResAdminDto resAdminDto,
        String bisDesc,
        String bisCategory,
        String bisRegion,
        String bisLunch,
        String bisDinner,
//        String bisConvenience,
        LocalDateTime regDate,
        PhotoDto photoDto,
        Long revCnt,
        Double revAvg
) {
    public static BistroInfoDto of(Long bisIdx,ResAdminDto resAdminDto, String bisDesc,String bisCategory,String bisRegion,String bisLunch,String bisDinner,LocalDateTime regDate,PhotoDto photoDto){
        return new BistroInfoDto(bisIdx,resAdminDto,bisDesc,bisCategory,bisRegion,bisLunch,bisDinner,regDate,photoDto,null,null);
    }
    public static BistroInfoDto ofIdx(Long bisIdx){
        return new BistroInfoDto(bisIdx,null,null,null,null,null,null,null,null,null,null);
    }
    public static BistroInfoDto from(BistroInfo bistroInfo){
        return new BistroInfoDto(
                bistroInfo.getBisIdx(),
                ResAdminDto.from(bistroInfo.getResAdmin()),
                bistroInfo.getBisDesc(),
                bistroInfo.getBisCategory(),
                bistroInfo.getBisRegion(),
                bistroInfo.getBisLunch(),
                bistroInfo.getBisDinner(),
                bistroInfo.getRegDate(),
                PhotoDto.from(bistroInfo.getPhoto()),
                null,
                null
//                bistroInfo.getBisConvenience(),
        );
    }
    public static BistroInfoDto from(BistroInfo bistroInfo, List<Review> reviews){
        Double avg=0.0;
        Double sum= 0.0;
        Long cnt= 0L;
        for(Review review: reviews){
            cnt+=1L;
            sum+=review.getRevScore();
        }
        avg=sum/cnt;

        return new BistroInfoDto(
                bistroInfo.getBisIdx(),
                ResAdminDto.from(bistroInfo.getResAdmin()),
                bistroInfo.getBisDesc(),
                bistroInfo.getBisCategory(),
                bistroInfo.getBisRegion(),
                bistroInfo.getBisLunch(),
                bistroInfo.getBisDinner(),
                bistroInfo.getRegDate(),
                PhotoDto.from(bistroInfo.getPhoto()),
                cnt,
                avg
//                bistroInfo.getBisConvenience(),
        );
    }
    public BistroInfo toEntityIdx() {
        return BistroInfo.ofBisIdx(bisIdx);
    }
}
