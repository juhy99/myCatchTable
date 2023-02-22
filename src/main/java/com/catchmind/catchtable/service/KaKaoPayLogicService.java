package com.catchmind.catchtable.service;


import com.catchmind.catchtable.dto.network.request.PaymentRequest;
import com.catchmind.catchtable.dto.network.response.ApproveResponse;
import com.catchmind.catchtable.dto.network.response.ReadyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KaKaoPayLogicService {

    public ReadyResponse payReady(PaymentRequest request) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", "partner_order_id");
        parameters.add("partner_user_id", "inflearn");
        parameters.add("item_name", request.resaBisName());
        parameters.add("quantity", "1");
        parameters.add("total_amount", request.total_amount());
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8888/reservation/pay/completed"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8888/reservation/pay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8888/reservation/pay/fail"); // 결제 실패시 넘어갈 url

        log.info("파트너주문아이디:"+ parameters.get("partner_order_id")) ;
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        // 외부url요청 통로 열기.
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        // template으로 값을 보내고 받아온 ReadyResponse값 readyResponse에 저장.
        ReadyResponse readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);
        log.info("결재준비 응답객체: " + readyResponse);
        // 받아온 값 return
        return readyResponse;
    }

    public ApproveResponse payApprove(String pgToken, String tid) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", tid);
        parameters.add("partner_order_id", "partner_order_id"); // 주문명
        parameters.add("partner_user_id", "inflearn");
        parameters.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부url 통신
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결재승인 응답객체: " + approveResponse);

        return approveResponse;
    }

    // header() 셋팅
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 662ebe948a51a7b5605a058507228c79");
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
