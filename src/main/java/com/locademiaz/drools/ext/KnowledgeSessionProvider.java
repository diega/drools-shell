package com.locademiaz.drools.ext;

import org.drools.runtime.StatefulKnowledgeSession;

/**
 * creation date: 3/1/11
 */
public interface KnowledgeSessionProvider {

    StatefulKnowledgeSession getSession();
}
