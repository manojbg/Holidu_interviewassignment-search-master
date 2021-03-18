package com.holidu.interview.assignment.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.holidu.interview.assignment.model.Tree;
import com.holidu.interview.assignment.serviceimpl.DataHandlerImpl;

public class DataHandlerTest {
	private DataHandlerImpl test = new DataHandlerImpl();

    private ObjectMapper testMapper = new ObjectMapper();

    @Test
    public void findTreesInsideTheCircleTest() {

        Tree expectedTreeInsideTheCircle = new Tree("t_id_1", "Oak", 1.2, 1.2);

        Set<Tree> testTrees = new HashSet<>();
        testTrees.add(expectedTreeInsideTheCircle);
        testTrees.add(new Tree("t_id_2", "Pine", 3.2, 8.2));

        Map<String, Long> treesInsideTheCircle = test.findTreesInsideTheCircle(testTrees, 1.0, 1.0, 2.0);

        assertThat(treesInsideTheCircle).hasSize(1);
        assertThat(treesInsideTheCircle).containsKeys("Oak");
        assertThat(treesInsideTheCircle).containsValue(1L);
    }

    @Test
    public void getAllTreesTest() {

    	test.objectMapper = testMapper;
        String json = "[\n" +
                "  {\n" +
                "    \"tree_id\": \"180683\",\n" +
                "    \"spc_common\": \"red maple\",\n" +
                "    \"x_sp\": \"1027431.148\",\n" +
                "    \"y_sp\": \"202756.7687\"\n" +
                "  }\n" +
                "]";
        ObjectNode[] sampleData = new ObjectNode[0];
        try {
            sampleData = testMapper.readValue(json, ObjectNode[].class);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        ObjectNode[] finalSampleData = sampleData;
        Supplier<ObjectNode[]> treeDataSupplier = () -> finalSampleData;

        Set<Tree> allTrees = test.getAllTrees(treeDataSupplier);

        assertThat(allTrees).hasSize(1);
        for (Tree t: allTrees) {
            assertThat(t.getTreeId()).isEqualTo("180683");
            assertThat(t.getTreeName()).isEqualTo("red apple");
            assertThat(t.getX()).isEqualTo(1027431.148);
            assertThat(t.getY()).isEqualTo(202756.7687);

        }
    }
}
