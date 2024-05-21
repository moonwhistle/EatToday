package com.eattoday.Eattoday.controller;

import com.eattoday.Eattoday.entity.Store;
import com.eattoday.Eattoday.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class RestController {
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/rest/{id}") //매장 상세페이지
    public String show(@PathVariable("id") Long id, Model model) {
        log.info("id = " + id); //매장 id 제대로 가져왔는지 log 확인

        Store storeentity = storeRepository.findById(id).orElse(null); // id 조회해 데이터 가져오기
        model.addAttribute("rest", storeentity); // 모델에 데이터 등록

        return "rest/detail"; // 뷰 페이지 반환
    }

    @GetMapping("/category")
    public String category(Model model)
    {
        long count = storeRepository.count(); // store 개수 추출
        int random = (int)((Math.random()*count));  // count까지의 숫자 중 랜덤 함수로 정수 추출
        Long id=Long.valueOf(random); // id와 같은 Long타입으로 변경
        Store store = storeRepository.findById(id).orElse(null);
        model.addAttribute("randomStore", store); //model에 엔티티 값 저장
        return "rest/category";
    }

}
