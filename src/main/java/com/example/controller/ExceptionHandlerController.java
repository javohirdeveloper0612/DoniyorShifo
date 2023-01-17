package com.example.controller;

import com.example.exp.AdminNotFoundException;
import com.example.exp.NewsDataNotFoundException;
import com.example.exp.attach.AttachNotFoundException;
import com.example.exp.attach.FileNameNotFoundException;
import com.example.exp.attach.FileNotFoundException;
import com.example.exp.doctor.DoctorNotFoundException;
import com.example.exp.patient.PatientNotFoundException;
import com.example.exp.doctor.DoctorNotFoundListException;
import com.example.exp.services.AlreadyExistsServicesException;
import com.example.exp.services.ServicesNotFoundException;
import com.example.exp.servicesButton.AllReadyExistsButtonException;
import com.example.exp.servicesButton.ButtonNotExistsException;
import com.example.exp.servicesButton.ButtonNotFoundException;
import com.example.exp.servicesButton.DataInCompleException;
import com.example.exp.services_data.ServicesDataNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatusCode status,
                                 WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = new LinkedList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler({AdminNotFoundException.class})
    private ResponseEntity<?> handler(AdminNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({AlreadyExistsServicesException.class})
    private ResponseEntity<?> handler(AlreadyExistsServicesException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ServicesNotFoundException.class})
    private ResponseEntity<?> handler(ServicesNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    @ExceptionHandler({AllReadyExistsButtonException.class})
    private ResponseEntity<?> handler(AllReadyExistsButtonException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({DataInCompleException.class})
    private ResponseEntity<?> handler(DataInCompleException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({AttachNotFoundException.class})
    private ResponseEntity<?> handler(AttachNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ButtonNotFoundException.class})
    private ResponseEntity<?> handler(ButtonNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ServicesDataNotFoundException.class})
    private ResponseEntity<?> handler(ServicesDataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({DoctorNotFoundException.class})
    private ResponseEntity<?> handler(DoctorNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({DoctorNotFoundListException.class})
    private ResponseEntity<?> handler(DoctorNotFoundListException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({PatientNotFoundException.class})
    private ResponseEntity<?> handler(PatientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({FileNotFoundException.class})
    private ResponseEntity<?> handler(FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({FileNameNotFoundException.class})
    private ResponseEntity<?> handler(FileNameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({NewsDataNotFoundException.class})
    private ResponseEntity<?> handler(NewsDataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ButtonNotExistsException.class})
    private ResponseEntity<?> handler(ButtonNotExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
