package com.locademiaz.drools.commands;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
* creation date: 2/19/11
*/
public class RulesByPackageCommand implements GenericCommand<Map<KnowledgePackage,Collection<Rule>>> {
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
}
