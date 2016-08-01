package com.intuit.wasabi.repository.cassandra.pojo;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;

import java.util.UUID;

public class Bucket {
    @PartitionKey(0)
    @Column(name="experiment_id")
    UUID experimentId;

    @PartitionKey(1)
    String label;

    String state;

    String description;

    double allocation;

    @Column(name="is_control")
    boolean isControl;

    String payload;

    Bucket(UUID experimentId, String label, String state, String description, double allocation, boolean isControl, String payload) {
        this.experimentId = experimentId;
        this.label = label;
        this.state = state;
        this.description = description;
        this.allocation = allocation;
        this.isControl = isControl;
        this.payload = payload;
    }

    UUID getExperimentId() {
        return experimentId;
    }

    String getLabel() {
        return label;
    }

    String getState() {
        return state;
    }

    String getDescription() {
        return description;
    }

    double getAllocation() {
        return allocation;
    }

    boolean isControl() {
        return isControl;
    }

    String getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bucket bucket = (Bucket) o;

        if (Double.compare(bucket.allocation, allocation) != 0) return false;
        if (isControl != bucket.isControl) return false;
        if (experimentId != null ? !experimentId.equals(bucket.experimentId) : bucket.experimentId != null)
            return false;
        if (label != null ? !label.equals(bucket.label) : bucket.label != null) return false;
        if (state != null ? !state.equals(bucket.state) : bucket.state != null) return false;
        if (description != null ? !description.equals(bucket.description) : bucket.description != null) return false;
        return payload != null ? payload.equals(bucket.payload) : bucket.payload == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = experimentId != null ? experimentId.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(allocation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isControl ? 1 : 0);
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "experimentId=" + experimentId +
                ", label='" + label + '\'' +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                ", allocation=" + allocation +
                ", isControl=" + isControl +
                ", payload='" + payload + '\'' +
                '}';
    }
}