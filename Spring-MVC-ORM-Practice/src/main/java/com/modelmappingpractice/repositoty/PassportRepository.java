package com.modelmappingpractice.repositoty;

import com.modelmappingpractice.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, String> {
}
