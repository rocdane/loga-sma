package com.loga.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.MessageQueue;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.ArrayList;

public class Marketer extends Agent {

    private AID market = new AID();
    private ArrayList<AID> name = new ArrayList();

    @Override
    protected void setup() {
        super.setup();

        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());

        ServiceDescription serviceDesc = new ServiceDescription();
        serviceDesc.setType("MARKETING");
        serviceDesc.setName("MARKETER");

        agentDescription.addServices(serviceDesc);

        try {
            DFService.register(this, agentDescription);
        } catch (FIPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();

        addBehaviour(parallelBehaviour);


        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO: 6 - Proposer la commande des fournitures
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 8 - Informer de la livraison des fournitures
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
