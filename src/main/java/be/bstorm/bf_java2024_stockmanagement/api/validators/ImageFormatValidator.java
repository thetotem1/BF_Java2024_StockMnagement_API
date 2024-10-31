package be.bstorm.bf_java2024_stockmanagement.api.validators;

import be.bstorm.bf_java2024_stockmanagement.api.validators.annotations.ImageFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * Validator class for checking the format of image files. Ensures that the file extension is one of the allowed types: .jpg, .jpeg, or .png.
 * Implements {@link ConstraintValidator} to integrate with the {@link ImageFormat} custom annotation.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #isValid(MultipartFile, ConstraintValidatorContext)} - Validates that the provided file is either empty or has an allowed image format.</li>
 * </ul>
 * </p>
 *
 * @see ImageFormat
 * @see MultipartFile
 */
public class ImageFormatValidator implements ConstraintValidator<ImageFormat, MultipartFile> {

    /**
     * Validates the format of the provided image file. Returns {@code true} if the file is null, empty, or has a valid extension.
     *
     * @param value The {@link MultipartFile} to validate.
     * @param constraintValidatorContext The context in which the constraint is evaluated.
     * @return {@code true} if the file is null, empty, or has an allowed extension (.jpg, .jpeg, .png); {@code false} otherwise.
     */
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        String imageName = value.getOriginalFilename();
        return imageName != null && (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg") || imageName.endsWith(".png"));
    }
}
