package com.holidu.interview.assignment.serviceimpl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.service.DataProvider;

@Service
public class DataProviderImpl implements DataProvider{
	private static final Logger log = LoggerFactory.getLogger(DataProvider.class);

    @Value("${treedata.cityofnewyork.url}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ObjectNode[] getData() {

        return DataCache.getData().orElseGet(this::fetchRemoteData);
    }

    private ObjectNode[] fetchRemoteData() {
        log.debug("Calling remote endpoint=" + endpoint);
        ObjectNode[] fetchedData = null;
        try {
            fetchedData = restTemplate.getForObject(URI.create(endpoint), ObjectNode[].class);
        } catch (Exception e) {
            e.getStackTrace();
        }
        DataCache.updateCache(fetchedData);

        return fetchedData;
    }
}
