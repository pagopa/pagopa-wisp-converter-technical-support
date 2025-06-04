package it.gov.pagopa.wispconverter.technicalsupport.service;

import it.gov.pagopa.wispconverter.technicalsupport.controller.mapper.ReEventMapperImpl;
import it.gov.pagopa.wispconverter.technicalsupport.controller.model.ReEvent;
import it.gov.pagopa.wispconverter.technicalsupport.repository.ReEventRepository;
import it.gov.pagopa.wispconverter.technicalsupport.repository.model.ReEventEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = {ReService.class, ReEventMapperImpl.class})
class ReServiceTest {

    private static final String SESSION_ID = "sessionId";
    private static final String NOTICE_NUMBER = "noticeNumber";
    private static final String ORGANIZATION_ID = "12345678900";
    private static final String IUV = "iuv";

    @MockBean
    private ReEventRepository reEventRepository;

    @Autowired
    private ReService sut;

    @Test
    void findByNoticeNumberOK() {
        LocalDate localDate = LocalDate.now();
        Set<ReEventEntity> reEventEntityList = Collections.singleton(ReEventEntity.builder()
                .partitionKey(localDate.toString())
                .domainId(ORGANIZATION_ID)
                .noticeNumber(NOTICE_NUMBER)
                .insertedTimestamp(Instant.now())
                .build());

        doReturn(Collections.singleton(SESSION_ID))
                .when(reEventRepository)
                .findSessionIdByNoticeNumberAndDomainId(localDate.toString(), localDate.toString(), ORGANIZATION_ID, NOTICE_NUMBER);
        doReturn(reEventEntityList)
                .when(reEventRepository).findBySessionId(localDate.toString(), localDate.toString(), SESSION_ID);

        List<ReEvent> result = assertDoesNotThrow(() -> sut.findByNoticeNumber(localDate, localDate, ORGANIZATION_ID, NOTICE_NUMBER));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ORGANIZATION_ID, result.get(0).getDomainId());
        assertEquals(NOTICE_NUMBER, result.get(0).getNoticeNumber());
    }

    @Test
    void findByIuvOK() {
        LocalDate localDate = LocalDate.now();
        Set<ReEventEntity> reEventEntityList = Collections.singleton(ReEventEntity.builder()
                .partitionKey(localDate.toString())
                .domainId(ORGANIZATION_ID)
                .iuv(IUV)
                .insertedTimestamp(Instant.now())
                .build());

        doReturn(Collections.singleton(SESSION_ID))
                .when(reEventRepository)
                .findSessionIdByIuvAndDomainId(localDate.toString(), localDate.toString(), ORGANIZATION_ID, IUV);
        doReturn(reEventEntityList)
                .when(reEventRepository).findBySessionId(localDate.toString(), localDate.toString(), SESSION_ID);

        List<ReEvent> result = assertDoesNotThrow(() -> sut.findByIuv(localDate, localDate, ORGANIZATION_ID, IUV));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ORGANIZATION_ID, result.get(0).getDomainId());
        assertEquals(IUV, result.get(0).getIuv());
    }

    @Test
    void findBySessionIdOK() {
        LocalDate localDate = LocalDate.now();
        Set<ReEventEntity> reEventEntityList = Collections.singleton(ReEventEntity.builder()
                .partitionKey(localDate.toString())
                .domainId(ORGANIZATION_ID)
                .sessionId(SESSION_ID)
                .insertedTimestamp(Instant.now())
                .build());

        doReturn(reEventEntityList)
                .when(reEventRepository).findBySessionId(localDate.toString(), localDate.toString(), SESSION_ID);

        List<ReEvent> result = assertDoesNotThrow(() -> sut.findBySessionId(localDate, localDate, SESSION_ID));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ORGANIZATION_ID, result.get(0).getDomainId());
        assertEquals(SESSION_ID, result.get(0).getSessionId());
    }
}