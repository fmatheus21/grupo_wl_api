package com.firecode.app.controller.rule;

import com.firecode.app.controller.dto.EventGuestDto;
import com.firecode.app.model.repository.EventGuestRepository;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import com.firecode.app.model.service.EventGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EventGuestRule {

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    private EventGuestService eventGuestService;

    public ResponseEntity<?> delete(int id) {

        var eventGuest = eventGuestService.findById(id);

        if (eventGuest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.not.found", null));
        }

        eventGuestService.deleteById(eventGuest.getId());
        return ResponseEntity.status(HttpStatus.OK).body(messageResponseRule.messageResponse(200, HttpStatus.OK, "message.success.delete", null));
    }

    public ResponseEntity<Page<EventGuestDto>> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        Page<EventGuestDto> eventGuests = eventGuestService.findAll(pageable).map(EventGuestDto::converterObject);
        return !eventGuests.isEmpty() ? ResponseEntity.ok(eventGuests) : ResponseEntity.status(204).build();
    }

}
