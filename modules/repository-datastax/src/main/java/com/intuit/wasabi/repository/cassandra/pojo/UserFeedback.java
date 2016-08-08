package com.intuit.wasabi.repository.cassandra.pojo;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="user_feedback")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserFeedback {
    @PartitionKey(0)
    @Column(name = "user_id")
    String userId;

    @PartitionKey(1)
    Date submitted;

    int score;

    String comment;

    @Column(name = "user_email")
    String userEmail;

    @Column(name = "contact_okay")
    boolean contactOkay;
}