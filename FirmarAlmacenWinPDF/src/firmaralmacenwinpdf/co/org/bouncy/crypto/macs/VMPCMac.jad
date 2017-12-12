// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VMPCMac.java

package co.org.bouncy.crypto.macs;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class VMPCMac
    implements Mac
{

    public VMPCMac()
    {
        n = 0;
        P = null;
        s = 0;
    }

    public int doFinal(byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        for(int r = 1; r < 25; r++)
        {
            s = P[s + P[n & 0xff] & 0xff];
            x4 = P[x4 + x3 + r & 0xff];
            x3 = P[x3 + x2 + r & 0xff];
            x2 = P[x2 + x1 + r & 0xff];
            x1 = P[x1 + s + r & 0xff];
            T[g & 0x1f] = (byte)(T[g & 0x1f] ^ x1);
            T[g + 1 & 0x1f] = (byte)(T[g + 1 & 0x1f] ^ x2);
            T[g + 2 & 0x1f] = (byte)(T[g + 2 & 0x1f] ^ x3);
            T[g + 3 & 0x1f] = (byte)(T[g + 3 & 0x1f] ^ x4);
            g = (byte)(g + 4 & 0x1f);
            byte temp = P[n & 0xff];
            P[n & 0xff] = P[s & 0xff];
            P[s & 0xff] = temp;
            n = (byte)(n + 1 & 0xff);
        }

        for(int m = 0; m < 768; m++)
        {
            s = P[s + P[m & 0xff] + T[m & 0x1f] & 0xff];
            byte temp = P[m & 0xff];
            P[m & 0xff] = P[s & 0xff];
            P[s & 0xff] = temp;
        }

        byte M[] = new byte[20];
        for(int i = 0; i < 20; i++)
        {
            s = P[s + P[i & 0xff] & 0xff];
            M[i] = P[P[P[s & 0xff] & 0xff] + 1 & 0xff];
            byte temp = P[i & 0xff];
            P[i & 0xff] = P[s & 0xff];
            P[s & 0xff] = temp;
        }

        System.arraycopy(M, 0, out, outOff, M.length);
        reset();
        return M.length;
    }

    public String getAlgorithmName()
    {
        return "VMPC-MAC";
    }

    public int getMacSize()
    {
        return 20;
    }

    public void init(CipherParameters params)
        throws IllegalArgumentException
    {
        if(!(params instanceof ParametersWithIV))
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        ParametersWithIV ivParams = (ParametersWithIV)params;
        KeyParameter key = (KeyParameter)ivParams.getParameters();
        if(!(ivParams.getParameters() instanceof KeyParameter))
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        workingIV = ivParams.getIV();
        if(workingIV == null || workingIV.length < 1 || workingIV.length > 768)
        {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        } else
        {
            workingKey = key.getKey();
            reset();
            return;
        }
    }

    private void initKey(byte keyBytes[], byte ivBytes[])
    {
        s = 0;
        P = new byte[256];
        for(int i = 0; i < 256; i++)
            P[i] = (byte)i;

        for(int m = 0; m < 768; m++)
        {
            s = P[s + P[m & 0xff] + keyBytes[m % keyBytes.length] & 0xff];
            byte temp = P[m & 0xff];
            P[m & 0xff] = P[s & 0xff];
            P[s & 0xff] = temp;
        }

        for(int m = 0; m < 768; m++)
        {
            s = P[s + P[m & 0xff] + ivBytes[m % ivBytes.length] & 0xff];
            byte temp = P[m & 0xff];
            P[m & 0xff] = P[s & 0xff];
            P[s & 0xff] = temp;
        }

        n = 0;
    }

    public void reset()
    {
        initKey(workingKey, workingIV);
        g = x1 = x2 = x3 = x4 = n = 0;
        T = new byte[32];
        for(int i = 0; i < 32; i++)
            T[i] = 0;

    }

    public void update(byte in)
        throws IllegalStateException
    {
        s = P[s + P[n & 0xff] & 0xff];
        byte c = (byte)(in ^ P[P[P[s & 0xff] & 0xff] + 1 & 0xff]);
        x4 = P[x4 + x3 & 0xff];
        x3 = P[x3 + x2 & 0xff];
        x2 = P[x2 + x1 & 0xff];
        x1 = P[x1 + s + c & 0xff];
        T[g & 0x1f] = (byte)(T[g & 0x1f] ^ x1);
        T[g + 1 & 0x1f] = (byte)(T[g + 1 & 0x1f] ^ x2);
        T[g + 2 & 0x1f] = (byte)(T[g + 2 & 0x1f] ^ x3);
        T[g + 3 & 0x1f] = (byte)(T[g + 3 & 0x1f] ^ x4);
        g = (byte)(g + 4 & 0x1f);
        byte temp = P[n & 0xff];
        P[n & 0xff] = P[s & 0xff];
        P[s & 0xff] = temp;
        n = (byte)(n + 1 & 0xff);
    }

    public void update(byte in[], int inOff, int len)
        throws DataLengthException, IllegalStateException
    {
        if(inOff + len > in.length)
            throw new DataLengthException("input buffer too short");
        for(int i = 0; i < len; i++)
            update(in[i]);

    }

    private byte g;
    private byte n;
    private byte P[];
    private byte s;
    private byte T[];
    private byte workingIV[];
    private byte workingKey[];
    private byte x1;
    private byte x2;
    private byte x3;
    private byte x4;
}
