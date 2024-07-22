package backend.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Pageable;

public class PageableValidator implements ConstraintValidator<PageableConstraint, Pageable> {

    private int maxPerPage;

    @Override
    public void initialize(PageableConstraint constraintAnnotation) {

        maxPerPage = constraintAnnotation.maxPerPage(); // Getting this from the controller method
    }

    @Override
    public boolean isValid(Pageable pageable, ConstraintValidatorContext context) {
        if (pageable == null) {
            return true; // If no page parameters provided, skip it
        }

        boolean isValid = true;

        if (pageable.getPageNumber() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Page number must be greater than or equal to 0").addConstraintViolation();
            isValid = false;
        }

        if (pageable.getPageSize() > maxPerPage) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Page size must be less than or equal to " + maxPerPage).addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}