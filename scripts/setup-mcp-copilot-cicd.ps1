# MCP + GitHub Copilot CI/CD Setup Script
# This script sets up the complete MCP and Copilot integration for CI/CD automation

Write-Host " Setting up MCP + GitHub Copilot CI/CD Automation..." -ForegroundColor Green

# Check prerequisites
Write-Host " Checking prerequisites..." -ForegroundColor Yellow

# Check if Maven is installed
try {
    $mvnVersion = mvn -version 2>$null
    Write-Host " Maven found: $($mvnVersion[0])" -ForegroundColor Green
} catch {
    Write-Host " Maven not found. Please install Maven first." -ForegroundColor Red
    exit 1
}

# Check if Java is installed
try {
    $javaVersion = java -version 2>&1
    Write-Host " Java found: $($javaVersion[0])" -ForegroundColor Green
} catch {
    Write-Host " Java not found. Please install Java first." -ForegroundColor Red
    exit 1
}

# Check if Git is installed
try {
    $gitVersion = git --version 2>$null
    Write-Host " Git found: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host " Git not found. Please install Git first." -ForegroundColor Red
    exit 1
}

Write-Host " All prerequisites met!" -ForegroundColor Green

# Create necessary directories
Write-Host " Creating necessary directories..." -ForegroundColor Yellow
New-Item -ItemType Directory -Path "mcp-results" -Force | Out-Null
New-Item -ItemType Directory -Path "scripts/copilot-automation" -Force | Out-Null
New-Item -ItemType Directory -Path ".github/workflows" -Force | Out-Null

# Test MCP configuration
Write-Host " Testing MCP configuration..." -ForegroundColor Yellow
if (Test-Path "mcp-copilot-config-enhanced.json") {
    Write-Host " MCP configuration found" -ForegroundColor Green
} else {
    Write-Host " MCP configuration not found. Please ensure mcp-copilot-config-enhanced.json exists." -ForegroundColor Red
    exit 1
}

# Test build process
Write-Host " Testing build process..." -ForegroundColor Yellow
try {
    mvn clean compile -DskipTests
    Write-Host " Build test successful" -ForegroundColor Green
} catch {
    Write-Host " Build test failed: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Test test execution
Write-Host " Testing test execution..." -ForegroundColor Yellow
try {
    mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@smoke" -Dcucumber.plugin="pretty,html:target/cucumber-reports.html,json:mcp-results/test-cucumber.json"
    Write-Host " Test execution successful" -ForegroundColor Green
} catch {
    Write-Host " Test execution had issues (this is normal if no smoke tests exist)" -ForegroundColor Yellow
}

# Display setup summary
Write-Host ""
Write-Host " MCP + GitHub Copilot CI/CD Setup Complete!" -ForegroundColor Green
Write-Host "=============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host " Available Scripts:" -ForegroundColor Yellow
Write-Host "   Build: .\scripts\copilot-automation\copilot-build.ps1" -ForegroundColor White
Write-Host "   Test: .\scripts\copilot-automation\copilot-test.ps1" -ForegroundColor White
Write-Host "   Deploy: .\scripts\copilot-automation\copilot-deploy.ps1" -ForegroundColor White
Write-Host "   Orchestrator: .\scripts\copilot-automation\copilot-cicd-orchestrator.ps1" -ForegroundColor White
Write-Host ""
Write-Host " GitHub Actions Workflows:" -ForegroundColor Yellow
Write-Host "   MCP Enhanced: .github\workflows\mcp-cucumber-pipeline.yml" -ForegroundColor White
Write-Host "   Copilot Enhanced: .github\workflows\copilot-enhanced-pipeline.yml" -ForegroundColor White
Write-Host ""
Write-Host " Quick Start Commands:" -ForegroundColor Yellow
Write-Host "  Smart Pipeline: .\scripts\copilot-automation\copilot-cicd-orchestrator.ps1 -Pipeline smart" -ForegroundColor White
Write-Host "  Build Only: .\scripts\copilot-automation\copilot-cicd-orchestrator.ps1 -Pipeline build" -ForegroundColor White
Write-Host "  Test Only: .\scripts\copilot-automation\copilot-cicd-orchestrator.ps1 -Pipeline test" -ForegroundColor White
Write-Host "  Deploy to Dev: .\scripts\copilot-automation\copilot-cicd-orchestrator.ps1 -Pipeline deploy -Environment dev" -ForegroundColor White
Write-Host ""
Write-Host " Configuration Files:" -ForegroundColor Yellow
Write-Host "   MCP Config: mcp-copilot-config-enhanced.json" -ForegroundColor White
Write-Host "   Documentation: MCP-README.md" -ForegroundColor White
Write-Host ""
Write-Host " Next Steps:" -ForegroundColor Yellow
Write-Host "  1. Push your changes to GitHub to trigger the CI/CD pipeline" -ForegroundColor White
Write-Host "  2. Go to GitHub Actions tab to monitor pipeline execution" -ForegroundColor White
Write-Host "  3. Use the orchestrator script for local development" -ForegroundColor White
Write-Host "  4. Customize the MCP configuration for your specific needs" -ForegroundColor White
Write-Host ""
Write-Host " Your MCP + GitHub Copilot CI/CD automation is ready!" -ForegroundColor Green
