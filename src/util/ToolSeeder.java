package util;

import model.Tool;
import service.ToolFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ToolSeeder {
    /**
     * Populates and returns a map of tools for the rental service using the service.ToolFactory.
     *
     * @return A map of tool objects keyed by their tool codes.
     */
    public static Map<String, Tool> getToolInventory() {
        Map<String, Tool> tools = new HashMap<>();
        tools.put("CHNS", ToolFactory.createTool("CHNS", "chainsaw", "Stihl", BigDecimal.valueOf(1.49)));
        tools.put("LADW", ToolFactory.createTool("LADW", "ladder","Werner", BigDecimal.valueOf(1.99)));
        tools.put("JAKD", ToolFactory.createTool("JAKD", "jackhammer","DeWalt", BigDecimal.valueOf(2.99)));
        tools.put("JAKR", ToolFactory.createTool("JAKR", "jackhammer","Ridgid", BigDecimal.valueOf(2.99)));
        return tools;
    }
}