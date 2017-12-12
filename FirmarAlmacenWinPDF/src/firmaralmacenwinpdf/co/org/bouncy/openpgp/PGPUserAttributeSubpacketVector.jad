// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPUserAttributeSubpacketVector.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.UserAttributeSubpacket;
import co.org.bouncy.bcpg.attr.ImageAttribute;

public class PGPUserAttributeSubpacketVector
{

    PGPUserAttributeSubpacketVector(UserAttributeSubpacket packets[])
    {
        this.packets = packets;
    }

    public UserAttributeSubpacket getSubpacket(int type)
    {
        for(int i = 0; i != packets.length; i++)
            if(packets[i].getType() == type)
                return packets[i];

        return null;
    }

    public ImageAttribute getImageAttribute()
    {
        UserAttributeSubpacket p = getSubpacket(1);
        if(p == null)
            return null;
        else
            return (ImageAttribute)p;
    }

    UserAttributeSubpacket[] toSubpacketArray()
    {
        return packets;
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o instanceof PGPUserAttributeSubpacketVector)
        {
            PGPUserAttributeSubpacketVector other = (PGPUserAttributeSubpacketVector)o;
            if(other.packets.length != packets.length)
                return false;
            for(int i = 0; i != packets.length; i++)
                if(!other.packets[i].equals(packets[i]))
                    return false;

            return true;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int code = 0;
        for(int i = 0; i != packets.length; i++)
            code ^= packets[i].hashCode();

        return code;
    }

    UserAttributeSubpacket packets[];
}
