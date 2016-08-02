package com.intuit.wasabi.repository.cassandra.accessor;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.intuit.wasabi.cassandra.datastax.CassandraDriver;
import com.intuit.wasabi.repository.cassandra.CassandraRepositoryModule;
import com.intuit.wasabi.repository.cassandra.pojo.Experiment;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

public class ExperimentAccessorTest {
    static Session session;
    static MappingManager manager;
    static ExperimentAccessor accessor;
    static Mapper<Experiment> mapper;
    private final Logger logger = LoggerFactory.getLogger(ExperimentAccessorTest.class);
    private static final String TEST_UUID = "29f9db95-9a58-44f4-9e0f-2f1218e15e3c";
    @BeforeClass
    public static void setup(){
        Injector injector = Guice.createInjector(new CassandraRepositoryModule());
        injector.getInstance(Key.get(String.class, Names.named("CassandraInstanceName")));

        session = injector.getInstance(CassandraDriver.class).getSession();
        manager = new MappingManager(session);
        mapper = manager.mapper(Experiment.class);
        accessor = manager.createAccessor(ExperimentAccessor.class);
    }

    @Test
    public void insertData(){
        Experiment experiment = new Experiment(UUID.fromString(TEST_UUID),
                "Test description",
                1.0,
                new Date(),
                new Date(),
                "DRAFT",
                "TestApp",
                new Date(),
                new Date(),
                "",
                "",
                "",
                false,
                false,
                1_000_000,
                "me :)");

        mapper.save(experiment);
    }

    @Test
    public void insertPartialData(){
        /*
         INSERT INTO wassabi_experiment_local.experiment
         ("modified",
         "creatorid",
         "app_name",
         "start_time",
         "model_name",
         "id",
         "description",
         "rule",
         "is_personalized",
         "user_cap",
         "end_time",
         "is_rapid_experiment",
         "created",
         "sample_percent",
         "model_version",
         "state")
         VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);
         */
        Experiment experiment = new Experiment(UUID.randomUUID(),
                "Test description",
                1.0,
                null,
                null,
                "DRAFT",
                "TestApp",
                null,
                null,
                null,
                null,
                null,
                false,
                false,
                1_000_000,
                "me :)");

        logger.info(mapper.saveQuery(experiment).toString());
    }

    @Test
    public void getExperimentByAppName(){
        Result<Experiment> experimentResult = accessor.getExperimentBy("TestApp");
        logger.info(experimentResult.one().toString());
    }

    @Test
    public void getExperimentUsingDefaultMapper(){
        Experiment experiment = mapper.get(UUID.fromString(TEST_UUID));
        logger.info(experiment.toString());
    }

    @Test
    public void preparedStatementTest(){
        PreparedStatement preparedStatement1 = session.prepare("UPDATE experiment SET STATE=?, MODIFIED=? WHERE id = ?");
        session.execute(preparedStatement1.bind(
                "RUNNING",
                new Date(),
                UUID.fromString(TEST_UUID)
        ));
    }

    @Test
    public void preparedStatementTest2(){

        PreparedStatement preparedStatement2 = session.prepare("UPDATE experiment SET STATE=:state, MODIFIED=:modified WHERE id = :id");
        //Using positional binding
        session.execute(preparedStatement2.bind(
                "RUNNING2",
                new Date(),
                UUID.fromString(TEST_UUID)
        ));
        //Using name binding
        session.execute(preparedStatement2.bind()
                .setString("state", "RUNNING")
                .setTimestamp("modified", new Date())
                .setUUID("id", UUID.fromString(TEST_UUID))
        );
    }


}