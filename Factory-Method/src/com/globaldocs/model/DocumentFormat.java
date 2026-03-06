package globaldocs.model;

public enum DocumentFormat {
    PDF(".pdf"), DOC(".doc"), DOCX(".docx"),
    MARKDOWN(".md"), CSV(".csv"), TXT(".txt"), XLSX(".xlsx");

    private final String extension;

    DocumentFormat(String extension) { this.extension = extension; }
    public String getExtension() { return extension; }

    public static DocumentFormat fromExtension(String ext) {
        for (DocumentFormat f : values())
            if (f.extension.equalsIgnoreCase(ext)) return f;
        throw new IllegalArgumentException("Unsupported format: " + ext);
    }
}