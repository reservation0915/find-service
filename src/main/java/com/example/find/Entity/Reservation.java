package com.example.find.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //?? 숙박업소
    @ManyToOne
    private Room room;
    @ManyToOne
    private Customer customer;
}
