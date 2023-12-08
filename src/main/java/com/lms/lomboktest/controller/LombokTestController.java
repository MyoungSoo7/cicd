package com.lms.lomboktest.controller;


import com.lms.lomboktest.dto.InputDto;
import com.lms.lomboktest.entity.Address;
import com.lms.lomboktest.service.AddressRegistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LombokTestController {

    private final AddressRegistService addressRegistService;


    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/postTest")
    public ModelAndView postTest(@ModelAttribute InputDto address){
        log.info("address : {}", address.getAddress());
        List<Address> addressList = new ArrayList<>();
        addressList.add(Address.builder().address(address.getAddress()).build());
        addressRegistService.registAddress(addressList);

        List<Address> addressList1 = addressRegistService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("addressList", addressList1);
        modelAndView.addObject("address", address.getAddress());

        return modelAndView;
    }

    public void save_read(){

    }

}
