package org.plugtree.drools.shell.commands;

import jline.Completor;
import jline.ConsoleReader;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.drools.command.Command;
import org.plugtree.drools.commands.DummyCommand;
import org.plugtree.drools.commands.RulesByPackageCommand;
import org.plugtree.drools.commands.RulesForPackageCommand;
import org.plugtree.drools.shell.exceptions.HelpRequestedException;
import org.plugtree.drools.shell.exceptions.UnknownArgumentException;

import java.io.IOException;
import java.util.*;

import static java.lang.Class.*;

/**
 * creation date: 2/20/11
 */
public class InsertFactCliCommand extends CliCommandSupport {

    private ConsoleReader reader;
    private OptionParser parser;
    private OptionSpec<String> classNameOpt;
    private OptionSpec<Void> helpOpt;

    public InsertFactCliCommand(ConsoleReader reader) {
        this.reader = reader;
        parser = new OptionParser();
        classNameOpt = parser.acceptsAll(Arrays.asList(new String[]{"c", "className"}), "Object class to insert")
                .withRequiredArg().ofType(String.class);
        helpOpt = parser.acceptsAll(Arrays.asList(new String[]{"?", "help", "h"}), "This help");
    }

    @Override
    public Completor getCompletor() {
        return null;
    }

    @Override
    public Command<?> getCommand(String... args) throws UnknownArgumentException, HelpRequestedException {
        OptionSet optionSet = parseArguments(parser, args);
        isPrintHelp(optionSet, helpOpt, parser);
        checkMandatoryOption(optionSet, classNameOpt);

        final String className = optionSet.valueOf(classNameOpt);
        Class<?> clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class " + className +" not found", e);
        }

        //remove parent completors
        List<Completor> completors = new ArrayList<Completor>(reader.getCompletors());
        for (Completor completor : completors){
            reader.removeCompletor(completor);
        }

        try {
            String line;
            while ((line = reader.readLine(clazz.getSimpleName() + "> ")) != null) {
                line = line.trim();

                if("".equals(line))
                    break;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        //readd parent completors
        for (Completor completor : completors){
            reader.addCompletor(completor);
        }

        return new DummyCommand();
    }
}
