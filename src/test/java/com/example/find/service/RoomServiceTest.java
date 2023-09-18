package com.example.find.service;

import com.example.find.Entity.Accomdation;
import com.example.find.Entity.Room;
import com.example.find.dto.request.AccomRequest;
import com.example.find.dto.request.RoomRequest;
import com.example.find.dto.response.RoomResponse;
import com.example.find.repository.AccommodationRepository;
import com.example.find.repository.RoomRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired
    AccomService accomService;
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EntityManager em;

    Long accomIndex ;

    @BeforeEach
    void init(){
        //given
        AccomRequest accomRequest = new AccomRequest(
                "숙박",
                "영이네숙박",
                "서울",
                "서울에서 가장 예약이 많은 집"
        );

        Accomdation save = accomService.save(accomRequest);
        accomIndex=save.getId();
    }

    @AfterEach
    void clear(){

       roomRepository.deleteAll();
       accommodationRepository.deleteAll();
    }

    @Test
    void addRoom() {
        //given
        Accomdation accom = accomService.findAccom(1L);

        List<RoomRequest> roomList = new ArrayList<>();

        RoomRequest room = new RoomRequest("1","스위트룸",4);
        RoomRequest room2 = new RoomRequest("2","커플룸",2);

        roomList.add(room);
        roomList.add(room2);

        //when
        roomService.addRoom(accom,roomList);


        //then

        //Accom 연관관계
        assertThat(accom.getRoomList().size()).isEqualTo(2);
        //Repository
        assertThat( roomRepository.findAll().size()).isEqualTo(2);

    }

    @Test
    void findAllById() {
        //given
        Accomdation accom = accomService.findAccom(accomIndex);

        List<RoomRequest> roomList = new ArrayList<>();

        RoomRequest room = new RoomRequest("1","스위트룸",4);
        RoomRequest room2 = new RoomRequest("2","커플룸",2);

        roomList.add(room);
        roomList.add(room2);

        roomService.addRoom(accom, roomList);

        //when

        // by AccomSeq
        List<RoomResponse> allById = roomService.findAllById(accomIndex);

        //then
        assertThat(allById.size()).isEqualTo(2);
        assertThat(allById.get(0).roomName()).isEqualTo("스위트룸");
        assertThat(allById.get(1).roomName()).isEqualTo("커플룸");

    }

    @Test
    void update() {

    }

    @Test
    void findById() {
        //given
        Accomdation accom = accomService.findAccom(accomIndex);

        List<RoomRequest> roomList = new ArrayList<>();

        RoomRequest room = new RoomRequest("1","스위트룸",4);
        RoomRequest room2 = new RoomRequest("2","커플룸",2);

        roomList.add(room);
        roomList.add(room2);

        List<Room> roomList1 = roomService.addRoom(accom, roomList);
        //when

        //이거는 룸서비스 상세페이지
        RoomResponse room1 = roomService.findById(roomList1.get(0).getId());

        //then

        assertThat(room1.roomName()).isEqualTo("스위트룸");

    }
}