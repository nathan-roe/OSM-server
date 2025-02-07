package com.digitalremains.deceasedmanagement.service;

import com.digitalremains.ai.tesseract.OpticalCharacterJsonConverter;
import com.digitalremains.deceasedmanagement.model.FileUpload;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CertificateContentParser {
    private static final String RAW_TEXT = """
    I Tom Smith County Clerk of Redmund County, in the State of New York document, record, seal, and hereby certify the death of John Robert Doe DOB 12/12/1900 Age 125 Cause of Death old age married signle widowed divorced date of death 12/12/2020 date recorded 12/12/2020 document # 123123123123 book and page: G 1600 P 433 Application: DOA-5555 this is to certify that this document is a true abstract of death recorded and filed with the County. Signature County Clerk: SIgnature Witness: Date: Print Name:  
    """;

    private static final String JSON_STRUCTURE = """
    {
        firstName: John,
        middleName: Robert,
        lastName: Doe,
    }
    """;

    private final OpticalCharacterJsonConverter opticalCharacterJsonConverter;

    public CertificateContentParser(final OpticalCharacterJsonConverter opticalCharacterJsonConverter) {
        this.opticalCharacterJsonConverter = opticalCharacterJsonConverter;
    }

    public String parse(final FileUpload fileUpload) {
        try {
            final String text = opticalCharacterJsonConverter.convert(
                    fileUpload.getName(),
                    fileUpload.getContent(),
                    RAW_TEXT,
                    JSON_STRUCTURE
            );
            log.info(text);
            return text;
        } catch (final TesseractException | IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
