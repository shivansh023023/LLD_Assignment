import java.nio.charset.StandardCharsets;

public class PdfEncoder implements ContentEncoder {
    @Override
    public byte[] encode(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + (req.body == null ? "" : req.body);
        return fakePdf.getBytes(StandardCharsets.UTF_8);
    }
}
