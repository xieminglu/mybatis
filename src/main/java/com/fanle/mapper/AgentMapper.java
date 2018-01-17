package com.fanle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface AgentMapper {
	
	@Select({
		"select * from op_agent"
	})
	public List<Map<String, Object>> getList();

}
