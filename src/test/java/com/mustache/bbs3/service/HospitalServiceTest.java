package com.mustache.bbs3.service;

import com.mustache.bbs3.domain.dto.HospitalResponse;
import com.mustache.bbs3.domain.entity.Hospital;
import com.mustache.bbs3.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HospitalServiceTest {
    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp(){
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("13일때 영업중")
    void addTest() {

        Hospital hospital1 = Hospital.builder()
                .id(1)
                .businessStatusCode(13).build();
        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital1));
        HospitalResponse hospitalResponse = hospitalService.getHospital(1);
        assertEquals("영업중", hospitalResponse.getBusinessStatusName());
    }

    @Test
    @DisplayName("3일때 폐업")
    void addTestClosed() {

        Hospital hospital1 = Hospital.builder()
                .id(1)
                .businessStatusCode(3).build();
        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital1));
        HospitalResponse hospitalResponse = hospitalService.getHospital(1);
        assertEquals("폐업", hospitalResponse.getBusinessStatusName());
    }
}