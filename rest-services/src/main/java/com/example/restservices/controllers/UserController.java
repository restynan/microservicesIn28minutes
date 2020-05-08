package com.example.restservices.controllers;

import com.example.restservices.exception.UserNotFoundException;
import com.example.restservices.models.User;
import com.example.restservices.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping

    @ApiOperation(value = "Retrieve All users ")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }



    @GetMapping("/user/{id}")
    @ApiOperation(value = "Find user by id ",notes="provide an id to look up specific user information",response = User.class)
    public EntityModel<User> retrieveUser(@ApiParam(value="Id value for the user you need to retrieve",required = true)
                                              @PathVariable int id) {
     User user= userService.findOne(id);
     if (user==null){
         throw new UserNotFoundException("id- "+id);
     }

     //using heros to add link for retrieve all users link to the result
        EntityModel<User> model = new EntityModel<>(user);

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkTo.withRel("all-users"));

        return model;


    }

    @PostMapping("/user")
    @ApiOperation(value = "Create new user",notes="provide user  details",response = User.class)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);

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

        User user= userService.deleteById(id);
        if (user==null){
            throw new UserNotFoundException("id- "+id);
        }


    }
}
