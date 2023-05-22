package com.healthCheckApplication.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestController
public class HealthCheckController {

    private static final String STATUS_OK = "OK";
    private static final String FORMAT_SHORT = "short";
    private static final String FORMAT_FULL = "full";

    @RequestMapping(value = "/healthcheck",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> healthCheck(@RequestParam(value = "format", defaultValue = FORMAT_SHORT)  String format) {
        if (!format.equals(FORMAT_SHORT) && !format.equals(FORMAT_FULL))
            return ResponseEntity.badRequest().build();//.body("Invalid format parameter value. Allowed values: 'short' or 'full'");


        if (format.equals(FORMAT_SHORT)) {
            return ResponseEntity.ok(new HealthCheckResponse(STATUS_OK));
        } else {
            LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            return ResponseEntity.ok(new DetailedHealthCheckResponse(STATUS_OK, currentTime.format(formatter)));
        }
    }

    @PostMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> healthCheck() {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    private static class HealthCheckResponse {
        private final String status;

        HealthCheckResponse(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    private static class DetailedHealthCheckResponse extends HealthCheckResponse {
        private final String currentTime;

        DetailedHealthCheckResponse(String status, String currentTime) {
            super(status);
            this.currentTime = currentTime;
        }

        public String getCurrentTime() {
            return currentTime;
        }
    }
}

