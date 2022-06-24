package com.system.blog.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.system.blog.sms.dto.PublicationDTO;
import com.system.blog.sms.dto.PublicationResponse;
import com.system.blog.sms.entity.Publication;
import com.system.blog.sms.exceptions.ResourceNotFoundException;
import com.system.blog.sms.repository.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PublicationDTO createPublication(PublicationDTO publicationDTO) {
		Publication publication = mapperEntity(publicationDTO);
		Publication newPublication = publicationRepository.save(publication);
		PublicationDTO publicationResponse = mapperDTO(newPublication);
		return publicationResponse;
	}

	@Override
	public PublicationResponse getAllPublications(int numberOfPage, int measureOfPage, String orderBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
		Pageable pageable = PageRequest.of(numberOfPage, measureOfPage,sort);
		Page<Publication> publications = publicationRepository.findAll(pageable);
		List<Publication> listOfPublications = publications.getContent();
		List<PublicationDTO> content = listOfPublications.stream().map(publication -> mapperDTO(publication)).collect(Collectors.toList());
		PublicationResponse publicationResponse = new PublicationResponse();
		publicationResponse.setContent(content);
		publicationResponse.setNumberPage(publications.getNumber());
		publicationResponse.setMeasurePage(publications.getSize());
		publicationResponse.setTotalElements(publications.getTotalElements());
		publicationResponse.setTotalPages(publications.getTotalPages());
		publicationResponse.setLast(publications.isLast());
		
		return publicationResponse;
		//return listOfPublications.stream().map(publication -> mapperDTO(publication)).collect(Collectors.toList());
	}

	// Convert Entity to DTO
	private PublicationDTO mapperDTO(Publication publication) {
		PublicationDTO publicationDTO = modelMapper.map(publication,PublicationDTO.class);
		return publicationDTO;
	}

	// Convert Entity to DTO
	private Publication mapperEntity(PublicationDTO publicationDTO) {
		Publication publication = modelMapper.map(publicationDTO, Publication.class);
		return publication;
	}

	@Override
	public PublicationDTO getPublicationForId(long id) {
		// TODO Auto-generated method stub
		Publication publication = publicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		return mapperDTO(publication);
	}

	@Override
	public PublicationDTO updatePublication(PublicationDTO publicationDTO, long id) {
		// TODO Auto-generated method stub
		Publication publication = publicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

		publication.setTitle(publicationDTO.getTitle());
		publication.setDescription(publicationDTO.getDescription());
		publication.setContent(publicationDTO.getContent());

		Publication publicationUpdated = publicationRepository.save(publication);
		return mapperDTO(publicationUpdated);
	}

	@Override
	public void deletePublication(long id) {
		// TODO Auto-generated method stub
		Publication publication = publicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
		publicationRepository.delete(publication);
	}

}
