# 🥒 Cucumber Capstone Project - E-commerce Testing Framework

## 📋 Project Overview

This is a comprehensive **Cucumber BDD Testing Framework** developed as a capstone project for automated testing of e-commerce websites. The framework demonstrates modern test automation practices using **Behavior-Driven Development (BDD)** with **Cucumber**, **Selenium WebDriver**, and **Java**.

### 🎯 **Tested Applications:**
- **BestBuy.com** - Product search, cart operations, and user account management
- **Staples.com** - User registration and validation testing

### 🏗️ **Framework Architecture:**
- **Language:** Java 22
- **Testing Framework:** Cucumber with JUnit
- **Web Automation:** Selenium WebDriver 4.34.0
- **Build Tool:** Maven
- **Reporting:** ExtentReports with Cucumber Adapter
- **CI/CD:** GitHub Actions with intelligent test selection

---

## 🚀 Quick Start Guide

### **Prerequisites**
- ☕ **Java 22** or higher
- 📦 **Maven 3.6+**
- 🌐 **Chrome Browser** (latest version)
- 🔧 **Git**

### **Installation & Setup**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd Cucumber-Capstone
   ```

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Run Tests**
   
   **For Linux/Mac (Bash):**
   ```bash
   # Clean and run all tests (recommended)
   mvn clean test
   
   # Run all tests
   mvn test
   
   # Run tests using TestRunner class directly
   mvn test -Dtest=TestRunner
   
   # Run specific test scenarios
   mvn test -Dcucumber.filter.tags="@Positive"
   mvn test -Dcucumber.filter.tags="@UC-101 or @UC-102"
   
   # Run with TestRunner and specific tags
   mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@Positive"
   ```
   
   **For Windows (PowerShell):**
   ```powershell
   # Clean and run all tests (recommended)
   mvn clean test
   
   # Run all tests
   mvn test
   
   # Run tests using TestRunner class directly
   mvn test -Dtest=TestRunner
   
   # Run specific test scenarios (note the single quotes)
   mvn test '-Dcucumber.filter.tags=@Positive'
   mvn test '-Dcucumber.filter.tags=@UC-101 or @UC-102'
   
   # Run with TestRunner and specific tags
   mvn test -Dtest=TestRunner '-Dcucumber.filter.tags=@Positive'
   ```

---

## 🧪 Test Scenarios

### **BestBuy E-commerce Testing**

#### **🔍 Product Search & Cart Operations**
- **@UC-101** - Search for MacBook Pro products
- **@UC-102** - Add products to shopping cart
- **@UC-103** - Verify items in cart page
- **@UC-104** - Remove items from cart

#### **👤 User Account Management**
- **@UC-105** - Password validation (positive cases)
- **@UC-106** - Password validation (negative cases)

### **Staples Registration Testing**
- **@UC-107** - Successful user registration
- **@UC-108** - Phone number format validation (negative testing)

### **Test Tags**
- `@Positive` - All positive test scenarios
- `@Negative` - All negative test scenarios  
- `@Login` - Login-related tests
- `@PasswordValidation` - Password validation tests
- `@phoneValidation` - Phone number validation tests

---

## 📁 Project Structure

```
📦 Cucumber-Capstone
├── 🤖 .github/workflows/
│   └── copilot-enhanced-pipeline.yml    # CI/CD Pipeline
├── 📝 src/test/java/
│   ├── runner/
│   │   └── TestRunner.java              # Main test execution
│   └── steps/
│       ├── BestBuySteps.java           # BestBuy test steps
│       ├── BestBuyLoginSteps.java      # Login/account steps
│       ├── StaplesRegisterSteps.java   # Staples registration steps
│       └── WebDriverManager.java       # WebDriver utilities
├── 🥒 src/test/resources/
│   ├── features/
│   │   ├── BestBuy.feature             # BestBuy test scenarios
│   │   └── BestBuylogin.feature        # Login test scenarios
│   └── extent.properties               # Reporting configuration
├── ⚙️ pom.xml                          # Maven configuration
├── 🎓 CapstonePresentation/
│   ├── QEA Final Capstone presentation.pdf
│   └── final_capStone_project_video.mp4
└── 📚 Documentation/
    ├── FINAL-SETUP-GUIDE.md
    └── MCP-README.md
```

---

## 🎓 Capstone Presentation Materials

### 📊 **Presentation Slides**
- **File:** `CapstonePresentation/QEA Final Capstone presentation.pdf`
- **Content:** Project overview, framework architecture, test scenarios, and results
- **Format:** PDF (3.2MB)

### 🎥 **Video Demonstration**
- **File:** `CapstonePresentation/final_capStone_project_video.mp4`
- **Content:** Live code walkthrough and test execution demonstration
- **Size:** 12MB
- **Format:** MP4 video file

#### **📥 How to Access the Video:**
1. **Download Required:** The video file must be downloaded to view
2. **Location:** Navigate to `CapstonePresentation/` folder
3. **File Name:** `final_capStone_project_video.mp4`
4. **Viewing:** Use any standard video player (VLC, Windows Media Player, etc.)

#### **🎬 Video Contents:**
- Framework architecture explanation
- Live test execution demonstration
- Code walkthrough of key components
- Results analysis and reporting
- Q&A session

---

## 🔧 Advanced Features

### **🤖 Intelligent CI/CD Pipeline**
- **Automatic test selection** based on code changes
- **Risk-based deployment** decisions
- **Parallel test execution** for efficiency
- **Comprehensive reporting** with artifacts

### **📊 Enhanced Reporting**
- **ExtentReports** integration for detailed HTML reports
- **JSON and JUnit XML** outputs for CI/CD integration
- **Screenshot capture** on test failures
- **Test execution metrics** and insights

### **🏷️ Smart Test Tagging**
- **Scenario-based tagging** for targeted test execution
- **Priority-based execution** (@UC-101, @UC-102, etc.)
- **Category-based filtering** (@Positive, @Negative)

---

## 🚀 Running Tests

### **Basic Maven Commands**

```bash
# Clean build and run all tests (recommended for fresh execution)
mvn clean test

# Run all tests (uses existing compiled classes)
mvn test
```

### **Advanced Local Execution**

**For Linux/Mac (Bash):**
```bash
# Run all tests with TestRunner
mvn test -Dtest=TestRunner

# Quick smoke tests (fastest)
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@UC-101 or @UC-102"

# Comprehensive testing (all positive scenarios)
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@Positive"

# Full regression testing
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@Positive or @Negative"

# Specific feature testing
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@Login"

# Alternative: Run without specifying TestRunner (Maven will find it automatically)
mvn test -Dcucumber.filter.tags="@Positive"
```

**For Windows (PowerShell):**
```powershell
# Run all tests with TestRunner
mvn test -Dtest=TestRunner

# Quick smoke tests (fastest)
mvn test -Dtest=TestRunner '-Dcucumber.filter.tags=@UC-101 or @UC-102'

# Comprehensive testing (all positive scenarios)
mvn test -Dtest=TestRunner '-Dcucumber.filter.tags=@Positive'

# Full regression testing
mvn test -Dtest=TestRunner '-Dcucumber.filter.tags=@Positive or @Negative'

# Specific feature testing
mvn test -Dtest=TestRunner '-Dcucumber.filter.tags=@Login'

# Alternative: Run without specifying TestRunner (Maven will find it automatically)
mvn test '-Dcucumber.filter.tags=@Positive'
```

### **GitHub Actions (Automatic)**
- **Push to any branch** triggers intelligent test selection
- **Pull requests** run comprehensive validation
- **Manual execution** available through GitHub Actions UI

---

## 📈 Test Results & Reporting

### **Report Locations**
- **HTML Reports:** `target/cucumber-reports/`
- **JSON Results:** `target/cucumber-reports/Cucumber.json`
- **JUnit XML:** `target/cucumber-reports/Cucumber.xml`
- **ExtentReports:** `target/extent-reports/`

### **Viewing Reports**
1. Run tests: `mvn test -Dtest=TestRunner`
2. Open: `target/cucumber-reports/index.html`
3. View detailed test execution results with screenshots

### **TestRunner Class**
The `TestRunner.java` class is the main entry point for test execution:
- **Location:** `src/test/java/runner/TestRunner.java`
- **Purpose:** Configures Cucumber options, features location, and reporting
- **Usage:** Can be run directly with `mvn test -Dtest=TestRunner`

---

## 👥 Team & Contributors

- **Project Lead:** Tata (Cognizant)
- **Test Scenarios:** Robert, Tata
- **Framework Development:** QEA Team
- **Presentation:** Abule Othow, Tata

---

## 🛠️ Troubleshooting

### **Common Issues**

1. **WebDriver Issues**
   - Ensure Chrome browser is updated
   - Check ChromeDriver compatibility

2. **Test Failures**
   - Verify internet connection
   - Check if target websites are accessible
   - Review test data and selectors

3. **Build Issues**
   - Ensure Java 22 is installed
   - Run `mvn clean install` to refresh dependencies

4. **PowerShell Command Issues (Windows)**
   - Use single quotes around `-Dcucumber.filter.tags` parameter
   - Correct: `mvn test '-Dcucumber.filter.tags=@Positive'`
   - Incorrect: `mvn test -Dcucumber.filter.tags="@Positive"`
   - If still having issues, try Command Prompt (cmd) instead of PowerShell

### **Getting Help**
- Check the `FINAL-SETUP-GUIDE.md` for detailed setup instructions
- Review test logs in `target/` directory
- Examine GitHub Actions logs for CI/CD issues

---

## 📞 Contact & Support

For questions about this capstone project:
- **Review the presentation slides** in `CapstonePresentation/`
- **Watch the video demonstration** for detailed walkthrough
- **Check documentation** in the project root

---

## 🎉 Project Highlights

✅ **Complete BDD Framework** with Cucumber and Selenium  
✅ **Real-world E-commerce Testing** on live websites  
✅ **Intelligent CI/CD Pipeline** with GitHub Actions  
✅ **Comprehensive Test Coverage** with positive and negative scenarios  
✅ **Professional Reporting** with ExtentReports  
✅ **Clean Architecture** following best practices  
✅ **Video Demonstration** showing live execution  
✅ **Detailed Documentation** for easy understanding  

---

*This project demonstrates modern test automation practices and serves as a comprehensive example of Cucumber BDD framework implementation for e-commerce testing.* 