package org.plugtree.drools.shell;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;
import org.plugtree.drools.Person;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * creation date: 3/1/11
 */
public class InsertFactCliCommandTest {

    @Test
    public void runCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession();
        System.out.println(shell.run(ksession, "insert", "-c", Person.class.getName(), "-f", "name='robert',age=23"));

        Assert.assertEquals(1, ksession.getObjects().size());
    }

    private StatefulKnowledgeSession getStatefulKnowledgeSession() {
        List<InputStream> rules = Arrays.asList(getClass().getResourceAsStream("/test1.drl"),
                getClass().getResourceAsStream("/test2.drl")
        );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for(InputStream rule : rules ){
            kbuilder.add(ResourceFactory.newInputStreamResource(rule), ResourceType.DRL);
        }
        if(kbuilder.hasErrors()){
            throw new IllegalArgumentException(kbuilder.getErrors().toString());
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase.newStatefulKnowledgeSession();
    }
}
