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

import br.com.rapidmotorbike.rapidmotorbike.models.Locations;
import br.com.rapidmotorbike.rapidmotorbike.repositories.LocationsRepository;

@RestController
public class LocationsController {
    @Autowired
    private LocationsRepository _locationsRepository;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public List<Locations> Get() {
        return _locationsRepository.findAll();
    }

    @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
    public ResponseEntity<Locations> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Locations> locations = _locationsRepository.findById(id);
        if(locations.isPresent())
            return new ResponseEntity<Locations>(locations.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/locations", method =  RequestMethod.POST)
    public Locations Post(@RequestBody Locations locations)
    {
        return _locationsRepository.save(locations);
    }

    @RequestMapping(value = "/locations/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Locations> Put(@PathVariable(value = "id") long id, @RequestBody Locations newLocations)
    {
        Optional<Locations> oldLocations = _locationsRepository.findById(id);
        if(oldLocations.isPresent()){
            Locations locations = oldLocations.get();
            locations.setTitle(newLocations.getTitle());
            _locationsRepository.save(locations);
            return new ResponseEntity<Locations>(locations, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/locations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Locations> locations = _locationsRepository.findById(id);
        if(locations.isPresent()){
            _locationsRepository.delete(locations.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}