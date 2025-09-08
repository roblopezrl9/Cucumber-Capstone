# Copilot-Enhanced Test Script
# This script uses GitHub Copilot for intelligent test execution

Write-Host " Copilot-Enhanced Test Execution Starting..." -ForegroundColor Green

# Load MCP configuration
$MCP_CONFIG = "mcp-copilot-config-enhanced.json"
if (-not (Test-Path $MCP_CONFIG)) {
    Write-Host " MCP configuration not found: $MCP_CONFIG" -ForegroundColor Red
    exit 1
}

# Parse test strategy from environment or MCP config
$TEST_STRATEGY = $env:TEST_STRATEGY
if (-not $TEST_STRATEGY) {
    $TEST_STRATEGY = "comprehensive"
}

Write-Host " Test Strategy: $TEST_STRATEGY" -ForegroundColor Cyan

# Create results directory
New-Item -ItemType Directory -Path "mcp-results" -Force | Out-Null

# Execute tests based on strategy
switch ($TEST_STRATEGY) {
    "smoke" {
        Write-Host " Running smoke tests..." -ForegroundColor Yellow
        mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@smoke" -Dcucumber.plugin="pretty,html:target/cucumber-reports.html,json:mcp-results/cucumber.json,junit:mcp-results/junit.xml"
    }
    "regression" {
        Write-Host " Running regression tests..." -ForegroundColor Yellow
        mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@regression" -Dcucumber.plugin="pretty,html:target/cucumber-reports.html,json:mcp-results/cucumber.json,junit:mcp-results/junit.xml"
    }
    "comprehensive" {
        Write-Host " Running comprehensive tests..." -ForegroundColor Yellow
        mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@T1" -Dcucumber.plugin="pretty,html:target/cucumber-reports.html,json:mcp-results/cucumber.json,junit:mcp-results/junit.xml"
    }
    "parallel" {
        Write-Host " Running parallel tests..." -ForegroundColor Yellow
        mvn test -Dtest=TestRunner -Dcucumber.execution.parallel.enabled=true -Dcucumber.execution.parallel.config.strategy=dynamic -Dcucumber.plugin="pretty,html:target/cucumber-reports.html,json:mcp-results/cucumber.json,junit:mcp-results/junit.xml"
    }
    default {
        Write-Host " Running default test suite..." -ForegroundColor Yellow
        mvn test -Dtest=TestRunner -Dcucumber.plugin="pretty,html:target/cucumber-reports.html,json:mcp-results/cucumber.json,junit:mcp-results/junit.xml"
    }
}

Write-Host " Copilot-Enhanced Test Execution Complete!" -ForegroundColor Green
Write-Host " Test results available in mcp-results/ directory" -ForegroundColor Cyan
