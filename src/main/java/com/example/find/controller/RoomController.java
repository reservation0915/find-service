package com.example.find.controller;

import com.example.find.Entity.Accomdation;
import com.example.find.dto.request.RoomRequest;
import com.example.find.dto.response.RoomResponse;
import com.example.find.service.AccomService;
import com.example.find.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/find/room")
public class RoomController {

    private final AccomService accomService;
    private final RoomService roomService;

    //기본적인 CRUD

    //Room Create
    //요청 , Accom Id와 RoomRequest
    //여러개의 방 추가
    @PostMapping("{id}")
    public void create(@PathVariable Long id, @RequestBody List<RoomRequest> roomRequest){

        Accomdation accom = accomService.findAccom(id);
        roomService.addRoom(accom,roomRequest);

    }


    //Rooms Read
    //요청 Param은 Accom id
    // 숙박시설 id를 통해 숙박시설 방들을 찾는 로직
    @GetMapping("{id}")
    public List<RoomResponse> find(@PathVariable Long id){
        return roomService.findAllById(id);
    }

    //Room Read 1개  (상세페이지)
    @GetMapping("/detail/{id}")
    public RoomResponse findById(@PathVariable Long id){
        return roomService.findById(id);
    }

    //숙박시설 별로 최저가 방 찾기
    //일단은 숙박 Seq 순으로
    @GetMapping("")
    public List<Integer> findAllMinRoom(){
        return roomService.findAllMinRoom();
    }

    // Room+숙박시설 동시에 찾기
    // ++ 여기에 숙박시설 거래량순서대로 찾기
    @GetMapping("/sale")
    public List<RoomResponse> findAllOrderBySale(){
        return roomService.findAllOrderSale();
    }

    //Room Update
    //요청 Param은 RoomId, RoomRequest
    // 방 1개 정보 수정
    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody RoomRequest roomRequest){

         roomService.update(id, roomRequest);
    }


}
