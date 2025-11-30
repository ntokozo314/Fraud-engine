package com.example.fraudEngine.evaluator;



public abstract class AbstractEvaluator implements iEvaluator{

    protected String beanName;

    @Override
    public void setBeanName(String name) {
        beanName =  name;
    }
}
