package org.plugtree.drools.shell;

import jline.ConsoleReader;
import org.plugtree.drools.ext.KnowledgeBaseProvider;
import org.plugtree.drools.ext.KnowledgeBaseProviderFromInputStreams;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * creation date: 2/17/11
 */
public class DroolsShellCli {

    private DroolsShell shell;
    private static final Logger logger = LoggerFactory.getLogger(DroolsShellCli.class);

    public DroolsShellCli(KnowledgeBaseProvider kbaseProvider) {
        this.shell = new DroolsShell(kbaseProvider.getKnowledgeBase().newStatefulKnowledgeSession());
    }

    public void run() throws IOException {
        ConsoleReader reader = new ConsoleReader();
        reader.setBellEnabled(false);

        String line;
        PrintWriter out = new PrintWriter(System.out);

        while ((line = reader.readLine("drools> ")) != null) {
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
                out.print(shell.run(command, args));
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
        List<InputStream> rules = new ArrayList<InputStream>();
        for(String rule : args) {
            rules.add(new FileInputStream(rule));
        }
        DroolsShellCli shell = new DroolsShellCli(new KnowledgeBaseProviderFromInputStreams(rules));
        shell.run();
    }
}
