// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1ObjectIdentifier.java

package co.org.bouncy.asn1;


// Referenced classes of package co.org.bouncy.asn1:
//            DERObjectIdentifier

public class ASN1ObjectIdentifier extends DERObjectIdentifier
{

    public ASN1ObjectIdentifier(String identifier)
    {
        super(identifier);
    }

    ASN1ObjectIdentifier(byte bytes[])
    {
        super(bytes);
    }

    ASN1ObjectIdentifier(ASN1ObjectIdentifier oid, String branch)
    {
        super(oid, branch);
    }

    public ASN1ObjectIdentifier branch(String branchID)
    {
        return new ASN1ObjectIdentifier(this, branchID);
    }

    public boolean on(ASN1ObjectIdentifier stem)
    {
        String id = getId();
        String stemId = stem.getId();
        return id.length() > stemId.length() && id.charAt(stemId.length()) == '.' && id.startsWith(stemId);
    }
}
