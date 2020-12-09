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
        RateEntity rate = rateService.findById(dto.getIdRate());
        /* Se a taxa do evento nao for encontrada retorna um Bad Request */
        if (rate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.rate.event.exist", null));
        }

        /* Se a taxa do evento for encontrada mas, nao corresponde a taxa referente ao funcionario */
        if (rate != null) {
            if (rate.isEmployee() == false) {
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
    public ResponseEntity<?> deleteEventEmployee(int idEmployee) {

        System.out.println("idEmployee: " + idEmployee);

        var employee = new EmployeeEntity(idEmployee);

        /* Verifica se existe o funcionario na lista. Como na tabela EventParticipation so e permitido 1 registro
        de funcionario, somente e necessario consultar o funcionario
         */
        var eventParticipation = eventParticipationService.findByIdEmployee(employee);

        if (eventParticipation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.event.employee.exist", null));
        }

        System.out.println("eventParticipation: " + eventParticipation.getId());

        eventParticipationService.delete(eventParticipation);
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

}
