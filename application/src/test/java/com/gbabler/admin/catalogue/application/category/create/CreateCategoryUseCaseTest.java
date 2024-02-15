package com.gbabler.admin.catalogue.application.category.create;

import com.gbabler.admin.catalogue.domain.category.CategoryGateway;
import com.gbabler.admin.catalogue.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase defaultCreateCategoryUseCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    void givenAValidCommand_whenCallsCreateCategory_thenShouldReturnCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = defaultCreateCategoryUseCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, times(1))
                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
        ));
    }

    @Test
    void givenAnInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> defaultCreateCategoryUseCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(categoryGateway, times(0)).create(any());
    }

    @Test
    void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_thenShouldReturnInactiveCategoryId() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = defaultCreateCategoryUseCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, times(1))
                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.nonNull(aCategory.getDeletedAt())
        ));
    }

    @Test
    void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAnException() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> defaultCreateCategoryUseCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(categoryGateway, times(1))
                .create(argThat(aCategory ->
                    Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
                ));
    }
}
