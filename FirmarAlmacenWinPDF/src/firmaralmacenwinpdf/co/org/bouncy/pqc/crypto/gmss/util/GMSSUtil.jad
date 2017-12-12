// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSUtil.java

package co.org.bouncy.pqc.crypto.gmss.util;

import java.io.PrintStream;

public class GMSSUtil
{

    public GMSSUtil()
    {
    }

    public byte[] intToBytesLittleEndian(int value)
    {
        byte bytes[] = new byte[4];
        bytes[0] = (byte)(value & 0xff);
        bytes[1] = (byte)(value >> 8 & 0xff);
        bytes[2] = (byte)(value >> 16 & 0xff);
        bytes[3] = (byte)(value >> 24 & 0xff);
        return bytes;
    }

    public int bytesToIntLittleEndian(byte bytes[])
    {
        return bytes[0] & 0xff | (bytes[1] & 0xff) << 8 | (bytes[2] & 0xff) << 16 | (bytes[3] & 0xff) << 24;
    }

    public int bytesToIntLittleEndian(byte bytes[], int offset)
    {
        return bytes[offset++] & 0xff | (bytes[offset++] & 0xff) << 8 | (bytes[offset++] & 0xff) << 16 | (bytes[offset] & 0xff) << 24;
    }

    public byte[] concatenateArray(byte arraycp[][])
    {
        byte dest[] = new byte[arraycp.length * arraycp[0].length];
        int indx = 0;
        for(int i = 0; i < arraycp.length; i++)
        {
            System.arraycopy(arraycp[i], 0, dest, indx, arraycp[i].length);
            indx += arraycp[i].length;
        }

        return dest;
    }

    public void printArray(String text, byte array[][])
    {
        System.out.println(text);
        int counter = 0;
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array[0].length; j++)
            {
                System.out.println((new StringBuilder()).append(counter).append("; ").append(array[i][j]).toString());
                counter++;
            }

        }

    }

    public void printArray(String text, byte array[])
    {
        System.out.println(text);
        int counter = 0;
        for(int i = 0; i < array.length; i++)
        {
            System.out.println((new StringBuilder()).append(counter).append("; ").append(array[i]).toString());
            counter++;
        }

    }

    public boolean testPowerOfTwo(int testValue)
    {
        int a;
        for(a = 1; a < testValue; a <<= 1);
        return testValue == a;
    }

    public int getLog(int intValue)
    {
        int log = 1;
        for(int i = 2; i < intValue;)
        {
            i <<= 1;
            log++;
        }

        return log;
    }
}
