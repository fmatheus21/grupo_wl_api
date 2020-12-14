package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.controller.util.AppUtil;
import com.firecode.app.controller.util.LocalDatetUtil;
import com.firecode.app.controller.util.NumberFormatUtil;
import com.firecode.app.model.entity.EventEntity;
import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.EventGuestEntity;
import com.firecode.app.model.entity.RateEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@JsonPropertyOrder({"id", "event_name", "event_date", "employee_name", "employee_drink", "employee_rate", "id_guest",
    "guest_name", "guest_document", "guest_drink", "guest_rate", "has_guest", "employee_total", "guest_total", "grand_total"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Employees", description = "Event Participation API")
public class EventParticipationDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Event Participation ID")
    private Integer id;

    @Getter
    @Setter    
    @JsonProperty("id_event")
    @ApiModelProperty(notes = "Event ID", name = "id_event")
    private Integer idEvent;

    @Getter
    @Setter
    @NotNull(message = "{idEmployee.not.null}")
    @JsonProperty("id_employee")
    @ApiModelProperty(notes = "Employee ID", name = "id_employee", required = true)
    private Integer idEmployee;

    @Getter
    @Setter
    @NotNull(message = "{idRate.not.null}")
    @JsonProperty("id_employee_rate")
    @ApiModelProperty(notes = "Rate ID", name = "id_employee_rate", required = true)
    private Integer idEmployeeRate;

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
    @JsonProperty("employee_rate")
    @ApiModelProperty(notes = "Rate employee", name = "employee_rate", required = true)
    private BigDecimal employeeRate;

    @Getter
    @Setter
    @JsonProperty("guest_rate")
    @ApiModelProperty(notes = "Rate guest", name = "guest_rate", required = true)
    private BigDecimal guestRate;

    @Getter
    @Setter
    @JsonProperty("id_guest_rate")
    @ApiModelProperty(notes = "Rate guest ID", name = "id_guest_rate", required = true)
    private Integer idGuestRate;

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
    @JsonProperty("employee_name")
    @ApiModelProperty(notes = "Employee name", name = "employee_name")
    private String employeeName;

    @Getter
    @Setter
    @JsonProperty("employee_drink")
    @ApiModelProperty(notes = "Does the employee drink?", name = "employee_drink")
    private String employeeDrink;

    @Getter
    @Setter
    @JsonProperty("guest_drink")
    @ApiModelProperty(notes = "Does the guest drink?", name = "guest_drink")
    private String guestDrink;

    @Getter
    @Setter
    @JsonProperty("employee_total")
    @ApiModelProperty(notes = "Total collected from employee", name = "employee_total")
    private String employeeTotal;

    @Getter
    @Setter
    @JsonProperty("guest_total")
    @ApiModelProperty(notes = "Total collected from the guest", name = "guest_total")
    private String guestTotal;

    @Getter
    @Setter
    @JsonProperty("grand_total")
    @ApiModelProperty(notes = "Total collected", name = "grand_total")
    private String grandTotal;

    @Getter
    @Setter
    @JsonProperty("has_guest")
    @ApiModelProperty(notes = "HasGuest", name = "has_guest")
    private boolean hasGuest;
    
    @Getter
    @Setter
    @JsonProperty("id_guest")
    @ApiModelProperty(notes = "Guest ID", name = "id_guest")
    private int id_guest;
    
    

    public EventParticipationEntity create(EventParticipationDto dto, EmployeeEntity employee) {

        var eventParticipation = new EventParticipationEntity();
        var eventGuest = new EventGuestEntity();

        eventParticipation.setIdEvent(new EventEntity(1));
        eventParticipation.setIdEmployee(employee);
        eventParticipation.setIdRate(new RateEntity(dto.getIdEmployeeRate()));
        eventParticipation.setFilter(EventParticipationDto.converterJson(
                employee.getIdPersonPhysical().getName(),
                employee.getIdPersonPhysical().getDocument(),
                employee.getIdPersonPhysical().getContactEntity().getEmail(),
                employee.getIdPersonPhysical().getContactEntity().getPhone())
        );

        try {
            eventGuest.setNameGuest(dto.getGuestName());
            eventGuest.setDocument(dto.getGuestDocument());
            eventGuest.setIdEventParticipation(eventParticipation);
            eventGuest.setIdRate(new RateEntity(dto.getIdGuestRate()));
            eventParticipation.setEventGuestEntity(eventGuest);
        } catch (NullPointerException e) {
        }

        return eventParticipation;
    }

    public static EventParticipationDto converterObject(EventParticipationEntity eventParticipation) {

        EventParticipationDto dto = new EventParticipationDto();

        if (eventParticipation != null) {

            dto.setId(eventParticipation.getId());
            dto.setEventName(eventParticipation.getIdEvent().getNameEvent());
            dto.setEventDate(LocalDatetUtil.converterLocalDateTimeToString(eventParticipation.getIdEvent().getDateEvent()));
            dto.setEmployeeName(eventParticipation.getIdEmployee().getIdPersonPhysical().getName());
            dto.setEmployeeRate(eventParticipation.getIdRate().getValue());

            if (eventParticipation.getIdRate().getId() == 1) {
                dto.setEmployeeDrink("Sim");
            } else if (eventParticipation.getIdRate().getId() == 2) {
                dto.setEmployeeDrink("Não");
            }

            if (eventParticipation.getEventGuestEntity() != null) {
                dto.setId_guest(eventParticipation.getEventGuestEntity().getId());
                dto.setGuestName(eventParticipation.getEventGuestEntity().getNameGuest());
                dto.setGuestDocument(eventParticipation.getEventGuestEntity().getDocument());
                dto.setGuestRate(eventParticipation.getEventGuestEntity().getIdRate().getValue());
                dto.setHasGuest(true);

                if (eventParticipation.getEventGuestEntity().getIdRate().getId() == 3) {
                    dto.setGuestDrink("Sim");
                } else if (eventParticipation.getEventGuestEntity().getIdRate().getId() == 4) {
                    dto.setGuestDrink("Não");
                }

            }

        }

        return dto;
    }

    public static EventParticipationDto totalCollected(List<EventParticipationEntity> list) {

        BigDecimal totalEmployee = new BigDecimal(0);
        BigDecimal totalGuest = new BigDecimal(0);
        BigDecimal grandTotal = new BigDecimal(0);

        for (EventParticipationEntity event : list) {
            totalEmployee = totalEmployee.add(event.getIdRate().getValue());
            if (event.getEventGuestEntity() != null) {
                totalGuest = totalGuest.add(event.getIdRate().getValue());
            }
        }

        var dto = new EventParticipationDto();
        dto.setEmployeeTotal(NumberFormatUtil.formatMoney(totalEmployee, 2, 2));
        dto.setGuestTotal(NumberFormatUtil.formatMoney(totalGuest, 2, 2));
        grandTotal = totalEmployee.add(totalGuest);
        dto.setGrandTotal(NumberFormatUtil.formatMoney(grandTotal, 2, 2));

        return dto;

    }

    public static String converterJson(String employee, String document, String email, String phone) {
        JSONObject obj = new JSONObject();
        obj.put("employee", AppUtil.convertAllLowercaseCharacters(employee));
        obj.put("document", AppUtil.removeSpecialCharacters(document));
        obj.put("email", AppUtil.convertAllLowercaseCharacters(email));
        obj.put("phone", AppUtil.removeSpecialCharacters(phone));
        return obj.toString();
    }
}
