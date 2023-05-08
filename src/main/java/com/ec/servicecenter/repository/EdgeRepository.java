package com.ec.servicecenter.repository;

import com.ec.servicecenter.model.EdgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeRepository extends JpaRepository<EdgeInfo, Long> {

    List<EdgeInfo> findAllByActiveIsTrue();
    List<EdgeInfo> findAllByActiveIsFalse();

}
