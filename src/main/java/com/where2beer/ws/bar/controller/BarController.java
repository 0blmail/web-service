package com.where2beer.ws.bar.controller;

import com.where2beer.ws.bar.dto.BarDto;
import com.where2beer.ws.bar.model.Bar;
import com.where2beer.ws.bar.service.BarService;
import com.where2beer.ws.common.exception.RestExceptionEnum;
import com.where2beer.ws.common.exception.custom.BadRequestException;
import com.where2beer.ws.common.helper.CriteriaHelper;
import com.where2beer.ws.common.model.dto.UpdateGroup;
import com.where2beer.ws.common.model.search.SearchCriterion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bars")
@RequiredArgsConstructor
public class BarController {

    private final BarService barService;

    @GetMapping("search")
    public Page<Bar> search(@RequestParam(value = "criteria", required = false) String params, Pageable pageable) {
        List<SearchCriterion> criteria = CriteriaHelper.fromString(params);

        return this.barService.search(criteria, pageable);
    }

    @PostMapping
    public Bar create(@RequestBody @Validated BarDto barDto) {
        return this.barService.create(barDto);
    }

    @PutMapping("{id}")
    public Bar update(@PathVariable Long id, @RequestBody @Validated(UpdateGroup.class) BarDto barDto) {
        if (!id.equals(barDto.getId())) {
            throw new BadRequestException(RestExceptionEnum.DIFFERENT_ID);
        }

        return this.barService.update(barDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.barService.delete(id);
    }
}
