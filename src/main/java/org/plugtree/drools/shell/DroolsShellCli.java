package org.plugtree.drools.shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jline.ConsoleReader;
import jline.SimpleCompletor;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import org.drools.command.Command;
import org.drools.command.runtime.rule.GetObjectsCommand;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.runtime.StatefulKnowledgeSession;
import org.plugtree.drools.commands.RulesByPackageCommand;
import org.plugtree.drools.commands.RulesForPackageCommand;
import org.plugtree.drools.ext.KnowledgeBaseProvider;
import org.plugtree.drools.ext.KnowledgeBaseProviderFromInputStreams;
import org.plugtree.drools.ext.KnowledgeSessionProvider;
import org.plugtree.drools.ext.StaticKnowledgeSessionProvider;
import org.plugtree.drools.shell.commands.CliCommand;
import org.plugtree.drools.shell.commands.InsertFactCliCommand;
import org.plugtree.drools.shell.commands.ListFactsCliCommand;
import org.plugtree.drools.shell.commands.ListRulesCliCommand;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.plugtree.drools.shell.outputbuilders.FactHandlerOutputBuilder;
import org.plugtree.drools.shell.outputbuilders.OutputBuilder;
import org.plugtree.drools.shell.outputbuilders.RulesByPackageOutputBuilder;
import org.plugtree.drools.shell.outputbuilders.RulesForPackageOutputBuilder;
import org.plugtree.drools.shell.outputbuilders.ToStringOutputBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * creation date: 2/17/11
 */
public class DroolsShellCli {

    private DroolsShell shell;
    private ConsoleReader reader;
    private static final Logger logger = LoggerFactory.getLogger(DroolsShellCli.class);

    public DroolsShellCli(ConsoleReader reader, DroolsShell shell) {
        this.shell = shell;
        this.reader = reader;
    }

    public void run(KnowledgeSessionProvider ksessionProvider) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        StatefulKnowledgeSession ksession = ksessionProvider.getSession();

        String line;
        while ((line = reader.readLine("drools> ")) != null) {
            line = line.trim();

            if("".equals(line))
                continue;

            try{
                final int firstSpace = line.indexOf(" ");
                String command = null;
                String[] args = null;
                if(-1 == firstSpace) {
                    command = line;
                    args = new String[]{};
                } else {
                    command = line.substring(0, firstSpace);
                    args = line.substring(firstSpace + 1).split(" ");
                }
                logger.debug("command: {} | args {}", command, Arrays.toString(args));
                out.print(shell.run(ksession, command, args));
            } catch (CommandNotFoundException cnfe) {
                out.print(cnfe.getMessage());
            }
            out.print(ConsoleReader.CR);

            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
            }
            out.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        OptionParser parser = new OptionParser();
        final OptionSpec<Void> helpOption = parser.acceptsAll(Arrays.asList(new String[]{"?", "help", "h"}), "This help");
        final OptionSpec<File> rulesOption = parser.acceptsAll(Arrays.asList(new String[]{"r","rules"}), "List of rule files (using : as separator)")
                .withRequiredArg().ofType(File.class).withValuesSeparatedBy(':');
        final OptionSpec<File> domainOption = parser.acceptsAll(Arrays.asList(new String[]{"d", "domain"}), "Jar file with domain classes")
                .withRequiredArg().ofType(File.class);

        final OptionSet optionSet = parser.parse(args);

        if (!optionSet.nonOptionArguments().isEmpty()) {
            System.out.println("drools-shell: '" + optionSet.nonOptionArguments().get(0).toString() + "' is not a command. See 'drools-shell --help'.");
            return;
        }

        if(optionSet.has(helpOption)){
            parser.printHelpOn(System.out);
            return;
        }

        List<File> ruleFiles = optionSet.valuesOf(rulesOption);
        List<InputStream> rules = new ArrayList<InputStream>();
        for(File rule : ruleFiles) {
            rules.add(new FileInputStream(rule));
        }

        if(optionSet.has(domainOption)){
            final File domainJar = optionSet.valueOf(domainOption);
            final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{domainJar.toURI().toURL()},
                                            currentClassLoader);
            Thread.currentThread().setContextClassLoader(urlClassLoader);
        }

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");

        DroolsShellCli cli = applicationContext.getBean("cli", DroolsShellCli.class);
        cli.run(new StaticKnowledgeSessionProvider(rules));
    }

}
