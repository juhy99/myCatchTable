package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.Ask;
import com.catchmind.catchtable.domain.Improvement;
import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.AskRequest;
import com.catchmind.catchtable.dto.network.request.DeclareCommentRequest;
import com.catchmind.catchtable.dto.network.request.DeclareReviewRequest;
import com.catchmind.catchtable.dto.network.request.ImprovementRequest;
import com.catchmind.catchtable.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j  // 로그를 찍기 위해서 사용하는 어노테이션
@Transactional
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final AskRepository askRepository;
    private final ImprovementRepository improvementRepository;
    private final DeclareReviewRepository declareReviewRepository;
    private final DeclareCommentRepository declareCommentRepository;

    @Transactional(readOnly = true)
    public List<Ask> list(Long prIdx) {
        return askRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "askIdx"));
    }

    public List<Improvement> listImp(Long prIdx) {
        return improvementRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "impIdx"));
    }

    public List<DeclareReviewDto> listDe(Long prIdx){
        return declareReviewRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "derIdx")).stream().map(DeclareReviewDto::from).toList();
    }

    public List<DeclareCommentDto> listDec(Long prIdx){
        return declareCommentRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "decIdx")).stream().map(DeclareCommentDto::from).toList();
    }

    public AskDto getDetail(Long askIdx){
        return askRepository.findById(askIdx).map(AskDto::from).orElseThrow();
    }

    public ImprovementDto getImpDetail(Long impIdx){
        return improvementRepository.findById(impIdx).map(ImprovementDto::from).orElseThrow();
    }

    @Transactional
    public NoticeDto getNotice(Long noIdx) {
        return noticeRepository.findById(noIdx).map(NoticeDto::from).orElseThrow();
    }

    // 1:!문의 등록
    public String saveFile(AskRequest askRequest){
        AskDto newAsk = askRequest.of(askRequest.askTitle(), askRequest.askContent(), askRequest.prIdx(), askRequest.askAnswer()).toDto();
        askRepository.save(newAsk.toEntity());
        return null;
    }

    // 개선제안 등록
    public String saveImprovementFile(ImprovementRequest improvementRequest){       // saveImprovementFile 이렇게 안하니까 버튼 클릭 두번해야 페이지 넘어가고 데이터 두번 저장됨
        ImprovementDto newImprovement = improvementRequest.of(improvementRequest.impTitle(),
                improvementRequest.impContent(), improvementRequest.prIdx(), improvementRequest.impAnswer()).toDto();
        improvementRepository.save(newImprovement.toImprovementEntity());
        return null;
    }

    // 1:1문의 수정
    @Transactional
    public void updateWrite(Long askIdx, AskRequest askRequest){
        Optional<Ask> ask = askRepository.findById(askIdx);
        ask.ifPresent(
                newRes -> {
                    newRes.setAskTitle(askRequest.askTitle());
                    newRes.setAskContent(askRequest.askContent());
                    askRepository.save(newRes);
                }
        );
    }

    // 개선제안 수정
    @Transactional
    public void updateImpWrite(Long impIdx, ImprovementRequest improvementRequest){
        Optional<Improvement> improvement = improvementRepository.findById(impIdx);
        improvement.ifPresent(
                newRes -> {
                    newRes.setImpTitle(improvementRequest.impTitle());
                    newRes.setImpContent(improvementRequest.impContent());
                    improvementRepository.save(newRes);
                }
        );
    }

    // 1:1문의 삭제
    @Transactional
    public void deletePost(Long askIdx) {
        askRepository.deleteById(askIdx);
    }

    // 개선제안 삭제
    @Transactional
    public void impDeletePost(Long impIdx) {
        improvementRepository.deleteById(impIdx);
    }


    public String saveDeclareReview(DeclareReviewRequest declareReviewRequest) {
        DeclareReviewDto newDeclareReview = declareReviewRequest.of(
                declareReviewRequest.revIdx(),
                declareReviewRequest.derNick(),
                declareReviewRequest.prIdx(),
                declareReviewRequest.derTitle(),
                declareReviewRequest.derContent()
        ).toDto();
        declareReviewRepository.save(newDeclareReview.toEntity());
        return null;
    }

    public void saveDeclareComment(DeclareCommentRequest declareCommentRequest) {
        DeclareCommentDto newDeclareComment = declareCommentRequest.of(
                declareCommentRequest.revIdx(),
                declareCommentRequest.comIdx(),
                declareCommentRequest.decNick(),
                declareCommentRequest.prIdx(),
                declareCommentRequest.decTitle(),
                declareCommentRequest.decContent()
        ).toDto();
        declareCommentRepository.save(newDeclareComment.toDeclareCommentEntity());
    }
}


