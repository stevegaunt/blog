package steve.blog.api.response;

import steve.blog.core.model.User;

public record ProfilesResponse(ProfileResponse profile) {
    public static ProfilesResponse from(User user) {
        return from(user, false);
    }

    public static ProfilesResponse from(User user, boolean following) {
        return new ProfilesResponse(ProfileResponse.from(user, following));
    }
}
