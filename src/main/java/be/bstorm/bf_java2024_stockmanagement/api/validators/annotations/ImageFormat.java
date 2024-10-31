package be.bstorm.bf_java2024_stockmanagement.api.validators.annotations;

import be.bstorm.bf_java2024_stockmanagement.api.validators.ImageFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to check if an image file has a valid format.
 * Supports image formats .jpg, .jpeg, and .png.
 *
 * <p>Usage:</p>
 * <pre>
 * {@code @ImageFormat(message = "Only JPG, JPEG, and PNG formats are allowed")}
 * private MultipartFile imageFile;
 * </pre>
 *
 * <p>Fields:
 * <ul>
 * <li>{@code message} - Custom error message if the format is invalid.</li>
 * <li>{@code groups} - Allows grouping of constraints.</li>
 * <li>{@code payload} - Can be used to provide additional metadata.</li>
 * </ul>
 * </p>
 *
 * @see ImageFormatValidator
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFormatValidator.class})
public @interface ImageFormat {

    /**
     * The error message displayed if the image format is invalid.
     *
     * @return The custom error message.
     */
    String message() default "Invalid image format";

    /**
     * Allows grouping of constraints.
     *
     * @return The group classes.
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients to assign custom metadata objects to the constraint.
     *
     * @return The payload type.
     */
    Class<? extends Payload>[] payload() default {};
}
