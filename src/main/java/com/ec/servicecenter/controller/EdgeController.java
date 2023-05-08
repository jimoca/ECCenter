package com.ec.servicecenter.controller;


import com.ec.servicecenter.exception.BusinessException;
import com.ec.servicecenter.model.EdgeInfo;
import com.ec.servicecenter.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EdgeController {

    @Autowired
    private EdgeService edgeService;


    @PostMapping("/register")
    public ResponseEntity<EdgeInfo> register(@RequestBody EdgeInfo edgeInfo) {
        return edgeService.register(edgeInfo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with register!"));
    }


    @GetMapping("/discover")
    public ResponseEntity<List<EdgeInfo>> discover() {
        return edgeService.discover()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException("Something wrong with discover!"));
    }
}
