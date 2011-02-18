package org.plugtree.drools.shell;

import jline.ConsoleReader;
import org.plugtree.drools.ext.KnowledgeBaseProvider;
import org.plugtree.drools.ext.KnowledgeBaseProviderFromInputStreams;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * creation date: 2/17/11
 */
public class DroolsShellCli {

    private DroolsShell shell;

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
                out.print(shell.run(line));
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
