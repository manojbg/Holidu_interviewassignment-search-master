package com.holidu.interview.assignment.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.holidu.interview.assignment.service.ApiService;

@RestController
@RequestMapping(value = "/treesearch")
public class ApiController {

	@Autowired
    private ApiService service;

    @RequestMapping(value = "/{x}/{y}/{radius}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseBody
    public Map<String, Long> getTreeCountInArea(@PathVariable("x") Double x,
                                         @PathVariable("y") Double y,
                                         @PathVariable("radius") Double radius) {
        return service.getTreeStatistics(x, y, radius);
    }
}
