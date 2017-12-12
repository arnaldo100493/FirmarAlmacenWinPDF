// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageImprint.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.x509.DigestInfo;

public class MessageImprint
{

    public MessageImprint(DigestInfo messageImprint)
    {
        this.messageImprint = messageImprint;
    }

    public DigestInfo toASN1Structure()
    {
        return messageImprint;
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(o instanceof MessageImprint)
            return messageImprint.equals(((MessageImprint)o).messageImprint);
        else
            return false;
    }

    public int hashCode()
    {
        return messageImprint.hashCode();
    }

    private final DigestInfo messageImprint;
}
