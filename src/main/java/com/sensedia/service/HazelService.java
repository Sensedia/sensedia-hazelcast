package com.sensedia.service;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.sensedia.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by renanpetronilho on 01/04/16.
 */
public class HazelService {

	static HazelcastInstance instance;

	public static void init() {
		Config cfg = new Config();
		instance = Hazelcast.newHazelcastInstance(cfg);
		System.out.println("Hazelcast init......");
	}

	public static void put(Model model) {
		Map<Integer, String> mapCustomers = instance.getMap("sensedia-model");
		mapCustomers.put(model.getKey(), model.getValue());
	}

	public static String get(int id) {
		Map<Integer, String> mapCustomers = instance.getMap("sensedia-model");
		return mapCustomers.get(id);
	}

	public static List<Model> getAll() {
		Map<Integer, String> mapCustomers = instance.getMap("sensedia-model");
		List<Model> models = new ArrayList<Model>();
		for (int key : mapCustomers.keySet()) {
			Model model = new Model(key, mapCustomers.get(key));
			models.add(model);
		}
		return models;
	}
}
