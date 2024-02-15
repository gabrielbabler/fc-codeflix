package com.gbabler.admin.catalogue.application.category.update;

import com.gbabler.admin.catalogue.domain.category.Category;
import com.gbabler.admin.catalogue.domain.category.CategoryID;

public record UpdateCategoryOutput(
    CategoryID id
) {

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
