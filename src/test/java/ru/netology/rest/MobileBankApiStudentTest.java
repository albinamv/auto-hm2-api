package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiStudentTest {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
            .baseUri("http://localhost:9999/api/v1")
        // Выполняемые действия
        .when()
            .get("/demo/accounts")
        // Проверки
        .then()
            .statusCode(200);
    }

    @Test
    void shouldMatchJSONSchema() {
        // Given - When - Then
        // Предусловия
        given()
            .baseUri("http://localhost:9999/api/v1")
        // Выполняемые действия
        .when()
            .get("/demo/accounts")
        // Проверки
        .then()
            .statusCode(200)
            // static import для JsonSchemaValidator.matchesJsonSchemaInClasspath
            .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }

    @Test
    void shouldReturnCurrencyValueRUBOrUSD() {
        // Given - When - Then
        // Предусловия
        given()
            .baseUri("http://localhost:9999/api/v1")
        // Выполняемые действия
        .when()
            .get("/demo/accounts")
        // Проверки
        .then()
            .body("every{ it.currency.equals(\"RUB\") || it.currency.equals(\"USD\") }", is(true));
    }
}
