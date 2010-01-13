/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @author tags. See the COPYRIGHT.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.teiid.netty.handler.codec.serialization;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

/**
 * An {@link ObjectInput} which is interoperable with {@link ObjectEncoder}
 * and {@link ObjectEncoderOutputStream}.
 *
 * @author The Netty Project (netty-dev@lists.jboss.org)
 * @author Trustin Lee (tlee@redhat.com)
 *
 * @version $Rev: 628 $, $Date: 2009-01-05 20:06:00 -0600 (Mon, 05 Jan 2009) $
 *
 */
public class ObjectDecoderInputStream extends ObjectInputStream {

    private final InputStream in;
    private final ClassLoader classLoader;
    private final int maxObjectSize;
    
    private boolean foundLength;
    private byte[] buffer;
    private int count;

    public ObjectDecoderInputStream(InputStream in, ClassLoader classLoader, int maxObjectSize) throws SecurityException, IOException {
    	super();
    	this.in = in;
        this.classLoader = classLoader;
        this.maxObjectSize = maxObjectSize;
    }
    
    @Override
    protected final Object readObjectOverride() throws IOException,
    		ClassNotFoundException {
        if (!foundLength) {
        	if (buffer == null) {
        		buffer = new byte[4];
        	}
            fillBuffer();
    		int dataLen = ((buffer[0] & 0xff << 24) + (buffer[1] & 0xff << 16) + (buffer[2] & 0xff << 8) + (buffer[3] & 0xff << 0));
	        if (dataLen <= 0) {
	            throw new StreamCorruptedException("invalid data length: " + dataLen); //$NON-NLS-1$
	        }
	        if (dataLen > maxObjectSize) {
	            throw new StreamCorruptedException(
	                    "data length too big: " + dataLen + " (max: " + maxObjectSize + ')'); //$NON-NLS-1$ //$NON-NLS-2$
	        }
        	//check if the underlying buffer can be used
	        if (in.available() >= dataLen) { 
	        	return new CompactObjectInputStream(in, classLoader).readObject();
	        }
	        buffer = new byte[dataLen];
	        foundLength = true;
        }
        fillBuffer();
        foundLength = false;
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        buffer = null;
        return new CompactObjectInputStream(bais, classLoader).readObject();
    }

	private void fillBuffer() throws IOException, EOFException {
		while (count < buffer.length) {
	        int read = in.read(buffer, count, buffer.length - count);
	        if (read == -1) {
	        	throw new EOFException();
	        }
	        count += read;
        }
        count = 0;
	}
    
    @Override
    public void close() throws IOException {
    	in.close();
    }
    
}
