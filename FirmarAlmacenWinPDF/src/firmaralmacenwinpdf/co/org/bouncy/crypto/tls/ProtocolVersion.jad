// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProtocolVersion.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert

public final class ProtocolVersion
{

    private ProtocolVersion(int v, String name)
    {
        version = v & 0xffff;
        this.name = name;
    }

    public int getFullVersion()
    {
        return version;
    }

    public int getMajorVersion()
    {
        return version >> 8;
    }

    public int getMinorVersion()
    {
        return version & 0xff;
    }

    public boolean isDTLS()
    {
        return getMajorVersion() == 254;
    }

    public boolean isSSL()
    {
        return this == SSLv3;
    }

    public ProtocolVersion getEquivalentTLSVersion()
    {
        if(!isDTLS())
            return this;
        if(this == DTLSv10)
            return TLSv11;
        else
            return TLSv12;
    }

    public boolean isEqualOrEarlierVersionOf(ProtocolVersion version)
    {
        if(getMajorVersion() != version.getMajorVersion())
        {
            return false;
        } else
        {
            int diffMinorVersion = version.getMinorVersion() - getMinorVersion();
            return isDTLS() ? diffMinorVersion <= 0 : diffMinorVersion >= 0;
        }
    }

    public boolean isLaterVersionOf(ProtocolVersion version)
    {
        if(getMajorVersion() != version.getMajorVersion())
        {
            return false;
        } else
        {
            int diffMinorVersion = version.getMinorVersion() - getMinorVersion();
            return isDTLS() ? diffMinorVersion > 0 : diffMinorVersion < 0;
        }
    }

    public boolean equals(Object obj)
    {
        return this == obj;
    }

    public int hashCode()
    {
        return version;
    }

    public static ProtocolVersion get(int major, int minor)
        throws IOException
    {
        switch(major)
        {
        case 3: // '\003'
            switch(minor)
            {
            case 0: // '\0'
                return SSLv3;

            case 1: // '\001'
                return TLSv10;

            case 2: // '\002'
                return TLSv11;

            case 3: // '\003'
                return TLSv12;
            }
            // fall through

        case 254: 
            switch(minor)
            {
            case 255: 
                return DTLSv10;

            case 253: 
                return DTLSv12;
            }
            // fall through

        default:
            throw new TlsFatalAlert((short)47);
        }
    }

    public String toString()
    {
        return name;
    }

    public static final ProtocolVersion SSLv3 = new ProtocolVersion(768, "SSL 3.0");
    public static final ProtocolVersion TLSv10 = new ProtocolVersion(769, "TLS 1.0");
    public static final ProtocolVersion TLSv11 = new ProtocolVersion(770, "TLS 1.1");
    public static final ProtocolVersion TLSv12 = new ProtocolVersion(771, "TLS 1.2");
    public static final ProtocolVersion DTLSv10 = new ProtocolVersion(65279, "DTLS 1.0");
    public static final ProtocolVersion DTLSv12 = new ProtocolVersion(65277, "DTLS 1.2");
    private int version;
    private String name;

}
