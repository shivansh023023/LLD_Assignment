import java.nio.charset.StandardCharsets;

public class JsonEncoder implements ContentEncoder {
    @Override
    public byte[] encode(ExportRequest req) {
        String safeTitle = req.title == null ? "" : req.title.replace("\"", "\\\"");
        String safeBody = req.body == null ? "" : req.body.replace("\"", "\\\"").replace("\n", "\\n");
        String json = "{ \"title\": \"" + safeTitle + "\", \"body\": \"" + safeBody + "\" }";
        return json.getBytes(StandardCharsets.UTF_8);
    }
}
