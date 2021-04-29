package br.com.isaquebrb.iftm.batchcreditanalysis.reader;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.CreditAnalysis;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CustomReader {

    @Value("${file.input}")
    private String pathFile;

    public ItemReader<CreditAnalysis> documentReader() {
        return new FlatFileItemReaderBuilder<CreditAnalysis>()
                .name("fileReader")
                .resource(new ClassPathResource(pathFile))
                .delimited()
                .names("document")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(CreditAnalysis.class);
                }}).build();
    }
}