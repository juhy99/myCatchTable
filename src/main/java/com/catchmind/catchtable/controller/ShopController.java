package com.catchmind.catchtable.controller;

import com.catchmind.catchtable.domain.BistroInfo;
import com.catchmind.catchtable.domain.BistroSave;
import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.BistroSaveRequest;
import com.catchmind.catchtable.dto.network.response.ReviewResponse;
import com.catchmind.catchtable.dto.network.response.ShopListResponse;
import com.catchmind.catchtable.dto.network.response.ShopResponse;
import com.catchmind.catchtable.dto.network.response.ShopReviewResponse;
import com.catchmind.catchtable.dto.security.CatchPrincipal;
import com.catchmind.catchtable.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {
    private final BistroDetailLogicService bistroDetailLogicService;
    private final BistroInfoLogicService bistroInfoLogicService;
    private final MenuLogicService menuLogicService;
    private final BisNoticeLogicService bisNoticeLogicService;
    private final ReviewLogicService reviewLogicService;
    private final PaginationService paginationService;
    private final FacilityLogicService facilityLogicService;
    private final PhotoLogicService photoLogicService;
    private final ShopService shopService;
    private final BistroSaveService bistroSaveService;

    // 식당별 리뷰 프로그레스만 뺀 메소드
    public ShopReviewResponse reviewProgress(String resaBisName) {
        List<ReviewDto> lists = reviewLogicService.reviewList(resaBisName);
        Double sum = 0.0;
        Double avg = 0.0;
        int cnt = 0;
        for (ReviewDto review : lists) {
            sum += review.revScore();
            cnt += 1;
        }
        avg = sum / cnt;
        String average = String.format("%.1f", avg);
        //그래프
        Long score5 = 0L;
        Long score4 = 0L;
        Long score3 = 0L;
        Long score2 = 0L;
        Long score1 = 0L;
        Long scoreSum = 0L;

        for (ReviewDto reviewDto : lists) {
            if ((int) Math.round(reviewDto.revScore()) == 5) {
                score5 += 1L;
            } else if ((int) Math.round(reviewDto.revScore()) == 4) {
                score4 += 1L;
            } else if ((int) Math.round(reviewDto.revScore()) == 3) {
                score3 += 1L;
            } else if ((int) Math.round(reviewDto.revScore()) == 2) {
                score2 += 1L;
            } else if ((int) Math.round(reviewDto.revScore()) == 1) {
                score1 += 1L;
            }
        }
        scoreSum = score1 + score2 + score3 + score4 + score5;
        ShopReviewResponse response = new ShopReviewResponse(average, cnt, score1, score2, score3, score4, score5, scoreSum);
        return response;
    }

    // 식당별 리뷰
    @GetMapping("/review/{resaBisName}")
    public String shopReview(@PageableDefault(size = 10) Pageable pageable,
                             @RequestParam(value = "sort", required = false) String sort,
                             ModelMap map,
                             @PathVariable String resaBisName,
                             @AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        Page<ReviewResponse> reviews = null;
        ShopReviewResponse response = reviewProgress(resaBisName);
        String filtername = "최근 등록순";
        if (sort == null) {
            if (catchPrincipal == null) {
                reviews = shopService.getBisNameReviews(pageable, resaBisName, null, sort);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                map.addAttribute("reviews", reviews);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
                map.addAttribute("prIdx", 0);
            } else {
                Long prIdx = catchPrincipal.prIdx();
                reviews = shopService.getBisNameReviews(pageable, resaBisName, prIdx, sort);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                map.addAttribute("reviews", reviews);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
                map.addAttribute("prIdx", prIdx);
            }
        } else {
            switch (sort) {
                case "regDate":
                    if (catchPrincipal == null) {
                        reviews = shopService.getBisNameReviews(pageable, resaBisName, null, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                        map.addAttribute("reviews", reviews);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        reviews = shopService.getBisNameReviews(pageable, resaBisName, prIdx, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                        map.addAttribute("reviews", reviews);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("sort", sort);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("filtername", filtername);
                    }
                    break;
                case "highScore":
                    filtername = "별점 높은 순";
                    if (catchPrincipal == null) {
                        reviews = shopService.getBisNameReviews(pageable, resaBisName, null, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                        map.addAttribute("reviews", reviews);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        reviews = shopService.getBisNameReviews(pageable, resaBisName, prIdx, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                        map.addAttribute("reviews", reviews);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("prIdx", prIdx);
                    }
                    break;
                case "lowScore":
                    filtername = "별점 낮은 순";
                    if (catchPrincipal == null) {
                        reviews = shopService.getBisNameReviews(pageable, resaBisName, null, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                        map.addAttribute("reviews", reviews);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        reviews = shopService.getBisNameReviews(pageable, resaBisName, prIdx, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
                        map.addAttribute("reviews", reviews);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("prIdx", prIdx);
                    }
                    break;
            }
        }
        map.addAttribute("resaBisName", resaBisName);
        map.addAttribute("progress", response);

        return "shop/shopReview";
    }

    // 식당별 메뉴리스트
    @GetMapping("/menulist/{resaBisName}")
    public String menu(@PathVariable String resaBisName, ModelMap map) {
        System.out.println("inside");
        List<MenuDto> list = menuLogicService.menuList(resaBisName);
        map.addAttribute("menu", list);
        map.addAttribute("resaBisName", resaBisName);
        System.out.println(list);
        return "shop/menuAllList";

    }

    // 식당 리스트
    @GetMapping("/list")
    public String list(@PageableDefault(size = 10) Pageable pageable,
                       ModelMap map,
                       @AuthenticationPrincipal CatchPrincipal catchPrincipal,
                       @RequestParam(required = false) String sort) {
        String filtername = "최신등록순";
        if (sort == "" || sort == null) {
            if (catchPrincipal == null) {
                Page<ShopListResponse> shopList = shopService.shopList(pageable, null, null);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                map.addAttribute("shopList", shopList);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("prIdx", 0);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
            } else {
                Long prIdx = catchPrincipal.prIdx();
                Page<ShopListResponse> shopList = shopService.shopList(pageable, prIdx, null);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                map.addAttribute("shopList", shopList);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("prIdx", prIdx);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
            }
        } else {
            switch (sort) {
                case "regDate":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.shopList(pageable, null, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.shopList(pageable, prIdx, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    }
                    break;
                case "revCnt":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.shopList(pageable, null, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "리뷰 많은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.shopList(pageable, prIdx, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "리뷰 많은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    }
                    break;
                case "revScore":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.shopList(pageable, null, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "별점 높은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.shopList(pageable, prIdx, sort);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "별점 높은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    }
            }

        }
        return "shop/list";
    }

    // 식당 카테고리 리스트
    @GetMapping("/list/bisCategory/{bisCategory}")
    public String cateList(@PathVariable String bisCategory,
                           @RequestParam(required = false) String sort,
                           @PageableDefault(size = 10) Pageable pageable,
                           ModelMap map,
                           @AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        String filtername = "최신등록순";
        if (sort == "" || sort == null) {
            if (catchPrincipal == null) {
                Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, null, bisCategory, null);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                map.addAttribute("shopList", shopList);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("prIdx", 0);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
                map.addAttribute("bisCategory", bisCategory);
            } else {
                Long prIdx = catchPrincipal.prIdx();
                Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, null, bisCategory, null);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                map.addAttribute("shopList", shopList);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("prIdx", prIdx);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
                map.addAttribute("bisCategory", bisCategory);
            }
        } else {
            switch (sort) {
                case "regDate":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, sort, bisCategory, null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisCategory", bisCategory);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, sort, bisCategory, null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisCategory", bisCategory);
                    }
                    break;
                case "revCnt":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, sort, bisCategory,null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "리뷰 많은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisCategory", bisCategory);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, sort, bisCategory,null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "리뷰 많은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                    }
                    break;
                case "revScore":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, sort, bisCategory,null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "별점 높은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisCategory", bisCategory);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, sort,bisCategory,null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "별점 높은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisCategory", bisCategory);
                    }
            }
        }
        return "shop/cateList";
    }

    @GetMapping("/list/bisRegion/{bisRegion}")
    public String regionList(@PathVariable String bisRegion,
                             @RequestParam(required = false) String sort,
                             @PageableDefault(size = 10) Pageable pageable,
                             ModelMap map,
                             @AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        String filtername = "최신등록순";
        if (sort == "" || sort == null) {
            if (catchPrincipal == null) {
                Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, null, null, bisRegion);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                map.addAttribute("shopList", shopList);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("prIdx", 0);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
                map.addAttribute("bisRegion", bisRegion);
            } else {
                Long prIdx = catchPrincipal.prIdx();
                Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, null, null, bisRegion);
                List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                map.addAttribute("shopList", shopList);
                map.addAttribute("paginationBarNumbers", barNumbers);
                map.addAttribute("prIdx", prIdx);
                map.addAttribute("sort", sort);
                map.addAttribute("filtername", filtername);
                map.addAttribute("bisRegion", bisRegion);
            }
        } else {
            switch (sort) {
                case "regDate":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, sort, null, bisRegion);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisRegion", bisRegion);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, sort, null, bisRegion);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisRegion", bisRegion);
                    }
                    break;
                case "revCnt":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, sort, bisRegion,null);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "리뷰 많은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisRegion", bisRegion);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, sort, null,bisRegion);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "리뷰 많은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisRegion", bisRegion);
                    }
                    break;
                case "revScore":
                    if (catchPrincipal == null) {
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, null, sort, null,bisRegion);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "별점 높은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", 0);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisRegion", bisRegion);
                    } else {
                        Long prIdx = catchPrincipal.prIdx();
                        Page<ShopListResponse> shopList = shopService.categoryList(pageable, prIdx, sort,null,bisRegion);
                        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), shopList.getTotalPages());
                        filtername = "별점 높은순";
                        map.addAttribute("shopList", shopList);
                        map.addAttribute("paginationBarNumbers", barNumbers);
                        map.addAttribute("prIdx", prIdx);
                        map.addAttribute("sort", sort);
                        map.addAttribute("filtername", filtername);
                        map.addAttribute("bisRegion", bisRegion);
                    }
            }
        }
        return "shop/regionList";
    }



    // 식당 상세
    @GetMapping("/{resaBisName}")
    public String service(@PathVariable String resaBisName, ModelMap map, @AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        BistroDetailDto lists = bistroDetailLogicService.detailList(resaBisName);
        List<BisNoticeDto> nlists = bisNoticeLogicService.noticeList(resaBisName);
        List<MenuDto> listss = menuLogicService.menuList(resaBisName);
        List<FacilityDto> flist = facilityLogicService.facilityList(resaBisName);
        //식당 평균 별점
        List<ReviewDto> rlists = reviewLogicService.reviewList(resaBisName);
        List<ShopResponse> reviewAndPhoto = reviewLogicService.reviewPhotoList(resaBisName);
        PhotoDto photos = photoLogicService.photoDto(resaBisName);
        Double sum = 0.0;
        Double avg = 0.0;
        int cnt = 0;
        for (ReviewDto review : rlists) {
            sum += review.revScore();
            cnt += 1;
        }
        avg = sum / cnt;
        String average = String.format("%.1f", avg);

        if(catchPrincipal == null) {
            map.addAttribute("bisDetail", lists);

            map.addAttribute("bisNotice", nlists);

            map.addAttribute("menu", listss);

            map.addAttribute("facility", flist);
            System.out.println("flist" + flist);

            map.addAttribute("reviews", rlists);
            map.addAttribute("avgpoint", average);
            map.addAttribute("revcnt", cnt);
            map.addAttribute("reviewAndPhoto", reviewAndPhoto);
            map.addAttribute("shopPic", photos);
            map.addAttribute("prIdx", 0);
            System.out.println(photos);
        } else {
            Long prIdx = catchPrincipal.prIdx();
            map.addAttribute("bisDetail", lists);

            map.addAttribute("bisNotice", nlists);

            map.addAttribute("menu", listss);

            map.addAttribute("facility", flist);
            map.addAttribute("prIdx",prIdx);
            System.out.println("flist" + flist);

            map.addAttribute("reviews", rlists);
            map.addAttribute("avgpoint", average);
            map.addAttribute("revcnt", cnt);
            map.addAttribute("reviewAndPhoto", reviewAndPhoto);
            map.addAttribute("shopPic", photos);
            System.out.println(photos);
        }

        return "shop/shop";

    }

    // 식당 검색
    @GetMapping("/search/list")
    public String searchList(@RequestParam(value = "resaBisName", required = false) String resaBisName, @PageableDefault(size = 10, sort = "bisIdx", direction = Sort.Direction.DESC) Pageable pageable, Model map) {
        Page<BistroInfo> list = bistroInfoLogicService.searchList(resaBisName, pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), list.getTotalPages());

        System.out.println(list);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("list", list);
        map.addAttribute("totalpage", list.getTotalPages());
        map.addAttribute("resaBisName", resaBisName);
        return "shop/search_list";
    }

    // 북마크 저장
    @PostMapping(path = "/new/bookmark")
    @ResponseBody
    public String newBookmark(@RequestBody BistroSaveRequest request) {
        System.out.println(request);
        BistroSave bistroSave = bistroSaveService.newBookmark(request);
        if (bistroSave != null) {
            return "OK";
        } else {
            return "NO";
        }
    }

    // 북마크 삭제
    @PostMapping(path = "/del/bookmark")
    @ResponseBody
    public String delBookmark(@RequestBody BistroSaveRequest request) {
        System.out.println("컨트롤러 진입" + request);
        Optional<BistroSave> bistroSave = bistroSaveService.delBookmark(request);
        if (bistroSave != null) {
            return "OK";
        } else {
            return "NO";
        }
    }

}
