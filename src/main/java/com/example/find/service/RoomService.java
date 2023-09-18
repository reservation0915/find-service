package com.example.find.service;

import com.example.find.Entity.Accomdation;
import com.example.find.Entity.Room;
import com.example.find.dto.request.RoomRequest;
import com.example.find.dto.response.RoomResponse;
import com.example.find.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> addRoom(Accomdation accom, List<RoomRequest> roomRequest) {

        List<Room> roomList = new ArrayList<>();

        for(RoomRequest room :roomRequest){
            accom.addRoom(room.toEntity(accom));

            Room newRoom = roomRepository.save(room.toEntity(accom));

            roomList.add(newRoom);
        }

        return roomList;
    }

    public List<RoomResponse> findAllById(Long id) {
        List<Room> all = roomRepository.findAllByAccomdation_Id(id);


        return all.stream().map(RoomResponse::of).toList();

    }

    public void update(Long id, RoomRequest roomRequest) {

        //룸 찾기
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("룸 추가"));

        room.updateRoom(roomRequest);


    }

    public RoomResponse findById(Long id){

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO Room Error"));

        return RoomResponse.of(room);

    }

    public List<Integer> findAllMinRoom() {

        List<Integer> allGroup = roomRepository.findAllGroup();
        return allGroup;
    }

    public List<RoomResponse> findAllOrderSale(){

        List<Room> all = roomRepository.findAllRoomOrderBySale();
        return  all.stream().map(RoomResponse::of).toList();
    }
}
