package com.holidu.interview.assignment.service;

import java.util.Map;

public interface ApiService {

	public Map<String, Long> getTreeStatistics(Double x, Double y, Double radius);
}
