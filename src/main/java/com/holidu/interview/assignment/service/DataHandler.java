package com.holidu.interview.assignment.service;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.model.Tree;

public interface DataHandler {
	
	public Set<Tree> getAllTrees(Supplier<ObjectNode[]> treeDataSupplier);

	public Map<String, Long> findTreesInsideTheCircle(Set<Tree> allTrees, Double x, Double y, Double radius);
}
