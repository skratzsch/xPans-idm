package rocks.tuwa.xpans.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUserDto {

    private String userId;

    private String password;

    private String description;

    private LocalDateTime dateCreated;

}
