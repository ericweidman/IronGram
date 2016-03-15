package com.theironyard.Controllers;

import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

/**
 * Created by ericweidman on 3/15/16.
 */
@Controller
public class IronGramController {
    @Autowired
    UserRepository users;
    @Autowired
    PhotoRepository photos;


    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }
}
