package steve.blog.api;

import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import steve.blog.api.request.LoginUserRequest;
import steve.blog.api.request.SignupRequest;
import steve.blog.api.request.UpdateUserRequest;
import steve.blog.api.response.UsersResponse;
import steve.blog.core.model.User;
import steve.blog.core.model.UserRegistry;
import steve.blog.core.service.UserService;

@RestController
@RequiredArgsConstructor
class UserController {
    private static final String LOGIN_URL = "/api/users/login";

    private final UserService userService;
    private final DummyBearerTokenProvider bearerTokenProvider;

    @PostMapping("/api/users")
    public ModelAndView doPost(HttpServletRequest httpServletRequest, @RequestBody SignupRequest request) {
        var userRegistry = new UserRegistry(
                request.user().email(),
                request.user().username(),
                request.user().password());

        userService.signup(userRegistry);

        // Redirect to login API to automatically login when signup is complete
        var loginRequest =
                new LoginUserRequest(request.user().email(), request.user().password());
        httpServletRequest.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

        return new ModelAndView("redirect:" + LOGIN_URL, "user", Map.of("user", loginRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(LOGIN_URL)
    @Operation(summary = "to login user", description = "name and password needed")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Successful"),
                @ApiResponse(responseCode = "500", description = "Internal server error"),
                @ApiResponse(responseCode = "1001", description = "Application specific error.")
            })
    public UsersResponse doPost(@RequestBody LoginUserRequest request) {
        var email = request.user().email();
        var password = request.user().password();

        var user = userService.login(email, password);
        var accessToken = bearerTokenProvider.getToken(user);

        return UsersResponse.from(user, accessToken.getTokenValue());
    }

    @GetMapping("/api/user")
    public UsersResponse doGet(JwtAuthenticationToken authentication) {
        var user = userService.getUser(UUID.fromString(authentication.getName()));

        return UsersResponse.from(user, authentication.getToken().getTokenValue());
    }

    @PutMapping("/api/user")
    public UsersResponse doPut(JwtAuthenticationToken authentication, @RequestBody UpdateUserRequest request) {
        User requester = userService.updateUserDetails(
                UUID.fromString(authentication.getName()),
                request.user().email(),
                request.user().username(),
                request.user().password(),
                request.user().bio(),
                request.user().image());

        return UsersResponse.from(requester, authentication.getToken().getTokenValue());
    }
}
