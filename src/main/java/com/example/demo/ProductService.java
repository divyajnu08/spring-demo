package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.drools.compiler.kie.builder.impl.KieContainerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	KieContainer kieContainer;
	
	public ProductService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Product getProductDiscount(Product product) throws InstantiationException, IllegalAccessException {
	    FactType personType = kieContainer.getKieBase("rules").getFactType("rules", "Person");
		Object bob = personType.newInstance();
		personType.set(bob, "name", "James" );
		personType.set(bob, "dateOfBirth", new Date());
		List<String> list = new ArrayList<>();
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(product);
		kieSession.insert(bob);
		kieSession.setGlobal( "myGlobalList", list );
		kieSession.fireAllRules();
		kieSession.dispose();
		return product;
	}
	
	public static int calculateMax(int am1,int am2) {
		if(am1>am2)return am1;
		return am2;
	}

}
