package com.devstaff.assessment.repository;

import com.devstaff.assessment.entity.Harvest;
import com.devstaff.assessment.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    List<Harvest> findBySeason_SeasonId(Long seasonId);
}
