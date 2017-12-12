// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPEncryptedDataList.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPPBEEncryptedData, PGPPublicKeyEncryptedData

public class PGPEncryptedDataList
{

    public PGPEncryptedDataList(BCPGInputStream pIn)
        throws IOException
    {
        list = new ArrayList();
        for(; pIn.nextPacketTag() == 1 || pIn.nextPacketTag() == 3; list.add(pIn.readPacket()));
        data = (InputStreamPacket)pIn.readPacket();
        for(int i = 0; i != list.size(); i++)
            if(list.get(i) instanceof SymmetricKeyEncSessionPacket)
                list.set(i, new PGPPBEEncryptedData((SymmetricKeyEncSessionPacket)list.get(i), data));
            else
                list.set(i, new PGPPublicKeyEncryptedData((PublicKeyEncSessionPacket)list.get(i), data));

    }

    public Object get(int index)
    {
        return list.get(index);
    }

    public int size()
    {
        return list.size();
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * @deprecated Method getEncyptedDataObjects is deprecated
     */

    public Iterator getEncyptedDataObjects()
    {
        return list.iterator();
    }

    public Iterator getEncryptedDataObjects()
    {
        return list.iterator();
    }

    List list;
    InputStreamPacket data;
}
