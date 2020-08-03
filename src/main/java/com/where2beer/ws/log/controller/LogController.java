package com.where2beer.ws.log.controller;

import com.where2beer.ws.common.country.model.Country;
import com.where2beer.ws.log.model.Log;
import com.where2beer.ws.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("countries/{timestamp}")
    public List<Log<Country>> countries(@PathVariable long timestamp) {
        return this.logService.findAll(Country.class, timestamp);
    }
}
