// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKEKRecipientInfoGenerator.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.cms.KEKIdentifier;
import co.org.bouncy.cms.KEKRecipientInfoGenerator;
import co.org.bouncy.operator.bc.BcSymmetricKeyWrapper;

public class BcKEKRecipientInfoGenerator extends KEKRecipientInfoGenerator
{

    public BcKEKRecipientInfoGenerator(KEKIdentifier kekIdentifier, BcSymmetricKeyWrapper kekWrapper)
    {
        super(kekIdentifier, kekWrapper);
    }

    public BcKEKRecipientInfoGenerator(byte keyIdentifier[], BcSymmetricKeyWrapper kekWrapper)
    {
        this(new KEKIdentifier(keyIdentifier, null, null), kekWrapper);
    }
}
