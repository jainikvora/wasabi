package com.intuit.wasabi.repository;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ParseUtils;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.datastax.driver.core.utils.Bytes;
import com.intuit.wasabi.assignmentobjects.User;
import com.intuit.wasabi.experimentobjects.Experiment;

public class ExperimentIDToVarcharCodec extends TypeCodec<Experiment.ID> {

    public ExperimentIDToVarcharCodec() {
        super(DataType.varchar(), Experiment.ID.class);
    }

    @Override
    public Experiment.ID parse(String value) {
        if (value == null )
            return null;

        return Experiment.ID.valueOf(value);
    }

    @Override
    public String format(Experiment.ID value) {
        if (value == null)
            return "NULL";
        return value.toString();
    }

    @Override
    public ByteBuffer serialize(Experiment.ID value, ProtocolVersion protocolVersion) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(0, value.getRawID().getMostSignificantBits());
        bb.putLong(8, value.getRawID().getLeastSignificantBits());
        return bb;
    }

    @Override
    public Experiment.ID deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) {
        if ( bytes == null || bytes.remaining() == 0)
            return null;
        return Experiment.ID.valueOf(new UUID(bytes.getLong(),
        		bytes.getLong()));
    }
}
