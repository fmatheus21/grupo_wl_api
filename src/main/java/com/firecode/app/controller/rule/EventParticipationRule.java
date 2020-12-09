package com.firecode.app.controller.rule;

import com.firecode.app.controller.dto.EventParticipationDto;
import com.firecode.app.controller.event.ResourceEvent;
import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.RateEntity;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import com.firecode.app.model.service.EventParticipationService;
import com.firecode.app.model.service.EmployeeService;
import com.firecode.app.model.service.RateService;
import java.math.BigDecimal;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EventParticipationRule {

    @Autowired
    private MessageResponseRule messageResponseRule;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EventParticipationService eventParticipationService;

    @Autowired
    private RateService rateService;

    public ResponseEntity<?> create(EventParticipationDto dto, HttpServletResponse response) {


        /* Verifica se o ID funcionario informado existe */
        EmployeeEntity employee = employeeService.findById(dto.getIdEmployee());
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.employee.not.found", null));
        }

        /* Verifica se o funcionario ja consta na lista do evento */
        EventParticipationEntity eventParticipationEntity = eventParticipationService.findByIdEmployee(new EmployeeEntity(dto.getIdEmployee()));
        if (eventParticipationEntity != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.employee.event.exist", null));
        }

        /* Verifica se a taxa do evento existe */
        RateEntity rate = rateService.findById(dto.getIdEmployeeRate());
        /* Se a taxa do evento nao for encontrada retorna um Bad Request */
        if (rate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.rate.event.exist", null));
        }

        /* Se a taxa do evento for encontrada mas, nao corresponde a taxa referente ao funcionario */
        if (rate != null) {
            if (rate.getEmployee() == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.rate.event.employee.exist", null));
            }
        }

        var eventParticipationDto = new EventParticipationDto();
        var eventParticipation = eventParticipationDto.create(dto, employee);
        eventParticipationService.create(eventParticipation);
        publisher.publishEvent(new ResourceEvent(this, response, eventParticipation.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseRule.messageResponse(201, HttpStatus.CREATED, "message.success.create", null));

    }

    /* Exclui um funcionario do evento */
    public ResponseEntity<?> delete(int id) {

        var eventParticipation = eventParticipationService.findById(id);

        if (eventParticipation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.event.employee.exist", null));
        }

        System.out.println("ID: " + eventParticipation.getId() + " - ID Employee: " + eventParticipation.getIdEmployee().getId());

        eventParticipationService.deleteById(eventParticipation.getId());

        return ResponseEntity.status(HttpStatus.OK).body(messageResponseRule.messageResponse(200, HttpStatus.OK, "message.success.delete", null));
    }

    public ResponseEntity<Page<EventParticipationDto>> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        Page<EventParticipationDto> eventParticipations = eventParticipationService.findAllPaginator(filter, pageable).map(EventParticipationDto::converterObject);
        return !eventParticipations.isEmpty() ? ResponseEntity.ok(eventParticipations) : ResponseEntity.status(204).build();
    }

    public ResponseEntity<?> findById(int id) {

        var eventParticipation = eventParticipationService.findById(id);

        if (eventParticipation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.not.found", null));
        }

        var eventParticipationDto = EventParticipationDto.converterObject(eventParticipation);

        return eventParticipationDto != null ? ResponseEntity.ok(eventParticipationDto) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponseRule.messageResponse(404, HttpStatus.NOT_FOUND, "message.error.not.found", null));

    }

    public ResponseEntity<?> totalCollected() {

        var event = EventParticipationDto.totalCollected(eventParticipationService.findAll("id"));

        return event != null ? ResponseEntity.ok(event) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponseRule.messageResponse(404, HttpStatus.NOT_FOUND, "message.error.not.found", null));

    }

    private boolean guestAnalyze(String nameGuest, String documentGuest, int idRate) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, nameGuest);
        map.put(2, documentGuest);
        map.put(3, String.valueOf(idRate));

        int count = 0;

        for (String s : map.values()) {
            if (s == null || s.equals("")) {
                count++;
            }
        }

        if (count > 0 && count < 3) {
            return false;
        }

        return true;
    }

}
