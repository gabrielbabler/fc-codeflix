package com.gbabler.admin.catalogue.application.category.update;

import com.gbabler.admin.catalogue.application.UseCase;
import com.gbabler.admin.catalogue.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
