package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import com.firecode.app.model.entity.RateEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@JsonPropertyOrder({"id", "name_event", "date_event", "employee_name", "rate_employee"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Employees", description = "Event Participation API")
public class EventParticipationDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Event Participation ID")
    private int id;

    @Getter
    @Setter
    @NotNull(message = "{idEvent.not.null}")
    @JsonProperty("id_event")
    @ApiModelProperty(notes = "Event ID", name = "id_event", required = true)
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
    @JsonProperty("id_rate")
    @ApiModelProperty(notes = "Rate ID", name = "id_rate", required = true)
    private Integer idRate;

    @Getter
    @Setter
    @JsonProperty("name_event")
    @ApiModelProperty(notes = "Event name", name = "name_event")
    private String nameEvent;

    @Getter
    @Setter
    @JsonProperty("date_event")
    @ApiModelProperty(notes = "Date of the event", name = "date_event")
    private String dateEvent;

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
    @JsonProperty("employee_rate")
    @ApiModelProperty(notes = "Event rate", name = "employee_rate")
    private String employeeRate;

    public EventParticipationEntity create(EventParticipationDto dto, EmployeeEntity employee) {

        EventParticipationEntity eventParticipation = new EventParticipationEntity();

        eventParticipation.setIdEvent(new EventEntity(1));
        eventParticipation.setIdEmployee(employee);
        eventParticipation.setIdRate(new RateEntity(dto.getIdRate()));
        eventParticipation.setFilter(EventParticipationDto.converterJson(
                employee.getIdPersonPhysical().getName(),
                employee.getIdPersonPhysical().getDocument(),
                employee.getIdPersonPhysical().getContactEntity().getEmail(),
                employee.getIdPersonPhysical().getContactEntity().getPhone())
        );

        return eventParticipation;
    }

    public static EventParticipationDto converterObject(EventParticipationEntity eventParticipation) {

        EventParticipationDto dto = new EventParticipationDto();

        if (eventParticipation != null) {

            dto.setId(eventParticipation.getId());
            dto.setNameEvent(eventParticipation.getIdEvent().getNameEvent());
            dto.setDateEvent(LocalDatetUtil.converterToLocalDate(eventParticipation.getIdEvent().getDateEvent()));
            dto.setEmployeeName(eventParticipation.getIdEmployee().getIdPersonPhysical().getName());

            if (eventParticipation.getIdRate().getId() == 1 || eventParticipation.getIdRate().getId() == 3) {
                dto.setEmployeeDrink("Yes");
            }

            dto.setEmployeeRate(NumberFormatUtil.formatMoney(eventParticipation.getIdRate().getValue(), 2, 2));

        }

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
