package com.gbabler.admin.catalogue.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anInput);
}