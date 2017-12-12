// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSPUtil.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Set;
import co.org.bouncy.asn1.cms.Attribute;
import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.ExtendedKeyUsage;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.ExtensionsGenerator;
import co.org.bouncy.asn1.x509.KeyPurposeId;
import co.org.bouncy.asn1.x509.X509Extensions;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.SignerInformation;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.operator.DigestCalculatorProvider;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package co.org.bouncy.tsp:
//            TimeStampToken, TSPValidationException, TSPException, TSPIOException, 
//            TimeStampTokenInfo

public class TSPUtil
{

    public TSPUtil()
    {
    }

    /**
     * @deprecated Method getSignatureTimestamps is deprecated
     */

    public static Collection getSignatureTimestamps(SignerInformation signerInfo, Provider provider)
        throws TSPValidationException
    {
        List timestamps = new ArrayList();
        AttributeTable unsignedAttrs = signerInfo.getUnsignedAttributes();
        if(unsignedAttrs != null)
        {
            ASN1EncodableVector allTSAttrs = unsignedAttrs.getAll(PKCSObjectIdentifiers.id_aa_signatureTimeStampToken);
            for(int i = 0; i < allTSAttrs.size(); i++)
            {
                Attribute tsAttr = (Attribute)allTSAttrs.get(i);
                ASN1Set tsAttrValues = tsAttr.getAttrValues();
                for(int j = 0; j < tsAttrValues.size(); j++)
                    try
                    {
                        ContentInfo contentInfo = ContentInfo.getInstance(tsAttrValues.getObjectAt(j));
                        TimeStampToken timeStampToken = new TimeStampToken(contentInfo);
                        TimeStampTokenInfo tstInfo = timeStampToken.getTimeStampInfo();
                        MessageDigest digest = createDigestInstance(tstInfo.getMessageImprintAlgOID().getId(), provider);
                        byte expectedDigest[] = digest.digest(signerInfo.getSignature());
                        if(!Arrays.constantTimeAreEqual(expectedDigest, tstInfo.getMessageImprintDigest()))
                            throw new TSPValidationException("Incorrect digest in message imprint");
                        timestamps.add(timeStampToken);
                    }
                    catch(NoSuchAlgorithmException e)
                    {
                        throw new TSPValidationException("Unknown hash algorithm specified in timestamp");
                    }
                    catch(Exception e)
                    {
                        throw new TSPValidationException("Timestamp could not be parsed");
                    }

            }

        }
        return timestamps;
    }

    public static Collection getSignatureTimestamps(SignerInformation signerInfo, DigestCalculatorProvider digCalcProvider)
        throws TSPValidationException
    {
        List timestamps = new ArrayList();
        AttributeTable unsignedAttrs = signerInfo.getUnsignedAttributes();
        if(unsignedAttrs != null)
        {
            ASN1EncodableVector allTSAttrs = unsignedAttrs.getAll(PKCSObjectIdentifiers.id_aa_signatureTimeStampToken);
            for(int i = 0; i < allTSAttrs.size(); i++)
            {
                Attribute tsAttr = (Attribute)allTSAttrs.get(i);
                ASN1Set tsAttrValues = tsAttr.getAttrValues();
                for(int j = 0; j < tsAttrValues.size(); j++)
                    try
                    {
                        ContentInfo contentInfo = ContentInfo.getInstance(tsAttrValues.getObjectAt(j));
                        TimeStampToken timeStampToken = new TimeStampToken(contentInfo);
                        TimeStampTokenInfo tstInfo = timeStampToken.getTimeStampInfo();
                        DigestCalculator digCalc = digCalcProvider.get(tstInfo.getHashAlgorithm());
                        OutputStream dOut = digCalc.getOutputStream();
                        dOut.write(signerInfo.getSignature());
                        dOut.close();
                        byte expectedDigest[] = digCalc.getDigest();
                        if(!Arrays.constantTimeAreEqual(expectedDigest, tstInfo.getMessageImprintDigest()))
                            throw new TSPValidationException("Incorrect digest in message imprint");
                        timestamps.add(timeStampToken);
                    }
                    catch(OperatorCreationException e)
                    {
                        throw new TSPValidationException("Unknown hash algorithm specified in timestamp");
                    }
                    catch(Exception e)
                    {
                        throw new TSPValidationException("Timestamp could not be parsed");
                    }

            }

        }
        return timestamps;
    }

    public static void validateCertificate(X509Certificate cert)
        throws TSPValidationException
    {
        if(cert.getVersion() != 3)
            throw new IllegalArgumentException("Certificate must have an ExtendedKeyUsage extension.");
        byte ext[] = cert.getExtensionValue(X509Extensions.ExtendedKeyUsage.getId());
        if(ext == null)
            throw new TSPValidationException("Certificate must have an ExtendedKeyUsage extension.");
        if(!cert.getCriticalExtensionOIDs().contains(X509Extensions.ExtendedKeyUsage.getId()))
            throw new TSPValidationException("Certificate must have an ExtendedKeyUsage extension marked as critical.");
        ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(ext));
        try
        {
            aIn = new ASN1InputStream(new ByteArrayInputStream(((ASN1OctetString)aIn.readObject()).getOctets()));
            ExtendedKeyUsage extKey = ExtendedKeyUsage.getInstance(aIn.readObject());
            if(!extKey.hasKeyPurposeId(KeyPurposeId.id_kp_timeStamping) || extKey.size() != 1)
                throw new TSPValidationException("ExtendedKeyUsage not solely time stamping.");
        }
        catch(IOException e)
        {
            throw new TSPValidationException("cannot process ExtendedKeyUsage extension");
        }
    }

    public static void validateCertificate(X509CertificateHolder cert)
        throws TSPValidationException
    {
        if(cert.toASN1Structure().getVersionNumber() != 3)
            throw new IllegalArgumentException("Certificate must have an ExtendedKeyUsage extension.");
        Extension ext = cert.getExtension(Extension.extendedKeyUsage);
        if(ext == null)
            throw new TSPValidationException("Certificate must have an ExtendedKeyUsage extension.");
        if(!ext.isCritical())
            throw new TSPValidationException("Certificate must have an ExtendedKeyUsage extension marked as critical.");
        ExtendedKeyUsage extKey = ExtendedKeyUsage.getInstance(ext.getParsedValue());
        if(!extKey.hasKeyPurposeId(KeyPurposeId.id_kp_timeStamping) || extKey.size() != 1)
            throw new TSPValidationException("ExtendedKeyUsage not solely time stamping.");
        else
            return;
    }

    static String getDigestAlgName(String digestAlgOID)
    {
        String digestName = (String)digestNames.get(digestAlgOID);
        if(digestName != null)
            return digestName;
        else
            return digestAlgOID;
    }

    static int getDigestLength(String digestAlgOID)
        throws TSPException
    {
        Integer length = (Integer)digestLengths.get(digestAlgOID);
        if(length != null)
            return length.intValue();
        else
            throw new TSPException("digest algorithm cannot be found.");
    }

    static MessageDigest createDigestInstance(String digestAlgOID, Provider provider)
        throws NoSuchAlgorithmException
    {
        String digestName = getDigestAlgName(digestAlgOID);
        if(provider != null)
            try
            {
                return MessageDigest.getInstance(digestName, provider);
            }
            catch(NoSuchAlgorithmException e) { }
        return MessageDigest.getInstance(digestName);
    }

    static Set getCriticalExtensionOIDs(X509Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(java.util.Arrays.asList(extensions.getCriticalExtensionOIDs())));
    }

    static Set getNonCriticalExtensionOIDs(X509Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(java.util.Arrays.asList(extensions.getNonCriticalExtensionOIDs())));
    }

    static List getExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_LIST;
        else
            return Collections.unmodifiableList(java.util.Arrays.asList(extensions.getExtensionOIDs()));
    }

    static void addExtension(ExtensionsGenerator extGenerator, ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws TSPIOException
    {
        try
        {
            extGenerator.addExtension(oid, isCritical, value);
        }
        catch(IOException e)
        {
            throw new TSPIOException((new StringBuilder()).append("cannot encode extension: ").append(e.getMessage()).toString(), e);
        }
    }

    private static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    private static List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());
    private static final Map digestLengths;
    private static final Map digestNames;

    static 
    {
        digestLengths = new HashMap();
        digestNames = new HashMap();
        digestLengths.put(PKCSObjectIdentifiers.md5.getId(), Integers.valueOf(16));
        digestLengths.put(OIWObjectIdentifiers.idSHA1.getId(), Integers.valueOf(20));
        digestLengths.put(NISTObjectIdentifiers.id_sha224.getId(), Integers.valueOf(28));
        digestLengths.put(NISTObjectIdentifiers.id_sha256.getId(), Integers.valueOf(32));
        digestLengths.put(NISTObjectIdentifiers.id_sha384.getId(), Integers.valueOf(48));
        digestLengths.put(NISTObjectIdentifiers.id_sha512.getId(), Integers.valueOf(64));
        digestLengths.put(TeleTrusTObjectIdentifiers.ripemd128.getId(), Integers.valueOf(16));
        digestLengths.put(TeleTrusTObjectIdentifiers.ripemd160.getId(), Integers.valueOf(20));
        digestLengths.put(TeleTrusTObjectIdentifiers.ripemd256.getId(), Integers.valueOf(32));
        digestLengths.put(CryptoProObjectIdentifiers.gostR3411.getId(), Integers.valueOf(32));
        digestNames.put(PKCSObjectIdentifiers.md5.getId(), "MD5");
        digestNames.put(OIWObjectIdentifiers.idSHA1.getId(), "SHA1");
        digestNames.put(NISTObjectIdentifiers.id_sha224.getId(), "SHA224");
        digestNames.put(NISTObjectIdentifiers.id_sha256.getId(), "SHA256");
        digestNames.put(NISTObjectIdentifiers.id_sha384.getId(), "SHA384");
        digestNames.put(NISTObjectIdentifiers.id_sha512.getId(), "SHA512");
        digestNames.put(PKCSObjectIdentifiers.sha1WithRSAEncryption.getId(), "SHA1");
        digestNames.put(PKCSObjectIdentifiers.sha224WithRSAEncryption.getId(), "SHA224");
        digestNames.put(PKCSObjectIdentifiers.sha256WithRSAEncryption.getId(), "SHA256");
        digestNames.put(PKCSObjectIdentifiers.sha384WithRSAEncryption.getId(), "SHA384");
        digestNames.put(PKCSObjectIdentifiers.sha512WithRSAEncryption.getId(), "SHA512");
        digestNames.put(TeleTrusTObjectIdentifiers.ripemd128.getId(), "RIPEMD128");
        digestNames.put(TeleTrusTObjectIdentifiers.ripemd160.getId(), "RIPEMD160");
        digestNames.put(TeleTrusTObjectIdentifiers.ripemd256.getId(), "RIPEMD256");
        digestNames.put(CryptoProObjectIdentifiers.gostR3411.getId(), "GOST3411");
    }
}
