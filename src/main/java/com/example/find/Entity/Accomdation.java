package com.example.find.Entity;


import com.example.find.dto.request.AccomRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accomdations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accomdation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accomdationType; //숙소종류(호텔, 펜션)
    private String accomdationName; //숙소이름
    private String accomdationLocation; //숙소주소
    private Float accomdationGrade; //숙소별점
    private String accomdationContent; //숙소정보
    private Integer accomdationCount; //거래량
    private String ImageLink; // 숙소 사진
    @OneToMany(mappedBy = "accomdation")
    private List<Room> roomList;

    public void updateAccom(AccomRequest accomRequest){
        accomdationType= accomRequest.accomdationType();
        accomdationName=accomRequest.accomdationNam();
        accomdationLocation=accomRequest.accomdationLocation();
        accomdationContent=accomRequest.accomdationContent();
    }

    public void addRoom(Room room){
        this.roomList.add(room);
    }
}
