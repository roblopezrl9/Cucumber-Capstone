import java.util.*;
import java.io.*;
import java.nio.file.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 🤖 MCP AI Agent for Cucumber Testing
 * 
 * This agent uses Model Context Protocol (MCP) principles to:
 * - Analyze code changes and test requirements
 * - Provide intelligent test selection recommendations  
 * - Generate insights from test results
 * - Make deployment readiness decisions
 */
public class MCPAIAgent {
    
    private static final String MCP_CONFIG_FILE = "mcp-config.json";
    private final ObjectMapper objectMapper;
    private final Map<String, Object> mcpConfig;
    
    public MCPAIAgent() {
        this.objectMapper = new ObjectMapper();
        this.mcpConfig = loadMCPConfig();
        System.out.println("🤖 MCP AI Agent initialized");
    }
    
    /**
     * 🧠 MCP Context: Analyze repository for intelligent test selection
     */
    public MCPTestStrategy analyzeRepository(List<String> changedFiles, String requestType) {
        System.out.println("🧠 MCP: Analyzing repository context...");
        System.out.println("📁 Changed files: " + changedFiles.size());
        System.out.println("🎯 Request type: " + requestType);
        
        MCPTestStrategy strategy = new MCPTestStrategy();
        
        // MCP Context Analysis: File types and change patterns
        int featureChanges = (int) changedFiles.stream().filter(f -> f.endsWith(".feature")).count();
        int javaChanges = (int) changedFiles.stream().filter(f -> f.endsWith(".java")).count();
        int configChanges = (int) changedFiles.stream().filter(f -> f.matches(".*\\.(xml|yml|properties)$")).count();
        
        // MCP Decision Engine: Determine test strategy based on context
        if (requestType.equals("all")) {
            strategy = createComprehensiveStrategy();
        } else if (featureChanges > 0) {
            strategy = createFeatureChangeStrategy();
            System.out.println("🎯 MCP Decision: Feature changes detected");
        } else if (javaChanges > 0) {
            strategy = createCodeChangeStrategy();
            System.out.println("🎯 MCP Decision: Code changes detected");
        } else if (configChanges > 0) {
            strategy = createConfigChangeStrategy();
            System.out.println("🎯 MCP Decision: Configuration changes detected");
        } else {
            strategy = createMinimalStrategy();
            System.out.println("🎯 MCP Decision: Minimal changes detected");
        }
        
        // MCP Context Enhancement: Add metadata
        strategy.setChangedFiles(changedFiles);
        strategy.setAnalysisTime(new Date());
        strategy.setMcpVersion("1.0");
        
        System.out.println("✅ MCP Analysis Complete: " + strategy.getStrategyName());
        return strategy;
    }
    
    /**
     * 📊 MCP Context: Analyze test results and generate insights
     */
    public MCPInsights analyzeResults(String resultsPath) {
        System.out.println("📊 MCP: Analyzing test results...");
        
        MCPInsights insights = new MCPInsights();
        
        try {
            Path jsonPath = Paths.get(resultsPath, "cucumber.json");
            if (Files.exists(jsonPath)) {
                String jsonContent = Files.readString(jsonPath);
                JsonNode results = objectMapper.readTree(jsonContent);
                
                // MCP Analysis: Extract metrics from results
                int totalSteps = 0;
                int passedSteps = 0;
                int failedSteps = 0;
                
                for (JsonNode feature : results) {
                    for (JsonNode scenario : feature.get("elements")) {
                        for (JsonNode step : scenario.get("steps")) {
                            totalSteps++;
                            String status = step.get("result").get("status").asText();
                            if ("passed".equals(status)) passedSteps++;
                            else if ("failed".equals(status)) failedSteps++;
                        }
                    }
                }
                
                // MCP Intelligence: Calculate success metrics
                double successRate = totalSteps > 0 ? (double) passedSteps / totalSteps * 100 : 0;
                
                insights.setTotalSteps(totalSteps);
                insights.setPassedSteps(passedSteps);
                insights.setFailedSteps(failedSteps);
                insights.setSuccessRate(successRate);
                
                // MCP Recommendations
                insights.setRecommendations(generateRecommendations(successRate, failedSteps));
                insights.setDeploymentReady(successRate >= 85 && failedSteps == 0);
                
                System.out.println("📈 MCP Results: " + successRate + "% success rate");
            }
        } catch (IOException e) {
            System.err.println("❌ MCP Error analyzing results: " + e.getMessage());
        }
        
        return insights;
    }
    
    /**
     * 🎯 MCP Context: Generate test scenarios from user story
     */
    public List<String> generateScenariosFromStory(String userStory) {
        System.out.println("🤖 MCP: Generating scenarios from user story...");
        
        List<String> scenarios = new ArrayList<>();
        
        // MCP Pattern Recognition: Extract key components
        String actor = extractActor(userStory);
        String action = extractAction(userStory);
        String goal = extractGoal(userStory);
        
        // MCP Generation: Create test scenarios
        scenarios.add(generateHappyPathScenario(actor, action, goal));
        scenarios.add(generateErrorScenario(actor, action));
        scenarios.add(generateEdgeCaseScenario(actor, action, goal));
        
        System.out.println("✅ MCP Generated: " + scenarios.size() + " scenarios");
        return scenarios;
    }
    
    // MCP Helper Methods
    
    private Map<String, Object> loadMCPConfig() {
        try {
            if (Files.exists(Paths.get(MCP_CONFIG_FILE))) {
                String content = Files.readString(Paths.get(MCP_CONFIG_FILE));
                return objectMapper.readValue(content, Map.class);
            }
        } catch (IOException e) {
            System.err.println("⚠️ Could not load MCP config: " + e.getMessage());
        }
        return getDefaultConfig();
    }
    
    private MCPTestStrategy createComprehensiveStrategy() {
        return new MCPTestStrategy("comprehensive", "@regression", "high", 120);
    }
    
    private MCPTestStrategy createFeatureChangeStrategy() {
        return new MCPTestStrategy("targeted", "@smoke or @regression", "high", 60);
    }
    
    private MCPTestStrategy createCodeChangeStrategy() {
        return new MCPTestStrategy("targeted", "@smoke or @regression", "medium", 45);
    }
    
    private MCPTestStrategy createConfigChangeStrategy() {
        return new MCPTestStrategy("smoke", "@smoke", "medium", 20);
    }
    
    private MCPTestStrategy createMinimalStrategy() {
        return new MCPTestStrategy("smoke", "@smoke", "low", 15);
    }
    
    private List<String> generateRecommendations(double successRate, int failedSteps) {
        List<String> recommendations = new ArrayList<>();
        
        if (successRate >= 95) {
            recommendations.add("✅ Excellent test performance - ready for deployment");
            recommendations.add("🎯 Consider expanding test coverage for edge cases");
        } else if (successRate >= 85) {
            recommendations.add("🟡 Good performance with room for improvement");
            recommendations.add("🔍 Review failed tests and update if needed");
        } else if (successRate >= 70) {
            recommendations.add("🟠 Moderate performance - investigation recommended");
            recommendations.add("🛠️ Check test environment and data setup");
        } else {
            recommendations.add("🚨 Poor performance - immediate attention required");
            recommendations.add("🛑 Block deployment until issues are resolved");
        }
        
        if (failedSteps > 0) {
            recommendations.add("🔧 " + failedSteps + " failed steps need attention");
        }
        
        return recommendations;
    }
    
    // Story Analysis Methods
    private String extractActor(String story) {
        if (story.toLowerCase().contains("as a ")) {
            return story.substring(story.toLowerCase().indexOf("as a ") + 5)
                       .split(" ")[0];
        }
        return "user";
    }
    
    private String extractAction(String story) {
        if (story.toLowerCase().contains("i want to ")) {
            return story.substring(story.toLowerCase().indexOf("i want to ") + 10)
                       .split(" so that")[0].trim();
        }
        return "perform action";
    }
    
    private String extractGoal(String story) {
        if (story.toLowerCase().contains("so that ")) {
            return story.substring(story.toLowerCase().indexOf("so that ") + 8).trim();
        }
        return "achieve goal";
    }
    
    private String generateHappyPathScenario(String actor, String action, String goal) {
        return String.format(
            "Scenario: %s - Happy Path\n" +
            "  Given I am a %s\n" +
            "  When I %s\n" +
            "  Then I should %s",
            action, actor, action, goal
        );
    }
    
    private String generateErrorScenario(String actor, String action) {
        return String.format(
            "Scenario: %s - Error Handling\n" +
            "  Given I am a %s\n" +
            "  When I attempt to %s with invalid data\n" +
            "  Then I should see an appropriate error message",
            action, actor, action
        );
    }
    
    private String generateEdgeCaseScenario(String actor, String action, String goal) {
        return String.format(
            "Scenario: %s - Edge Case\n" +
            "  Given I am a %s with special conditions\n" +
            "  When I %s\n" +
            "  Then I should still %s",
            action, actor, action, goal
        );
    }
    
    private Map<String, Object> getDefaultConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("mcp_version", "1.0");
        config.put("confidence_threshold", 0.75);
        return config;
    }
    
    // MCP Data Classes
    
    public static class MCPTestStrategy {
        private String strategyName;
        private String testTags;
        private String riskLevel;
        private int maxTimeMinutes;
        private List<String> changedFiles;
        private Date analysisTime;
        private String mcpVersion;
        
        public MCPTestStrategy() {}
        
        public MCPTestStrategy(String strategyName, String testTags, String riskLevel, int maxTimeMinutes) {
            this.strategyName = strategyName;
            this.testTags = testTags;
            this.riskLevel = riskLevel;
            this.maxTimeMinutes = maxTimeMinutes;
        }
        
        // Getters and setters
        public String getStrategyName() { return strategyName; }
        public void setStrategyName(String strategyName) { this.strategyName = strategyName; }
        public String getTestTags() { return testTags; }
        public void setTestTags(String testTags) { this.testTags = testTags; }
        public String getRiskLevel() { return riskLevel; }
        public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
        public int getMaxTimeMinutes() { return maxTimeMinutes; }
        public void setMaxTimeMinutes(int maxTimeMinutes) { this.maxTimeMinutes = maxTimeMinutes; }
        public List<String> getChangedFiles() { return changedFiles; }
        public void setChangedFiles(List<String> changedFiles) { this.changedFiles = changedFiles; }
        public Date getAnalysisTime() { return analysisTime; }
        public void setAnalysisTime(Date analysisTime) { this.analysisTime = analysisTime; }
        public String getMcpVersion() { return mcpVersion; }
        public void setMcpVersion(String mcpVersion) { this.mcpVersion = mcpVersion; }
    }
    
    public static class MCPInsights {
        private int totalSteps;
        private int passedSteps;
        private int failedSteps;
        private double successRate;
        private List<String> recommendations = new ArrayList<>();
        private boolean deploymentReady;
        
        // Getters and setters
        public int getTotalSteps() { return totalSteps; }
        public void setTotalSteps(int totalSteps) { this.totalSteps = totalSteps; }
        public int getPassedSteps() { return passedSteps; }
        public void setPassedSteps(int passedSteps) { this.passedSteps = passedSteps; }
        public int getFailedSteps() { return failedSteps; }
        public void setFailedSteps(int failedSteps) { this.failedSteps = failedSteps; }
        public double getSuccessRate() { return successRate; }
        public void setSuccessRate(double successRate) { this.successRate = successRate; }
        public List<String> getRecommendations() { return recommendations; }
        public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
        public boolean isDeploymentReady() { return deploymentReady; }
        public void setDeploymentReady(boolean deploymentReady) { this.deploymentReady = deploymentReady; }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        MCPAIAgent agent = new MCPAIAgent();
        
        // Example: Analyze repository changes
        List<String> changes = Arrays.asList("BestBuy.feature", "BestBuySteps.java");
        MCPTestStrategy strategy = agent.analyzeRepository(changes, "smart");
        System.out.println("Strategy: " + strategy.getStrategyName());
        
        // Example: Generate scenarios from user story
        String story = "As a customer, I want to search for products so that I can find items to purchase";
        List<String> scenarios = agent.generateScenariosFromStory(story);
        scenarios.forEach(System.out::println);
    }
} 