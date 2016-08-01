package com.intuit.wasabi.repository;

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
	
	/*
	user_id text,
    context text,
    experiment_id uuid,
    bucket_label text,
    created timestamp,
    PRIMARY KEY (user_id, context, experiment_id)
	 */

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
