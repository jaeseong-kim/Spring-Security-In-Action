package com.example.oauth2application18.repository;

import com.example.oauth2application18.entity.Workout;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkoutRepository extends JpaRepository<Workout,Integer> {

  @Query("SELECT w From Workout w WHERE w.user = ?#{authentication.name}")
  List<Workout> findAllByUser();
}
