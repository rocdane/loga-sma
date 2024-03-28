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

public class Manager extends Agent {

    private AID manager = new AID();
    private ArrayList<AID> name = new ArrayList();

    @Override
    protected void setup() {
        super.setup();

        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());

        ServiceDescription serviceDesc = new ServiceDescription();
        serviceDesc.setType("MANAGEMENT");
        serviceDesc.setName("MANAGER");

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
                //TODO : 4 - Accepter la réparation
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 5 - Demander la commande des fournitures
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 7 - Accepter la commande des fournitures
            }
        });

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 9 - Demander la planification de la réparation
            }
        });
    }

    @Override
    protected MessageQueue createMessageQueue() {
        return super.createMessageQueue();
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
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
