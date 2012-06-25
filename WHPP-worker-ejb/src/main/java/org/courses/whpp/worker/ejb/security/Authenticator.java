/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.courses.whpp.entity.Employee;
import org.courses.whpp.entity.User;
import org.courses.whpp.session.UserFacade;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class Authenticator {

	@EJB
	public UserFacade userFacade;

	public boolean isDriverExists(String id, String passHash) {

		User findedUser = userFacade.find(id);
		Logger.getLogger(Authenticator.class.getName()).log(Level.INFO, "Check is friver exists : {0}", id);

		if (findedUser != null && findedUser.getPassword().equals(passHash)) {
			Logger.getLogger(Authenticator.class.getName()).log(Level.INFO, "Driver with {0} founded", id);
			return true;
		}
		Logger.getLogger(Authenticator.class.getName()).log(Level.INFO, "Driver with {0} NOT founded : {0}", id);
		return false;
	}
}
