package com.kcbgroup.main.repository;


import com.kcbgroup.main.domain.CustomerCreationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCreationRepository extends JpaRepository<CustomerCreationDomain, Integer> {


}
