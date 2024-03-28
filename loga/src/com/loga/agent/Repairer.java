package com.loga.agent;

import com.loga.model.Log;
import jade.core.AID;
import jade.core.Agent;
import jade.core.MessageQueue;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.ArrayList;

public class Repairer extends Agent {
    private AID repairer = new AID();
    private ArrayList<AID> name = new ArrayList();

    @Override
    protected void setup() {
        super.setup();

        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());

        ServiceDescription serviceDesc = new ServiceDescription();
        serviceDesc.setType("REPAIR");
        serviceDesc.setName("REPAIRER");

        agentDescription.addServices(serviceDesc);

        try {
            DFService.register(this, agentDescription);
        } catch (FIPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();

        addBehaviour(parallelBehaviour);

        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 2000) {

            @Override
            protected void onTick() {
                //Récupérer tous les agents manager
                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription servDesc = new ServiceDescription();
                servDesc.setType("MANAGER");
                agentDesc.addServices(servDesc);

                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);
                    String[] acheteurs = new String[resultat.length];

                    for(int i=0; i<resultat.length;i++) {
                        acheteurs[i] = resultat[i].getName().getLocalName();
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {//CFP
            @Override
            public void action() {
                //TODO: 1- Receptionner le véhicule
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {//INFORM
            @Override
            public void action() {
                //TODO : 2- Informer du diagnostic
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {//PROPOSE
            @Override
            public void action() {
                //TODO : 3- Proposer réparation
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {//INFORM
            @Override
            public void action() {
                //TODO : 10 - Informer la réparation terminée
            }
        });
    }

    @Override
    protected MessageQueue createMessageQueue() {
        return super.createMessageQueue();
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

    @Override
    protected void beforeMove() {
        super.beforeMove();
    }

    @Override
    protected void afterMove() {
        super.afterMove();
    }
}
