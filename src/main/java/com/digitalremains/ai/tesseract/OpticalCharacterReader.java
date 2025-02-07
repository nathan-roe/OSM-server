package com.digitalremains.ai.tesseract;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@Slf4j
public class OpticalCharacterReader {
    private static final String DATA_PATH = "/usr/share/tesseract-ocr/4.00/tessdata/";
    private static final Tesseract tesseract;

    static {
        tesseract = new Tesseract();
        configureTesseract();
    }

    public static String convertFileContentToText(final String fileName, final String fileContent) throws IOException, TesseractException {
        final byte[] decodedFileContent = Base64.getDecoder().decode(fileContent);
        final File file = new File(String.format("src/main/resources/%s", fileName));
        try (final OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(decodedFileContent);
            return tesseract.doOCR(file);
        } finally {
            if(!file.delete()) {
                file.deleteOnExit();
                log.error("Unable to delete created file at: {}", file.getAbsolutePath());
            }
        }
    }

    private static void configureTesseract() {
        tesseract.setDatapath(DATA_PATH);
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
    }
}
