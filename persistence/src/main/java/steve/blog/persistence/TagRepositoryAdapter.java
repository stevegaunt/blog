package steve.blog.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import steve.blog.core.model.Tag;
import steve.blog.core.model.TagRepository;

@Repository
@RequiredArgsConstructor
class TagRepositoryAdapter implements TagRepository {
    private final TagJpaRepository tagJpaRepository;

    @Override
    public List<Tag> findAll() {
        return tagJpaRepository.findAll();
    }
}
