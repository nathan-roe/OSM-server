package com.digitalremains.ai.langchain;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@NoArgsConstructor
public class LangchainConfiguration {
    @Value("${ollama.model.name}")
    private String modelName;

    @Value("${ollama.host.url}")
    private String baseUrl;

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OllamaChatModel.builder()
                .modelName(modelName)
                .baseUrl(baseUrl)
                .build();
    }


}