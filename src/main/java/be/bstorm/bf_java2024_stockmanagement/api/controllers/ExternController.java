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

/**
 * REST controller for managing external entities (clients and suppliers).
 * Provides an endpoint for creating a new external entity in the system.
 *
 * <p>Endpoints:
 * <ul>
 * <li>{@link #extern(ExternForm)} - Creates a new external entity, such as a client or supplier, based on the provided {@link ExternForm} data.</li>
 * </ul>
 * </p>
 *
 * @see ExternService
 * @see ExternForm
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/extern")
public class ExternController {

    private final ExternService externService;

    /**
     * Creates a new external entity in the system. The type of entity (client or supplier) is determined
     * based on the `externType` field in the provided {@link ExternForm}.
     *
     * <p>Validation:
     * <ul>
     * <li>The `ExternForm` fields, including `firstName`, `lastName`, `email`, and `externType`, are validated for correctness.</li>
     * <li>Constraints like `@NotBlank`, `@Size`, and `@NotNull` ensure required fields are filled and within allowed limits.</li>
     * </ul>
     * </p>
     *
     * @param form The {@link ExternForm} containing the data for the new external entity. This includes personal details
     *             and type information to determine if the entity is a client or supplier.
     * @return A {@link ResponseEntity} with no content if the creation is successful.
     */
    @PostMapping
    public ResponseEntity<Void> extern(@Valid @RequestBody ExternForm form) {
        Extern extern = externService.save(form.toExtern());

        // If returning the location of the created resource:
        // URI location = ServletUriComponentsBuilder
        //         .fromCurrentRequest()
        //         .path("/{id}")
        //         .buildAndExpand(extern.getId())
        //         .toUri();
        // return ResponseEntity.created(location).build();

        return ResponseEntity.noContent().build();
    }
}
