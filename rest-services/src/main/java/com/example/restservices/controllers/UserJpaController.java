package com.example.restservices.controllers;

import com.example.restservices.exception.UserNotFoundException;
import com.example.restservices.models.User;
import com.example.restservices.repository.UserRepository;
import com.example.restservices.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Jpa/users")
public class UserJpaController {
  @Autowired
  private UserRepository userRepository;




    @GetMapping

    @ApiOperation(value = "Retrieve All users ")
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();
    }



    @GetMapping("/user/{id}")
    @ApiOperation(value = "Find user by id ",notes="provide an id to look up specific user information",response = User.class)
    public EntityModel<User> retrieveUser(@ApiParam(value="Id value for the user you need to retrieve",required = true)
                                          @PathVariable int id) {
        Optional<User> user= userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("id- "+id);
        }

        //using heros to add link for retrieve all users link to the result

        //return type EntityModel<User>
        EntityModel<User> model = new EntityModel<>(user.get());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkTo.withRel("all-users"));

        return model;

      /*
      springboot release 2.1.3
      Resource<User> resource = new Resource<User>(user);

        ControllerLinkBuilder linkTo =ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;*/


    }

    @PostMapping("/user")
    @ApiOperation(value = "Create new user",notes="provide user  details",response = User.class)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);

        //returning response 201 and providing a link in the headers for the newly created user
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }


    @DeleteMapping("user/{id}")
    @ApiOperation(value = "Delete user by id ",notes="provide an id to delete a specific user ",response = User.class)
    public void  deleteUser(@ApiParam(value="Id value for the user you want to delete",required = true)
                            @PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

      if (!user.isPresent()){
            throw new UserNotFoundException("id- "+id);
        }

        //deleteById  doesnot return any thing
        userRepository.deleteById(id);


    }
}
