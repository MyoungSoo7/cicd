package com.lms.lomboktest.test.controller;


import com.lms.lomboktest.test.dto.InputDto;
import com.lms.lomboktest.test.entity.Address;
import com.lms.lomboktest.test.service.AddressRegistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LombokTestController {

    private final AddressRegistService addressRegistService;


    @GetMapping("/main")
    public String main() {
        return "main2";
    }

    @PostMapping("/postTest")
    public ModelAndView postTest(@ModelAttribute InputDto address){
        log.info("address : {}", address.getAddress());
        // address 가져와서 등록
        List<Address> addressList = new ArrayList<>();
        addressList.add(Address.builder().address(address.getAddress()).build());
        addressRegistService.registAddress(addressList);
        
        // 등록된거 가져와서 보여주
        List<Address> addressList1 = addressRegistService.findAll();


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("addressList", addressList1);
        modelAndView.addObject("address", address.getAddress());

        return modelAndView;
    }


}
