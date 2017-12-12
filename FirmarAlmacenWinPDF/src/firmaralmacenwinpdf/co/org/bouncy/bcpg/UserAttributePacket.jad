// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserAttributePacket.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, UserAttributeSubpacketInputStream, UserAttributeSubpacket, BCPGInputStream, 
//            BCPGOutputStream

public class UserAttributePacket extends ContainedPacket
{

    public UserAttributePacket(BCPGInputStream in)
        throws IOException
    {
        UserAttributeSubpacketInputStream sIn = new UserAttributeSubpacketInputStream(in);
        Vector v = new Vector();
        UserAttributeSubpacket sub;
        while((sub = sIn.readPacket()) != null) 
            v.addElement(sub);
        subpackets = new UserAttributeSubpacket[v.size()];
        for(int i = 0; i != subpackets.length; i++)
            subpackets[i] = (UserAttributeSubpacket)v.elementAt(i);

    }

    public UserAttributePacket(UserAttributeSubpacket subpackets[])
    {
        this.subpackets = subpackets;
    }

    public UserAttributeSubpacket[] getSubpackets()
    {
        return subpackets;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        for(int i = 0; i != subpackets.length; i++)
            subpackets[i].encode(bOut);

        out.writePacket(17, bOut.toByteArray(), false);
    }

    private UserAttributeSubpacket subpackets[];
}
