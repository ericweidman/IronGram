package com.theironyard.services;

import com.theironyard.Entities.Photo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericweidman on 3/15/16.
 */
public interface PhotoRepository extends CrudRepository<Photo, Integer>{
}
