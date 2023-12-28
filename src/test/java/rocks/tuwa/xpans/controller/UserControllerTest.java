package rocks.tuwa.xpans.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rocks.tuwa.xpans.pojo.CreateUserDto;
import rocks.tuwa.xpans.pojo.ValidateUserDto;
import rocks.tuwa.xpans.services.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void validateUser_ShouldReturnOk() throws Exception {
        when(userService.validateUser(any(ValidateUserDto.class))).thenReturn(true);

        mockMvc.perform(get("/xpans/validateUser")
                        .param("userId", "user123")
                        .param("password", "pass123"))
                .andExpect(status().isOk());

        verify(userService).validateUser(any(ValidateUserDto.class));
    }

    @Test
    public void createUser_ShouldReturnCreatedMessage() throws Exception {
        when(userService.createUser(any(CreateUserDto.class))).thenReturn(true);

        String createUserJson = "{\"username\":\"user123\",\"password\":\"pass123\"}";
        mockMvc.perform(post("/xpans/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User wurde erstellt"));

        verify(userService).createUser(any(CreateUserDto.class));
    }
}
