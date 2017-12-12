// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TIFFLZWDecoder.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;

public class TIFFLZWDecoder
{

    public TIFFLZWDecoder(int w, int predictor, int samplesPerPixel)
    {
        data = null;
        bitsToGet = 9;
        nextData = 0;
        nextBits = 0;
        this.w = w;
        this.predictor = predictor;
        this.samplesPerPixel = samplesPerPixel;
    }

    public byte[] decode(byte data[], byte uncompData[], int h)
    {
        if(data[0] == 0 && data[1] == 1)
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("tiff.5.0.style.lzw.codes.are.not.supported", new Object[0]));
        initializeStringTable();
        this.data = data;
        this.h = h;
        this.uncompData = uncompData;
        bytePointer = 0;
        bitPointer = 0;
        dstIndex = 0;
        nextData = 0;
        nextBits = 0;
        int oldCode = 0;
        do
        {
            int code;
            if((code = getNextCode()) == 257 || dstIndex >= uncompData.length)
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
        if(predictor == 2)
        {
            for(int j = 0; j < h; j++)
            {
                int count = samplesPerPixel * (j * w + 1);
                for(int i = samplesPerPixel; i < w * samplesPerPixel; i++)
                {
                    uncompData[count] += uncompData[count - samplesPerPixel];
                    count++;
                }

            }

        }
        return uncompData;
    }

    public void initializeStringTable()
    {
        stringTable = new byte[4096][];
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
        int max = uncompData.length - dstIndex;
        if(string.length < max)
            max = string.length;
        System.arraycopy(string, 0, uncompData, dstIndex, max);
        dstIndex += max;
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
    byte uncompData[];
    int tableIndex;
    int bitsToGet;
    int bytePointer;
    int bitPointer;
    int dstIndex;
    int w;
    int h;
    int predictor;
    int samplesPerPixel;
    int nextData;
    int nextBits;
    int andTable[] = {
        511, 1023, 2047, 4095
    };
}
