package com.locademiaz.drools.commands;

import org.drools.WorkingMemoryEventManager;
import org.drools.audit.WorkingMemoryLogger;
import org.drools.audit.event.LogEvent;
import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.event.AgendaEventListener;
import org.drools.event.RuleBaseEventListener;
import org.drools.event.WorkingMemoryEventListener;
import org.drools.impl.StatefulKnowledgeSessionImpl;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * creation date: 3/3/11
 */
public class VerboseFireAllRules implements GenericCommand<Integer> {

    @Override
    public Integer execute(Context context) {
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
        WorkingMemoryLogger logger = new WorkingMemoryLogger() {
            @Override
            public void logEventCreated(LogEvent logEvent) {
                System.out.println(logEvent);
            }
        };

        WorkingMemoryEventManager eventManager = ((StatefulKnowledgeSessionImpl) ksession).session;
        eventManager.addEventListener((WorkingMemoryEventListener) logger);
        eventManager.addEventListener((RuleBaseEventListener) logger);
        eventManager.addEventListener((AgendaEventListener) logger);

        final int rulesFired = ksession.fireAllRules();

        eventManager.removeEventListener((WorkingMemoryEventListener) logger);
        eventManager.removeEventListener((RuleBaseEventListener) logger);
        eventManager.removeEventListener((AgendaEventListener) logger);

        return rulesFired;
    }
}
