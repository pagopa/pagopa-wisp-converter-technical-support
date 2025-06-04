package it.gov.pagopa.wispconverter.technicalsupport.controller;

import it.gov.pagopa.wispconverter.technicalsupport.controller.model.ReEvent;
import it.gov.pagopa.wispconverter.technicalsupport.service.ReService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static it.gov.pagopa.wispconverter.technicalsupport.util.Constants.DATE_FROM;
import static it.gov.pagopa.wispconverter.technicalsupport.util.Constants.DATE_TO;
import static it.gov.pagopa.wispconverter.technicalsupport.util.Constants.IUV;
import static it.gov.pagopa.wispconverter.technicalsupport.util.Constants.NOTICE_NUMBER;
import static it.gov.pagopa.wispconverter.technicalsupport.util.Constants.ORGANIZATION;
import static it.gov.pagopa.wispconverter.technicalsupport.util.Constants.SESSION_ID;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TechnicalSupportControllerTest {

    private static final String ORGANIZATION_ID = "organizationId";
    @MockBean
    private ReService reService;

    @Autowired
    private MockMvc mvc;

    @Test
    void findByNoticeNumber() throws Exception {
        LocalDate localDate = LocalDate.now();
        doReturn(List.of(new ReEvent()))
                .when(reService).findByNoticeNumber(localDate, localDate, ORGANIZATION_ID, NOTICE_NUMBER);

        String urlTemplate = String.format("/organizations/%s/notice-number/%s", ORGANIZATION, NOTICE_NUMBER);
        mvc.perform(get(urlTemplate, ORGANIZATION_ID, NOTICE_NUMBER)
                        .param(DATE_FROM, localDate.toString())
                        .param(DATE_TO, localDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByIuv() throws Exception {
        LocalDate localDate = LocalDate.now();
        doReturn(List.of(new ReEvent()))
                .when(reService).findByIuv(localDate, localDate, ORGANIZATION_ID, IUV);

        String urlTemplate = String.format("/organizations/%s/iuv/%s", ORGANIZATION, IUV);
        mvc.perform(get(urlTemplate, ORGANIZATION_ID, IUV)
                        .param(DATE_FROM, localDate.toString())
                        .param(DATE_TO, localDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findBySessionId() throws Exception {
        LocalDate localDate = LocalDate.now();
        doReturn(List.of(new ReEvent()))
                .when(reService).findBySessionId(localDate, localDate, SESSION_ID);

        String urlTemplate = String.format("/session-id/%s", SESSION_ID);
        mvc.perform(get(urlTemplate, SESSION_ID)
                        .param(DATE_FROM, localDate.toString())
                        .param(DATE_TO, localDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}