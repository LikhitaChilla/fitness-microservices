package com.fitness.activityservice.service;

import org.springframework.stereotype.Service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
	private final ActivityRepository actrep;
	private ModelMapper map=new ModelMapper();
	public ActivityResponse trackActivity(ActivityRequest request) {
		Activity activity=Activity.builder()
				.userId(request.getUserId()).
				type(request.getType())
				.caloriesBurned(request.getCaloriesBurned())
				.duration(request.getDuration())
				.startTime(request.getStartTime())
				.additionalMetrics(request.getAdditionalMetrics())
				.build();
		Activity savedact=actrep.save(activity);
		ActivityResponse res=map.map(savedact,ActivityResponse.class);
		return res;
	}
	

}
