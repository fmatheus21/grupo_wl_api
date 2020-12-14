package com.firecode.app.controller.rule;

import com.firecode.app.controller.dto.RateDto;
import com.firecode.app.model.service.RateService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RateRule {

    @Autowired
    private RateService rateService;

    public ResponseEntity<List<RateDto>> findByEmployee() {
        List<RateDto> rates = rateService.findByEmployee(true).stream().map(RateDto::converterObject).collect(Collectors.toList());
        return !rates.isEmpty() ? ResponseEntity.ok(rates) : ResponseEntity.status(204).build();
    }

    public ResponseEntity<List<RateDto>> findByGuest() {
        List<RateDto> rates = rateService.findByEmployee(false).stream().map(RateDto::converterObject).collect(Collectors.toList());
        return !rates.isEmpty() ? ResponseEntity.ok(rates) : ResponseEntity.status(204).build();
    }

}
