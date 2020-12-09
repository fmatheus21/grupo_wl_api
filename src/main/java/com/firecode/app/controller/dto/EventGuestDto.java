package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.controller.util.LocalDatetUtil;
import com.firecode.app.controller.util.NumberFormatUtil;
import com.firecode.app.model.entity.EventGuestEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id", "event_name", "event_date", "employee_rate",
    "guest_name", "guest_document", "guest_drink", "guest_rate"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Employees", description = "Event Guest API")
public class EventGuestDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Event Guest ID")
    private int id;

    @Getter
    @Setter
    @JsonProperty("event_name")
    @ApiModelProperty(notes = "Event name", name = "event_name")
    private String eventName;

    @Getter
    @Setter
    @JsonProperty("event_date")
    @ApiModelProperty(notes = "Date of the event", name = "event_date")
    private String eventDate;

    @Getter
    @Setter
    @JsonProperty("guest_name")
    @ApiModelProperty(notes = "Name guest", name = "guest_name", required = true)
    private String guestName;

    @Getter
    @Setter
    @JsonProperty("guest_document")
    @ApiModelProperty(notes = "Document guest", name = "guest_document", required = true)
    private String guestDocument;

    @Getter
    @Setter
    @JsonProperty("guest_rate")
    @ApiModelProperty(notes = "Rate guest", name = "guest_rate", required = true)
    private String guestRate;

    @Getter
    @Setter
    @JsonProperty("guest_drink")
    @ApiModelProperty(notes = "Does the guest drink?", name = "guest_drink")
    private String guestDrink;

    public static EventGuestDto converterObject(EventGuestEntity eventGuest) {

        var dto = new EventGuestDto();

        dto.setId(eventGuest.getId());
        dto.setEventName(eventGuest.getIdEventParticipation().getIdEvent().getNameEvent());
        dto.setEventDate(LocalDatetUtil.converterLocalDateTimeToString(eventGuest.getIdEventParticipation().getIdEvent().getDateEvent()));
        dto.setGuestName(eventGuest.getNameGuest());
        dto.setGuestRate(NumberFormatUtil.formatMoney(eventGuest.getIdRate().getValue(), 2, 2));

        if (eventGuest.getIdRate().getId() == 3) {
            dto.setGuestDrink("Yes");
        } else if (eventGuest.getIdRate().getId() == 4) {
            dto.setGuestDrink("No");
        }

        return dto;
    }

}
