// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteUtils.java

package co.org.bouncy.pqc.math.linearalgebra;


public final class ByteUtils
{

    private ByteUtils()
    {
    }

    public static boolean equals(byte left[], byte right[])
    {
        if(left == null)
            return right == null;
        if(right == null)
            return false;
        if(left.length != right.length)
            return false;
        boolean result = true;
        for(int i = left.length - 1; i >= 0; i--)
            result &= left[i] == right[i];

        return result;
    }

    public static boolean equals(byte left[][], byte right[][])
    {
        if(left.length != right.length)
            return false;
        boolean result = true;
        for(int i = left.length - 1; i >= 0; i--)
            result &= equals(left[i], right[i]);

        return result;
    }

    public static boolean equals(byte left[][][], byte right[][][])
    {
        if(left.length != right.length)
            return false;
        boolean result = true;
        for(int i = left.length - 1; i >= 0; i--)
        {
            if(left[i].length != right[i].length)
                return false;
            for(int j = left[i].length - 1; j >= 0; j--)
                result &= equals(left[i][j], right[i][j]);

        }

        return result;
    }

    public static int deepHashCode(byte array[])
    {
        int result = 1;
        for(int i = 0; i < array.length; i++)
            result = 31 * result + array[i];

        return result;
    }

    public static int deepHashCode(byte array[][])
    {
        int result = 1;
        for(int i = 0; i < array.length; i++)
            result = 31 * result + deepHashCode(array[i]);

        return result;
    }

    public static int deepHashCode(byte array[][][])
    {
        int result = 1;
        for(int i = 0; i < array.length; i++)
            result = 31 * result + deepHashCode(array[i]);

        return result;
    }

    public static byte[] clone(byte array[])
    {
        if(array == null)
        {
            return null;
        } else
        {
            byte result[] = new byte[array.length];
            System.arraycopy(array, 0, result, 0, array.length);
            return result;
        }
    }

    public static byte[] fromHexString(String s)
    {
        char rawChars[] = s.toUpperCase().toCharArray();
        int hexChars = 0;
        for(int i = 0; i < rawChars.length; i++)
            if(rawChars[i] >= '0' && rawChars[i] <= '9' || rawChars[i] >= 'A' && rawChars[i] <= 'F')
                hexChars++;

        byte byteString[] = new byte[hexChars + 1 >> 1];
        int pos = hexChars & 1;
        for(int i = 0; i < rawChars.length; i++)
        {
            if(rawChars[i] >= '0' && rawChars[i] <= '9')
            {
                byteString[pos >> 1] <<= 4;
                byteString[pos >> 1] |= rawChars[i] - 48;
            } else
            {
                if(rawChars[i] < 'A' || rawChars[i] > 'F')
                    continue;
                byteString[pos >> 1] <<= 4;
                byteString[pos >> 1] |= (rawChars[i] - 65) + 10;
            }
            pos++;
        }

        return byteString;
    }

    public static String toHexString(byte input[])
    {
        String result = "";
        for(int i = 0; i < input.length; i++)
        {
            result = (new StringBuilder()).append(result).append(HEX_CHARS[input[i] >>> 4 & 0xf]).toString();
            result = (new StringBuilder()).append(result).append(HEX_CHARS[input[i] & 0xf]).toString();
        }

        return result;
    }

    public static String toHexString(byte input[], String prefix, String seperator)
    {
        String result = new String(prefix);
        for(int i = 0; i < input.length; i++)
        {
            result = (new StringBuilder()).append(result).append(HEX_CHARS[input[i] >>> 4 & 0xf]).toString();
            result = (new StringBuilder()).append(result).append(HEX_CHARS[input[i] & 0xf]).toString();
            if(i < input.length - 1)
                result = (new StringBuilder()).append(result).append(seperator).toString();
        }

        return result;
    }

    public static String toBinaryString(byte input[])
    {
        String result = "";
        for(int i = 0; i < input.length; i++)
        {
            int e = input[i];
            for(int ii = 0; ii < 8; ii++)
            {
                int b = e >>> ii & 1;
                result = (new StringBuilder()).append(result).append(b).toString();
            }

            if(i != input.length - 1)
                result = (new StringBuilder()).append(result).append(" ").toString();
        }

        return result;
    }

    public static byte[] xor(byte x1[], byte x2[])
    {
        byte out[] = new byte[x1.length];
        for(int i = x1.length - 1; i >= 0; i--)
            out[i] = (byte)(x1[i] ^ x2[i]);

        return out;
    }

    public static byte[] concatenate(byte x1[], byte x2[])
    {
        byte result[] = new byte[x1.length + x2.length];
        System.arraycopy(x1, 0, result, 0, x1.length);
        System.arraycopy(x2, 0, result, x1.length, x2.length);
        return result;
    }

    public static byte[] concatenate(byte array[][])
    {
        int rowLength = array[0].length;
        byte result[] = new byte[array.length * rowLength];
        int index = 0;
        for(int i = 0; i < array.length; i++)
        {
            System.arraycopy(array[i], 0, result, index, rowLength);
            index += rowLength;
        }

        return result;
    }

    public static byte[][] split(byte input[], int index)
        throws ArrayIndexOutOfBoundsException
    {
        if(index > input.length)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            byte result[][] = new byte[2][];
            result[0] = new byte[index];
            result[1] = new byte[input.length - index];
            System.arraycopy(input, 0, result[0], 0, index);
            System.arraycopy(input, index, result[1], 0, input.length - index);
            return result;
        }
    }

    public static byte[] subArray(byte input[], int start, int end)
    {
        byte result[] = new byte[end - start];
        System.arraycopy(input, start, result, 0, end - start);
        return result;
    }

    public static byte[] subArray(byte input[], int start)
    {
        return subArray(input, start, input.length);
    }

    public static char[] toCharArray(byte input[])
    {
        char result[] = new char[input.length];
        for(int i = 0; i < input.length; i++)
            result[i] = (char)input[i];

        return result;
    }

    private static final char HEX_CHARS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

}
