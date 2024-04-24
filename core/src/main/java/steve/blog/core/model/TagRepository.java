package steve.blog.core.model;

import java.util.List;

public interface TagRepository {
    List<Tag> findAll();
}
