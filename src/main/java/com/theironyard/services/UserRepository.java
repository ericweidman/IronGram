package com.theironyard.services;

import com.theironyard.Entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericweidman on 3/15/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
}
