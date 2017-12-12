// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KEKRecipientId.java

package co.org.bouncy.cms;

import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientId, KEKRecipientInformation

public class KEKRecipientId extends RecipientId
{

    public KEKRecipientId(byte keyIdentifier[])
    {
        super(1);
        this.keyIdentifier = keyIdentifier;
    }

    public int hashCode()
    {
        return Arrays.hashCode(keyIdentifier);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof KEKRecipientId))
        {
            return false;
        } else
        {
            KEKRecipientId id = (KEKRecipientId)o;
            return Arrays.areEqual(keyIdentifier, id.keyIdentifier);
        }
    }

    public byte[] getKeyIdentifier()
    {
        return Arrays.clone(keyIdentifier);
    }

    public Object clone()
    {
        return new KEKRecipientId(keyIdentifier);
    }

    public boolean match(Object obj)
    {
        if(obj instanceof byte[])
            return Arrays.areEqual(keyIdentifier, (byte[])(byte[])obj);
        if(obj instanceof KEKRecipientInformation)
            return ((KEKRecipientInformation)obj).getRID().equals(this);
        else
            return false;
    }

    private byte keyIdentifier[];
}
