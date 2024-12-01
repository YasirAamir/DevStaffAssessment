package com.devstaff.assessment.repository;

import com.devstaff.assessment.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findBySeason_SeasonId(Long seasonId);
}
