// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBEParametersGenerator.java

package co.org.bouncy.crypto;

import co.org.bouncy.util.Strings;

// Referenced classes of package co.org.bouncy.crypto:
//            CipherParameters

public abstract class PBEParametersGenerator
{

    protected PBEParametersGenerator()
    {
    }

    public void init(byte password[], byte salt[], int iterationCount)
    {
        this.password = password;
        this.salt = salt;
        this.iterationCount = iterationCount;
    }

    public byte[] getPassword()
    {
        return password;
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public int getIterationCount()
    {
        return iterationCount;
    }

    public abstract CipherParameters generateDerivedParameters(int i);

    public abstract CipherParameters generateDerivedParameters(int i, int j);

    public abstract CipherParameters generateDerivedMacParameters(int i);

    public static byte[] PKCS5PasswordToBytes(char password[])
    {
        if(password != null)
        {
            byte bytes[] = new byte[password.length];
            for(int i = 0; i != bytes.length; i++)
                bytes[i] = (byte)password[i];

            return bytes;
        } else
        {
            return new byte[0];
        }
    }

    public static byte[] PKCS5PasswordToUTF8Bytes(char password[])
    {
        if(password != null)
            return Strings.toUTF8ByteArray(password);
        else
            return new byte[0];
    }

    public static byte[] PKCS12PasswordToBytes(char password[])
    {
        if(password != null && password.length > 0)
        {
            byte bytes[] = new byte[(password.length + 1) * 2];
            for(int i = 0; i != password.length; i++)
            {
                bytes[i * 2] = (byte)(password[i] >>> 8);
                bytes[i * 2 + 1] = (byte)password[i];
            }

            return bytes;
        } else
        {
            return new byte[0];
        }
    }

    protected byte password[];
    protected byte salt[];
    protected int iterationCount;
}
