package com.casaSolaire.repository;

import com.casaSolaire.models.Address;
import com.casaSolaire.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
