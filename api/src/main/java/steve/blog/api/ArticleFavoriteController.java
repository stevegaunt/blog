package steve.blog.api;

import java.util.UUID;

import steve.blog.core.service.ArticleService;
import steve.blog.core.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import steve.blog.api.response.SingleArticleResponse;

@RestController
@RequiredArgsConstructor
class ArticleFavoriteController {
    private final UserService userService;
    private final ArticleService articleService;

    @PostMapping("/api/articles/{slug}/favorite")
    SingleArticleResponse doPost(Authentication authentication, @PathVariable String slug) {
        var requester = userService.getUser(UUID.fromString(authentication.getName()));
        var article = articleService.getArticle(slug);

        articleService.favorite(requester, article);

        return new SingleArticleResponse(articleService.getArticleDetails(requester, article));
    }

    @DeleteMapping("/api/articles/{slug}/favorite")
    SingleArticleResponse doDelete(Authentication authentication, @PathVariable String slug) {
        var requester = userService.getUser(UUID.fromString(authentication.getName()));
        var article = articleService.getArticle(slug);

        articleService.unfavorite(requester, article);

        return new SingleArticleResponse(articleService.getArticleDetails(requester, article));
    }
}
