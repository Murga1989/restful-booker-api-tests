# 🏨 Booking API Test Suite

A comprehensive Java + Maven test automation framework for REST API testing using JUnit 5, REST Assured, and Jackson. This project demonstrates end-to-end testing of booking management APIs with full CRUD operations.

## 📋 Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Quick Start](#quick-start)
- [Test Scenarios](#test-scenarios)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Customization](#customization)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **Complete CRUD Testing**: Create, Read, Update, Delete operations
- **Chain Testing**: End-to-end scenarios with test data flow
- **Authentication Support**: Token-based authentication handling
- **Data Validation**: Request/response validation with comprehensive assertions
- **Error Handling**: Negative test cases and edge case coverage
- **Ordered Execution**: Tests run in logical sequence with data dependencies
- **Detailed Reporting**: Comprehensive test reports with REST Assured logging
- **Easy Configuration**: Environment-specific configurations
- **Industry Best Practices**: Clean code, maintainable test structure

## 🔧 Prerequisites

- **Java**: 17 or later
- **Maven**: 3.8+ (or use IntelliJ's bundled Maven)
- **IntelliJ IDEA**: Community or Ultimate Edition
- **Internet Connection**: Required for API calls and dependency downloads

## 📁 Project Structure

```
booking-api-tests/
├── README.md
├── pom.xml
├── src/
│   ├── main/java/com/example/
│   │   ├── config/
│   │   │   └── TestConfiguration.java    # Test configurations and endpoints
│   │   └── model/
│   │       └── Booking.java              # Booking data model with JSON mapping
│   └── test/java/com/example/tests/
│       └── BookingAPITests.java          # Main test suite
├── target/                               # Build output (auto-generated)
└── .gitignore                           # Git ignore file
```

## 🚀 Quick Start

### 1. Clone or Download the Project

```bash
git clone <repository-url>
cd booking-api-tests
```

### 2. Import into IntelliJ IDEA

1. Open IntelliJ IDEA
2. File → Open → Select the project directory
3. Wait for Maven to import dependencies

### 3. Build the Project

```bash
mvn clean compile test-compile
```

### 4. Run Tests

```bash
# Run all tests
mvn test

# Run with detailed output
mvn test -X
```

Or use IntelliJ:
- Right-click on `BookingAPITests.java` → Run

## 🧪 Test Scenarios

### 📝 Create Booking Tests
- **✅ Successful Creation**: Validates 200 OK response with correct booking data
- **❌ Missing Required Field**: Tests 400 Bad Request for incomplete data

### 📖 Get Booking Tests  
- **✅ Retrieve Existing Booking**: Gets booking by valid ID (200 OK)
- **❌ Non-existent Booking**: Tests 404 Not Found for invalid ID

### ✏️ Update Booking Tests
- **✅ Full Update (PUT)**: Complete booking replacement
- **✅ Partial Update (PATCH)**: Selective field updates

### 🗑️ Delete Booking Tests
- **✅ Successful Deletion**: Confirms 201 Created response
- **🔗 Chained Verification**: Delete → GET → Verify 404 Not Found

### 🔍 Additional Tests
- **Data Type Validation**: Invalid data type handling
- **Edge Cases**: Boundary value testing
- **Authentication**: Token-based security testing

## ⚙️ Configuration

### API Endpoint Configuration

Update the base URL in `BookingAPITests.java`:

```java
private static final String BASE_URL = "https://your-api-endpoint.com";
```

### Authentication Configuration

Modify the `getAuthToken()` method for your API's authentication:

```java
private String getAuthToken() {
    return given()
        .contentType(ContentType.JSON)
        .body("{\n" +
              "    \"username\" : \"your-username\",\n" +
              "    \"password\" : \"your-password\"\n" +
              "}")
    .when()
        .post("/auth")
    .then()
        .statusCode(200)
        .extract()
        .path("token");
}
```

### Test Data Configuration

Customize test data in `TestConfiguration.java`:

```java
public static class TestData {
    public static final String DEFAULT_USERNAME = "admin";
    public static final String DEFAULT_PASSWORD = "password123";
}
```

## 🏃‍♂️ Running Tests

### Command Line Options

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=BookingAPITests

# Run specific test method
mvn test -Dtest=BookingAPITests#testCreateBookingSuccess

# Run tests with system properties
mvn test -Dbase.url=https://staging-api.com

# Skip tests during build
mvn install -DskipTests
```

### IntelliJ IDEA Options

1. **Run All Tests**: Right-click on test class → Run
2. **Run Single Test**: Click green arrow next to test method
3. **Debug Mode**: Right-click → Debug (allows breakpoints)
4. **Run Configuration**: Create custom run configurations for different environments

### Test Execution Order

Tests are executed in the following order using `@Order` annotations:

1. Create booking (stores ID for later tests)
2. Create with missing field (independent)
3. Get existing booking (uses stored ID)
4. Get non-existent booking (independent)
5. Full update (uses stored ID)
6. Partial update (uses stored ID)  
7. Delete and verify chain (uses stored ID)

## 📊 Test Reports

### Maven Surefire Reports

Generated automatically in `target/surefire-reports/`:
- `TEST-*.xml`: JUnit XML reports
- `*.txt`: Text-based reports

### Custom Reporting (Optional)

Add Allure reporting to `pom.xml`:

```xml
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.12.0</version>
</plugin>
```

Generate reports:
```bash
mvn allure:report
mvn allure:serve
```

## 🔧 Customization

### Adding New Test Cases

1. Create new test method with `@Test` annotation
2. Use `@Order` annotation for execution sequence
3. Follow the existing pattern for REST Assured calls

Example:
```java
@Test
@Order(10)
@DisplayName("Test new scenario")
void testNewScenario() {
    given()
        .contentType(ContentType.JSON)
    .when()
        .get("/new-endpoint")
    .then()
        .statusCode(200);
}
```

### Modifying Data Model

Update `Booking.java` to match your API schema:

```java
@JsonProperty("new_field")
private String newField;

// Add getter/setter
public String getNewField() { return newField; }
public void setNewField(String newField) { this.newField = newField; }
```

### Environment-Specific Testing

Create profiles in `pom.xml`:

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <api.base.url>https://dev-api.example.com</api.base.url>
        </properties>
    </profile>
    <profile>
        <id>staging</id>
        <properties>
            <api.base.url>https://staging-api.example.com</api.base.url>
        </properties>
    </profile>
</profiles>
```

Run with profile:
```bash
mvn test -Pdev
```

## 🐛 Troubleshooting

### Common Issues and Solutions

#### ❌ Dependencies Not Resolving
```bash
mvn clean install -U
```
Or in IntelliJ: Maven tool window → "Reload All Maven Projects"

#### ❌ Tests Failing Due to Network Issues
- Check internet connection
- Verify API endpoint accessibility
- Check if external API is down (normal for integration tests)

#### ❌ Authentication Failures
- Verify credentials in `getAuthToken()` method
- Check if API requires different authentication method
- Ensure authentication endpoint is correct

#### ❌ Java Version Issues
- Ensure Java 17+ is installed and configured
- In IntelliJ: File → Project Structure → Project SDK

#### ❌ Port/Connection Issues
```java
// Add connection timeout configuration
RestAssured.config = RestAssured.config()
    .httpClient(HttpClientConfig.httpClientConfig()
        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
        .setParam(CoreConnectionPNames.SO_TIMEOUT, 10000));
```

### Debug Mode

Enable detailed logging:

```java
@BeforeAll
void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    // For even more detailed logging:
    RestAssured.given().log().all();
}
```

### Test Data Issues

If tests fail due to data dependencies:
- Check test execution order
- Verify test data cleanup
- Use `@TestInstance(TestInstance.Lifecycle.PER_CLASS)` for shared state

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines

- Follow existing code style and patterns
- Add tests for new functionality
- Update documentation for significant changes
- Ensure all tests pass before submitting PR

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [REST Assured](https://rest-assured.io/) - For comprehensive REST API testing capabilities
- [JUnit 5](https://junit.org/junit5/) - For modern testing framework features
- [Jackson](https://github.com/FasterXML/jackson) - For JSON processing
- [Restful Booker API](https://restful-booker.herokuapp.com/) - For providing a free testing API

## 📞 Support

For questions and support:
- Create an issue in the repository
- Check the [troubleshooting section](#troubleshooting)
- Review the [Maven documentation](https://maven.apache.org/guides/)
- Check [REST Assured documentation](https://rest-assured.io/)

---

**Happy Testing! 🚀**
