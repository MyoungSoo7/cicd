package com.lms.lomboktest.test.repository;


import com.lms.lomboktest.test.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressResigerRepository extends JpaRepository<Address, Long> {

}
