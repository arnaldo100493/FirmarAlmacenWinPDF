// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BitVector.java

package co.com.pdf.text.pdf.qrcode;


public final class BitVector
{

    public BitVector()
    {
        sizeInBits = 0;
        array = new byte[32];
    }

    public int at(int index)
    {
        if(index < 0 || index >= sizeInBits)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad index: ").append(index).toString());
        } else
        {
            int value = array[index >> 3] & 0xff;
            return value >> 7 - (index & 7) & 1;
        }
    }

    public int size()
    {
        return sizeInBits;
    }

    public int sizeInBytes()
    {
        return sizeInBits + 7 >> 3;
    }

    public void appendBit(int bit)
    {
        if(bit != 0 && bit != 1)
            throw new IllegalArgumentException("Bad bit");
        int numBitsInLastByte = sizeInBits & 7;
        if(numBitsInLastByte == 0)
        {
            appendByte(0);
            sizeInBits -= 8;
        }
        array[sizeInBits >> 3] |= bit << 7 - numBitsInLastByte;
        sizeInBits++;
    }

    public void appendBits(int value, int numBits)
    {
        if(numBits < 0 || numBits > 32)
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        for(int numBitsLeft = numBits; numBitsLeft > 0;)
            if((sizeInBits & 7) == 0 && numBitsLeft >= 8)
            {
                int newByte = value >> numBitsLeft - 8 & 0xff;
                appendByte(newByte);
                numBitsLeft -= 8;
            } else
            {
                int bit = value >> numBitsLeft - 1 & 1;
                appendBit(bit);
                numBitsLeft--;
            }

    }

    public void appendBitVector(BitVector bits)
    {
        int size = bits.size();
        for(int i = 0; i < size; i++)
            appendBit(bits.at(i));

    }

    public void xor(BitVector other)
    {
        if(sizeInBits != other.size())
            throw new IllegalArgumentException("BitVector sizes don't match");
        int sizeInBytes = sizeInBits + 7 >> 3;
        for(int i = 0; i < sizeInBytes; i++)
            array[i] ^= other.array[i];

    }

    public String toString()
    {
        StringBuffer result = new StringBuffer(sizeInBits);
        for(int i = 0; i < sizeInBits; i++)
        {
            if(at(i) == 0)
            {
                result.append('0');
                continue;
            }
            if(at(i) == 1)
                result.append('1');
            else
                throw new IllegalArgumentException("Byte isn't 0 or 1");
        }

        return result.toString();
    }

    public byte[] getArray()
    {
        return array;
    }

    private void appendByte(int value)
    {
        if(sizeInBits >> 3 == array.length)
        {
            byte newArray[] = new byte[array.length << 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[sizeInBits >> 3] = (byte)value;
        sizeInBits += 8;
    }

    private int sizeInBits;
    private byte array[];
    private static final int DEFAULT_SIZE_IN_BYTES = 32;
}
