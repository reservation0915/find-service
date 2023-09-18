package com.example.find.service;

import com.example.find.Entity.Accomdation;
import com.example.find.dto.request.AccomRequest;
import com.example.find.dto.response.AccomResponse;
import com.example.find.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccomService {

    private final AccommodationRepository accommodationRepository;

    public Accomdation save(AccomRequest accomRequest){

        return accommodationRepository.save(accomRequest.toEntity());
    }

    public Page<AccomResponse> findAll(PageRequest of){

        Page<Accomdation> all = accommodationRepository.findAll(of);

        return all.map(AccomResponse::of);
    }

    public Accomdation findAccom(Long id){

       return accommodationRepository.findById(id).
                orElseThrow(() -> new RuntimeException("숙박시설이 없어요"));
    }

    public void updateAccom(Long id,AccomRequest accomRequest) {

        Accomdation accom = findAccom(id);
        accom.updateAccom(accomRequest);

    }

    public void deleteAccom(Long id) {

        accommodationRepository.deleteById(id);

    }

    public Page<AccomResponse> findAllOrderBySale(PageRequest of) {
        Page<Accomdation> all = accommodationRepository.findAllOrderBySale(of);
        return all.map(AccomResponse::of);
    }

    public List<AccomResponse> findAllHotel() {

        List<Accomdation> all = accommodationRepository.findAllHotel();
        return all.stream().map(AccomResponse::of).toList();
    }

    public List<AccomResponse> findAllPension() {


        List<Accomdation> all = accommodationRepository.findAllPension();
        return all.stream().map(AccomResponse::of).toList();
    }
}
