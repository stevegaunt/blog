package steve.blog.core.model;

/** Can verify that the user's password is correct or encrypt the password. */
public interface PasswordEncoder {
    boolean matches(String rawPassword, String encodedPassword);

    String encode(String rawPassword);
}
