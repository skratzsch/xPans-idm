package rocks.tuwa.xpans.pojo;

import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
public class ValidateUserDto {

    public ValidateUserDto(String userId, String password) {
        this.userId = userId;
        this.password = hashPassword(password);
    }

    private String userId;

    private String password;

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
