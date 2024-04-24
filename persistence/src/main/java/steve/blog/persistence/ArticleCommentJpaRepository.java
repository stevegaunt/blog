package steve.blog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import steve.blog.core.model.Article;
import steve.blog.core.model.ArticleComment;

interface ArticleCommentJpaRepository extends JpaRepository<ArticleComment, Integer> {
    List<ArticleComment> findByArticleOrderByCreatedAtDesc(Article article);

    void deleteByArticle(Article article);
}
