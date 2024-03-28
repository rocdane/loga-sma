package com.loga.app.container;

import com.loga.agent.Manager;
import com.loga.agent.Marketer;
import com.loga.model.Session;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class MarketerContainer {

    private static AgentContainer marketerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "MARKETER");
        marketerContainer = runtime.createAgentContainer(impl);
    }

    public MarketerContainer(){

    }

    public AgentContainer getInstance(){
        return marketerContainer;
    }

    public static void createMarketer(Session session, boolean flag){
        try{
            AgentController agentController = marketerContainer.createNewAgent(session.getUser().getUsername(), Marketer.class.getName(),new Object[]{flag});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
