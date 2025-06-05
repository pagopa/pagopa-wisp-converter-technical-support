package it.gov.pagopa.wispconverter.technicalsupport.config;

import it.gov.pagopa.wispconverter.technicalsupport.repository.RTRepository;
import it.gov.pagopa.wispconverter.technicalsupport.repository.ReEventExperimentalRepository;
import it.gov.pagopa.wispconverter.technicalsupport.repository.ReEventRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosmosDBConfigMock {

    @Bean
    public ReEventExperimentalRepository getReEventExperimentalRepository() {
        return Mockito.mock(ReEventExperimentalRepository.class);
    }

    @Bean
    public ReEventRepository getReEventRepository() {
        return Mockito.mock(ReEventRepository.class);
    }

    @Bean
    public RTRepository getRtRepository() {
        return Mockito.mock(RTRepository.class);
    }
}