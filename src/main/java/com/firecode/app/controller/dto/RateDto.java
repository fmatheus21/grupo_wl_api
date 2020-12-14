package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.model.entity.RateEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id", "name", "value", "employee"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Rates", description = "Rate API")
public class RateDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Rate ID")
    private int id;

    @Getter
    @Setter
    @JsonProperty("name")
    @ApiModelProperty(notes = "Rate Name", name = "name")
    private String name;

    @Getter
    @Setter
    @JsonProperty("value")
    @ApiModelProperty(notes = "Value", name = "value")
    private BigDecimal value;

    @Getter
    @Setter
    @JsonProperty("employee")
    @ApiModelProperty(notes = "Employee", name = "employee")
    private boolean employee;

    public static RateDto converterObject(RateEntity rate) {

        RateDto dto = new RateDto();

        dto.setId(rate.getId());
        dto.setName(rate.getName());
        dto.setValue(rate.getValue());

        return dto;
    }

}
