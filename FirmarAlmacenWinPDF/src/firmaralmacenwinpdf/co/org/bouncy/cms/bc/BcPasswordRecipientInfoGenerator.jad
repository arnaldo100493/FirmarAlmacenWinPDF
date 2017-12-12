// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPasswordRecipientInfoGenerator.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.PasswordRecipientInfoGenerator;
import co.org.bouncy.crypto.Wrapper;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.operator.GenericKey;

// Referenced classes of package co.org.bouncy.cms.bc:
//            CMSUtils, EnvelopedDataHelper

public class BcPasswordRecipientInfoGenerator extends PasswordRecipientInfoGenerator
{

    public BcPasswordRecipientInfoGenerator(ASN1ObjectIdentifier kekAlgorithm, char password[])
    {
        super(kekAlgorithm, password);
    }

    public byte[] generateEncryptedBytes(AlgorithmIdentifier keyEncryptionAlgorithm, byte derivedKey[], GenericKey contentEncryptionKey)
        throws CMSException
    {
        byte contentEncryptionKeySpec[] = ((KeyParameter)CMSUtils.getBcKey(contentEncryptionKey)).getKey();
        Wrapper keyEncryptionCipher = EnvelopedDataHelper.createRFC3211Wrapper(keyEncryptionAlgorithm.getAlgorithm());
        keyEncryptionCipher.init(true, new ParametersWithIV(new KeyParameter(derivedKey), ASN1OctetString.getInstance(keyEncryptionAlgorithm.getParameters()).getOctets()));
        return keyEncryptionCipher.wrap(contentEncryptionKeySpec, 0, contentEncryptionKeySpec.length);
    }
}
