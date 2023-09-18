package com.example.find.dto.response;

import com.example.find.Entity.Accomdation;
import com.example.find.Entity.Room;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public record AccomResponse (
    Long id,
    String accomdationType, //숙소종류(호텔, 펜션)
    String accomdationName,//숙소이름
    String accomdationLocation, //숙소주소
    Float accomdationGrade, //숙소별점
    String accomdationContent,//숙소정보
    Integer accomdationCount,//거래량
    String ImageLink, //이미지 링크
    List<RoomResponse> room
){

    public static AccomResponse of(Accomdation accom){
        List<Room> roomList = accom.getRoomList();
        List<RoomResponse> list = roomList.stream().map(RoomResponse::of).toList();
        return new AccomResponse(
                accom.getId(),
                accom.getAccomdationType(),
                accom.getAccomdationName(),
                accom.getAccomdationLocation(),
                accom.getAccomdationGrade(),
                accom.getAccomdationContent(),
                accom.getAccomdationCount(),
                accom.getImageLink(),
                list
        );
    }
}
