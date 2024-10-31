package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;

/**
 * Interface for managing {@link Extern} entities, providing methods for saving external entities.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #save(Extern)} - Saves a new Extern entity, ensuring it meets necessary validation requirements.</li>
 * </ul>
 * </p>
 *
 * @see Extern
 */
public interface ExternService {

    /**
     * Saves a new Extern entity.
     *
     * @param extern The {@link Extern} entity to save.
     * @return The saved {@link Extern}.
     * @throws RuntimeException If validation requirements are not met, such as unique email constraints.
     */
    Extern save(Extern extern);
}
