package com.example.oauth2application18.service;

import com.example.oauth2application18.entity.Workout;
import com.example.oauth2application18.repository.WorkoutRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

  @Autowired

  private WorkoutRepository workoutRepository;

  @PreAuthorize("#workout.user == authentication.name")
  public void saveWorkout(Workout workout) {
    workoutRepository.save(workout);
  }

  public List<Workout> findWorkout() {
    return workoutRepository.findAllByUser();
  }

  public void deleteWorkout(Integer id) {
    workoutRepository.deleteById(id);
  }
}
