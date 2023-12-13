package com.Api.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.Api.Payload.ApiResponce;
import com.Api.Payload.LoadDto;
import com.Api.Service.LoadService;

@RestController
@RequestMapping("/")
public class LoadController {

	@Autowired
	private LoadService loadService;
	
	
	@PostMapping("load")
	
	public ResponseEntity<LoadDto> createload(@RequestBody LoadDto loadDto){
		LoadDto addLoad=this.loadService.createLoad(loadDto);
		return new ResponseEntity<LoadDto>(addLoad,HttpStatus.CREATED);
	}
	
	@GetMapping("load")
	
	public ResponseEntity<List<LoadDto>> getLoads(){
		return ResponseEntity.ok(this.loadService.getAllLoad());
	}
	@GetMapping("load/{loadId}")
	public ResponseEntity<LoadDto> getById(@PathVariable Integer loadId){
		return ResponseEntity.ok(this.loadService.getLoadById(loadId));
		}
	
	@DeleteMapping("load/{loadId}")
	public ResponseEntity<ApiResponce> deleteuser(@PathVariable Integer loadId)
{
		this.loadService.deleteLoad(loadId);
		return new ResponseEntity(new ApiResponce("load deleted succssfully",true),HttpStatus.OK);
		}
	
	@PutMapping("load/{loadId}")
	
	public ResponseEntity<LoadDto> updateedLoad(@RequestBody LoadDto loadDto ,@PathVariable Integer loadId){
		LoadDto updateLoad=loadService.updateLoad(loadDto, loadId);
		return ResponseEntity.ok(updateLoad);
	}
}
