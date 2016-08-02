/*******************************************************************************
 * Copyright 2016 Intuit
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.intuit.wasabi.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TypeCodec;
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
import com.intuit.wasabi.experimentobjects.Experiment.ID;

public class AssignmentsRepositoryIntegrationTest {

	private Session session;
	private ID id;

	@Before
	public void setUp() {
		Cluster cluster = Cluster.builder().addContactPoints("localhost").build();

		session = cluster.connect("wasabi_experiments");
		CodecRegistry registry = session.getCluster().getConfiguration().getCodecRegistry();
		registry.register(new UserIDCodec());
		registry.register(new ExperimentIDCodec());
		registry.register(new ExperimentIDToVarcharCodec());
		registry.register(new ApplicationNameCodec());
		registry.register(new ContextCodec());
		registry.register(TypeCodec.timestamp());
	}
	
	@After
	public void tearDown() {
		session.close();
	}
	
	
	@Test
	public void testGetUserAssignment() {
		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
//		Result<ExperimentUser> result = 
//		accessor.getUserAssignments("SW50ZWdyVGVzdA_User_60", 
//		"SW50ZWdyVGVzdA_1467633919665App_PRIMARY", "PROD");
//		System.out.println("result is " + result.all());
		/* Does not work ZZZ*/
		ResultSet resultWithWrappedId = 
		accessor.getUserAssignmentsByWrappedIds(User.ID.valueOf("SW50ZWdyVGVzdA_User_60"), 
				Application.Name.valueOf("SW50ZWdyVGVzdA_1467633919665App_PRIMARY"), Context.valueOf("PROD"));
		System.out.println("resultWithWrappedId is " + resultWithWrappedId.all());
		/**/
	}

	@Test
	public void testGetUserAssignmentFull() {
		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
//		Result<ExperimentUser> result = 
//		accessor.getUserAssignments("SW50ZWdyVGVzdA_User_60", 
//		"SW50ZWdyVGVzdA_1467633919665App_PRIMARY", "PROD");
//		System.out.println("result is " + result.all());
		/* Does not work ZZZ*/
		 ExperimentUser resultWithWrappedId = accessor.getUserAssignmentsFullByWrappedIds(User.ID.valueOf("SW50ZWdyVGVzdA_User_60"), 
				Application.Name.valueOf("SW50ZWdyVGVzdA_1467633919665App_PRIMARY"), Context.valueOf("PROD"));
		System.out.println("resultWithWrappedId is " + resultWithWrappedId);
		/**/
	}

	@Test
	public void testAssignUserToLookupWithLabelAndDeleteUserAssignmentLookup() {
		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
		id = Experiment.ID.newInstance();
		accessor.assignUserToLookup(id,
				User.ID.valueOf("AssignUserToLookup"), Context.valueOf("test"), new Date(), "label1");
		accessor.deleteUserFromUserAssignmentLookUp(id, 
				User.ID.valueOf("AssignUserToLookup"), Context.valueOf("test"));	
	}
	
	@Test
	public void testAssignUserToLookupWithoutLabel() {
		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
		Experiment.ID id = Experiment.ID.newInstance();
		accessor.assignUserToLookup(id,
				User.ID.valueOf("AssignUserToLookup"), Context.valueOf("test"), new Date());
	}

	@Test
	public void testDeleteUserAssignmentLookupWithoutLabel() {
	}

	@Test
	public void testGetAssignments() {
		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
		Experiment.ID id = Experiment.ID.newInstance();
		Result<ExperimentUser> result = accessor.getAssignments(User.ID.valueOf("SW50ZWdyVGVzdA_User_60"), 
				Application.Name.valueOf("SW50ZWdyVGVzdA_1467633919665App_PRIMARY"), Context.valueOf("PROD"));
		System.out.println("Assignments " + result.all());
	}

	@Test // Make sure the entry exists with user id AssignUserToLookup
	public void testGetUserAssignmentLookup() {
		MappingManager manager = new MappingManager(session);
		AssignmentsRepository accessor = manager.createAccessor(AssignmentsRepository.class);
		Result<UserAssignmentLookup> result = accessor.getUserAssignmentLookup(
				User.ID.valueOf("AssignUserToLookup"));
		System.out.println("Assignments " + result.all());
	}
}
