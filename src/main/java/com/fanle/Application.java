package com.fanle;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fanle.config.MybatisJavaConfig;
import com.fanle.config.MybatisXmlConfig;
import com.fanle.mapper.AgentMapper;

public class Application {
	
	public static void main(String[] args) {
		MybatisJavaConfig mybatis = new MybatisJavaConfig();
		List<Map<String, Object>> list = MybatisJavaConfig.getSession().getMapper(AgentMapper.class).getList();
		for (Map<String, Object> map : list) {
			for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); ) {
				String key = (String) iterator.next();
				System.out.println(key + "=====" + map.get(key));
			}
		}
	}
	
}
