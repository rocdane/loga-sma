package com.loga.api;

import com.loga.app.dao.LogRepository;
import com.loga.app.dao.SessionRepository;
import com.loga.app.dao.UserRepository;
import com.loga.app.controller.GuiController;
import com.loga.model.Session;
import com.loga.model.User;
import com.loga.ui.AlertWarning;

import java.util.Date;

public class Authenticate implements IAuthenticate{
    private static Authenticate authenticate;

    private Authenticate(){

    }

    public static Authenticate getInstance(){
        if(authenticate==null){
            authenticate = new Authenticate();
        }
        return authenticate;
    }

    /**
     * TODO:cette méthode permet de créer un objet Session pour un utilisateur.
     * @param usr,pwd
     * @return Session
     */
    @Override
    public Session login(String usr,String pwd) {
        Session session = null;
        UserRepository userRepository = new UserRepository();
        User user = userRepository.authenticate(usr,pwd);

        if (user != null && user.getActive()) {
            session = new Session();
            session.setUser(user);
            session.setDateOuverture(new Date());
            SessionRepository sessionRepository = new SessionRepository();
            try {
                sessionRepository.save(session);
                LogRepository.getInstance().write("Nouvelle session ",user.getUsername() + " ouvre une session",user);
                GuiController.setSession(session);
            } catch (Exception e) {
                AlertWarning.getInstance().setHeaderText("Ouverture de session");
                AlertWarning.getInstance().setContentText("Session non sauvergardée.!!!\n"+e.getMessage());
                AlertWarning.getInstance().show();
                e.printStackTrace();
            }
        }
        return session;
    }

    /**
     * TODO:Cette méthode permet de détruire un objet Session d'un utilisateur.
     * @param session
     * @return boolean
     */
    @Override
    public void logout(Session session) throws Exception {
        session.setDateFermeture(new Date());
        SessionRepository sessionRepository = new SessionRepository();
        sessionRepository.update(session);
    }

    /**
     * TODO:Cette méthode permet de mettre à jour un objet User.
     * @param up
     * @return
     */
    @Override
    public void updateUser(User up) throws Exception {
        UserRepository userRepository = new UserRepository();
        userRepository.update(up);
        login(up.getUsername(), up.getPassword());
    }
}
