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

import br.com.rapidmotorbike.rapidmotorbike.models.PaymentsInfos;
import br.com.rapidmotorbike.rapidmotorbike.repositories.PaymentsInfosRepository;

@RestController
public class PaymentsInfosController {
    @Autowired
    private PaymentsInfosRepository _paymentsInfosRepository;

    @RequestMapping(value = "/paymentsInfos", method = RequestMethod.GET)
    public List<PaymentsInfos> Get() {
        return _paymentsInfosRepository.findAll();
    }

    @RequestMapping(value = "/paymentsInfos/{id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentsInfos> GetById(@PathVariable(value = "id") long id)
    {
        Optional<PaymentsInfos> paymentsInfos = _paymentsInfosRepository.findById(id);
        if(paymentsInfos.isPresent())
            return new ResponseEntity<PaymentsInfos>(paymentsInfos.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/paymentsInfos", method =  RequestMethod.POST)
    public PaymentsInfos Post(@RequestBody PaymentsInfos paymentsInfos)
    {
        return _paymentsInfosRepository.save(paymentsInfos);
    }

    @RequestMapping(value = "/paymentsInfos/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<PaymentsInfos> Put(@PathVariable(value = "id") long id, @RequestBody PaymentsInfos newPaymentsInfos)
    {
        Optional<PaymentsInfos> oldPaymentsInfos = _paymentsInfosRepository.findById(id);
        if(oldPaymentsInfos.isPresent()){
            PaymentsInfos paymentsInfos = oldPaymentsInfos.get();
            paymentsInfos.setCpf(newPaymentsInfos.getCpf());
            _paymentsInfosRepository.save(paymentsInfos);
            return new ResponseEntity<PaymentsInfos>(paymentsInfos, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/paymentsInfos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<PaymentsInfos> paymentsInfos = _paymentsInfosRepository.findById(id);
        if(paymentsInfos.isPresent()){
            _paymentsInfosRepository.delete(paymentsInfos.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}