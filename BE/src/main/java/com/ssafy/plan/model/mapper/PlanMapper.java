package com.ssafy.plan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.plan.model.dto.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDto> readAll();

	PlanDto read(int plan_id);

	int update(PlanDto dto);

	int delete(int plan_id);

	void updateHit(int plan_id);

	int insert(PlanDto dto);


}
