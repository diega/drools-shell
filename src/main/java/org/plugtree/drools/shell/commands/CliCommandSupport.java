package org.plugtree.drools.shell.commands;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.plugtree.drools.shell.exceptions.HelpRequestedException;
import org.plugtree.drools.shell.exceptions.UnknownArgumentException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * creation date: 2/20/11
 */
public abstract class CliCommandSupport implements CliCommand{
    protected OptionSet parseArguments(OptionParser parser, String[] args) throws UnknownArgumentException {
        OptionSet optionSet = null;
        try {
            optionSet = parser.parse(args);
        } catch (OptionException oe) {
            throw new UnknownArgumentException(new ArrayList<String>(oe.options()));
        }
        if(!optionSet.nonOptionArguments().isEmpty())
            throw new UnknownArgumentException(optionSet.nonOptionArguments());
        return optionSet;
    }

    protected void isPrintHelp(OptionSet optionSet, OptionSpec<Void> helpOpt, OptionParser parser) throws HelpRequestedException {
        if(optionSet.has(helpOpt)){
            StringWriter helpBuffer = new StringWriter();
            try {
                parser.printHelpOn(helpBuffer);
            } catch (IOException e) {
                // in case of error we don't print any help message
            } finally{
                try {
                    helpBuffer.close();
                } catch (IOException e) {
                }
            }
            throw new HelpRequestedException(this, helpBuffer.toString());
        }
    }

    protected void checkMandatoryOption(OptionSet optionSet, OptionSpec<?>... optionSpecs) {
        for(OptionSpec<?> opt : optionSpecs){
            if(!optionSet.has(opt))
                throw new IllegalArgumentException("Missing "+ opt.options() + ". Use -h for help");
        }
    }
}
