package calculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Represents a list of booleans in bytes to save memory. Booleans can be represented as only 1 bit of information, yet in java, as well as other programming languages, their data type is inefficient by a factor of 8. BooleanList saves on space.
 * @author Gabe Classon
 *
 */
public class BooleanList {
	private byte[] array;
	private int length;
	private int byteLength; // the whole number of bytes
	private int byteExcess; // the bits of unused space in the last byte
	
	/**
	 * 
	 * @param length the number of booleans to be stored
	 * @param initValue the default (initial) value of all elements in the list
	 */
	public BooleanList(int length, boolean initValue) {
		this.length = length;
		if (length % 8 == 0) {
			array = new byte[length / 8];
		}
		else {
			array = new byte[length / 8 + 1];
		}
		
		if (initValue) {
			for (int i = 0; i < length / 8; i++) {
				array[i] = (byte) 0b11111111;
			}
		}
		
		byteLength = length / 8;
		byteExcess = length % 8;
	}
	
	/**
	 * Number of booleans in the list
	 * @return
	 */
	public int length() {
		return length;
	}
	
	/**
	 * Number of whole bytes in storage (length / 8)
	 * @return
	 */
	public int byteLength() {
		return byteLength;
	}
	
	/**
	 * Bits in excess of a whole number of bytes (length % 8)
	 * @return
	 */
	public int byteExcess() {
		return byteExcess;
	}
	
	/**
	 * Sets a certain boolean value in the list
	 * @param index the index to be altered
	 * @param value what to change it to
	 */
	public void set(int index, boolean value) {
		int b = index / 8;
		int j = index % 8;
		
		if (value) {
			byte flipByte = (byte) (0b10000000 >>> j);
			
			array[b] = (byte) (array[b] | flipByte);
		}
		else {
			byte flipByte = (byte) ~(0b10000000 >>> j);
			
			array[b] = (byte) (array[b] & flipByte);
		}
		
	}
	
	/**
	 * Sets a certain index's value to false
	 * @param index The index to set to false
	 */
	public void setFalse(int index) {
		int b = index / 8;
		
		byte flipByte = (byte) ~(0b10000000 >>> (index % 8));
		
		array[b] = (byte) (array[b] & flipByte);
	}
	
	/**
	 * Sets are certain index's value to true
	 * @param index The index to set to true
	 */
	public void setTrue(int index) {
		int b = index / 8;
		
		byte flipByte = (byte) (0b10000000 >>> (index % 8));
		
		array[b] = (byte) (array[b] | flipByte);
	}
	
	/**
	 * Flips the value of a certain index
	 * @param index The index to flip
	 */
	public void flip(int index) {
		int b = index / 8;
		int j = index % 8;
		
		byte flipByte = (byte) (0b10000000 >>> j);
		
		array[b] = (byte) (array[b] ^ flipByte);
	}
	
	/**
	 * Gets the value of a certain index
	 * @param index The index of the boolean to return
	 * @return The boolean at index
	 */
	public boolean get(int index) {
		int b = index / 8;
		int j = index % 8;
		
		return 1 == ((array[b] >>> (7 - j)) & 1);
	}
	
	/**
	 * Gets one byte of the boolean array
	 * @param The index of the byte to retrieve
	 * @return A representation of the byte at that index
	 */
	public String getByteString(int index) {
		if (byteExcess == 0 || index != byteLength)
			return String.format("%8s", Integer.toBinaryString(array[index] & 0xFF)).replace(' ', '0');
		return String.format("%8s", Integer.toBinaryString(array[index] & 0xFF)).replace(' ', '0').substring(0, byteExcess);
	}
	
	/**
	 * String representation of the boolean list in binary
	 */
	public String toString() {
		String out = "";
		for (int i = 0; i < byteLength; i++)
			out += String.format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0');
		if (byteExcess != 0)
			out += String.format("%8s", Integer.toBinaryString(array[byteLength] & 0xFF)).replace(' ', '0').substring(0, byteExcess);
		return out;
	}
	
	/**
	 * Converts to a boolean array
	 * @return A boolean array
	 */
	public boolean[] toBooleanArray() {
		boolean[] out = new boolean[length];
		
		for (int i = 0; i < byteLength; i++) {
			byte b = array[i];
			for (int j = 7; j >= 0; j--) {
				out[i * 8 + j] = (b & 1) == 1; 
				b = (byte) (b >>> 1);
			}
		}
		
		if (byteExcess != 0) {
			byte b = array[byteLength];
			for (int j = byteExcess - 1; j >= 0; j--) {
				out[byteLength * 8 + j] = (b & 1) == 1;
				b = (byte) (b>>> 1);
			}
		}
		
		return out;
	}
	
	/**
	 * Outputs Boolean values to file as a bit stream
	 * @param path the file path to save the text file at.
	 * @throws IOException
	 */
	public void outputToFile(String path) throws IOException {
		PrintWriter outF = new PrintWriter(new FileWriter(new File(path)));
		for (int i = 0; i < byteLength + (byteExcess() + 7) / 8; i++) {
			outF.print(getByteString(i));
		}
		outF.close();
	}
	
	/**
	 * Gets the byte at index in the byte array
	 * @param index
	 * @return the byte at index
	 */
	public byte getByte(int index) {
		return array[index];
	}
	
	/**
	 * Sets a byte in the byte array
	 * @param index
	 * @param b
	 */
	public void setByte(int index, byte b) {
		array[index] = b;
	}
}
