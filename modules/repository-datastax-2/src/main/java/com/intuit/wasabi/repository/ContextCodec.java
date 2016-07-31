package com.intuit.wasabi.repository;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.utils.Bytes;
import com.intuit.wasabi.assignmentobjects.User;
import com.intuit.wasabi.experimentobjects.Context;

public class ContextCodec extends TypeCodec<Context> {

    private final Charset charset = Charset.forName("UTF-8");

    public ContextCodec() {
        super(DataType.text(), Context.class);
    }

    @Override
    public String format(Context value) {
        if (value == null)
            return "NULL";
        return value.toString();
    }

    @Override
    public Context parse(String value) {
        if (value == null )
            return null;

        return Context.valueOf(value);
    }

    @Override
    public ByteBuffer serialize(Context value, ProtocolVersion protocolVersion) {
        return value == null ? null : ByteBuffer.wrap(value.toString().getBytes(charset));
    }

    @Override
    public Context deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) {
        if (bytes == null || bytes.remaining() == 0)
            return null;
        return Context.valueOf(new String(Bytes.getArray(bytes), charset));
    }
}
