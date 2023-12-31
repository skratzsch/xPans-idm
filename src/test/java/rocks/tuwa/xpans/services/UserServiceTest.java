package rocks.tuwa.xpans.services;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.User;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
import rocks.tuwa.xpans.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testValidUserAndCorrectPassword() {
        User mockUser = new User("validUserId", hashPassword("password"), "");
        when(userRepository.findByUserId("validUserId")).thenReturn(Optional.of(mockUser));
        ValidateUserDto dto = new ValidateUserDto("validUserId", "password");
        assertThat(userService.validateUser(dto), is(true));
    }

    @Test
    void testValidUserAndIncorrectPassword() {
        User mockUser = new User("validUserId", hashPassword("password"), "");
        when(userRepository.findByUserId("validUserId")).thenReturn(Optional.of(mockUser));
        ValidateUserDto dto = new ValidateUserDto("validUserId", "incorrectPassword");
        assertThat(userService.validateUser(dto), is(false));
    }

    @Test
    void testInvalidUserId() {
        when(userRepository.findByUserId("invalidUserId")).thenReturn(Optional.empty());
        ValidateUserDto dto = new ValidateUserDto("invalidUserId", "anyPassword");
        assertThat(userService.validateUser(dto), is(false));
    }

    @Test
    void testNullUserId() {
        ValidateUserDto dto = new ValidateUserDto(null, "anyPassword");
        assertThat(userService.validateUser(dto), is(false));
    }

    @Test
    void testNullPassword() {
        User mockUser = new User("validUserId", hashPassword("password"), "");
        when(userRepository.findByUserId("validUserId")).thenReturn(Optional.of(mockUser));
        ValidateUserDto dto = new ValidateUserDto("validUserId", null);
        assertThat(userService.validateUser(dto), is(false));
    }

    @Test
    void testEmptyUserId() {
        ValidateUserDto dto = new ValidateUserDto("", "anyPassword");
        assertThat(userService.validateUser(dto), is(false));
    }

    @Test
    void testEmptyPassword() {
        User mockUser = new User("validUserId", "hashedPassword", "");
        when(userRepository.findByUserId("validUserId")).thenReturn(Optional.of(mockUser));
        ValidateUserDto dto = new ValidateUserDto("validUserId", "");
        assertThat(userService.validateUser(dto), is(false));
    }

    @Test
    void testSpecialCharactersInUserId() {
        User mockUser = new User("validUserId!@#", hashPassword("password"), "");
        when(userRepository.findByUserId("validUserId!@#")).thenReturn(Optional.of(mockUser));
        ValidateUserDto dto = new ValidateUserDto("validUserId!@#", "password");
        assertThat(userService.validateUser(dto), is(true));
    }

    @Test
    void testSpecialCharactersInPassword() {
        User mockUser = new User("validUserId", hashPassword("password!@#"), "");
        when(userRepository.findByUserId("validUserId")).thenReturn(Optional.of(mockUser));
        ValidateUserDto dto = new ValidateUserDto("validUserId", "password!@#");
        assertThat(userService.validateUser(dto), is(true));
    }

    @Test
    void createUserWithNullDto() {
        boolean result = userService.createUser(null);
        assertThat(result, is(false));
    }

    @Test
    void createUserWithValidDto() {
        CreateUserDto validDto = new CreateUserDto("1", "password", "description");

        userService.createUser(validDto);

        ArgumentCaptor<User> userCaptor = forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        assertThat(capturedUser, is(notNullValue()));
        assertThat(capturedUser.getUserId(), is("1"));
    }

    @Test
    void createUserWithInvalidDto() {
        CreateUserDto invalidDto = new CreateUserDto(null, "", "");
        boolean result = userService.createUser(invalidDto);
        assertThat(result, is(false));
    }

    @Test
    void createUserThrowsException() {
        CreateUserDto validDto = new CreateUserDto(null, null, null);
        User user = User.fromCreateUser(validDto);
        boolean result = userService.createUser(validDto);
        assertThat(result, is(false));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256", new BouncyCastleProvider());
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encode(hash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Fehler beim Hashen des Passworts", e);
        }
    }
}