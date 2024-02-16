package com.gbabler.admin.catalogue.application.category.retrieve.list;

import com.gbabler.admin.catalogue.application.UseCase;
import com.gbabler.admin.catalogue.domain.category.CategorySearchQuery;
import com.gbabler.admin.catalogue.pagination.Pagination;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
