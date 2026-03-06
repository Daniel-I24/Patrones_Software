package globaldocs.model;

import java.util.Set;

public enum Country {
    COLOMBIA("CO", "Colombia", "DIAN - Decreto 2242/2015",
            Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.TAX_DECLARATION)),
    MEXICO("MX", "México", "SAT - CFDI 4.0",
            Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.TAX_DECLARATION, DocumentType.DIGITAL_CERTIFICATE)),
    ARGENTINA("AR", "Argentina", "AFIP - RG 4291/2018",
            Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.FINANCIAL_REPORT, DocumentType.TAX_DECLARATION)),
    CHILE("CL", "Chile", "SII - Ley 20.727",
            Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.LEGAL_CONTRACT, DocumentType.TAX_DECLARATION));

    private final String code;
    private final String displayName;
    private final String regulatoryFramework;
    private final Set<DocumentType> requiredDocumentTypes;

    Country(String code, String displayName, String regulatoryFramework, Set<DocumentType> requiredDocumentTypes) {
        this.code = code;
        this.displayName = displayName;
        this.regulatoryFramework = regulatoryFramework;
        this.requiredDocumentTypes = requiredDocumentTypes;
    }

    public String getCode() { 
        return code; 
    }
    public String getDisplayName() { 
        return displayName; 
    }
    public String getRegulatoryFramework() { 
        return regulatoryFramework; 
    }
    public Set<DocumentType> getRequiredDocumentTypes() { 
        return requiredDocumentTypes; 
    }
    public boolean supportsDocumentType(DocumentType type) { 
        return requiredDocumentTypes.contains(type); 
    }
}