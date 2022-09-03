package com.derivedqueries.repository;

import com.derivedqueries.entity.Department;
import com.derivedqueries.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    // Display all the regions in Canada
    List<Region>findByCountry(String country);

    // Display all regions in Canada, without duplications
    List<Region>findDistinctByCountry(String country);

    // display all regions with country name includes "us"
    List<Region>findByCountryContaining(String country);

    // display all regions with country name includes "us" in order
    List<Region>findByCountryContainingOrderByCountry(String country);

    // display top 2 regions in USA
    List<Region>findTop2ByCountry(String country);





}
