package com.kcbgroup.main.repository;

import com.kcbgroup.main.domain.AccountCreationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCreationRepository extends JpaRepository<AccountCreationDomain, Integer> {
}
