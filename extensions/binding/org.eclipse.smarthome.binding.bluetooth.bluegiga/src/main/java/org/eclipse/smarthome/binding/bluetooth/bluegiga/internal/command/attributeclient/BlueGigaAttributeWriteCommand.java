/**
 * Copyright (c) 2014,2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.command.attributeclient;

import org.eclipse.smarthome.binding.bluetooth.bluegiga.internal.BlueGigaCommand;

/**
 * Class to implement the BlueGiga command <b>attributeWrite</b>.
 * <p>
 * This command can be used to write an attributes value on a remote device. In order to write the
 * value of an attribute a connection must exists and you need to know the handle of the attribute
 * you want to write
 * <p>
 * This class provides methods for processing BlueGiga API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class BlueGigaAttributeWriteCommand extends BlueGigaCommand {
    public static int COMMAND_CLASS = 0x04;
    public static int COMMAND_METHOD = 0x05;

    /**
     * Connection handle
     * <p>
     * BlueGiga API type is <i>uint8</i> - Java type is {@link int}
     */
    private int connection;

    /**
     * Attribute handle to write to
     * <p>
     * BlueGiga API type is <i>uint16</i> - Java type is {@link int}
     */
    private int attHandle;

    /**
     * Attribute value
     * <p>
     * BlueGiga API type is <i>uint8array</i> - Java type is {@link int[]}
     */
    private int[] data;

    /**
     * Connection handle
     *
     * @param connection the connection to set as {@link int}
     */
    public void setConnection(int connection) {
        this.connection = connection;
    }
    /**
     * Attribute handle to write to
     *
     * @param attHandle the attHandle to set as {@link int}
     */
    public void setAttHandle(int attHandle) {
        this.attHandle = attHandle;
    }
    /**
     * Attribute value
     *
     * @param data the data to set as {@link int[]}
     */
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(COMMAND_CLASS, COMMAND_METHOD);

        // Serialize the fields
        serializeUInt8(connection);
        serializeUInt16(attHandle);
        serializeUInt8Array(data);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BlueGigaAttributeWriteCommand [connection=");
        builder.append(connection);
        builder.append(", attHandle=");
        builder.append(attHandle);
        builder.append(", data=");
        for (int c = 0; c < data.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", data[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}
