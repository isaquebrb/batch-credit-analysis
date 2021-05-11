package br.com.isaquebrb.iftm.batchcreditanalysis.controller;

import br.com.isaquebrb.iftm.batchcreditanalysis.model.request.ParameterRequest;
import br.com.isaquebrb.iftm.batchcreditanalysis.model.response.ParameterResponse;
import br.com.isaquebrb.iftm.batchcreditanalysis.service.ParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parameter")
@RequiredArgsConstructor
public class ParameterController {

    private final ParameterService service;

    @GetMapping
    public ResponseEntity<List<ParameterResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ParameterResponse> create(@RequestBody ParameterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParameterResponse> update(@PathVariable Long id, @Valid @RequestBody ParameterRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
