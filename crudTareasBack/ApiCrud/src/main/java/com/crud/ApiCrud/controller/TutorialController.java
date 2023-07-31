package com.crud.ApiCrud.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crud.ApiCrud.model.Tutorial;
import com.crud.ApiCrud.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;

	@GetMapping("/tutorials")
	public List<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
		return  tutorialRepository.findAll();
	}

	@PostMapping("/tutorials")
	public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
		return this.tutorialRepository.save(tutorial);
	}

	@GetMapping("/tutorials/{id}")
	public Optional<Tutorial> getTutorialById(@PathVariable("id") long id) {
		return this.tutorialRepository.findById(id);
	}

	@PutMapping("/tutorials/{id}")
	public Tutorial updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {

		Tutorial nuevoTutorial = tutorialRepository.findById(id).get();
		nuevoTutorial.setId(tutorial.getId());
		nuevoTutorial.setTitle(tutorial.getTitle());
		nuevoTutorial.setDescription(tutorial.getDescription());
		boolean estado = false;
		if(tutorial.isPublished())
			estado=true;			
		nuevoTutorial.setPublished(estado);
		
		this.tutorialRepository.save(nuevoTutorial);
		return nuevoTutorial;
	}

	@DeleteMapping("/tutorials/{id}")
	public String deleteTutorial(@PathVariable("id") long id) {
		try {
			this.tutorialRepository.deleteById(id);
			return "ok";
		}catch (Exception e) {
			return "nok";
		}
	}
}
