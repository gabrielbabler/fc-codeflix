package com.gbabler.admin.catalogue.application;

import com.gbabler.admin.catalogue.domain.category.Category;

public class UseCase {
    public Category execute() {
        return new Category();
    }
}