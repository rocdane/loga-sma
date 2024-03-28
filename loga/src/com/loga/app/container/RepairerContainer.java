package com.loga.app.container;

import com.loga.agent.Manager;
import com.loga.agent.Repairer;
import com.loga.model.Session;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class RepairerContainer {

    private static AgentContainer repairerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "REPAIRER");
        repairerContainer = runtime.createAgentContainer(impl);
    }

    public RepairerContainer(){

    }

    public AgentContainer getInstance(){
        return repairerContainer;
    }

    public static void createRepairer(Session session, boolean flag){
        try{
            AgentController agentController = repairerContainer.createNewAgent(session.getUser().getUsername(), Repairer.class.getName(),new Object[]{flag});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
