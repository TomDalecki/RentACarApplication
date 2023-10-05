package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeController.class)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class HomeControllerWebMvcTest {

    private final WebApplicationContext webApplicationContext;

    private final Environment environment;

    private MockMvc mockMvc;

    private String generatedPassword;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        generatedPassword = environment.getProperty("password");
    }

    @Test
    void thatHomeControllerWorksCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(HomeController.HOME)
                .header(HttpHeaders.AUTHORIZATION, "Basic user: " + generatedPassword))
                .andExpect(status().isOk());
    }
}