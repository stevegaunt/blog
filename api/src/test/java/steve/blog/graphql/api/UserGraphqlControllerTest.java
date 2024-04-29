package steve.blog.graphql.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import steve.blog.core.model.*;
import steve.blog.core.service.ArticleService;
import steve.blog.core.service.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGraphqlControllerTest {

    @InjectMocks
    UserGraphqlController sut;

    @Mock
    UserService userService;

    @Mock
    ArticleService articleService;

    User testUser1;

    Article testArticle1;
    Article testArticle2;

    List<Article> testArticles;


    @BeforeEach
    void setUp() {
        testUser1 = new TestUser( UUID.randomUUID(),"testuser1", "testuser1", "password");
        testArticle1 =  new TestArticle(1,testUser1,"test title 1 ", "test description 1","test");
        testArticle2 =  new TestArticle(2,testUser1,"test title 2 ", "test description 2","test");
        testArticles = Arrays.asList(testArticle1, testArticle2);


    }

    @Test
    void userByName() {

        when(userService.getUser("testuser1")).thenReturn(testUser1);
        // when
        User user = sut.userByName ("testuser1");

        assertEquals(user,testUser1);

    }

    @Test
    void article() {

        ArticleFacets facets = new ArticleFacets(0, 20);
        when(articleService.getArticlesOnly(testUser1, facets)).thenReturn(testArticles);

        List<Article> articles = sut.article(testUser1);

        assertNotNull(articles);
        assertEquals(articles.size(),testArticles.size());
        assertEquals(articles,testArticles);
    }
}