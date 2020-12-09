package com.firecode.app.controller.resource;

import com.firecode.app.controller.dto.EventParticipationDto;
import com.firecode.app.controller.rule.EventParticipationRule;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/event/participation")
public class EventParticipationResource {

    @Autowired
    private EventParticipationRule eventParticipationRule;

    @ApiOperation(value = "List Event Participatio")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns List"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('read')")
    @GetMapping()
    public ResponseEntity<Page<EventParticipationDto>> list(RepositoryFilter filter, Pageable page) {
        return eventParticipationRule.findAllPaginator(filter, page);
    }

    @ApiOperation(value = "Consult Event Participatio By ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns The Record"),
        @ApiResponse(code = 404, message = "Register Not Found"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('read')")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return eventParticipationRule.findById(id);
    }

    @ApiOperation(value = "Register a New Event Participatio")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Record Created"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid EventParticipationDto dto, HttpServletResponse response) {
        return eventParticipationRule.create(dto, response);
    }

    @ApiOperation(value = "Delete Event Participatio By ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Record Deleted"),
        @ApiResponse(code = 404, message = "Register Not Found"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return eventParticipationRule.delete(id);
    }

    @ApiOperation(value = "Total Collected")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns List"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('read')")
    @GetMapping("/totalcollected")
    public ResponseEntity<?> totalCollected() {
        return eventParticipationRule.totalCollected();
    }

}
