package com.marcosbarbero.wd.pcf.multidatasources.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcosbarbero.wd.pcf.multidatasources.first.domain.First;
import com.marcosbarbero.wd.pcf.multidatasources.first.repository.FirstRepository;



@RestController
@RequestMapping("/resources/v1")
public class FirstResource {

	@Autowired
	FirstRepository repository;
	
	private static final Logger LOGGER = Logger.getLogger(FirstResource.class.getName());
	
	
	@RequestMapping(value="/first", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find() {
		
		
		LOGGER.log(Level.INFO, "***********starting request**************");
		
		try {
			List<First> lista = new ArrayList<>();
			repository.findAll().forEach(first -> lista.add(first));
		
			LOGGER.log(Level.INFO, "***********end request**************");
			
			return new ResponseEntity<List<First>>(lista, HttpStatus.OK); 
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	
	}
	
	@RequestMapping(value="/first", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody First payload) {
		
		
		LOGGER.log(Level.INFO, "***********starting request**************");
		
		try {
			repository.save(payload);
			return new ResponseEntity<String>("Success.", HttpStatus.OK); 
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	
	}

}
