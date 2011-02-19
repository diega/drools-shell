package org.plugtree.drools.commands;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collection;
import java.util.Collections;

/**
* creation date: 2/19/11
*/
public class RulesForPackageCommand implements GenericCommand<Collection<Rule>> {
    private final String packageName;

    public RulesForPackageCommand(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public Collection<Rule> execute(Context context) {
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        final KnowledgePackage knowledgePackage = ksession.getKnowledgeBase().getKnowledgePackage(packageName);
        if(null == knowledgePackage)
            throw new IllegalArgumentException("Unknown package: " + packageName);
        return Collections.unmodifiableCollection(knowledgePackage.getRules());
    }
}
