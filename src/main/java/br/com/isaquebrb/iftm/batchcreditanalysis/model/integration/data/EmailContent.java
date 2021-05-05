package br.com.isaquebrb.iftm.batchcreditanalysis.model.integration.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmailContent {

    private List<EmailAddress> emailAddresses;
}
