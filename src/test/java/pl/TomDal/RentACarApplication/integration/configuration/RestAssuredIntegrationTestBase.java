package pl.TomDal.RentACarApplication.integration.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import pl.TomDal.RentACarApplication.integration.support.ControllerTestSupport;

public abstract class RestAssuredIntegrationTestBase extends AbstractIntegrationTest
        implements ControllerTestSupport {

    @Autowired
    protected ObjectMapper objectMapper;
    @LocalServerPort
    private int serverPort;
    @Value("${server.servlet.context-path}")
    private String basePath;

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    // ten fragment kodu możemy rozumieć jako konfigurację klienta Rest Assured,
    // który w testach będzie wołał naszą aplikację, nasze api
    public RequestSpecification requestSpecification() {
        return RestAssured
                .given()
                .config(getConfig())
                .basePath(basePath)
                .port(serverPort)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }
    // dodajemy objectMapper, żeby w testach zawsze tak samo były konfigurowane obiekty na jsona
    private RestAssuredConfig getConfig() {
        return RestAssuredConfig
                .config()
                .objectMapperConfig(new ObjectMapperConfig()
                    .jackson2ObjectMapperFactory((p1, p2) -> objectMapper));
    }
}
