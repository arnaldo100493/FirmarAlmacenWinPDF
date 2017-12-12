// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.sec.ECPrivateKey;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cert.*;
import co.org.bouncy.pkcs.PKCS10CertificationRequest;
import co.org.bouncy.pkcs.PKCS8EncryptedPrivateKeyInfo;
import co.org.bouncy.util.encoders.Hex;
import co.org.bouncy.util.io.pem.*;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMKeyPair, PEMKeyPairParser, PEMEncryptedKeyPair

public class PEMParser extends PemReader
{
    private class PrivateKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                return PrivateKeyInfo.getInstance(obj.getContent());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing PRIVATE KEY: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        public PrivateKeyParser()
        {
            this$0 = PEMParser.this;
            super();
        }
    }

    private class EncryptedPrivateKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                return new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(obj.getContent()));
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing ENCRYPTED PRIVATE KEY: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        public EncryptedPrivateKeyParser()
        {
            this$0 = PEMParser.this;
            super();
        }
    }

    private class ECCurveParamsParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            Object param;
            try
            {
                param = ASN1Primitive.fromByteArray(obj.getContent());
                if(param instanceof ASN1ObjectIdentifier)
                    return ASN1Primitive.fromByteArray(obj.getContent());
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("exception extracting EC named curve: ").append(e.toString()).toString());
            }
            if(param instanceof ASN1Sequence)
                return X9ECParameters.getInstance(param);
            return null;
        }

        final PEMParser this$0;

        private ECCurveParamsParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class X509AttributeCertificateParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            return new X509AttributeCertificateHolder(obj.getContent());
        }

        final PEMParser this$0;

        private X509AttributeCertificateParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class PKCS7Parser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                ASN1InputStream aIn = new ASN1InputStream(obj.getContent());
                return ContentInfo.getInstance(aIn.readObject());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing PKCS7 object: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private PKCS7Parser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class PKCS10CertificationRequestParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                return new PKCS10CertificationRequest(obj.getContent());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing certrequest: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private PKCS10CertificationRequestParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class X509CRLParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                return new X509CRLHolder(obj.getContent());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing cert: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private X509CRLParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class X509CertificateParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                return new X509CertificateHolder(obj.getContent());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing cert: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private X509CertificateParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class RSAPublicKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                RSAPublicKey rsaPubStructure = RSAPublicKey.getInstance(obj.getContent());
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), rsaPubStructure);
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem extracting key: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        public RSAPublicKeyParser()
        {
            this$0 = PEMParser.this;
            super();
        }
    }

    private class PublicKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            return SubjectPublicKeyInfo.getInstance(obj.getContent());
        }

        final PEMParser this$0;

        public PublicKeyParser()
        {
            this$0 = PEMParser.this;
            super();
        }
    }

    private class RSAKeyPairParser
        implements PEMKeyPairParser
    {

        public PEMKeyPair parse(byte encoding[])
            throws IOException
        {
            try
            {
                ASN1Sequence seq = ASN1Sequence.getInstance(encoding);
                if(seq.size() != 9)
                {
                    throw new PEMException("malformed sequence in RSA private key");
                } else
                {
                    RSAPrivateKey keyStruct = RSAPrivateKey.getInstance(seq);
                    RSAPublicKey pubSpec = new RSAPublicKey(keyStruct.getModulus(), keyStruct.getPublicExponent());
                    AlgorithmIdentifier algId = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE);
                    return new PEMKeyPair(new SubjectPublicKeyInfo(algId, pubSpec), new PrivateKeyInfo(algId, keyStruct));
                }
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem creating RSA private key: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private RSAKeyPairParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class ECDSAKeyPairParser
        implements PEMKeyPairParser
    {

        public PEMKeyPair parse(byte encoding[])
            throws IOException
        {
            try
            {
                ASN1Sequence seq = ASN1Sequence.getInstance(encoding);
                ECPrivateKey pKey = ECPrivateKey.getInstance(seq);
                AlgorithmIdentifier algId = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, pKey.getParameters());
                PrivateKeyInfo privInfo = new PrivateKeyInfo(algId, pKey);
                SubjectPublicKeyInfo pubInfo = new SubjectPublicKeyInfo(algId, pKey.getPublicKey().getBytes());
                return new PEMKeyPair(pubInfo, privInfo);
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem creating EC private key: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private ECDSAKeyPairParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class DSAKeyPairParser
        implements PEMKeyPairParser
    {

        public PEMKeyPair parse(byte encoding[])
            throws IOException
        {
            try
            {
                ASN1Sequence seq = ASN1Sequence.getInstance(encoding);
                if(seq.size() != 6)
                {
                    throw new PEMException("malformed sequence in DSA private key");
                } else
                {
                    ASN1Integer p = ASN1Integer.getInstance(seq.getObjectAt(1));
                    ASN1Integer q = ASN1Integer.getInstance(seq.getObjectAt(2));
                    ASN1Integer g = ASN1Integer.getInstance(seq.getObjectAt(3));
                    ASN1Integer y = ASN1Integer.getInstance(seq.getObjectAt(4));
                    ASN1Integer x = ASN1Integer.getInstance(seq.getObjectAt(5));
                    return new PEMKeyPair(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(p.getValue(), q.getValue(), g.getValue())), y), new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(p.getValue(), q.getValue(), g.getValue())), x));
                }
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem creating DSA private key: ").append(e.toString()).toString(), e);
            }
        }

        final PEMParser this$0;

        private DSAKeyPairParser()
        {
            this$0 = PEMParser.this;
            super();
        }

    }

    private class KeyPairParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            boolean isEncrypted = false;
            String dekInfo = null;
            List headers = obj.getHeaders();
            Iterator it = headers.iterator();
            do
            {
                if(!it.hasNext())
                    break;
                PemHeader hdr = (PemHeader)it.next();
                if(hdr.getName().equals("Proc-Type") && hdr.getValue().equals("4,ENCRYPTED"))
                    isEncrypted = true;
                else
                if(hdr.getName().equals("DEK-Info"))
                    dekInfo = hdr.getValue();
            } while(true);
            byte keyBytes[] = obj.getContent();
            try
            {
                if(isEncrypted)
                {
                    StringTokenizer tknz = new StringTokenizer(dekInfo, ",");
                    String dekAlgName = tknz.nextToken();
                    byte iv[] = Hex.decode(tknz.nextToken());
                    return new PEMEncryptedKeyPair(dekAlgName, iv, keyBytes, pemKeyPairParser);
                }
            }
            catch(IOException e)
            {
                if(isEncrypted)
                    throw new PEMException("exception decoding - please check password and data.", e);
                else
                    throw new PEMException(e.getMessage(), e);
            }
            catch(IllegalArgumentException e)
            {
                if(isEncrypted)
                    throw new PEMException("exception decoding - please check password and data.", e);
                else
                    throw new PEMException(e.getMessage(), e);
            }
            return pemKeyPairParser.parse(keyBytes);
        }

        private final PEMKeyPairParser pemKeyPairParser;
        final PEMParser this$0;

        public KeyPairParser(PEMKeyPairParser pemKeyPairParser)
        {
            this$0 = PEMParser.this;
            super();
            this.pemKeyPairParser = pemKeyPairParser;
        }
    }


    public PEMParser(Reader reader)
    {
        super(reader);
        parsers.put("CERTIFICATE REQUEST", new PKCS10CertificationRequestParser());
        parsers.put("NEW CERTIFICATE REQUEST", new PKCS10CertificationRequestParser());
        parsers.put("CERTIFICATE", new X509CertificateParser());
        parsers.put("X509 CERTIFICATE", new X509CertificateParser());
        parsers.put("X509 CRL", new X509CRLParser());
        parsers.put("PKCS7", new PKCS7Parser());
        parsers.put("ATTRIBUTE CERTIFICATE", new X509AttributeCertificateParser());
        parsers.put("EC PARAMETERS", new ECCurveParamsParser());
        parsers.put("PUBLIC KEY", new PublicKeyParser());
        parsers.put("RSA PUBLIC KEY", new RSAPublicKeyParser());
        parsers.put("RSA PRIVATE KEY", new KeyPairParser(new RSAKeyPairParser()));
        parsers.put("DSA PRIVATE KEY", new KeyPairParser(new DSAKeyPairParser()));
        parsers.put("EC PRIVATE KEY", new KeyPairParser(new ECDSAKeyPairParser()));
        parsers.put("ENCRYPTED PRIVATE KEY", new EncryptedPrivateKeyParser());
        parsers.put("PRIVATE KEY", new PrivateKeyParser());
    }

    public Object readObject()
        throws IOException
    {
        PemObject obj = readPemObject();
        if(obj != null)
        {
            String type = obj.getType();
            if(parsers.containsKey(type))
                return ((PemObjectParser)parsers.get(type)).parseObject(obj);
            else
                throw new IOException((new StringBuilder()).append("unrecognised object: ").append(type).toString());
        } else
        {
            return null;
        }
    }

    private final Map parsers = new HashMap();
}
