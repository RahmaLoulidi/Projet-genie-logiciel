package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.BesoinPropose;
import com.gestresmat.springjwt.repository.BesoinProposeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BesoinProposeService {
    private final BesoinProposeRepository besoinProposeRepository;

    public BesoinProposeService(BesoinProposeRepository besoinProposeRepository) {
        this.besoinProposeRepository = besoinProposeRepository;
    }


    public BesoinPropose findById(Long id) {
        return besoinProposeRepository.findById(id).orElse(null);
    }

    public List<BesoinPropose> getBesoinProposeByIds(List<Long> ids){
        return besoinProposeRepository.findAllById(ids);
    }
}
