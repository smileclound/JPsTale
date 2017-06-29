package com.jme3.scene.plugins.inx.base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.jme3.math.Vector3f;

public class ByteReader {
	protected ByteBuffer buffer;

	public void getByteBuffer(InputStream inputStream) throws IOException {
		int length = inputStream.available();

		ReadableByteChannel channel = Channels.newChannel(inputStream);

		// Allocates and loads a byte buffer from the channel
		buffer = ByteBuffer.allocate(length);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		channel.read(buffer);

		// Ready
		buffer.flip();

		channel.close();
	}

	/**
	 * Gets a long from the chunk Buffer
	 */
	public long getLong() {
		return buffer.getLong();
	}

	/**
	 * Reads a short and returns it as a signed int.
	 */
	public int getShort() {
		return buffer.getShort();
	}

	/**
	 * Reads a short and returns it as an unsigned int.
	 */
	public int getUnsignedShort() {
		return buffer.getShort() & 0xFFFF;
	}

	/**
	 * reads a float from the chunkBuffer.
	 */
	public float getFloat() {
		return buffer.getFloat();
	}

	public float getFloat(int offset) {
		buffer.position(offset);
		return getFloat();
	}
	
	public float getPTDouble() {
		int value = buffer.getInt();
		return ((float)value) / 256f;
	}
	
	public float getPTLightColor() {
		int value = buffer.getShort();
		return ((float)value) / 255f;
	}
	
	/**
	 * Reads 3 floats x,z,y from the chunkbuffer. Since 3ds has z as up and y as
	 * pointing in whereas java3d has z as pointing forward and y as pointing
	 * up; this returns new Point3f(x,-z,y)
	 */
	public Vector3f getPoint3f() {
		float x = buffer.getFloat();
		float z = -buffer.getFloat();
		float y = buffer.getFloat();

		return new Vector3f(x, y, z);
	}

	public Vector3f getVector3f() {
		float x = buffer.getFloat();
		float y = buffer.getFloat();
		float z = buffer.getFloat();

		return new Vector3f(x, y, z);
	}
	
	/**
	 * Reads an int and returns it
	 * 
	 * @return the int read
	 */
	public int getInt() {
		return buffer.getInt();
	}
	
	public int getInt(int offset) {
		buffer.position(offset);
		return getInt();
	}

	/**
	 * Reads an int and returns it unsigned, any ints greater than MAX_INT will
	 * break.
	 */
	public int getUnsignedInt() {
		return buffer.getInt() & 0xFFFFFFFF;
	}

	/**
	 * Reads a byte, unsigns it, returns the corresponding int.
	 * 
	 * @return the unsigned int corresponding to the read byte.
	 */
	public int getUnsignedByte() {
		return buffer.get() & 0xFF;
	}

	/**
	 * This reads bytes until it gets 0x00 and returns the corresponding string.
	 */
	public String getString() {
		StringBuffer stringBuffer = new StringBuffer();
		char charIn = (char) buffer.get();
		while (charIn != 0x00) {
			stringBuffer.append(charIn);
			charIn = (char) buffer.get();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * This reads bytes until it gets 0x00 and returns the corresponding string.
	 */
	public String getString(int size) {
		int count=0;
		StringBuffer stringBuffer = new StringBuffer();
		char charIn = (char) buffer.get();
		count++;
		while (charIn != 0x00) {
			stringBuffer.append(charIn);
			charIn = (char) buffer.get();
			count++;
		}
		
		// skip useless byte
		if (count < size) {
			for(int i=count; i< size; i++) {
				buffer.get();
			}
		}
		return stringBuffer.toString();
	}
}
