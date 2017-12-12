// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VMPCEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class VMPCEngine
    implements StreamCipher
{

    public VMPCEngine()
    {
        n = 0;
        P = null;
        s = 0;
    }

    public String getAlgorithmName()
    {
        return "VMPC";
    }

    public void init(boolean forEncryption, CipherParameters params)
    {
        if(!(params instanceof ParametersWithIV))
            throw new IllegalArgumentException("VMPC init parameters must include an IV");
        ParametersWithIV ivParams = (ParametersWithIV)params;
        KeyParameter key = (KeyParameter)ivParams.getParameters();
        if(!(ivParams.getParameters() instanceof KeyParameter))
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        workingIV = ivParams.getIV();
        if(workingIV == null || workingIV.length < 1 || workingIV.length > 768)
        {
            throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
        } else
        {
            workingKey = key.getKey();
            initKey(workingKey, workingIV);
            return;
        }
    }

    protected void initKey(byte keyBytes[], byte ivBytes[])
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

    public void processBytes(byte in[], int inOff, int len, byte out[], int outOff)
    {
        if(inOff + len > in.length)
            throw new DataLengthException("input buffer too short");
        if(outOff + len > out.length)
            throw new OutputLengthException("output buffer too short");
        for(int i = 0; i < len; i++)
        {
            s = P[s + P[n & 0xff] & 0xff];
            byte z = P[P[P[s & 0xff] & 0xff] + 1 & 0xff];
            byte temp = P[n & 0xff];
            P[n & 0xff] = P[s & 0xff];
            P[s & 0xff] = temp;
            n = (byte)(n + 1 & 0xff);
            out[i + outOff] = (byte)(in[i + inOff] ^ z);
        }

    }

    public void reset()
    {
        initKey(workingKey, workingIV);
    }

    public byte returnByte(byte in)
    {
        s = P[s + P[n & 0xff] & 0xff];
        byte z = P[P[P[s & 0xff] & 0xff] + 1 & 0xff];
        byte temp = P[n & 0xff];
        P[n & 0xff] = P[s & 0xff];
        P[s & 0xff] = temp;
        n = (byte)(n + 1 & 0xff);
        return (byte)(in ^ z);
    }

    protected byte n;
    protected byte P[];
    protected byte s;
    protected byte workingIV[];
    protected byte workingKey[];
}
