// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParametersWithSalt.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;

public class ParametersWithSalt
    implements CipherParameters
{

    public ParametersWithSalt(CipherParameters parameters, byte salt[])
    {
        this(parameters, salt, 0, salt.length);
    }

    public ParametersWithSalt(CipherParameters parameters, byte salt[], int saltOff, int saltLen)
    {
        this.salt = new byte[saltLen];
        this.parameters = parameters;
        System.arraycopy(salt, saltOff, this.salt, 0, saltLen);
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public CipherParameters getParameters()
    {
        return parameters;
    }

    private byte salt[];
    private CipherParameters parameters;
}
