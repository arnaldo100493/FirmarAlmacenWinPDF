// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPKeyRing.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import java.io.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPSignature, PGPException, PGPUserAttributeSubpacketVector, PGPPublicKey

public abstract class PGPKeyRing
{

    PGPKeyRing()
    {
    }

    static BCPGInputStream wrap(InputStream in)
    {
        if(in instanceof BCPGInputStream)
            return (BCPGInputStream)in;
        else
            return new BCPGInputStream(in);
    }

    static TrustPacket readOptionalTrustPacket(BCPGInputStream pIn)
        throws IOException
    {
        return pIn.nextPacketTag() != 12 ? null : (TrustPacket)pIn.readPacket();
    }

    static List readSignaturesAndTrust(BCPGInputStream pIn)
        throws IOException
    {
        try
        {
            List sigList = new ArrayList();
            SignaturePacket signaturePacket;
            TrustPacket trustPacket;
            for(; pIn.nextPacketTag() == 2; sigList.add(new PGPSignature(signaturePacket, trustPacket)))
            {
                signaturePacket = (SignaturePacket)pIn.readPacket();
                trustPacket = readOptionalTrustPacket(pIn);
            }

            return sigList;
        }
        catch(PGPException e)
        {
            throw new IOException((new StringBuilder()).append("can't create signature object: ").append(e.getMessage()).append(", cause: ").append(e.getUnderlyingException().toString()).toString());
        }
    }

    static void readUserIDs(BCPGInputStream pIn, List ids, List idTrusts, List idSigs)
        throws IOException
    {
        for(; pIn.nextPacketTag() == 13 || pIn.nextPacketTag() == 17; idSigs.add(readSignaturesAndTrust(pIn)))
        {
            Packet obj = pIn.readPacket();
            if(obj instanceof UserIDPacket)
            {
                UserIDPacket id = (UserIDPacket)obj;
                ids.add(id.getID());
            } else
            {
                UserAttributePacket user = (UserAttributePacket)obj;
                ids.add(new PGPUserAttributeSubpacketVector(user.getSubpackets()));
            }
            idTrusts.add(readOptionalTrustPacket(pIn));
        }

    }

    public abstract PGPPublicKey getPublicKey();

    public abstract Iterator getPublicKeys();

    public abstract PGPPublicKey getPublicKey(long l);

    public abstract void encode(OutputStream outputstream)
        throws IOException;

    public abstract byte[] getEncoded()
        throws IOException;
}
