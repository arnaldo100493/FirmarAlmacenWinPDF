// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsMac.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.Mac;
import co.org.bouncy.crypto.digests.LongDigest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            SSL3Mac, TlsContext, ProtocolVersion, TlsUtils

public class TlsMac
{

    public TlsMac(TlsContext context, Digest digest, byte key[], int keyOff, int keyLen)
    {
        this.context = context;
        KeyParameter keyParameter = new KeyParameter(key, keyOff, keyLen);
        secret = Arrays.clone(keyParameter.getKey());
        if(digest instanceof LongDigest)
        {
            digestBlockSize = 128;
            digestOverhead = 16;
        } else
        {
            digestBlockSize = 64;
            digestOverhead = 8;
        }
        if(context.getServerVersion().isSSL())
        {
            mac = new SSL3Mac(digest);
            if(digest.getDigestSize() == 20)
                digestOverhead = 4;
        } else
        {
            mac = new HMac(digest);
        }
        mac.init(keyParameter);
    }

    public byte[] getMACSecret()
    {
        return secret;
    }

    public int getSize()
    {
        return mac.getMacSize();
    }

    public byte[] calculateMac(long seqNo, short type, byte message[], int offset, int length)
    {
        ProtocolVersion serverVersion = context.getServerVersion();
        boolean isSSL = serverVersion.isSSL();
        ByteArrayOutputStream bosMac = new ByteArrayOutputStream(isSSL ? 11 : 13);
        try
        {
            TlsUtils.writeUint64(seqNo, bosMac);
            TlsUtils.writeUint8(type, bosMac);
            if(!isSSL)
                TlsUtils.writeVersion(serverVersion, bosMac);
            TlsUtils.writeUint16(length, bosMac);
        }
        catch(IOException e)
        {
            throw new IllegalStateException("Internal error during mac calculation");
        }
        byte macHeader[] = bosMac.toByteArray();
        mac.update(macHeader, 0, macHeader.length);
        mac.update(message, offset, length);
        byte result[] = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }

    public byte[] calculateMacConstantTime(long seqNo, short type, byte message[], int offset, int length, int fullLength, 
            byte dummyData[])
    {
        byte result[] = calculateMac(seqNo, type, message, offset, length);
        int headerLength = context.getServerVersion().isSSL() ? 11 : 13;
        for(int extra = getDigestBlockCount(headerLength + fullLength) - getDigestBlockCount(headerLength + length); --extra >= 0;)
            mac.update(dummyData, 0, digestBlockSize);

        mac.update(dummyData[0]);
        mac.reset();
        return result;
    }

    private int getDigestBlockCount(int inputLength)
    {
        return (inputLength + digestOverhead) / digestBlockSize;
    }

    protected TlsContext context;
    protected byte secret[];
    protected Mac mac;
    protected int digestBlockSize;
    protected int digestOverhead;
}
