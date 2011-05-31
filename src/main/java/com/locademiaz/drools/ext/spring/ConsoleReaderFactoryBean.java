package com.locademiaz.drools.ext.spring;

import jline.Completor;
import jline.ConsoleReader;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * creation date: 3/1/11
 */
public class ConsoleReaderFactoryBean implements FactoryBean<ConsoleReader> {

    private boolean bellEnabled;
    private List<Completor> completors = new ArrayList<Completor>();

    @Override
    public ConsoleReader getObject() throws Exception {
        ConsoleReader reader = new ConsoleReader();
        reader.setBellEnabled(bellEnabled);
        for(Completor completor : completors) {
            reader.addCompletor(completor);
        }
        return reader;
    }

    @Override
    public Class<?> getObjectType() {
        return ConsoleReader.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBellEnabled(boolean bellEnabled) {
        this.bellEnabled = bellEnabled;
    }

    public void setCompletors(List<Completor> completors) {
        this.completors = completors;
    }
}
