package GlobalDocs.model;

import java.util.UUID;

public abstract class Document {
    private final String id;
    private final String fileName;
    private final DocumentType type;
    private final DocumentFormat format;
    private final Country country;
    private String encryptedContent;
    private DocumentStatus status = DocumentStatus.PENDING;

    protected Document(String fileName, DocumentType type, DocumentFormat format, Country country) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.fileName = fileName;
        this.type = type;
        this.format = format;
        this.country = country;
    }

    public abstract boolean validate();

    public String getId() { return id; }
    public String getFileName() { return fileName; }
    public DocumentType getType() { return type; }
    public DocumentFormat getFormat() { return format; }
    public Country getCountry() { return country; }
    public String getEncryptedContent() { return encryptedContent; }
    public DocumentStatus getStatus() { return status; }
    public void setEncryptedContent(String c) { this.encryptedContent = c; }
    public void setStatus(DocumentStatus s) { this.status = s; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s | %s", id, fileName, type, country.code, status);
    }
}