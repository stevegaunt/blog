package steve.blog.api.response;

public record UserResponse(String email, String token, String username, String bio, String image) {}
