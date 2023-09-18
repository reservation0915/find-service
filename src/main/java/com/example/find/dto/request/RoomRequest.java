package com.example.find.dto.request;

import com.example.find.Entity.Accomdation;
import com.example.find.Entity.Room;

public record RoomRequest(

        String roomNumber, //방번호
        String roomName, //방이름
        Integer roomMaxPerson //방인원

) {

    public Room toEntity(Accomdation accom){
        return Room.builder()
                .roomNumber(roomNumber)
                .roomName(roomName)
                .roomMaxPerson(roomMaxPerson)
                .accomdation(accom)
                .build();
    }
}
