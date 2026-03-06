package GlobalDocs.model;

import java.util.Set;

public enum Country {
    COLOMBIA("CO", "DIAN - Decreto 2242", Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.TAX_DECLARATION)),
    MEXICO("MX", "SAT - CFDI 4.0", Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.DIGITAL_CERTIFICATE)),
    ARGENTINA("AR", "AFIP - RG 4291", Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.FINANCIAL_REPORT)),
    CHILE("CL", "SII - Ley 20.727", Set.of(DocumentType.ELECTRONIC_INVOICE, DocumentType.LEGAL_CONTRACT));

    public final String code, regulation;
    public final Set<DocumentType> supported;

    Country(String code, String regulation, Set<DocumentType> supported) {
        this.code = code;
        this.regulation = regulation;
        this.supported = supported;
    }

    public boolean supports(DocumentType t) { return supported.contains(t); }
}