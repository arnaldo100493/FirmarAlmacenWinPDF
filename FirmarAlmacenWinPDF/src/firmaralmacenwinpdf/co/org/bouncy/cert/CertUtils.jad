// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertUtils.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentSigner;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert:
//            X509CertificateHolder, X509AttributeCertificateHolder, X509CRLHolder, CertIOException

class CertUtils
{

    CertUtils()
    {
    }

    static X509CertificateHolder generateFullCert(ContentSigner signer, TBSCertificate tbsCert)
    {
        try
        {
            return new X509CertificateHolder(generateStructure(tbsCert, signer.getAlgorithmIdentifier(), generateSig(signer, tbsCert)));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("cannot produce certificate signature");
        }
    }

    static X509AttributeCertificateHolder generateFullAttrCert(ContentSigner signer, AttributeCertificateInfo attrInfo)
    {
        try
        {
            return new X509AttributeCertificateHolder(generateAttrStructure(attrInfo, signer.getAlgorithmIdentifier(), generateSig(signer, attrInfo)));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("cannot produce attribute certificate signature");
        }
    }

    static X509CRLHolder generateFullCRL(ContentSigner signer, TBSCertList tbsCertList)
    {
        try
        {
            return new X509CRLHolder(generateCRLStructure(tbsCertList, signer.getAlgorithmIdentifier(), generateSig(signer, tbsCertList)));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("cannot produce certificate signature");
        }
    }

    private static byte[] generateSig(ContentSigner signer, ASN1Encodable tbsObj)
        throws IOException
    {
        OutputStream sOut = signer.getOutputStream();
        DEROutputStream dOut = new DEROutputStream(sOut);
        dOut.writeObject(tbsObj);
        sOut.close();
        return signer.getSignature();
    }

    private static Certificate generateStructure(TBSCertificate tbsCert, AlgorithmIdentifier sigAlgId, byte signature[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(tbsCert);
        v.add(sigAlgId);
        v.add(new DERBitString(signature));
        return Certificate.getInstance(new DERSequence(v));
    }

    private static AttributeCertificate generateAttrStructure(AttributeCertificateInfo attrInfo, AlgorithmIdentifier sigAlgId, byte signature[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(attrInfo);
        v.add(sigAlgId);
        v.add(new DERBitString(signature));
        return AttributeCertificate.getInstance(new DERSequence(v));
    }

    private static CertificateList generateCRLStructure(TBSCertList tbsCertList, AlgorithmIdentifier sigAlgId, byte signature[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(tbsCertList);
        v.add(sigAlgId);
        v.add(new DERBitString(signature));
        return CertificateList.getInstance(new DERSequence(v));
    }

    static Set getCriticalExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getCriticalExtensionOIDs())));
    }

    static Set getNonCriticalExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getNonCriticalExtensionOIDs())));
    }

    static List getExtensionOIDs(Extensions extensions)
    {
        if(extensions == null)
            return EMPTY_LIST;
        else
            return Collections.unmodifiableList(Arrays.asList(extensions.getExtensionOIDs()));
    }

    static void addExtension(ExtensionsGenerator extGenerator, ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws CertIOException
    {
        try
        {
            extGenerator.addExtension(oid, isCritical, value);
        }
        catch(IOException e)
        {
            throw new CertIOException((new StringBuilder()).append("cannot encode extension: ").append(e.getMessage()).toString(), e);
        }
    }

    static DERBitString booleanToBitString(boolean id[])
    {
        byte bytes[] = new byte[(id.length + 7) / 8];
        for(int i = 0; i != id.length; i++)
            bytes[i / 8] |= id[i] ? ((byte) (1 << 7 - i % 8)) : 0;

        int pad = id.length % 8;
        if(pad == 0)
            return new DERBitString(bytes);
        else
            return new DERBitString(bytes, 8 - pad);
    }

    static boolean[] bitStringToBoolean(DERBitString bitString)
    {
        if(bitString != null)
        {
            byte bytes[] = bitString.getBytes();
            boolean boolId[] = new boolean[bytes.length * 8 - bitString.getPadBits()];
            for(int i = 0; i != boolId.length; i++)
                boolId[i] = (bytes[i / 8] & 128 >>> i % 8) != 0;

            return boolId;
        } else
        {
            return null;
        }
    }

    static Date recoverDate(ASN1GeneralizedTime time)
    {
        try
        {
            return time.getDate();
        }
        catch(ParseException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("unable to recover date: ").append(e.getMessage()).toString());
        }
    }

    static boolean isAlgIdEqual(AlgorithmIdentifier id1, AlgorithmIdentifier id2)
    {
        if(!id1.getAlgorithm().equals(id2.getAlgorithm()))
            return false;
        if(id1.getParameters() == null)
            return id2.getParameters() == null || id2.getParameters().equals(DERNull.INSTANCE);
        if(id2.getParameters() == null)
            return id1.getParameters() == null || id1.getParameters().equals(DERNull.INSTANCE);
        else
            return id1.getParameters().equals(id2.getParameters());
    }

    private static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    private static List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());

}
