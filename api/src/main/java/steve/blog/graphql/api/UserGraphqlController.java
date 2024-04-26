package steve.blog.graphql.api;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import steve.blog.core.model.Article;
import steve.blog.core.model.ArticleFacets;
import steve.blog.core.model.User;
import steve.blog.core.service.ArticleService;
import steve.blog.core.service.UserService;

@Controller
@RequiredArgsConstructor
class UserGraphqlController {

    Logger logger = LoggerFactory.getLogger(UserGraphqlController.class);
    private final UserService userService;
    private final ArticleService articleService;

    @QueryMapping
    public User userByName(@Argument String username) {

        return userService.getUser(username);
    }

    @SchemaMapping
    public List<Article> article(User user) {
        var facets = new ArticleFacets(0, 20);

        logger.debug("find user for artlcle  %s name %s".formatted(user.getId(),user.getUsername()));


        return articleService.getArticlesOnly(user, facets);
    }
}
