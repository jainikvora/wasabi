/*******************************************************************************
 * Copyright 2016 Intuit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.intuit.wasabi.repository.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.intuit.wasabi.assignmentobjects.User;
import com.intuit.wasabi.experimentobjects.Context;
import com.intuit.wasabi.experimentobjects.Experiment;

@Table(name="user_assignment_look_up",keyspace="wasabi_experiments")
public class UserAssignmentLookup {
	
	 @PartitionKey
	 @Column(name="user_id")
	 User.ID user_id;
	 
	 @ClusteringColumn(0)
	 Context   context;

	 @ClusteringColumn(1)
	 Experiment.ID   experiment_id;
	 
	 public User.ID getUser_id() {
		return user_id;
	}

	public void setUser_id(User.ID user_id) {
		this.user_id = user_id;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Experiment.ID getExperiment_id() {
		return experiment_id;
	}

	public void setExperiment_id(Experiment.ID experiment_id) {
		this.experiment_id = experiment_id;
	}

	public String getBucket_label() {
		return bucket_label;
	}

	public void setBucket_label(String bucket_label) {
		this.bucket_label = bucket_label;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	String   bucket_label;

	 Date created;
	 
	 public String toString() {
		 return ToStringBuilder.reflectionToString(this);
	 }

	
}
