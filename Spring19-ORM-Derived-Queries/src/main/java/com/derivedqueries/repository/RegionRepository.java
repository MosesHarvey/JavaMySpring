package com.derivedqueries.repository;

import com.derivedqueries.entity.Department;
import com.derivedqueries.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
