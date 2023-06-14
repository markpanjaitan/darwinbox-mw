package com.darwinbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darwinbox.entity.OfficeLocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<OfficeLocationEntity, Long>{

}
