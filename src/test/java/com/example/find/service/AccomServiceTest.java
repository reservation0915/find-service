package com.example.find.service;

import com.example.find.Entity.Accomdation;
import com.example.find.dto.request.AccomRequest;
import com.example.find.dto.response.AccomResponse;
import com.example.find.repository.AccommodationRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccomServiceTest {

    @Autowired
    AccomService accomService;
    @Autowired
    AccommodationRepository accommodationRepository;

    @Test
    void save() {
        //given
        AccomRequest accomRequest = new AccomRequest(
                "숙박",
                "영이네숙박",
                "서울",
                "서울에서 가장 예약이 많은 집"
        );
        //when
        accomService.save(accomRequest);
        //then

        int size = accommodationRepository.findAll().size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    void findAll() {
        //given
        AccomRequest accomRequest = new AccomRequest(
                "숙박",
                "영이네숙박",
                "서울",
                "서울에서 가장 예약이 많은 집"
        );

        AccomRequest accomRequest2 = new AccomRequest(
                "호텔",
                "철수네호텔",
                "부산",
                "부산에서 가장 인기가 많은 집"
        );
        accommodationRepository.save(accomRequest.toEntity());
        accommodationRepository.save(accomRequest2.toEntity());

        //when

        Page<AccomResponse> all = accomService.findAll(PageRequest.of(0, 5));

        //then
        assertThat(all.getTotalElements()).isEqualTo(2);

    }

    @Test
    void findAccom() {
        //given
        AccomRequest accomRequest = new AccomRequest(
                "숙박",
                "영이네숙박",
                "서울",
                "서울에서 가장 예약이 많은 집"
        );

        Accomdation save = accommodationRepository.save(accomRequest.toEntity());
        //when
        Accomdation accom = accomService.findAccom(save.getId());
        //then

        assertThat(accom.getAccomdationName()).isEqualTo("영이네숙박");
        assertThat(accom.getAccomdationLocation()).isEqualTo("서울");
    }

    @Test
    void updateAccom() {
        //given
        AccomRequest accomRequest = new AccomRequest(
                "숙박",
                "영이네숙박",
                "서울",
                "서울에서 가장 예약이 많은 집"
        );

        Accomdation save = accommodationRepository.save(accomRequest.toEntity());

        //when

        AccomRequest newAccomRequest = new AccomRequest(
                "숙박",
                "철이네숙박",
                "인천",
                "인천에서 가장 예약이 많은 집"
        );


        accomService.updateAccom(save.getId(),newAccomRequest);

        //then

        Accomdation accom = accomService.findAccom(save.getId());

        assertThat(accom.getAccomdationName()).isEqualTo("철이네숙박");
        assertThat(accom.getAccomdationLocation()).isEqualTo("인천");
    }

    @Test
    void deleteAccom() {
        //given
        AccomRequest accomRequest = new AccomRequest(
                "숙박",
                "영이네숙박",
                "서울",
                "서울에서 가장 예약이 많은 집"
        );

        accommodationRepository.save(accomRequest.toEntity());

        //when

        accomService.deleteAccom(1L);

        //then

        assertThrows(RuntimeException.class,()->accomService.findAccom(1L));
    }
}