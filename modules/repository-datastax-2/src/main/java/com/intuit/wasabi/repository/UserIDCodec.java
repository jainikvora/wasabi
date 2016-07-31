package com.intuit.wasabi.repository;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.utils.Bytes;
import com.intuit.wasabi.assignmentobjects.User;

public class UserIDCodec extends TypeCodec<User.ID> {

    private final Charset charset = Charset.forName("UTF-8");

    public UserIDCodec() {
        super(DataType.text(), User.ID.class);
    }

    @Override
    public String format(User.ID value) {
        if (value == null)
            return "NULL";
        return value.toString();
    }

    @Override
    public User.ID parse(String value) {
        if (value == null )
            return null;

        return User.ID.valueOf(value);
    }

    @Override
    public User.ID deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) {
        if (bytes == null ||bytes.remaining() == 0 )
            return null;
        return User.ID.valueOf(new String(Bytes.getArray(bytes), charset));
    }

    @Override
    public ByteBuffer serialize(User.ID value, ProtocolVersion protocolVersion) {
        return value == null ? null : ByteBuffer.wrap(value.toString().getBytes(charset));
    }

}
