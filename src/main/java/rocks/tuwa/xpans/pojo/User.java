package rocks.tuwa.xpans.pojo;

import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private String userId;

    private String password;

    private String hashedPassword;

    private String description;

    private LocalDateTime dateCreated;


    private User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    private User(String userId, String hashedPassword, String description, LocalDateTime dateCreated) {
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.description = description;
        this.dateCreated = dateCreated;
    };

    public static User fromValidateUser(ValidateUserDto validateUserDto) {
        return new User(validateUserDto.getUserId(), User.hashPassword(validateUserDto.getPassword()));
    }

    public static User fromCreateUser(CreateUserDto createUserDto) {
        return new User(createUserDto.getUserId(), User.hashPassword(createUserDto.getPassword()),
                createUserDto.getDescription(), createUserDto.getDateCreated());
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256", new BouncyCastleProvider());
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encode(hash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Fehler beim Hashen des Passworts", e);
        }
    }
}