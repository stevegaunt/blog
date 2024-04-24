package steve.blog.api.response;

import java.util.Collection;

import steve.blog.core.model.Tag;

public record TagsResponse(String[] tags) {
    public TagsResponse(Collection<Tag> tags) {
        this(tags.stream().map(Tag::getName).toArray(String[]::new));
    }
}
