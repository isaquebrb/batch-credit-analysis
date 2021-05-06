package br.com.isaquebrb.iftm.batchcreditanalysis.model.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class EmailContent {

    @JsonProperty("emails")
    private List<EmailAddress> emailAddresses;
}
