package br.com.rapidmotorbike.rapidmotorbike.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.rapidmotorbike.rapidmotorbike.models.Addresses;
import br.com.rapidmotorbike.rapidmotorbike.repositories.AddressesRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AddressesController {
    @Autowired
    private AddressesRepository _addressesRepository;

    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    public List<Addresses> Get() {
        return _addressesRepository.findAll();
    }

    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
    public ResponseEntity<Addresses> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Addresses> addresses = _addressesRepository.findById(id);
        if(addresses.isPresent())
            return new ResponseEntity<Addresses>(addresses.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/addresses", method =  RequestMethod.POST)
    public Addresses Post(@RequestBody Addresses addresses)
    {
        return _addressesRepository.save(addresses);
    }

    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Addresses> addresses = _addressesRepository.findById(id);
        if(addresses.isPresent()){
            _addressesRepository.delete(addresses.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
