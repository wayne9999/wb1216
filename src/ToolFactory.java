import java.math.BigDecimal;

// Factory class to create tool instances dynamically
public class ToolFactory {
    public static Tool createTool(String toolCode, String toolType, String brand, BigDecimal dailyCharge) {
        return switch (toolType.toLowerCase()) {
            case "ladder" -> new Ladder(toolCode, brand, dailyCharge);
            case "chainsaw" -> new Chainsaw(toolCode, brand, dailyCharge);
            case "jackhammer" -> new Jackhammer(toolCode, brand, dailyCharge);
            default -> throw new IllegalArgumentException("Invalid tool type: " + toolType);
        };
    }
}
