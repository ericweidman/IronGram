package com.theironyard.Controllers;

import com.sun.deploy.net.HttpResponse;
import com.theironyard.Entities.User;
import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by ericweidman on 3/15/16.
 */
@RestController
public class IronGramController {
    @Autowired
    UserRepository users;
    @Autowired
    PhotoRepository photos;

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException {
        dbui = Server.createWebServer().start();
    }

    @PreDestroy
    public void destroy() {
        dbui.stop();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(String username, String password, HttpSession session, HttpServletResponse response) throws Exception {
        User user = users.findByName(username);
        if (user == null) {
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        } else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) {
            throw new Exception("Wrong password!");
        }
        session.setAttribute("username", username);
        response.sendRedirect("/");
        return user;

    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return users.findByName(username);

    }


}
