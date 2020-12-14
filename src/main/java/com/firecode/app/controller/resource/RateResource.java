package com.firecode.app.controller.resource;

import com.firecode.app.controller.dto.RateDto;
import com.firecode.app.controller.rule.RateRule;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping; 

@RestController
@RequestMapping("/rates")
public class RateResource {

    @Autowired
    private RateRule rateRule;

    @ApiOperation(value = "List Rates")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns List"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('read')")
    @GetMapping("/employee")
    public ResponseEntity<List<RateDto>> listRatesByEmployee() {
        return rateRule.findByEmployee();
    }

    @ApiOperation(value = "List Rates")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns List"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('read')")
    @GetMapping("/guest")
    public ResponseEntity<List<RateDto>> listRatesByGuest() {
        return rateRule.findByGuest();
    }

}
