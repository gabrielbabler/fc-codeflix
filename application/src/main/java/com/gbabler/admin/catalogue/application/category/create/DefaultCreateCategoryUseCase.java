package com.gbabler.admin.catalogue.application.category.create;

import com.gbabler.admin.catalogue.domain.category.Category;
import com.gbabler.admin.catalogue.domain.category.CategoryGateway;
import com.gbabler.admin.catalogue.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static io.vavr.API.Try;

public class DefaultCreateCategoryUseCase  extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(notification);

        return notification.hasErrors() ? Left(notification) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
        return Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
