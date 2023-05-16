package com.ssafy.plan.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanService {
	
	private PlanMapper mapper;
	
	public PlanService(PlanMapper mapper) {
		this.mapper = mapper;
	}
	
	
	public List<PlanDto> selectAll() {
		return mapper.readAll();
	}
	
	public PlanDto select(int plan_id) {
		return mapper.read(plan_id);
	}


	public int modify(PlanDto dto) {
		return mapper.update(dto);
	}


	public int remove(int plan_id) {
		return mapper.delete(plan_id);
	}


	public void updateHit(int plan_id) {
		mapper.updateHit(plan_id);
		
	}


	public int write(PlanDto dto) {
		return mapper.insert(dto);
	}

}
