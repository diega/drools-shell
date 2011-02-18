package org.plugtree.drools.shell;

import org.junit.Ignore;
import org.junit.Test;
import org.plugtree.drools.ext.KnowledgeBaseProviderFromInputStreams;

import java.io.InputStream;
import java.util.Arrays;

/**
 * creation date: 2/17/11
 */
public class DroolsShellCliTest {
    
    @Test
    @Ignore
    public void testRun() throws Exception {
        DroolsShellCli shell = new DroolsShellCli(new KnowledgeBaseProviderFromInputStreams(Arrays.asList(new InputStream[]{
                getClass().getResourceAsStream("/test1.drl"),
                getClass().getResourceAsStream("/test2.drl")
        })));
        shell.run();
    }
}
