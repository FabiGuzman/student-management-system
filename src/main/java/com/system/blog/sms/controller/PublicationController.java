package com.system.blog.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.blog.sms.dto.PublicationDTO;
import com.system.blog.sms.dto.PublicationResponse;
import com.system.blog.sms.service.PublicationService;
import com.system.blog.sms.utils.AppConstants;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@PostMapping
	public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO) {
		return new ResponseEntity<>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public PublicationResponse listPublications(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.NUMBER_PAGE_FOR_DEFAULT, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.MEASURE_PAGE_FOR_DEFAULT, required = false) int measureOfPage,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.ORDERBY_FOR_DEFAULT, required = false) String orderBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.ORDER_DIRECTION_FOR_DEFAULT, required = false) String sortDir) {
		return publicationService.getAllPublications(pageNumber, measureOfPage, orderBy, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicationDTO> getPublicationForId(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(publicationService.getPublicationForId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicationDTO> updatePublication(@RequestBody PublicationDTO publicationDTO,
			@PathVariable(name = "id") long id) {
		PublicationDTO publicationResponse = publicationService.updatePublication(publicationDTO, id);
		return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublication(@PathVariable(name = "id") long id) {
		publicationService.deletePublication(id);
		return new ResponseEntity<>("Publication deleted successfully", HttpStatus.OK);
	}
	
}
