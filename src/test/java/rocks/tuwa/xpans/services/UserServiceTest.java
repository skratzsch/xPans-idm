package rocks.tuwa.xpans.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.User;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
import rocks.tuwa.xpans.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        userRepositoryMock = mock(UserRepository.class);
    }

    @Test
    void createUser_ShouldReturnTrue() {
        UserService userService = new UserService(userRepositoryMock);
        CreateUserDto createUserDto = new CreateUserDto(null, "null", null);
        boolean result = userService.createUser(createUserDto);
        assertTrue(result, "Die Methode createUser sollte true zurückgeben");
    }

}