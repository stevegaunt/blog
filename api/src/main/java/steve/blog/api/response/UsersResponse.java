package steve.blog.api.response;

import steve.blog.core.model.User;

public record UsersResponse(UserResponse user) {
    public static UsersResponse from(User user, String token) {
        return new UsersResponse(
                new UserResponse(user.getEmail(), token, user.getUsername(), user.getBio(), user.getImageUrl()));
    }
}
