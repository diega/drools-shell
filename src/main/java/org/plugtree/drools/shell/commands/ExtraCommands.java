package org.plugtree.drools.shell.commands;

import org.drools.command.Command;
import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.definition.KnowledgePackage;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collection;
import java.util.List;

/**
 * creation date: 2/17/11
 */
public class ExtraCommands {
    public static Command<Collection<KnowledgePackage>> getPackagesListCommand(){
        return new GenericCommand<Collection<KnowledgePackage>>(){
            @Override
            public Collection<KnowledgePackage> execute(Context context) {
                StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
                return ksession.getKnowledgeBase().getKnowledgePackages();
            }
        };
    }
}
