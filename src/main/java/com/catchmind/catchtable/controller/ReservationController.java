package com.catchmind.catchtable.controller;

import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.PaymentRequest;
import com.catchmind.catchtable.dto.network.request.ReserveRequest;
import com.catchmind.catchtable.dto.network.response.ApproveResponse;
import com.catchmind.catchtable.dto.network.response.ReadyResponse;
import com.catchmind.catchtable.dto.security.CatchPrincipal;
import com.catchmind.catchtable.repository.ReserveRepository;
import com.catchmind.catchtable.service.KaKaoPayLogicService;
import com.catchmind.catchtable.service.ReserveLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("reservation")
@RequiredArgsConstructor
@SessionAttributes({"tid","request"})
public class ReservationController {

    private final ReserveLogicService reserveLogicService;
    private final ReserveRepository reserveRepository;
    private final KaKaoPayLogicService kakaopayService;




    @GetMapping("{resaBisName}")
    public String resMain(@PathVariable String resaBisName, Model model, @AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        if (catchPrincipal == null) {
            model.addAttribute("prIdx",0);
            return "redirect:/login";
        } else {
            model.addAttribute("prIdx", catchPrincipal.prIdx());
            model.addAttribute("resaBisName", resaBisName);
        }
        return "reservation/reservation";
    }

    @GetMapping("/update/{resaBisName}/{resIdx}")
    public String resUpdate(@PathVariable String resaBisName, @PathVariable Long resIdx,Model model){
        model.addAttribute("resaBisName",resaBisName);
        model.addAttribute("resIdx",resIdx);
        return "reservation/reservation_update";
    }

    @PostMapping("/update/planned/{resIdx}")
    @ResponseBody
    public String resUpdatePlanned(@RequestBody ReserveRequest request,@PathVariable Long resIdx){
        reserveLogicService.updateReserve(request,resIdx);
        return "/mydining/planned";
    }

    @GetMapping("/blockCheck")
    @ResponseBody
    public boolean blockCheck(@AuthenticationPrincipal CatchPrincipal catchPrincipal){
        Long prIdx = catchPrincipal.prIdx();
        ProfileDto loginUser = reserveLogicService.getUser(prIdx);
        System.out.println(loginUser.prBlock());
        if(!loginUser.prBlock()){
            return false;
        }else {
            return true;
        }
    }

    @PostMapping(path="{resaBisName}")
    @ResponseBody
    public List<ShopResTableDto> resMain(@RequestBody ReserveRequest request) {
        List<ReserveDto> list = reserveLogicService.list(request);
        TotalTableDto totDto = reserveLogicService.searchShopTable(request);
        List<ShopResTableDto> shopResTableList = reserveLogicService.timeCal(list,request.resMonth(),request.resDay(),totDto,request).stream().map(ShopResTableDto::from).toList();
        System.out.println(shopResTableList);
        return shopResTableList;
    }

    @GetMapping("{resaBisName}/payment")
    public String payment(@PathVariable String resaBisName, @AuthenticationPrincipal CatchPrincipal catchPrincipal,Model model) {
        Long prIdx = catchPrincipal.prIdx();
        ProfileDto loginUser = reserveLogicService.getUser(prIdx);
        BistroDetailDto bistroDetailDto = reserveLogicService.getInfo(resaBisName);
        System.out.println(loginUser);
        model.addAttribute("profile", loginUser);
        model.addAttribute("bistro", bistroDetailDto);
        System.out.println(bistroDetailDto);
        return "reservation/payment";
    }

    @PostMapping("/pay")
    public @ResponseBody ReadyResponse payReady(@RequestBody PaymentRequest request, Model model){
        System.out.println(request);
        System.out.println(request.total_amount());

        ReadyResponse readyResponse = kakaopayService.payReady(request);
        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
        log.info("결재고유 번호: " + readyResponse.getTid());
        model.addAttribute("tid",readyResponse.getTid());
        model.addAttribute("request",request);


        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)
    }

    @GetMapping("pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken,@ModelAttribute("request") PaymentRequest request,@ModelAttribute("tid") String tid) {
        System.out.println("🍎🍎");
        System.out.println(tid);
        System.out.println(pgToken);
        System.out.println(request.resHp());
        System.out.println(request.resPerson());
        System.out.println(request.resaBisName());
        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("주문정보: " + "?");
        // 카카오 결제 요청하기
        ApproveResponse approveResponse = kakaopayService.payApprove(pgToken,tid);

        System.out.println(approveResponse.getItem_name());
        System.out.println(approveResponse.getPayment_method_type());
        System.out.println(approveResponse.getPartner_user_id());

        reserveLogicService.saveReserve(request);


        // 5. payment 저장
        //	orderNo, payMathod, 주문명.
        // - 카카오 페이로 넘겨받은 결재정보값을 저장.

        return "redirect:/mydining/planned";
    }

    // 결제 취소시 실행 url
    @GetMapping("/pay/cancel")
    public String payCancel() {
        return "redirect:/reservation/payment";
    }

    // 결제 실패시 실행 url
    @GetMapping("/pay/fail")
    public String payFail() {
        return "redirect:/reservation/payment";
    }
}

