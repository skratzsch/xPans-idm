package rocks.tuwa.xpans.controller;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
import rocks.tuwa.xpans.services.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /*
    @Test
    void validateUser_Successful() throws Exception {
        String userId = "userId";
        String password = "password";

        when(userService.validateUser(any(ValidateUserDto.class))).thenReturn(true);

        mockMvc.perform(get("/validateUser")
                        .param("userId", userId)
                        .param("password", password))
                .andExpect(status().isOk());

        verify(userService).validateUser(new ValidateUserDto(userId, password));
    }

    @Test
    void validateUser_Failed() throws Exception {
        String userId = "testUser";
        String password = "wrongPass";

        when(userService.validateUser(any(ValidateUserDto.class))).thenReturn(false);

        mockMvc.perform(get("/validateUser")
                        .param("userId", userId)
                        .param("password", password))
                .andExpect(status().isBadRequest());

        verify(userService).validateUser(new ValidateUserDto(userId, password));
    }
    */

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
