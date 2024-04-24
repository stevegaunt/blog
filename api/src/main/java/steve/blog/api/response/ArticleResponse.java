package steve.blog.api.response;

import java.time.LocalDateTime;
import java.util.Collection;

import steve.blog.core.model.Article;
import steve.blog.core.model.ArticleDetails;
import steve.blog.core.model.ArticleTag;
import steve.blog.core.model.Tag;

public record ArticleResponse(
        String slug,
        String title,
        String description,
        String body,
        String[] tagList,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean favorited,
        int favoritesCount,
        ProfileResponse author) {
    public ArticleResponse(ArticleDetails articleDetails) {
        this(
                articleDetails.article(),
                articleDetails.articleTags(),
                articleDetails.favorited(),
                articleDetails.favoritesCount());
    }

    public ArticleResponse(Article article, Collection<ArticleTag> articleTags, boolean favorited, int favoritesCount) {
        this(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getContent(),
                articleTags.stream().map(ArticleTag::getTag).map(Tag::getName).toArray(String[]::new),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                favorited,
                favoritesCount,
                ProfileResponse.from(article.getAuthor(), false));
    }
}
