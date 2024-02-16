package com.gbabler.admin.catalogue.application.category.retrieve.list;

import com.gbabler.admin.catalogue.domain.category.CategoryGateway;
import com.gbabler.admin.catalogue.domain.category.CategorySearchQuery;
import com.gbabler.admin.catalogue.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(CategorySearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery)
                .map(CategoryListOutput::from);
    }
}
