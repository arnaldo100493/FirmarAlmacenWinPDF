// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESParameters.java

package co.org.bouncy.crypto.params;


// Referenced classes of package co.org.bouncy.crypto.params:
//            KeyParameter

public class DESParameters extends KeyParameter
{

    public DESParameters(byte key[])
    {
        super(key);
        if(isWeakKey(key, 0))
            throw new IllegalArgumentException("attempt to create weak DES key");
        else
            return;
    }

    public static boolean isWeakKey(byte key[], int offset)
    {
        if(key.length - offset < 8)
            throw new IllegalArgumentException("key material too short.");
        int i = 0;
label0:
        do
        {
label1:
            {
                if(i >= 16)
                    break label0;
                for(int j = 0; j < 8; j++)
                    if(key[j + offset] != DES_weak_keys[i * 8 + j])
                        break label1;

                return true;
            }
            i++;
        } while(true);
        return false;
    }

    public static void setOddParity(byte bytes[])
    {
        for(int i = 0; i < bytes.length; i++)
        {
            int b = bytes[i];
            bytes[i] = (byte)(b & 0xfe | (b >> 1 ^ b >> 2 ^ b >> 3 ^ b >> 4 ^ b >> 5 ^ b >> 6 ^ b >> 7 ^ 1) & 1);
        }

    }

    public static final int DES_KEY_LENGTH = 8;
    private static final int N_DES_WEAK_KEYS = 16;
    private static byte DES_weak_keys[] = {
        1, 1, 1, 1, 1, 1, 1, 1, 31, 31, 
        31, 31, 14, 14, 14, 14, -32, -32, -32, -32, 
        -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, 
        -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, 
        31, -32, 31, -32, 14, -15, 14, -15, 1, -32, 
        1, -32, 1, -15, 1, -15, 31, -2, 31, -2, 
        14, -2, 14, -2, 1, 31, 1, 31, 1, 14, 
        1, 14, -32, -2, -32, -2, -15, -2, -15, -2, 
        -2, 1, -2, 1, -2, 1, -2, 1, -32, 31, 
        -32, 31, -15, 14, -15, 14, -32, 1, -32, 1, 
        -15, 1, -15, 1, -2, 31, -2, 31, -2, 14, 
        -2, 14, 31, 1, 31, 1, 14, 1, 14, 1, 
        -2, -32, -2, -32, -2, -15, -2, -15
    };

}
