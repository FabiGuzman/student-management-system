package com.system.blog.sms.service;

import com.system.blog.sms.dto.PublicationDTO;
import com.system.blog.sms.dto.PublicationResponse;

public interface PublicationService {

	public PublicationDTO createPublication(PublicationDTO publicationDTO);

	public PublicationResponse getAllPublications(int numberOfPage, int measureOfPage, String orderBy,String sortDir);

	public PublicationDTO getPublicationForId(long id);

	public PublicationDTO updatePublication(PublicationDTO publicationDTO, long id);

	public void deletePublication(long id);
}
