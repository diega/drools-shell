package org.plugtree.drools.ext.spring;

import jline.SimpleCompletor;
import org.plugtree.drools.shell.commands.CliCommand;
import org.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.Set;

/**
 * creation date: 3/1/11
 */
public class SimpleCompletorForCommandsFactoryBean implements FactoryBean<SimpleCompletor> {

    private Map<String,CliCommand> commands;

    @Override
    public SimpleCompletor getObject() throws Exception {
        Set<String> cliCommandNames = commands.keySet();
        return new SimpleCompletor(cliCommandNames.toArray(new String[cliCommandNames.size()]));
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleCompletor.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setCommands(Map<String, CliCommand> commands) {
        this.commands = commands;
    }
}
