// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KEKRecipientInfoGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.operator.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, RecipientInfoGenerator

public abstract class KEKRecipientInfoGenerator
    implements RecipientInfoGenerator
{

    protected KEKRecipientInfoGenerator(KEKIdentifier kekIdentifier, SymmetricKeyWrapper wrapper)
    {
        this.kekIdentifier = kekIdentifier;
        this.wrapper = wrapper;
    }

    public final RecipientInfo generate(GenericKey contentEncryptionKey)
        throws CMSException
    {
        try
        {
            co.org.bouncy.asn1.ASN1OctetString encryptedKey = new DEROctetString(wrapper.generateWrappedKey(contentEncryptionKey));
            return new RecipientInfo(new KEKRecipientInfo(kekIdentifier, wrapper.getAlgorithmIdentifier(), encryptedKey));
        }
        catch(OperatorException e)
        {
            throw new CMSException((new StringBuilder()).append("exception wrapping content key: ").append(e.getMessage()).toString(), e);
        }
    }

    private final KEKIdentifier kekIdentifier;
    protected final SymmetricKeyWrapper wrapper;
}
