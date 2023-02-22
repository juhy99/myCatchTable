package com.catchmind.catchtable.service;


import com.catchmind.catchtable.domain.Reserve;
import com.catchmind.catchtable.domain.ShopResTable;
import com.catchmind.catchtable.dto.BistroDetailDto;
import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ReserveDto;
import com.catchmind.catchtable.dto.TotalTableDto;
import com.catchmind.catchtable.dto.network.request.PaymentRequest;
import com.catchmind.catchtable.dto.network.request.ReserveRequest;
import com.catchmind.catchtable.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReserveLogicService {
    private final ProfileRepository profileRepository;

    private final ReserveRepository reserveRepository;
    private final TotalTableRepository totalTableRepository;

    private final ShopResTableRepository shopResTableRepository;
    private final BistroDetailRepository bistroDetailRepository;

    public ProfileDto getUser(Long prIdx){
        return profileRepository.findById(prIdx).map(ProfileDto::from).orElseThrow();
    }


    public List<ReserveDto> list(ReserveRequest request) {
        String resMonth = request.resMonth();
        String resDay = request.resDay();
        String resaBisName = request.resaBisName();
        List<ReserveDto> reserves = reserveRepository.findAllByResMonthAndResDayAndResAdmin_ResaBisName(resMonth, resDay, resaBisName).stream().map(ReserveDto::from).toList();
        if(reserves.isEmpty()){
            return null;
        }
        return reserves;
    }

    public List<ShopResTable> timeCal(List<ReserveDto> list, String resMonth, String resDay, TotalTableDto totalTableDto, ReserveRequest request) {
        Long totalTableId = totalTableDto.totTableId();
        int ifPeople = 0;
        int people = 0;
        int people10 = 0;
        int people12 = 0;
        int people14 = 0;
        int people17 = 0;
        int people19 = 0;
        int people21 = 0;
        String time = null;
        String month = resMonth;
        String day = resDay;
        if (list!=null){
            int personnel = (totalTableDto.totCapacity()*totalTableDto.totTable());

            if(Integer.parseInt(request.resPerson())%2==1 || Integer.parseInt(request.resPerson())==1){
                ifPeople = Integer.parseInt(request.resPerson())+1; // 3 ->4
            }else{
                ifPeople = Integer.parseInt(request.resPerson()); // 4
            }

            for(int i = 0; i < list.size(); i++){
                time = list.get(i).resTime();
                switch (time){
                    case "10":
                        people = Integer.parseInt(String.valueOf(list.get(i).resPerson()));
                        people10 += people;
                        if(people10 == personnel || personnel-people10 < ifPeople){
                            changeCloseStatus(time,month,day,totalTableId);
                        }else{
                            changeOpenStatus(time,month,day,totalTableId);
                        }
                        break;
                    case "12":
                        people = Integer.parseInt(String.valueOf(list.get(i).resPerson()));
                        people12 += people;
                        if(people12 == personnel || personnel-people12 < ifPeople){
                            changeCloseStatus(time,month,day,totalTableId);
                        }else{
                            changeOpenStatus(time,month,day,totalTableId);
                        }
                        break;
                    case "14":
                        people = Integer.parseInt(String.valueOf(list.get(i).resPerson()));
                        people14 += people;
                        if(people14 == personnel || personnel-people14 < ifPeople){
                            changeCloseStatus(time,month,day,totalTableId);
                        }else{
                            changeOpenStatus(time,month,day,totalTableId);
                        }
                        break;
                    case "17":
                        people = Integer.parseInt(String.valueOf(list.get(i).resPerson()));
                        people17 += people;
                        if(people17 == personnel || personnel-people17 < ifPeople){
                            changeCloseStatus(time,month,day,totalTableId);
                        }else{
                            changeOpenStatus(time,month,day,totalTableId);
                        }
                        break;
                    case "19":
                        people = Integer.parseInt(String.valueOf(list.get(i).resPerson()));
                        people19 += people;
                        if(people19 == personnel || personnel-people19 < ifPeople){
                            changeCloseStatus(time,month,day,totalTableId);
                        }else{
                            changeOpenStatus(time,month,day,totalTableId);
                        }
                        break;
                    case "21":
                        people = Integer.parseInt(String.valueOf(list.get(i).resPerson()));
                        people21 += people;
                        if(people21 == personnel || personnel-people21 < ifPeople){
                            changeCloseStatus(time,month,day,totalTableId);
                        }else{
                            changeOpenStatus(time,month,day,totalTableId);
                        }
                        break;
                }
            }
            List<ShopResTable> shopResTableList = shopResTableRepository.findByTotalTable_TotTableIdAndShopResMonthAndShopResDayAndShopResStatus(totalTableId,month,day,true);
            return shopResTableList;
        }else {
            List<ShopResTable> shopResTableList = shopResTableRepository.findByTotalTable_TotTableIdAndShopResMonthAndShopResDayAndShopResStatus(totalTableId,month,day,true);
            return shopResTableList;
        }

    }


    public TotalTableDto searchShopTable(ReserveRequest request) {
        String resaBisName = request.resaBisName();
        TotalTableDto totTable = totalTableRepository.findByResAdmin_ResaBisName(resaBisName).map(TotalTableDto::from).orElseThrow();
        return totTable;
    }

    public void changeCloseStatus(String time,String month,String day,Long totTableId){
        try{
            Optional<ShopResTable> shopResTable = shopResTableRepository.findByTotalTable_TotTableIdAndShopResMonthAndShopResDayAndShopResTime(totTableId,month,day,time);
            shopResTable.ifPresent(
                    shopResTable1 -> {
                        shopResTable1.setShopResStatus(false);
                        shopResTableRepository.save(shopResTable1);
                    }
            );
        }catch (EntityNotFoundException e){
            System.out.println("상태변경실패!");
            e.printStackTrace();
        }
    }

    private void changeOpenStatus(String time, String month, String day, Long totalTableId) {
        try{
            Optional<ShopResTable> shopResTable = shopResTableRepository.findByTotalTable_TotTableIdAndShopResMonthAndShopResDayAndShopResTime(totalTableId,month,day,time);
            shopResTable.ifPresent(
                    shopResTable1 -> {
                        shopResTable1.setShopResStatus(true);
                        shopResTableRepository.save(shopResTable1);
                    }
            );
        }catch (EntityNotFoundException e){
            System.out.println("상태변경실패!");
            e.printStackTrace();
        }
    }


    public void saveReserve(PaymentRequest request) {
        ReserveDto reserveDto = PaymentRequest.of(request.resaBisName(), request.prName(),
                request.resHp(), request.resRequest(), request.resPerson(), request.resTime(), request.resMonth(),
                request.resDay(), request.bisIdx(), request.bisIdx(), request.prIdx()).toDto();
        System.out.println("😂");
        reserveRepository.save(reserveDto.toEntityKaKao());
    }

    public BistroDetailDto getInfo(String resaBisName) {
        return bistroDetailRepository.findByResAdmin_ResaBisName(resaBisName).map(BistroDetailDto::from).orElseThrow();
    }

    public Reserve updateReserve(ReserveRequest request, Long resIdx) {
        Reserve reserve = reserveRepository.findById(resIdx).orElse(null);
        reserve.setResMonth(request.resMonth());
        reserve.setResDay(request.resDay());
        reserve.setResTime(request.resTime());
        reserve.setResPerson(Long.valueOf(request.resPerson()));
        return reserveRepository.save(reserve);
    }
}
