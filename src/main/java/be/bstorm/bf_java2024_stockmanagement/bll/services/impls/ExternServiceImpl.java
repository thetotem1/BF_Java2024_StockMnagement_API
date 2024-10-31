package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ExternService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.ExternRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the {@link ExternService} interface that provides operations for {@link Extern} entities.
 * This service includes business logic for saving external entities, ensuring email uniqueness.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #save(Extern)} - Saves a new Extern entity with a unique identifier, ensuring no duplicate email exists.</li>
 * </ul>
 * </p>
 *
 * @see ExternService
 * @see Extern
 */
@Service
@RequiredArgsConstructor
public class ExternServiceImpl implements ExternService {

    private final ExternRepository externRepository;

    /**
     * Saves a new Extern entity with a unique identifier.
     * Validates that no other Extern exists with the same email before saving.
     *
     * @param extern The {@link Extern} entity to save.
     * @return The saved {@link Extern}.
     * @throws RuntimeException If an Extern with the same email already exists.
     */
    @Override
    public Extern save(Extern extern) {
        if (externRepository.existsByEmail(extern.getEmail())) {
            throw new RuntimeException("Extern with email " + extern.getEmail() + " already exists");
        }
        extern.setId(UUID.randomUUID());
        return externRepository.save(extern);
    }
}
