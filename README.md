# SauceDemo-Playwright-Solution

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Playwright](https://img.shields.io/badge/Playwright-Java-green.svg)](https://playwright.dev/java/)
[![Allure Reports](https://img.shields.io/badge/Allure-Reports-brightgreen.svg)](https://docs.qameta.io/allure/)

A comprehensive automated testing solution for the [SauceDemo](https://www.saucedemo.com/) e-commerce website using Playwright with Java and Allure reporting.

## 🚀 Features

- End-to-end testing of the SauceDemo web application
- Java implementation with Playwright framework
- Page Object Model (POM) design pattern
- Cross-browser testing capabilities
- Parallel test execution
- Detailed test reporting with Allure
- CI/CD pipeline integration

## 📋 Prerequisites

- [Java JDK](https://www.oracle.com/java/technologies/downloads/) (version 11 or higher)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)
- [Allure Command Line](https://docs.qameta.io/allure/#_installing_a_commandline) (for report generation)

## 🔧 Installation

1. Clone the repository:
```bash
git clone https://github.com/Mukuldev21/SauceDemo-Playwright-Solution.git
```

2. Navigate to the project directory:
```bash
cd SauceDemo-Playwright-Solution
```

3. Install dependencies:
```bash
mvn clean install
```

4. Install Playwright browsers:
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

## 🏃‍♂️ Running Tests

### Run all tests
```bash
mvn clean test
```

### Run tests with specific tags
```bash
mvn clean test -Dgroups="smoke"
```

### Run tests in a specific browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run tests in headed mode
```bash
mvn clean test -Dheaded=true
```

## 📊 Test Reports

This project uses Allure for detailed test reporting.

### Generate and open Allure report
```bash
# Generate the report
allure generate target/allure-results -o allure-report --clean

# Open the report
allure open allure-report
```

### Jenkins Integration

If you're using Jenkins, you can install the Allure plugin to view reports directly in your Jenkins dashboard.

## 📁 Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mukuldev/saucedemo/
│   │   │       ├── pages/             # Page Object Models
│   │   │       │   ├── LoginPage.java
│   │   │       │   ├── InventoryPage.java
│   │   │       │   ├── CartPage.java
│   │   │       │   └── CheckoutPage.java
│   │   │       ├── utils/             # Utility functions
│   │   │       └── config/            # Configuration files
│   ├── test/
│   │   ├── java/
│   │   │   └── com/mukuldev/saucedemo/tests/
│   │   │       ├── BaseTest.java      # Test setup and teardown
│   │   │       ├── LoginTests.java    # Login functionality tests
│   │   │       ├── InventoryTests.java # Inventory page tests
│   │   │       ├── CartTests.java     # Shopping cart tests
│   │   │       └── CheckoutTests.java # Checkout process tests
│   │   ├── resources/
│   │   │   ├── allure.properties      # Allure configuration
│   │   │   └── testdata/              # Test data files
├── .github/                           # GitHub Actions workflow
├── pom.xml                            # Maven configuration
└── README.md                          # Project documentation
```

## 🔄 CI/CD Integration

This project includes configuration for continuous integration using GitHub Actions. Check the `.github/workflows` directory for workflow configurations.

## 🧪 Test Coverage

- User authentication (login/logout)
- Product browsing and filtering
- Adding/removing items from cart
- Checkout process
- Form validations
- Error scenarios

## 📈 Test Metrics

Allure reports provide detailed metrics including:
- Test execution time
- Pass/fail rates
- Screenshots of failures
- Step-by-step execution logs
- Environment details

## 👨‍💻 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgements

- [Sauce Labs](https://saucelabs.com/) for providing the SauceDemo website for testing purposes
- [Playwright](https://playwright.dev/java/) for the powerful Java testing framework
- [Allure Framework](https://github.com/allure-framework) for excellent test reporting capabilities

