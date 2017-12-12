// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.bc.BcDigestCalculatorProvider;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSSignedHelper, CMSSignedDataGenerator, CMSAttributeTableGenerator, 
//            SignerInfoGenerator

private class CMSSignedDataGenerator$SignerInf
{

    SignerInfoGenerator toSignerInfoGenerator(SecureRandom random, Provider sigProvider, boolean addDefaultAttributes)
        throws IOException, CertificateEncodingException, CMSException, OperatorCreationException, NoSuchAlgorithmException
    {
        String digestName = CMSSignedHelper.INSTANCE.getDigestAlgName(digestOID);
        String signatureName = (new StringBuilder()).append(digestName).append("with").append(CMSSignedHelper.INSTANCE.getEncryptionAlgName(encOID)).toString();
        JcaSignerInfoGeneratorBuilder builder = new JcaSignerInfoGeneratorBuilder(new BcDigestCalculatorProvider());
        if(addDefaultAttributes)
            builder.setSignedAttributeGenerator(sAttr);
        builder.setDirectSignature(!addDefaultAttributes);
        builder.setUnsignedAttributeGenerator(unsAttr);
        JcaContentSignerBuilder signerBuilder;
        try
        {
            signerBuilder = (new JcaContentSignerBuilder(signatureName)).setSecureRandom(random);
        }
        catch(IllegalArgumentException e)
        {
            throw new NoSuchAlgorithmException(e.getMessage());
        }
        if(sigProvider != null)
            signerBuilder.setProvider(sigProvider);
        ContentSigner contentSigner = signerBuilder.build(key);
        if(signerIdentifier instanceof X509Certificate)
            return builder.build(contentSigner, (X509Certificate)signerIdentifier);
        else
            return builder.build(contentSigner, (byte[])(byte[])signerIdentifier);
    }

    final PrivateKey key;
    final Object signerIdentifier;
    final String digestOID;
    final String encOID;
    final CMSAttributeTableGenerator sAttr;
    final CMSAttributeTableGenerator unsAttr;
    final AttributeTable baseSignedTable;
    final CMSSignedDataGenerator this$0;

    CMSSignedDataGenerator$SignerInf(PrivateKey key, Object signerIdentifier, String digestOID, String encOID, CMSAttributeTableGenerator sAttr, CMSAttributeTableGenerator unsAttr, 
            AttributeTable baseSignedTable)
    {
        this$0 = CMSSignedDataGenerator.this;
        super();
        this.key = key;
        this.signerIdentifier = signerIdentifier;
        this.digestOID = digestOID;
        this.encOID = encOID;
        this.sAttr = sAttr;
        this.unsAttr = unsAttr;
        this.baseSignedTable = baseSignedTable;
    }
}
