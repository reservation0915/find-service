package com.example.find.repository;

import com.example.find.Entity.Accomdation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accomdation,Long> {

    @Query("select  a from Accomdation a join Room r on a.id= r.accomdation.id order by a.accomdationCount desc" )
    Page<Accomdation> findAllOrderBySale(PageRequest of);
}
