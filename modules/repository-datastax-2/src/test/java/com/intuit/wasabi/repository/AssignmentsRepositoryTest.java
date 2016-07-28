package com.intuit.wasabi.repository;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
//import com.intuit.wasabi.assignmentobjects.User;
//import com.intuit.wasabi.experimentobjects.Application;
//import com.intuit.wasabi.experimentobjects.Context;
//import com.intuit.wasabi.experimentobjects.Experiment.ID;
import com.intuit.wasabi.assignmentobjects.User;
import com.intuit.wasabi.experimentobjects.Application;
import com.intuit.wasabi.experimentobjects.Context;
import com.intuit.wasabi.experimentobjects.Experiment;

public class AssignmentsRepositoryTest {

	@Test
	public void test() {
		Cluster cluster = Cluster.builder().addContactPoints("localhost").build();

		Session session = cluster.connect("wasabi_experiments");

		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
		Result<ExperimentUser> result = 
		accessor.getUserAssignments("SW50ZWdyVGVzdA_User_60", 
		"SW50ZWdyVGVzdA_1467633919665App_PRIMARY", "PROD");
		System.out.println("result is " + result.all());
		/* Does not work
		ResultSet resultWithWrappedId = 
		accessor.getUserAssignmentsByWrappedIds(User.ID.valueOf("SW50ZWdyVGVzdA_User_60"), 
				Application.Name.valueOf("SW50ZWdyVGVzdA_1467633919665App_PRIMARY"), Context.valueOf("PROD"));
		System.out.println("resultWithWrappedId is " + resultWithWrappedId.all());
		*/
	}

}
