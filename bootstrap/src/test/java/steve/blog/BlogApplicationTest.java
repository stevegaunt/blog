package steve.blog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Blog Application Test")
public class BlogApplicationTest {
    @Test
    @DisplayName("spring context of Blog Application is loaded.")
    void contextLoads() {}
}
