package com.mustache.bbs3.domain.entity;

import com.mustache.bbs3.domain.dto.HospitalResponse;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "nation_wide_hospitals")
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private Integer businessStatusCode;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getRoadNameAddress(), hospital.getHospitalName()
                , hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(), hospital.getTotalAreaSize());
    }
}
