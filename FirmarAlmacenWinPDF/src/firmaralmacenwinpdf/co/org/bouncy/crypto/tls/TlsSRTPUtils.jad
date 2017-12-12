// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsSRTPUtils.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Integers;
import java.io.*;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, UseSRTPData, TlsUtils, TlsProtocol

public class TlsSRTPUtils
{

    public TlsSRTPUtils()
    {
    }

    public static void addUseSRTPExtension(Hashtable extensions, UseSRTPData useSRTPData)
        throws IOException
    {
        extensions.put(EXT_use_srtp, createUseSRTPExtension(useSRTPData));
    }

    public static UseSRTPData getUseSRTPExtension(Hashtable extensions)
        throws IOException
    {
        if(extensions == null)
            return null;
        byte extensionValue[] = (byte[])(byte[])extensions.get(EXT_use_srtp);
        if(extensionValue == null)
            return null;
        else
            return readUseSRTPExtension(extensionValue);
    }

    public static byte[] createUseSRTPExtension(UseSRTPData useSRTPData)
        throws IOException
    {
        if(useSRTPData == null)
        {
            throw new IllegalArgumentException("'useSRTPData' cannot be null");
        } else
        {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int protectionProfiles[] = useSRTPData.getProtectionProfiles();
            TlsUtils.writeUint16(2 * protectionProfiles.length, buf);
            TlsUtils.writeUint16Array(protectionProfiles, buf);
            TlsUtils.writeOpaque8(useSRTPData.getMki(), buf);
            return buf.toByteArray();
        }
    }

    public static UseSRTPData readUseSRTPExtension(byte extensionValue[])
        throws IOException
    {
        if(extensionValue == null)
            throw new IllegalArgumentException("'extensionValue' cannot be null");
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionValue);
        int length = TlsUtils.readUint16(buf);
        if(length < 2 || (length & 1) != 0)
        {
            throw new TlsFatalAlert((short)50);
        } else
        {
            int protectionProfiles[] = TlsUtils.readUint16Array(length / 2, buf);
            byte mki[] = TlsUtils.readOpaque8(buf);
            TlsProtocol.assertEmpty(buf);
            return new UseSRTPData(protectionProfiles, mki);
        }
    }

    public static final Integer EXT_use_srtp = Integers.valueOf(14);

}
