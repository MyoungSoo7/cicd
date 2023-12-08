package com.lms.lomboktest.repository;


import com.lms.lomboktest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AddressResigerRepository extends JpaRepository<Address, Long> {

        Optional<Address> findByAddress(String address);

}
