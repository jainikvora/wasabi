/*******************************************************************************
 * Copyright 2016 Intuit
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
