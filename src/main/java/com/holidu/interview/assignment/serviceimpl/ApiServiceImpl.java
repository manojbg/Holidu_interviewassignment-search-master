package com.holidu.interview.assignment.serviceimpl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holidu.interview.assignment.model.Tree;
import com.holidu.interview.assignment.service.ApiService;
import com.holidu.interview.assignment.service.DataHandler;
import com.holidu.interview.assignment.service.DataProvider;



@Service
public class ApiServiceImpl implements ApiService{
	@Autowired
    private DataProvider dataProvider;

    @Autowired
    private DataHandler dataHandler;
    
    public Map<String, Long> getTreeStatistics(Double x, Double y, Double radius) {
        Set<Tree> allTrees = dataHandler.getAllTrees(dataProvider::getData);
        return dataHandler.findTreesInsideTheCircle(allTrees, x, y, radius);
    }
}
