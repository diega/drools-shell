package org.plugtree.drools.commands;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.definition.KnowledgePackage;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collection;
import java.util.Collections;

/**
* creation date: 2/19/11
*/
public class PackagesCommand implements GenericCommand<Collection<KnowledgePackage>> {
    @Override
    public Collection<KnowledgePackage> execute(Context context) {
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        return Collections.unmodifiableCollection(ksession.getKnowledgeBase().getKnowledgePackages());
    }
}
