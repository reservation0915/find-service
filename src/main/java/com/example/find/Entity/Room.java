package com.example.find.Entity;


import com.example.find.dto.request.AccomRequest;
import com.example.find.dto.request.RoomRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber; //방번호
    private String roomName; //방이름
    private Integer roomMaxPerson; //방인원
    private Boolean isRoomValid; //예약 가능 여부
    private String roomImage; // 방 사진
    private LocalDateTime checkIn; //유저의 실제 체크 인 시간
    private LocalDateTime checkOut; //유저의 실제 체크 아웃 시간
    private Integer roomPrice;
    @ManyToOne
    private Accomdation accomdation;


    public void updateRoom (RoomRequest roomRequest){
        roomNumber=roomRequest.roomNumber();
       roomName=roomRequest.roomName();
       roomMaxPerson=roomRequest.roomMaxPerson();
    }

}
