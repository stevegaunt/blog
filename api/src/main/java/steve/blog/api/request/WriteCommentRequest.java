package steve.blog.api.request;

public record WriteCommentRequest(Params comment) {
    public record Params(String body) {}
}
