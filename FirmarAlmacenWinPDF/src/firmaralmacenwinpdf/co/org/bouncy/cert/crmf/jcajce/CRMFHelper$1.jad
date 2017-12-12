// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRMFHelper.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.cms.CMSAlgorithm;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.cert.crmf.jcajce:
//            CRMFHelper

class CRMFHelper$1
    implements ECallback
{

    public Object doInJCE()
        throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
    {
        Cipher cipher = createCipher(val$encryptionAlgID.getAlgorithm());
        ASN1Primitive sParams = (ASN1Primitive)val$encryptionAlgID.getParameters();
        ASN1ObjectIdentifier encAlg = val$encryptionAlgID.getAlgorithm();
        if(sParams != null && !(sParams instanceof ASN1Null))
            try
            {
                AlgorithmParameters params = createAlgorithmParameters(val$encryptionAlgID.getAlgorithm());
                try
                {
                    params.init(sParams.getEncoded(), "ASN.1");
                }
                catch(IOException e)
                {
                    throw new CRMFException("error decoding algorithm parameters.", e);
                }
                cipher.init(2, val$sKey, params);
            }
            catch(NoSuchAlgorithmException e)
            {
                if(encAlg.equals(CMSAlgorithm.DES_EDE3_CBC) || encAlg.equals(CMSAlgorithm.IDEA_CBC) || encAlg.equals(CMSAlgorithm.AES128_CBC) || encAlg.equals(CMSAlgorithm.AES192_CBC) || encAlg.equals(CMSAlgorithm.AES256_CBC))
                    cipher.init(2, val$sKey, new IvParameterSpec(ASN1OctetString.getInstance(sParams).getOctets()));
                else
                    throw e;
            }
        else
        if(encAlg.equals(CMSAlgorithm.DES_EDE3_CBC) || encAlg.equals(CMSAlgorithm.IDEA_CBC) || encAlg.equals(CMSAlgorithm.CAST5_CBC))
            cipher.init(2, val$sKey, new IvParameterSpec(new byte[8]));
        else
            cipher.init(2, val$sKey);
        return cipher;
    }

    final AlgorithmIdentifier val$encryptionAlgID;
    final Key val$sKey;
    final CRMFHelper this$0;

    CRMFHelper$1()
    {
        this$0 = final_crmfhelper;
        val$encryptionAlgID = algorithmidentifier;
        val$sKey = Key.this;
        super();
    }
}
