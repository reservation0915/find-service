package com.example.find.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tourdestinations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tourDestinationName; //관광지이름
    private String tourDestinationLocation; //관광지위치
    private String tourDestinationContent; //관광지정보 키워드 주제 등


}
