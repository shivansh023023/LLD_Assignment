public class BasicExporter implements Exporter {
    private final ContentEncoder encoder;
    private final String contentType;

    public BasicExporter(ContentEncoder encoder, String contentType) {
        this.encoder = encoder;
        this.contentType = contentType;
    }

    @Override
    public ExportResult export(ExportRequest req) {
        byte[] data = encoder.encode(req);
        return new ExportResult(contentType, data);
    }
}
