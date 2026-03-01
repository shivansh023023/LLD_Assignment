import java.nio.charset.StandardCharsets;

public class CsvEncoder implements ContentEncoder {
    @Override
    public byte[] encode(ExportRequest req) {
        // Fix data corruption: properly escape instead of blindly replacing newlines/commas
        String body = req.body == null ? "" : req.body;
        boolean needsQuotes = body.contains(",") || body.contains("\"\n") || body.contains("\n");
        if (needsQuotes) {
            body = "\"" + body.replace("\"", "\"\"") + "\"";
        }
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return csv.getBytes(StandardCharsets.UTF_8);
    }
}
