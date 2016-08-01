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
package com.intuit.wasabi.repository;


import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.intuit.wasabi.assignmentobjects.User;
import com.intuit.wasabi.experimentobjects.Application;
import com.intuit.wasabi.experimentobjects.Context;
import com.intuit.wasabi.experimentobjects.Experiment;

@Table(name="experiment_user_index",keyspace="wasabi_experiments")
public final class ExperimentUser {

	 @PartitionKey
	 @Column(name="user_id")
	 User.ID user_id;

	 @ClusteringColumn(0)
	 Context   context;

	 @ClusteringColumn(1)
	 Application.Name   app_name;
	 
	 @ClusteringColumn(2)
	 Experiment.ID   experiment_id;

	 String   bucket;

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

	public Application.Name getApp_name() {
		return app_name;
	}

	public void setApp_name(Application.Name app_name) {
		this.app_name = app_name;
	}

	public String getBucket() {
		return bucket;
	}

	public Experiment.ID getExperiment_id() {
		return experiment_id;
	}

	public void setExperiment_id(Experiment.ID experiment_id) {
		this.experiment_id = experiment_id;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	 public String toString() {
		 return ToStringBuilder.reflectionToString(this);
	 }
}
