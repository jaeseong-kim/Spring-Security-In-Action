package com.example.oauth2application18.controller;

import com.example.oauth2application18.entity.Workout;
import com.example.oauth2application18.service.WorkoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class WorkController {

  @Autowired
  private WorkoutService workoutService;

  @PostMapping("/")
  public void add(@RequestBody Workout workout) {
    workoutService.saveWorkout(workout);
  }

  @GetMapping("/")
  public List<Workout> findAll() {
    return workoutService.findWorkout();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    workoutService.deleteWorkout(id);
  }
}
