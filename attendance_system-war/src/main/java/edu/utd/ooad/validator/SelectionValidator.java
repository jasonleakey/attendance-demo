package edu.utd.ooad.validator;

import edu.utd.ooad.model.Selection;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
@Component
public class SelectionValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return Selection.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Selection sel = (Selection) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "required.date");

        if("NONE".equals(sel.getDate())){
            errors.rejectValue("date", "required.date");
        }
    }
}
