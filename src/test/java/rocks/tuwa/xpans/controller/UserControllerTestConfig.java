package rocks.tuwa.xpans.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.mockito.Mockito;
import rocks.tuwa.xpans.services.UserService;

@TestConfiguration
public class UserControllerTestConfig {

    @Bean
    public UserService userService() {
        // Mock des UserService
        return Mockito.mock(UserService.class);
    }
}
