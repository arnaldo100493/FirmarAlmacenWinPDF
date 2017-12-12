// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSubpacketInputStream.java

package co.org.bouncy.bcpg;

import co.org.bouncy.bcpg.sig.Exportable;
import co.org.bouncy.bcpg.sig.IssuerKeyID;
import co.org.bouncy.bcpg.sig.KeyExpirationTime;
import co.org.bouncy.bcpg.sig.KeyFlags;
import co.org.bouncy.bcpg.sig.NotationData;
import co.org.bouncy.bcpg.sig.PreferredAlgorithms;
import co.org.bouncy.bcpg.sig.PrimaryUserID;
import co.org.bouncy.bcpg.sig.Revocable;
import co.org.bouncy.bcpg.sig.SignatureCreationTime;
import co.org.bouncy.bcpg.sig.SignatureExpirationTime;
import co.org.bouncy.bcpg.sig.SignerUserID;
import co.org.bouncy.bcpg.sig.TrustSignature;
import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.bcpg:
//            SignatureSubpacket, SignatureSubpacketTags

public class SignatureSubpacketInputStream extends InputStream
    implements SignatureSubpacketTags
{

    public SignatureSubpacketInputStream(InputStream in)
    {
        this.in = in;
    }

    public int available()
        throws IOException
    {
        return in.available();
    }

    public int read()
        throws IOException
    {
        return in.read();
    }

    public SignatureSubpacket readPacket()
        throws IOException
    {
        int l = read();
        int bodyLen = 0;
        if(l < 0)
            return null;
        if(l < 192)
            bodyLen = l;
        else
        if(l <= 223)
            bodyLen = (l - 192 << 8) + in.read() + 192;
        else
        if(l == 255)
            bodyLen = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
        int tag = in.read();
        if(tag < 0)
            throw new EOFException("unexpected EOF reading signature sub packet");
        byte data[] = new byte[bodyLen - 1];
        if(Streams.readFully(in, data) < data.length)
            throw new EOFException();
        boolean isCritical = (tag & 0x80) != 0;
        int type = tag & 0x7f;
        switch(type)
        {
        case 2: // '\002'
            return new SignatureCreationTime(isCritical, data);

        case 9: // '\t'
            return new KeyExpirationTime(isCritical, data);

        case 3: // '\003'
            return new SignatureExpirationTime(isCritical, data);

        case 7: // '\007'
            return new Revocable(isCritical, data);

        case 4: // '\004'
            return new Exportable(isCritical, data);

        case 16: // '\020'
            return new IssuerKeyID(isCritical, data);

        case 5: // '\005'
            return new TrustSignature(isCritical, data);

        case 11: // '\013'
        case 21: // '\025'
        case 22: // '\026'
            return new PreferredAlgorithms(type, isCritical, data);

        case 27: // '\033'
            return new KeyFlags(isCritical, data);

        case 25: // '\031'
            return new PrimaryUserID(isCritical, data);

        case 28: // '\034'
            return new SignerUserID(isCritical, data);

        case 20: // '\024'
            return new NotationData(isCritical, data);

        case 6: // '\006'
        case 8: // '\b'
        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 23: // '\027'
        case 24: // '\030'
        case 26: // '\032'
        default:
            return new SignatureSubpacket(type, isCritical, data);
        }
    }

    InputStream in;
}
