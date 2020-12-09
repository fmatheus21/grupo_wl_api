package com.firecode.app.controller.resource;

import com.firecode.app.controller.dto.EventGuestDto;
import com.firecode.app.controller.rule.EventGuestRule;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RestController
@RequestMapping("/event/guest")
public class EventGuestResource {

    @Autowired
    private EventGuestRule eventGuestRule;

    @ApiOperation(value = "List Event Guest")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns List"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('read')")
    @GetMapping()
    public ResponseEntity<Page<EventGuestDto>> list(RepositoryFilter filter, Pageable page) {
        return eventGuestRule.findAllPaginator(filter, page);
    }

    @ApiOperation(value = "Delete Event Guest By ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Record Deleted"),
        @ApiResponse(code = 404, message = "Register Not Found"),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 500, message = "Server Error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return eventGuestRule.delete(id);
    }

}
