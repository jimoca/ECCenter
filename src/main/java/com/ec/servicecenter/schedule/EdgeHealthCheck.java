package com.ec.servicecenter.schedule;


import com.ec.servicecenter.repository.EdgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class EdgeHealthCheck {

    @Autowired
    private EdgeRepository edgeRepository;

    @Scheduled(initialDelay = 2000, fixedRate = 10000)
    public void checkActiveEdge() {
        Optional.of(edgeRepository.findAllByActiveIsTrue())
                .map(it -> {
                    it.forEach(edge -> {
                        RestTemplate restTemplate = new RestTemplate();
                        String url = "http://"+edge.getIpAddress()+"/api/ping";
                        try {
                            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                            if(!response.getStatusCode().is2xxSuccessful()) {
                                edge.setActive(false);
                                edgeRepository.save(edge);
                            }
                            System.out.println(response);
                        } catch (ResourceAccessException e) {
                            edge.setActive(false);
                            edgeRepository.save(edge);
                        }

                    });
                    return it;
                });
    }

    @Scheduled(initialDelay = 2000, fixedRate = 60000)
    public void checkInactiveEdge() {
        Optional.of(edgeRepository.findAllByActiveIsFalse())
                .map(it -> {
                    it.forEach(edge -> {
                        RestTemplate restTemplate = new RestTemplate();
                        String url = "http://"+edge.getIpAddress()+"/api/ping";
                        try {
                            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                            if(response.getStatusCode().is2xxSuccessful()) {
                                edge.setActive(true);
                                edgeRepository.save(edge);
                            }
                            System.out.println(response);
                        } catch (ResourceAccessException ignored) {

                        }

                    });
                    return it;
                });
    }
}
