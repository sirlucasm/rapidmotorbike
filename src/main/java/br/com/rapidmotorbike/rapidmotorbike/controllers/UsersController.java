package br.com.rapidmotorbike.rapidmotorbike.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rapidmotorbike.rapidmotorbike.models.Users;
import br.com.rapidmotorbike.rapidmotorbike.repositories.UsersRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UsersController {
    @Autowired
    private UsersRepository _usersRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> Get() {
        return _usersRepository.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Users> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Users> users = _usersRepository.findById(id);
        if(users.isPresent())
            return new ResponseEntity<Users>(users.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/users", method =  RequestMethod.POST)
    public Users Post(@RequestBody Users users)
    {
        return _usersRepository.save(users);
    }

    @RequestMapping(value = "/users/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Users> Put(@PathVariable(value = "id") long id, @RequestBody Users newUsers)
    {
        Optional<Users> oldUsers = _usersRepository.findById(id);
        if(oldUsers.isPresent()){
            Users users = oldUsers.get();
            users.setName(newUsers.getName());
            _usersRepository.save(users);
            return new ResponseEntity<Users>(users, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Users> users = _usersRepository.findById(id);
        if(users.isPresent()){
            _usersRepository.delete(users.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/users/me", method =  RequestMethod.POST)
    public Optional<Users> fetchLoggedUser(@RequestBody Users users)
    {
        Optional<Users> user = _usersRepository.findById(users.getId());
        if (user.isPresent()) {
            return user;
        }
        return null;
    }

    @RequestMapping(value = "/users/login", method =  RequestMethod.POST)
    public Optional<Users> Login(@RequestBody Users users)
    {
        Optional<Users> user = _usersRepository.findByCellPhoneAndPassword(users.getCellPhone(), users.getPassword());
        if (user.isPresent()) 
            return user;
        return null;
    }

    @RequestMapping(value = "/users/userType/{userType}", method =  RequestMethod.GET)
    public List<Users> fetchUserByUserType(@PathVariable(value = "userType") int userType)
    {
        List<Users> user = _usersRepository.findByUserType(userType);
        if (user.size() > 0)
            return user;
        return null;
    }
}