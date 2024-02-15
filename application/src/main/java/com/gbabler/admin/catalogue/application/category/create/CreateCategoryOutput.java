package com.gbabler.admin.catalogue.application.category.create;

import com.gbabler.admin.catalogue.domain.category.Category;
import com.gbabler.admin.catalogue.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}
