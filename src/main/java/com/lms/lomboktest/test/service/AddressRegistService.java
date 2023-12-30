package com.lms.lomboktest.test.service;

import com.lms.lomboktest.test.entity.Address;
import com.lms.lomboktest.test.repository.AddressResigerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressRegistService {

    private final AddressResigerRepository addressResigerRepository;

    @Transactional
    public void registAddress(List<Address> inputDtoList) {
        log.info("registAddress"+inputDtoList.toString());
        inputDtoList.forEach(inputDto -> {
            log.info("inputDto : {}", inputDto.getAddress());
            addressResigerRepository.save(inputDto);
            //throw new RuntimeException("error");
        });
    }

    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressResigerRepository.findAll();
    }


}
