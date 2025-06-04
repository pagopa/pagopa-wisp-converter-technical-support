package it.gov.pagopa.wispconverter.technicalsupport.config;

import com.microsoft.azure.kusto.data.Client;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataExplorerConfigMock {

    @Bean
    public Client getClient() {
        return Mockito.mock(Client.class);
    }
}
