package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.controller.util.AppUtil;
import com.firecode.app.controller.util.LocalDatetUtil;
import com.firecode.app.model.entity.ContactEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.entity.PersonPhysicalEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@JsonPropertyOrder({"id", "name", "document", "email", "phone", "created_at", "updated_at"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Employees", description = "Employee API")
public class EmployeeDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Employee ID")
    private int id;

    @Getter
    @Setter
    @NotNull(message = "{name.not.null}")
    @NotBlank(message = "{name.not.blank}")
    @Size(min = 3, max = 70, message = "{name.size}")
    @JsonProperty("name")
    @ApiModelProperty(notes = "Employee Name", name = "name", required = true)
    private String name;

    @Getter
    @Setter
    @NotNull(message = "{document.not.null}")
    @NotBlank(message = "{document.not.blank}")
    @JsonProperty("document")
    @ApiModelProperty(notes = "Employee Document", name = "document", required = true)
    private String document;

    @Getter
    @Setter
    @NotNull(message = "{email.not.null}")
    @NotBlank(message = "{email.not.blank}")
    @JsonProperty("email")
    @ApiModelProperty(notes = "Employee Email", name = "email", required = true)
    private String email;

    @Getter
    @Setter
    @NotNull(message = "{phone.not.null}")
    @NotBlank(message = "{phone.not.blank}")
    @JsonProperty("phone")
    @ApiModelProperty(notes = "Employee phone", name = "phone", required = true)
    private String phone;

    @Getter
    @Setter
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public PersonEntity create(EmployeeDto dto) {

        PersonEntity person = new PersonEntity();
        PersonPhysicalEntity personPhysical = new PersonPhysicalEntity();
        ContactEntity contact = new ContactEntity();
        EmployeeEntity employee = new EmployeeEntity();

        person.setCreatedAt(LocalDatetUtil.currentDateTime());

        personPhysical.setIdPerson(person);
        personPhysical.setDocument(dto.getDocument());
        personPhysical.setName(dto.getName());
        person.setPersonPhysicalEntity(personPhysical);

        contact.setIdPersonPhysical(personPhysical);
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        personPhysical.setContactEntity(contact);

        employee.setIdPersonPhysical(personPhysical);
        employee.setCreatedAt(LocalDatetUtil.currentDateTime());
        employee.setUpdatedAt(LocalDatetUtil.currentDateTime());
        employee.setFilter(
                EmployeeDto.converterJson(
                        dto.getName(),
                        dto.getDocument(),
                        dto.getEmail(),
                        dto.getPhone())
        );
        personPhysical.setEmployeeEntity(employee);

        return person;
    }

    public static EmployeeDto converterObject(EmployeeEntity employee) {

        EmployeeDto dto = new EmployeeDto();

        dto.setId(employee.getId());
        dto.setName(employee.getIdPersonPhysical().getName());
        dto.setDocument(employee.getIdPersonPhysical().getDocument());
        dto.setEmail(employee.getIdPersonPhysical().getContactEntity().getEmail());
        dto.setPhone(employee.getIdPersonPhysical().getContactEntity().getPhone());
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());

        return dto;
    }

    public static String converterJson(String name, String document, String email, String phone) {
        JSONObject obj = new JSONObject();
        obj.put("name", AppUtil.convertAllLowercaseCharacters(name));
        obj.put("document", AppUtil.removeSpecialCharacters(document));
        obj.put("email", AppUtil.convertAllLowercaseCharacters(email));
        obj.put("phone", AppUtil.removeSpecialCharacters(phone));
        return obj.toString();
    }
}
