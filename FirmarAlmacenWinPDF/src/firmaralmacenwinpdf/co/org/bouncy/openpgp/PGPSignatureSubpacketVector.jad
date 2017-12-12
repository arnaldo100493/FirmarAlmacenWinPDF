// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSignatureSubpacketVector.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.SignatureSubpacket;
import co.org.bouncy.bcpg.sig.*;
import java.util.*;

public class PGPSignatureSubpacketVector
{

    PGPSignatureSubpacketVector(SignatureSubpacket packets[])
    {
        this.packets = packets;
    }

    public SignatureSubpacket getSubpacket(int type)
    {
        for(int i = 0; i != packets.length; i++)
            if(packets[i].getType() == type)
                return packets[i];

        return null;
    }

    public boolean hasSubpacket(int type)
    {
        return getSubpacket(type) != null;
    }

    public SignatureSubpacket[] getSubpackets(int type)
    {
        List list = new ArrayList();
        for(int i = 0; i != packets.length; i++)
            if(packets[i].getType() == type)
                list.add(packets[i]);

        return (SignatureSubpacket[])(SignatureSubpacket[])list.toArray(new SignatureSubpacket[0]);
    }

    public NotationData[] getNotationDataOccurences()
    {
        SignatureSubpacket notations[] = getSubpackets(20);
        NotationData vals[] = new NotationData[notations.length];
        for(int i = 0; i < notations.length; i++)
            vals[i] = (NotationData)notations[i];

        return vals;
    }

    public long getIssuerKeyID()
    {
        SignatureSubpacket p = getSubpacket(16);
        if(p == null)
            return 0L;
        else
            return ((IssuerKeyID)p).getKeyID();
    }

    public Date getSignatureCreationTime()
    {
        SignatureSubpacket p = getSubpacket(2);
        if(p == null)
            return null;
        else
            return ((SignatureCreationTime)p).getTime();
    }

    public long getSignatureExpirationTime()
    {
        SignatureSubpacket p = getSubpacket(3);
        if(p == null)
            return 0L;
        else
            return ((SignatureExpirationTime)p).getTime();
    }

    public long getKeyExpirationTime()
    {
        SignatureSubpacket p = getSubpacket(9);
        if(p == null)
            return 0L;
        else
            return ((KeyExpirationTime)p).getTime();
    }

    public int[] getPreferredHashAlgorithms()
    {
        SignatureSubpacket p = getSubpacket(21);
        if(p == null)
            return null;
        else
            return ((PreferredAlgorithms)p).getPreferences();
    }

    public int[] getPreferredSymmetricAlgorithms()
    {
        SignatureSubpacket p = getSubpacket(11);
        if(p == null)
            return null;
        else
            return ((PreferredAlgorithms)p).getPreferences();
    }

    public int[] getPreferredCompressionAlgorithms()
    {
        SignatureSubpacket p = getSubpacket(22);
        if(p == null)
            return null;
        else
            return ((PreferredAlgorithms)p).getPreferences();
    }

    public int getKeyFlags()
    {
        SignatureSubpacket p = getSubpacket(27);
        if(p == null)
            return 0;
        else
            return ((KeyFlags)p).getFlags();
    }

    public String getSignerUserID()
    {
        SignatureSubpacket p = getSubpacket(28);
        if(p == null)
            return null;
        else
            return ((SignerUserID)p).getID();
    }

    public boolean isPrimaryUserID()
    {
        PrimaryUserID primaryId = (PrimaryUserID)getSubpacket(25);
        if(primaryId != null)
            return primaryId.isPrimaryUserID();
        else
            return false;
    }

    public int[] getCriticalTags()
    {
        int count = 0;
        for(int i = 0; i != packets.length; i++)
            if(packets[i].isCritical())
                count++;

        int list[] = new int[count];
        count = 0;
        for(int i = 0; i != packets.length; i++)
            if(packets[i].isCritical())
                list[count++] = packets[i].getType();

        return list;
    }

    public Features getFeatures()
    {
        SignatureSubpacket p = getSubpacket(30);
        if(p == null)
            return null;
        else
            return new Features(p.isCritical(), p.getData());
    }

    public int size()
    {
        return packets.length;
    }

    SignatureSubpacket[] toSubpacketArray()
    {
        return packets;
    }

    SignatureSubpacket packets[];
}
