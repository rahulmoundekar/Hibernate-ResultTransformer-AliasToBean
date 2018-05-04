package com.hibernate.demo.app.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.hibernate.demo.app.model.Employee;

public class Test {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		return factory;
	}

	@SuppressWarnings("unchecked")
	public void emloyeeAliasToBeanView() {
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> list = criteria
				.setProjection(Projections.projectionList().add(Projections.property("name").as("name")))
				.setResultTransformer(Transformers.aliasToBean(Employee.class)).list();
		for (Employee employee : list) {
			System.out.println(employee.getName());
		}
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.emloyeeAliasToBeanView();
	}

}
