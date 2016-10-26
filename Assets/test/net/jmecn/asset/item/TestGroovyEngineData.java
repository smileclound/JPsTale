package net.jmecn.asset.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

/**
 * Groovy脚本引擎
 * 通过设定CLASSPATH来初始化groovy脚本引擎，可以运行该path下的任何groovy脚本文件了
 * @author yanmaoyuan
 *
 */
public class TestGroovyEngineData {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		
		String path = "./assets/scripts";
		GroovyScriptEngine gse = new GroovyScriptEngine(path);
		Binding binding = new Binding();

		// 加载NPC数据
		gse.run("npc-init.groovy", binding);
		List<String> obj = (ArrayList<String>)binding.getVariable("npclist");
		
		for(int i=0; i<obj.size(); i++) {
			String npcfile = obj.get(i);
			gse.run(npcfile, binding);
			Map map = (Map)binding.getVariable("npc");
			System.out.println(map);
		}

		// 加载FIELD数据
		gse.run("field-init.groovy", binding);
		List fields = (ArrayList)binding.getVariable("output");
		for(int i=0; i<fields.size(); i++) {
			Map fieldData = (Map)fields.get(i);
			System.out.println(fieldData);
		}
		
		gse.run("monster-init.groovy", binding);
		List monsterlist = (ArrayList)binding.getVariable("monsterlist");
		for(int i=0; i<monsterlist.size(); i++) {
			String script = (String)monsterlist.get(i);
			gse.run(script, binding);
			Map map = (Map)binding.getVariable("monster");
			System.out.println(map);
		}
		
		long time = System.currentTimeMillis() - start;
		System.out.println("总耗时:" + time/1000.0f + "秒");
	}

}