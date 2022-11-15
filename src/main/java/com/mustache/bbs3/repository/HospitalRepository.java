package com.mustache.bbs3.repository;

import com.mustache.bbs3.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByBusinessTypeNameContainingAndRoadNameAddressContaining(String name, String city);
    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressContaining(List<String> businessType, String city);

    List<Hospital> findByTotalNumberOfBedsBetweenOrderByTotalNumberOfBedsDesc(int startNum, int endNum);
}
