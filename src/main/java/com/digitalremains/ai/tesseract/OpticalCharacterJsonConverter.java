package com.digitalremains.ai.tesseract;

import com.digitalremains.ai.langchain.Assistant;
import com.digitalremains.ai.langchain.LangchainConfiguration;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OpticalCharacterJsonConverter {
    private static final String SYSTEM_MESSAGE = """
            You are the best model to map raw texts to desired Json format. you will be provided invoice's texts that you need to map into a JSON format.\s
            You are tasked with converting the given text into a JSON object with the specified structure.\s
            Please follow these guidelines:

            1. - If the provided text is empty or does not contain any relevant information, return the JSON structure with all values as empty strings.
               - If the provided text contains multiple instances of the same information (e.g., multiple names), use the first occurrence.
               - If the provided text contains conflicting information (e.g., different ages), use the first occurrence.

            2. Extract relevant information from the provided text and map it to the corresponding keys in the JSON structure.

            3. If a particular key's value is not found in the given text, leave the value as an empty string.

            4. Do not include any additional information or formatting beyond the requested JSON object.

            Here are some examples, I'm gonna provide you the raw_texts and json structure.
            raw_texts: %s
            json_structure: %s

            Convert the following text into a JSON object: %s
    """;

    final LangchainConfiguration config;

    public OpticalCharacterJsonConverter(final LangchainConfiguration config) {
        this.config = config;
    }

    public String convert(final String fileName, final String fileContent, final String rawText, final String jsonStructure) throws TesseractException, IOException {
        final String textFromFile = OpticalCharacterReader.convertFileContentToText(fileName, fileContent);
        log.info("OCR retrieved the following: {}", textFromFile);
        final Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(config.chatLanguageModel())
                .build();
        return assistant.answer(String.format(SYSTEM_MESSAGE, rawText, jsonStructure, textFromFile));
    }
}
