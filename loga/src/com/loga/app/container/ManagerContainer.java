package com.loga.app.container;

import com.loga.agent.Manager;
import com.loga.model.Session;
import com.loga.model.User;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ManagerContainer {

    private static AgentContainer managerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "MANAGER");
        managerContainer = runtime.createAgentContainer(impl);
    }

    public ManagerContainer(){

    }

    public AgentContainer getInstance(){
        return managerContainer;
    }

    public static void createManager(Session session, boolean flag){
        try{
            AgentController agentController = managerContainer.createNewAgent(session.getUser().getUsername(), Manager.class.getName(),new Object[]{flag});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
