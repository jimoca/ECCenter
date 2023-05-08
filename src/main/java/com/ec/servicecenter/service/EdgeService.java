package com.ec.servicecenter.service;


import com.ec.servicecenter.model.EdgeInfo;
import com.ec.servicecenter.repository.EdgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    public Optional<EdgeInfo> register(EdgeInfo edgeInfo) {
        return Optional.of(edgeInfo)
                .map(edgeRepository::save);
    }


    public Optional<List<EdgeInfo>> discover() {
        return Optional.of(edgeRepository.findAllByActiveIsTrue());
    }
}
