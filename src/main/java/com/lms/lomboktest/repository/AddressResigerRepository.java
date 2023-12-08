package com.lms.lomboktest.repository;


import com.lms.lomboktest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressResigerRepository extends JpaRepository<Address, Long> {

}
