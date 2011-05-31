package com.locademiaz.drools.shell;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;
import com.locademiaz.drools.Person;
import com.locademiaz.drools.shell.exceptions.CommandNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * creation date: 3/2/11
 */
public class FireAllRulesTest extends CliCommandTest{

    @Test
    public void runCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession();

        final String output = shell.run(ksession, "fire");
        Assert.assertEquals(1, ksession.getObjects().size());
        System.out.println(output);
    }

    @Test
    public void runVerboseCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession(getRules());

        ksession.insert(new Person());
        String output = shell.run(ksession, "fire", "-v");
        System.out.println(output);
        Assert.assertEquals(1, ksession.getObjects().size());
        ksession.insert(new Person());
        output = shell.run(ksession, "fire");
        System.out.println(output);
        Assert.assertEquals(2, ksession.getObjects().size());
    }

    protected List<InputStream> getRules() {
        List<InputStream> rules = new ArrayList<InputStream>();
        String text = "package com.locademiaz.drools\n" +
                "rule \"dummy rule\"\n" +
                "when Person()\n" +
                "then System.out.println(\"fired\");\n" +
                "end";
        try {
            InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
            rules.add(is);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return rules;
    }
}
