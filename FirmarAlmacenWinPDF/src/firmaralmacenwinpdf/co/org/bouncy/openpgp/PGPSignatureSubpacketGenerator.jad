// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSignatureSubpacketGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.SignatureSubpacket;
import co.org.bouncy.bcpg.sig.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPSignatureSubpacketVector, PGPSignature

public class PGPSignatureSubpacketGenerator
{

    public PGPSignatureSubpacketGenerator()
    {
        list = new ArrayList();
    }

    public void setRevocable(boolean isCritical, boolean isRevocable)
    {
        list.add(new Revocable(isCritical, isRevocable));
    }

    public void setExportable(boolean isCritical, boolean isExportable)
    {
        list.add(new Exportable(isCritical, isExportable));
    }

    public void setFeature(boolean isCritical, byte feature)
    {
        list.add(new Features(isCritical, feature));
    }

    public void setTrust(boolean isCritical, int depth, int trustAmount)
    {
        list.add(new TrustSignature(isCritical, depth, trustAmount));
    }

    public void setKeyExpirationTime(boolean isCritical, long seconds)
    {
        list.add(new KeyExpirationTime(isCritical, seconds));
    }

    public void setSignatureExpirationTime(boolean isCritical, long seconds)
    {
        list.add(new SignatureExpirationTime(isCritical, seconds));
    }

    public void setSignatureCreationTime(boolean isCritical, Date date)
    {
        list.add(new SignatureCreationTime(isCritical, date));
    }

    public void setPreferredHashAlgorithms(boolean isCritical, int algorithms[])
    {
        list.add(new PreferredAlgorithms(21, isCritical, algorithms));
    }

    public void setPreferredSymmetricAlgorithms(boolean isCritical, int algorithms[])
    {
        list.add(new PreferredAlgorithms(11, isCritical, algorithms));
    }

    public void setPreferredCompressionAlgorithms(boolean isCritical, int algorithms[])
    {
        list.add(new PreferredAlgorithms(22, isCritical, algorithms));
    }

    public void setKeyFlags(boolean isCritical, int flags)
    {
        list.add(new KeyFlags(isCritical, flags));
    }

    public void setSignerUserID(boolean isCritical, String userID)
    {
        if(userID == null)
        {
            throw new IllegalArgumentException("attempt to set null SignerUserID");
        } else
        {
            list.add(new SignerUserID(isCritical, userID));
            return;
        }
    }

    public void setEmbeddedSignature(boolean isCritical, PGPSignature pgpSignature)
        throws IOException
    {
        byte sig[] = pgpSignature.getEncoded();
        byte data[];
        if(sig.length - 1 > 256)
            data = new byte[sig.length - 3];
        else
            data = new byte[sig.length - 2];
        System.arraycopy(sig, sig.length - data.length, data, 0, data.length);
        list.add(new EmbeddedSignature(isCritical, data));
    }

    public void setPrimaryUserID(boolean isCritical, boolean isPrimaryUserID)
    {
        list.add(new PrimaryUserID(isCritical, isPrimaryUserID));
    }

    public void setNotationData(boolean isCritical, boolean isHumanReadable, String notationName, String notationValue)
    {
        list.add(new NotationData(isCritical, isHumanReadable, notationName, notationValue));
    }

    public void setRevocationReason(boolean isCritical, byte reason, String description)
    {
        list.add(new RevocationReason(isCritical, reason, description));
    }

    public void setRevocationKey(boolean isCritical, int keyAlgorithm, byte fingerprint[])
    {
        list.add(new RevocationKey(isCritical, (byte)-128, keyAlgorithm, fingerprint));
    }

    public void setIssuerKeyID(boolean isCritical, long keyID)
    {
        list.add(new IssuerKeyID(isCritical, keyID));
    }

    public PGPSignatureSubpacketVector generate()
    {
        return new PGPSignatureSubpacketVector((SignatureSubpacket[])(SignatureSubpacket[])list.toArray(new SignatureSubpacket[list.size()]));
    }

    List list;
}
