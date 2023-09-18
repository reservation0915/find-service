package com.example.find.dto.request;

import com.example.find.Entity.Accomdation;
import com.example.find.Entity.Room;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public record AccomRequest(

        String accomdationType,//숙소종류(호텔, 펜션)
        String accomdationNam, //숙소이름
        String accomdationLocation, //숙소주소

//        Float accomdationGrade, //숙소별점
        String accomdationContent //숙소정보

//        Integer accomdationCount //거래량

) {
    public Accomdation toEntity(){
        return Accomdation.builder()
                .accomdationType(accomdationType)
                .accomdationName(accomdationNam)
                .accomdationLocation(accomdationLocation)
                .accomdationContent(accomdationContent)
                .roomList(new ArrayList<>())
                .build();
    }
}