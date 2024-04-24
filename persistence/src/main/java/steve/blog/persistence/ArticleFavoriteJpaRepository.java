package steve.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import steve.blog.core.model.Article;
import steve.blog.core.model.ArticleFavorite;
import steve.blog.core.model.User;

interface ArticleFavoriteJpaRepository extends JpaRepository<ArticleFavorite, Integer> {
    void deleteByUserAndArticle(User user, Article article);

    boolean existsByUserAndArticle(User user, Article article);

    int countByArticle(Article article);
}
