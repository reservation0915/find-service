package com.example.find.repository;

import com.example.find.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    List<Room> findAllByAccomdation_Id(Long id);

    @Query("SELECT Min(r.roomPrice) from Room r group by r.accomdation.id")
    List<Integer> findAllGroup();

    @Query("SELECT r from Room r inner join fetch Accomdation a on r.accomdation.id=a.id order by a.accomdationCount")
    List<Room> findAllRoomOrderBySale();


}
