package steve.blog.api;

import steve.blog.core.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import steve.blog.api.response.TagsResponse;

@RestController
@RequiredArgsConstructor
class TagController {
    private final TagService tagService;

    @GetMapping("/api/tags")
    TagsResponse doGet() {
        return new TagsResponse(tagService.getAllTags());
    }
}
