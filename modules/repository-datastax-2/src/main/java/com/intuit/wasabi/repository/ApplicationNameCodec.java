package com.intuit.wasabi.repository;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.utils.Bytes;
import com.intuit.wasabi.assignmentobjects.User;
import com.intuit.wasabi.experimentobjects.Application;

public class ApplicationNameCodec extends TypeCodec<Application.Name> {

    private final Charset charset = Charset.forName("UTF-8");

    public ApplicationNameCodec() {
        super(DataType.text(), Application.Name.class);
    }

    @Override
    public Application.Name parse(String value) {
    	
        if (value == null )
            return null;

        return Application.Name.valueOf(value);
    }

    @Override
    public String format(Application.Name value) {
        if (value == null)
            return "NULL";
        return value.toString();
    }

    @Override
    public ByteBuffer serialize(Application.Name value, ProtocolVersion protocolVersion) {
        return value == null ? null : ByteBuffer.wrap(value.toString().getBytes(charset));
    }

    @Override
    public Application.Name deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) {
        if ( bytes == null || bytes.remaining() == 0 )
            return null;
        return Application.Name.valueOf(new String(Bytes.getArray(bytes), charset));
    }
}
