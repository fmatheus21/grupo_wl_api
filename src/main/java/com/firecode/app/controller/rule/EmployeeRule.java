package com.firecode.app.controller.rule;

import com.firecode.app.controller.dto.EmployeeDto;

import com.firecode.app.controller.event.ResourceEvent;
import com.firecode.app.controller.util.AppUtil;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.entity.PersonPhysicalEntity;
import com.firecode.app.model.service.EmployeeService;
import com.firecode.app.model.service.PersonPhysicalService;
import com.firecode.app.model.service.PersonService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRule {

    @Autowired
    private MessageResponseRule messageResponseRule;

    /* Publicador de eventos de aplicacao */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonPhysicalService personPhysicalService;

    private PersonEntity person;

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto employeeDto;

    public ResponseEntity<?> create(EmployeeDto dto, HttpServletResponse response) {

        String document = AppUtil.removeSpecialCharacters(dto.getDocument());

        PersonPhysicalEntity personPhysical = personPhysicalService.findByDocument(document);
        if (personPhysical != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponseRule.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.documento.exist", null));
        }

        employeeDto = new EmployeeDto();
        person = employeeDto.create(dto);
        personService.create(person);
        publisher.publishEvent(new ResourceEvent(this, response, person.getPersonPhysicalEntity().getEmployeeEntity().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseRule.messageResponse(201, HttpStatus.CREATED, "message.success.create", null));

    }

    public ResponseEntity<?> delete(int id) {

        EmployeeEntity employee = employeeService.findById(id);
        if (employee == null) {
            throw messageResponseRule.responseStatusException(HttpStatus.NOT_FOUND, "message.error.not.found");
        }

        personService.deleteById(employee.getIdPersonPhysical().getIdPerson().getId());
        return ResponseEntity.status(HttpStatus.OK).body(messageResponseRule.messageResponse(200, HttpStatus.OK, "message.success.delete", null));
    }

    public ResponseEntity<Page<EmployeeDto>> findAllPaginator(Pageable pageable) {
        Page<EmployeeDto> employees = employeeService.findAllPaginator(pageable).map(EmployeeDto::converterObject);
        return !employees.isEmpty() ? ResponseEntity.ok(employees) : ResponseEntity.status(204).build();
    }

    public ResponseEntity<?> findById(int id) {
        EmployeeDto employee = EmployeeDto.converterObject(employeeService.findById(id));
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponseRule.messageResponse(404, HttpStatus.NOT_FOUND, "message.error.not.found", null));
    }

}
