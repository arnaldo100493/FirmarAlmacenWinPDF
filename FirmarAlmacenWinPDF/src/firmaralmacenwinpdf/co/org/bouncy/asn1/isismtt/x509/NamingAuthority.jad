// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamingAuthority.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.isismtt.ISISMTTObjectIdentifiers;
import co.org.bouncy.asn1.x500.DirectoryString;
import java.util.Enumeration;

public class NamingAuthority extends ASN1Object
{

    public static NamingAuthority getInstance(Object obj)
    {
        if(obj == null || (obj instanceof NamingAuthority))
            return (NamingAuthority)obj;
        if(obj instanceof ASN1Sequence)
            return new NamingAuthority((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static NamingAuthority getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    private NamingAuthority(ASN1Sequence seq)
    {
        if(seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        if(e.hasMoreElements())
        {
            ASN1Encodable o = (ASN1Encodable)e.nextElement();
            if(o instanceof ASN1ObjectIdentifier)
                namingAuthorityId = (ASN1ObjectIdentifier)o;
            else
            if(o instanceof DERIA5String)
                namingAuthorityUrl = DERIA5String.getInstance(o).getString();
            else
            if(o instanceof ASN1String)
                namingAuthorityText = DirectoryString.getInstance(o);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(o.getClass()).toString());
        }
        if(e.hasMoreElements())
        {
            ASN1Encodable o = (ASN1Encodable)e.nextElement();
            if(o instanceof DERIA5String)
                namingAuthorityUrl = DERIA5String.getInstance(o).getString();
            else
            if(o instanceof ASN1String)
                namingAuthorityText = DirectoryString.getInstance(o);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(o.getClass()).toString());
        }
        if(e.hasMoreElements())
        {
            ASN1Encodable o = (ASN1Encodable)e.nextElement();
            if(o instanceof ASN1String)
                namingAuthorityText = DirectoryString.getInstance(o);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(o.getClass()).toString());
        }
    }

    public ASN1ObjectIdentifier getNamingAuthorityId()
    {
        return namingAuthorityId;
    }

    public DirectoryString getNamingAuthorityText()
    {
        return namingAuthorityText;
    }

    public String getNamingAuthorityUrl()
    {
        return namingAuthorityUrl;
    }

    /**
     * @deprecated Method NamingAuthority is deprecated
     */

    public NamingAuthority(DERObjectIdentifier namingAuthorityId, String namingAuthorityUrl, DirectoryString namingAuthorityText)
    {
        this.namingAuthorityId = new ASN1ObjectIdentifier(namingAuthorityId.getId());
        this.namingAuthorityUrl = namingAuthorityUrl;
        this.namingAuthorityText = namingAuthorityText;
    }

    public NamingAuthority(ASN1ObjectIdentifier namingAuthorityId, String namingAuthorityUrl, DirectoryString namingAuthorityText)
    {
        this.namingAuthorityId = namingAuthorityId;
        this.namingAuthorityUrl = namingAuthorityUrl;
        this.namingAuthorityText = namingAuthorityText;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        if(namingAuthorityId != null)
            vec.add(namingAuthorityId);
        if(namingAuthorityUrl != null)
            vec.add(new DERIA5String(namingAuthorityUrl, true));
        if(namingAuthorityText != null)
            vec.add(namingAuthorityText);
        return new DERSequence(vec);
    }

    public static final ASN1ObjectIdentifier id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern;
    private ASN1ObjectIdentifier namingAuthorityId;
    private String namingAuthorityUrl;
    private DirectoryString namingAuthorityText;

    static 
    {
        id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern = new ASN1ObjectIdentifier((new StringBuilder()).append(ISISMTTObjectIdentifiers.id_isismtt_at_namingAuthorities).append(".1").toString());
    }
}
