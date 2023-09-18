package com.example.find.dto.response;

import com.example.find.Entity.Accomdation;
import com.example.find.Entity.Room;

public record RoomResponse(

        Long id,
        String roomNumber, //방번호
        String roomName, //방이름
        Integer roomMaxPerson, //방인원
        Integer roomPrice
//        AccomResponse accomdationResponse //숙박시설


//        @OneToMany(mappedBy = "room")
//        private List<ReservationDate> reservationDateList;
//        @ManyToOne
//        private Accomdation accomdation;

) {

    public static RoomResponse of(Room room){
        return new RoomResponse(
                room.getId(),
                room.getRoomNumber(),
                room.getRoomName(),
                room.getRoomMaxPerson(),
                room.getRoomPrice()
        );
    }
}
