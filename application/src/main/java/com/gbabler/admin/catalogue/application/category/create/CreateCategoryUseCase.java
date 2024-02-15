package com.gbabler.admin.catalogue.application.category.create;

import com.gbabler.admin.catalogue.application.UseCase;
import com.gbabler.admin.catalogue.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
