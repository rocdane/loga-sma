package com.loga.api;

import com.loga.model.Session;
import com.loga.model.User;

/**
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 */
public interface IAuthenticate {
    Session login(String usr,String pwd);
    void logout(Session session) throws Exception;
    void updateUser(User up) throws Exception;
}