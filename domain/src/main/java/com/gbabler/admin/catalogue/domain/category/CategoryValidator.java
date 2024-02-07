package com.gbabler.admin.catalogue.domain.category;

import com.gbabler.admin.catalogue.validation.Error;
import com.gbabler.admin.catalogue.validation.ValidationHandler;
import com.gbabler.admin.catalogue.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    protected CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
