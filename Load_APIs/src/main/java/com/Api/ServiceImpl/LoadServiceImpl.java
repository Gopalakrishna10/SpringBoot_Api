package com.Api.ServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Entity.Load;
import com.Api.Exception.ResourceNotFoundException;
import com.Api.Payload.LoadDto;
import com.Api.Repository.LoadRepo;
import com.Api.Service.LoadService;
@Service
public class LoadServiceImpl implements LoadService {

	@Autowired
	private LoadRepo loadRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public LoadDto createLoad(LoadDto loadDto) {
		Load load= this.modelMapper.map(loadDto, Load.class);
		load.setDate(new java.util.Date());
		Load addLoad=this.loadRepo.save(load);
		return this.modelMapper.map(addLoad, LoadDto.class);
	}

	@Override
	public LoadDto updateLoad(LoadDto loadDto, Integer loadId) {
		Load loads=this.loadRepo.findById(loadId).orElseThrow(()-> new ResourceNotFoundException("Load", "load Id", loadId));
		loads.setComment(loadDto.getComment());
		loads.setDate(loadDto.getDate());
		loads.setLoadingPoint(loadDto.getLoadingPoint());
		loads.setUnloadingPoint(loadDto.getUnloadingPoint());
		loads.setProductType(loadDto.getProductType());
		loads.setTruckType(loadDto.getTruckType());
		loads.setNoOfTrucks(loadDto.getNoOfTrucks());
		loads.setWeight(loadDto.getWeight());
		Load UpdateLoads=this.loadRepo.save(loads);
		return this.modelMapper.map(UpdateLoads, LoadDto.class);
	}

	@Override
	public List<LoadDto> getAllLoad() {
		List<Load> loads=this.loadRepo.findAll();
		List<LoadDto> getload=loads.stream().map(load->this.modelMapper.map(load, LoadDto.class)).collect(Collectors.toList());
		return getload;
	}

	@Override
	public LoadDto getLoadById(Integer loadId) {
		Load load=this.loadRepo.findById(loadId).orElseThrow(()-> new ResourceNotFoundException("Load", "load Id", loadId));
		return this.modelMapper.map(load, LoadDto.class);
	}

	@Override
	public void deleteLoad(Integer loadId) {
		Load load=this.loadRepo.findById(loadId).orElseThrow(()-> new ResourceNotFoundException("Load", "load Id", loadId));
		 this.loadRepo.delete(load);
	}

}
