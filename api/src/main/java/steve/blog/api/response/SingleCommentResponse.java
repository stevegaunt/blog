package steve.blog.api.response;

import steve.blog.core.model.ArticleComment;

public record SingleCommentResponse(ArticleCommentResponse comment) {
    public SingleCommentResponse(ArticleComment articleComment) {
        this(new ArticleCommentResponse(articleComment));
    }
}
