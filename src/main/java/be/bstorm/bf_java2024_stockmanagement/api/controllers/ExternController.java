package be.bstorm.bf_java2024_stockmanagement.api.controllers;

import be.bstorm.bf_java2024_stockmanagement.api.models.forms.extern.ExternForm;
import be.bstorm.bf_java2024_stockmanagement.bll.services.ExternService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/extern")
public class ExternController {

    private final ExternService externService;

    @PostMapping
    public ResponseEntity<Void> extern(@Valid @RequestBody ExternForm form) {

        Extern extern = externService.save(form.toExtern());

//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(extern.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).build();
        return ResponseEntity.noContent().build();
    }
}
