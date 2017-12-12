// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreeRecipient.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;

// Referenced classes of package co.org.bouncy.cms:
//            Recipient, CMSException, RecipientOperator

public interface KeyAgreeRecipient
    extends Recipient
{

    public abstract RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmidentifier, AlgorithmIdentifier algorithmidentifier1, SubjectPublicKeyInfo subjectpublickeyinfo, ASN1OctetString asn1octetstring, byte abyte0[])
        throws CMSException;

    public abstract AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier();
}
