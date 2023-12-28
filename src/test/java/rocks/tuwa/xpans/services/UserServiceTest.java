package rocks.tuwa.xpans.services;

import org.junit.jupiter.api.Test;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.ValidateUserDto;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void validateUser_ShouldReturnTrue() {
        UserService userService = new UserService();
        ValidateUserDto validateUserDto = new ValidateUserDto(null, null);
        boolean result = userService.validateUser(validateUserDto);
        assertTrue(result, "Die Methode validateUser sollte true zurückgeben");
    }

    @Test
    void createUser_ShouldReturnTrue() {
        UserService userService = new UserService();
        CreateUserDto createUserDto = new CreateUserDto(null, null, null, null);
        boolean result = userService.createUser(createUserDto);
        assertTrue(result, "Die Methode createUser sollte true zurückgeben");
    }

}