package it.gov.pagopa.wispconverter.technicalsupport.service.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.Instant;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder()
//@Accessors(fluent = true,chain = true)
public class ReEventDto {
    //// START KEY
    private String id;
    //// END KEY

    //// START LOGICAL REF
    private String requestId;           //gruppo, tutte le chiamate fatte con lo stesso X-Request-ID
    private String operationId;         //id operation associato a un requestId
    private String clientOperationId;   //id client operation associato a un operationId
    private ComponentEnum component;  //componente che scrive l'evento
    private Instant insertedTimestamp;  //ora di inserimento evento
    //// END LOGICAL REF

    //// START FIELD FOR INTERFACE AND INTERN CHANGE
    private EventCategoryEnum eventCategory;
    private EventSubcategoryEnum eventSubcategory;
    //// END FIELD FOR INTERFACE AND INTERN CHANGE

    //// START FIELD FOR INTERFACE
    private CallTypeEnum callType;

    private String consumer;
    private String provider;

    private OutcomeEnum outcome;

    private String httpMethod;
    private String httpUri;
    private String httpHeaders;
    private String httpCallRemoteAddress;

    private Integer httpStatusCode;
    private Long executionTimeMs;

    private String compressedPayload;       //zip+Base64
    private Integer compressedPayloadLength;

    private String businessProcess;

    private String operationStatus;       //dettaglio response in uscita
    private String operationErrorTitle;   //dettaglio response in uscita
    private String operationErrorDetail;  //dettaglio response in uscita
    private String operationErrorCode;    //dettaglio response in uscita
    //// END FIELD FOR INTERFACE

    //// START FIELD FOR INTERN CHANGE
    private String primitive;
    private String sessionId;
    private String cartId;
    private String iuv;
    private String noticeNumber;
    private String domainId;
    private String ccp;
    private String psp;
    private String station;
    private String channel;
    private String status;
    private String paymentToken;
    private String info;
    //// END FIELD FOR INTERN CHANGE

}
