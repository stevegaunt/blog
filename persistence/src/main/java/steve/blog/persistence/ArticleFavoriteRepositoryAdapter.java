package steve.blog.persistence;

import steve.blog.core.model.ArticleFavoriteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import steve.blog.core.model.Article;
import steve.blog.core.model.ArticleFavorite;
import steve.blog.core.model.User;

@Repository
@RequiredArgsConstructor
class ArticleFavoriteRepositoryAdapter implements ArticleFavoriteRepository {
    private final ArticleFavoriteJpaRepository articleFavoriteJpaRepository;

    @Override
    public void save(ArticleFavorite articleFavorite) {
        articleFavoriteJpaRepository.save(articleFavorite);
    }

    @Override
    @Transactional
    public void deleteBy(User user, Article article) {
        articleFavoriteJpaRepository.deleteByUserAndArticle(user, article);
    }

    @Override
    public boolean existsBy(User user, Article article) {
        return articleFavoriteJpaRepository.existsByUserAndArticle(user, article);
    }
}
