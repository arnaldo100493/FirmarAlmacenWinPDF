// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LZWDecoder.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.io.OutputStream;

public class LZWDecoder
{

    public LZWDecoder()
    {
        data = null;
        bitsToGet = 9;
        nextData = 0;
        nextBits = 0;
    }

    public void decode(byte data[], OutputStream uncompData)
    {
        if(data[0] == 0 && data[1] == 1)
            throw new RuntimeException(MessageLocalization.getComposedMessage("lzw.flavour.not.supported", new Object[0]));
        initializeStringTable();
        this.data = data;
        this.uncompData = uncompData;
        bytePointer = 0;
        bitPointer = 0;
        nextData = 0;
        nextBits = 0;
        int oldCode = 0;
        do
        {
            int code;
            if((code = getNextCode()) == 257)
                break;
            if(code == 256)
            {
                initializeStringTable();
                code = getNextCode();
                if(code == 257)
                    break;
                writeString(stringTable[code]);
                oldCode = code;
            } else
            if(code < tableIndex)
            {
                byte string[] = stringTable[code];
                writeString(string);
                addStringToTable(stringTable[oldCode], string[0]);
                oldCode = code;
            } else
            {
                byte string[] = stringTable[oldCode];
                string = composeString(string, string[0]);
                writeString(string);
                addStringToTable(string);
                oldCode = code;
            }
        } while(true);
    }

    public void initializeStringTable()
    {
        stringTable = new byte[8192][];
        for(int i = 0; i < 256; i++)
        {
            stringTable[i] = new byte[1];
            stringTable[i][0] = (byte)i;
        }

        tableIndex = 258;
        bitsToGet = 9;
    }

    public void writeString(byte string[])
    {
        try
        {
            uncompData.write(string);
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public void addStringToTable(byte oldString[], byte newString)
    {
        int length = oldString.length;
        byte string[] = new byte[length + 1];
        System.arraycopy(oldString, 0, string, 0, length);
        string[length] = newString;
        stringTable[tableIndex++] = string;
        if(tableIndex == 511)
            bitsToGet = 10;
        else
        if(tableIndex == 1023)
            bitsToGet = 11;
        else
        if(tableIndex == 2047)
            bitsToGet = 12;
    }

    public void addStringToTable(byte string[])
    {
        stringTable[tableIndex++] = string;
        if(tableIndex == 511)
            bitsToGet = 10;
        else
        if(tableIndex == 1023)
            bitsToGet = 11;
        else
        if(tableIndex == 2047)
            bitsToGet = 12;
    }

    public byte[] composeString(byte oldString[], byte newString)
    {
        int length = oldString.length;
        byte string[] = new byte[length + 1];
        System.arraycopy(oldString, 0, string, 0, length);
        string[length] = newString;
        return string;
    }

    public int getNextCode()
    {
        try
        {
            nextData = nextData << 8 | data[bytePointer++] & 0xff;
            nextBits += 8;
            if(nextBits < bitsToGet)
            {
                nextData = nextData << 8 | data[bytePointer++] & 0xff;
                nextBits += 8;
            }
            int code = nextData >> nextBits - bitsToGet & andTable[bitsToGet - 9];
            nextBits -= bitsToGet;
            return code;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return 257;
        }
    }

    byte stringTable[][];
    byte data[];
    OutputStream uncompData;
    int tableIndex;
    int bitsToGet;
    int bytePointer;
    int bitPointer;
    int nextData;
    int nextBits;
    int andTable[] = {
        511, 1023, 2047, 4095
    };
}
