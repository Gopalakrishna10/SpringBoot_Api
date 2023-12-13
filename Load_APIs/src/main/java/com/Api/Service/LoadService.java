package com.Api.Service;

import java.util.List;

import com.Api.Payload.LoadDto;

public interface LoadService {

	LoadDto createLoad(LoadDto loadDto);
	LoadDto updateLoad(LoadDto loadDto, Integer loadId);
	List<LoadDto> getAllLoad();;
	LoadDto getLoadById(Integer loadId);
	void deleteLoad(Integer loadId);
}
