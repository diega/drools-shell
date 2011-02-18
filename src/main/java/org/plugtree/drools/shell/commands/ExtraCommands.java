package org.plugtree.drools.shell.commands;

import org.drools.command.Command;
import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.*;

/**
 * creation date: 2/17/11
 */
public class ExtraCommands {
    public static Command<Collection<KnowledgePackage>> getPackagesListCommand(){
        return new GenericCommand<Collection<KnowledgePackage>>(){
            @Override
            public Collection<KnowledgePackage> execute(Context context) {
                StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
                return Collections.unmodifiableCollection(ksession.getKnowledgeBase().getKnowledgePackages());
            }
        };
    }

    public static Command<Map<KnowledgePackage,Collection<Rule>>> getRulesListCommand(){
        return new GenericCommand<Map<KnowledgePackage,Collection<Rule>>>() {
            @Override
            public Map<KnowledgePackage,Collection<Rule>> execute(Context context) {
                Map<KnowledgePackage,Collection<Rule>> out = new HashMap<KnowledgePackage,Collection<Rule>>();
                StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
                for (KnowledgePackage kpackage : ksession.getKnowledgeBase().getKnowledgePackages()){
                    Collection<Rule> rules = out.get(kpackage);
                    if(rules == null){
                        rules = new ArrayList<Rule>();
                        out.put(kpackage, rules);
                    }
                    rules.addAll(kpackage.getRules());
                }
                return out;
            }
        };
    }
}
