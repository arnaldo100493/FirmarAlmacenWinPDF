// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MiscPEMGenerator.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cert.*;
import co.org.bouncy.pkcs.PKCS10CertificationRequest;
import co.org.bouncy.util.Strings;
import co.org.bouncy.util.io.pem.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMEncryptor

public class MiscPEMGenerator
    implements PemObjectGenerator
{

    public MiscPEMGenerator(Object o)
    {
        obj = o;
        encryptor = null;
    }

    public MiscPEMGenerator(Object o, PEMEncryptor encryptor)
    {
        obj = o;
        this.encryptor = encryptor;
    }

    private PemObject createPemObject(Object o)
        throws IOException
    {
        if(o instanceof PemObject)
            return (PemObject)o;
        if(o instanceof PemObjectGenerator)
            return ((PemObjectGenerator)o).generate();
        String type;
        byte encoding[];
        if(o instanceof X509CertificateHolder)
        {
            type = "CERTIFICATE";
            encoding = ((X509CertificateHolder)o).getEncoded();
        } else
        if(o instanceof X509CRLHolder)
        {
            type = "X509 CRL";
            encoding = ((X509CRLHolder)o).getEncoded();
        } else
        if(o instanceof PrivateKeyInfo)
        {
            PrivateKeyInfo info = (PrivateKeyInfo)o;
            ASN1ObjectIdentifier algOID = info.getPrivateKeyAlgorithm().getAlgorithm();
            if(algOID.equals(PKCSObjectIdentifiers.rsaEncryption))
            {
                type = "RSA PRIVATE KEY";
                encoding = info.parsePrivateKey().toASN1Primitive().getEncoded();
            } else
            if(algOID.equals(dsaOids[0]) || algOID.equals(dsaOids[1]))
            {
                type = "DSA PRIVATE KEY";
                DSAParameter p = DSAParameter.getInstance(info.getPrivateKeyAlgorithm().getParameters());
                ASN1EncodableVector v = new ASN1EncodableVector();
                v.add(new DERInteger(0L));
                v.add(new DERInteger(p.getP()));
                v.add(new DERInteger(p.getQ()));
                v.add(new DERInteger(p.getG()));
                BigInteger x = ASN1Integer.getInstance(info.parsePrivateKey()).getValue();
                BigInteger y = p.getG().modPow(x, p.getP());
                v.add(new DERInteger(y));
                v.add(new DERInteger(x));
                encoding = (new DERSequence(v)).getEncoded();
            } else
            if(algOID.equals(X9ObjectIdentifiers.id_ecPublicKey))
            {
                type = "EC PRIVATE KEY";
                encoding = info.parsePrivateKey().toASN1Primitive().getEncoded();
            } else
            {
                throw new IOException("Cannot identify private key");
            }
        } else
        if(o instanceof SubjectPublicKeyInfo)
        {
            type = "PUBLIC KEY";
            encoding = ((SubjectPublicKeyInfo)o).getEncoded();
        } else
        if(o instanceof X509AttributeCertificateHolder)
        {
            type = "ATTRIBUTE CERTIFICATE";
            encoding = ((X509AttributeCertificateHolder)o).getEncoded();
        } else
        if(o instanceof PKCS10CertificationRequest)
        {
            type = "CERTIFICATE REQUEST";
            encoding = ((PKCS10CertificationRequest)o).getEncoded();
        } else
        if(o instanceof ContentInfo)
        {
            type = "PKCS7";
            encoding = ((ContentInfo)o).getEncoded();
        } else
        {
            throw new PemGenerationException("unknown object passed - can't encode.");
        }
        if(encryptor != null)
        {
            String dekAlgName = Strings.toUpperCase(encryptor.getAlgorithm());
            if(dekAlgName.equals("DESEDE"))
                dekAlgName = "DES-EDE3-CBC";
            byte iv[] = encryptor.getIV();
            byte encData[] = encryptor.encrypt(encoding);
            List headers = new ArrayList(2);
            headers.add(new PemHeader("Proc-Type", "4,ENCRYPTED"));
            headers.add(new PemHeader("DEK-Info", (new StringBuilder()).append(dekAlgName).append(",").append(getHexEncoded(iv)).toString()));
            return new PemObject(type, headers, encData);
        } else
        {
            return new PemObject(type, encoding);
        }
    }

    private String getHexEncoded(byte bytes[])
        throws IOException
    {
        char chars[] = new char[bytes.length * 2];
        for(int i = 0; i != bytes.length; i++)
        {
            int v = bytes[i] & 0xff;
            chars[2 * i] = (char)hexEncodingTable[v >>> 4];
            chars[2 * i + 1] = (char)hexEncodingTable[v & 0xf];
        }

        return new String(chars);
    }

    public PemObject generate()
        throws PemGenerationException
    {
        try
        {
            return createPemObject(obj);
        }
        catch(IOException e)
        {
            throw new PemGenerationException((new StringBuilder()).append("encoding exception: ").append(e.getMessage()).toString(), e);
        }
    }

    private static final ASN1ObjectIdentifier dsaOids[];
    private static final byte hexEncodingTable[] = {
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        65, 66, 67, 68, 69, 70
    };
    private final Object obj;
    private final PEMEncryptor encryptor;

    static 
    {
        dsaOids = (new ASN1ObjectIdentifier[] {
            X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1
        });
    }
}
