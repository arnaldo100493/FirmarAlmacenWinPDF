// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12KeyStoreSpi.java

package co.org.bouncy.jcajce.provider.keystore.pkcs12;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.util.ASN1Dump;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.jcajce.provider.config.PKCS12StoreParameter;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.jcajce.provider.util.SecretKeyUtil;
import co.org.bouncy.jce.interfaces.BCKeyStore;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.provider.JDKPKCS12StoreParameter;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import co.org.bouncy.util.encoders.Hex;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class PKCS12KeyStoreSpi extends KeyStoreSpi
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore
{
    private static class IgnoresCaseHashtable
    {

        public void put(String key, Object value)
        {
            String lower = key != null ? Strings.toLowerCase(key) : null;
            String k = (String)keys.get(lower);
            if(k != null)
                orig.remove(k);
            keys.put(lower, key);
            orig.put(key, value);
        }

        public Enumeration keys()
        {
            return orig.keys();
        }

        public Object remove(String alias)
        {
            String k = (String)keys.remove(alias != null ? ((Object) (Strings.toLowerCase(alias))) : null);
            if(k == null)
                return null;
            else
                return orig.remove(k);
        }

        public Object get(String alias)
        {
            String k = (String)keys.get(alias != null ? ((Object) (Strings.toLowerCase(alias))) : null);
            if(k == null)
                return null;
            else
                return orig.get(k);
        }

        public Enumeration elements()
        {
            return orig.elements();
        }

        private Hashtable orig;
        private Hashtable keys;

        private IgnoresCaseHashtable()
        {
            orig = new Hashtable();
            keys = new Hashtable();
        }

    }

    public static class DefPKCS12KeyStore3DES extends PKCS12KeyStoreSpi
    {

        public DefPKCS12KeyStore3DES()
        {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    public static class DefPKCS12KeyStore extends PKCS12KeyStoreSpi
    {

        public DefPKCS12KeyStore()
        {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class BCPKCS12KeyStore3DES extends PKCS12KeyStoreSpi
    {

        public BCPKCS12KeyStore3DES()
        {
            super(PKCS12KeyStoreSpi.bcProvider, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    public static class BCPKCS12KeyStore extends PKCS12KeyStoreSpi
    {

        public BCPKCS12KeyStore()
        {
            super(PKCS12KeyStoreSpi.bcProvider, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    private class CertId
    {

        public int hashCode()
        {
            return Arrays.hashCode(id);
        }

        public boolean equals(Object o)
        {
            if(o == this)
                return true;
            if(!(o instanceof CertId))
            {
                return false;
            } else
            {
                CertId cId = (CertId)o;
                return Arrays.areEqual(id, cId.id);
            }
        }

        byte id[];
        final PKCS12KeyStoreSpi this$0;

        CertId(PublicKey key)
        {
            this$0 = PKCS12KeyStoreSpi.this;
            super();
            id = createSubjectKeyId(key).getKeyIdentifier();
        }

        CertId(byte id[])
        {
            this$0 = PKCS12KeyStoreSpi.this;
            super();
            this.id = id;
        }
    }


    public PKCS12KeyStoreSpi(Provider provider, ASN1ObjectIdentifier keyAlgorithm, ASN1ObjectIdentifier certAlgorithm)
    {
        keys = new IgnoresCaseHashtable();
        localIds = new Hashtable();
        certs = new IgnoresCaseHashtable();
        chainCerts = new Hashtable();
        keyCerts = new Hashtable();
        random = new SecureRandom();
        this.keyAlgorithm = keyAlgorithm;
        this.certAlgorithm = certAlgorithm;
        try
        {
            if(provider != null)
                certFact = CertificateFactory.getInstance("X.509", provider);
            else
                certFact = CertificateFactory.getInstance("X.509");
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("can't create cert factory - ").append(e.toString()).toString());
        }
    }

    private SubjectKeyIdentifier createSubjectKeyId(PublicKey pubKey)
    {
        try
        {
            SubjectPublicKeyInfo info = new SubjectPublicKeyInfo((ASN1Sequence)ASN1Primitive.fromByteArray(pubKey.getEncoded()));
            return new SubjectKeyIdentifier(info);
        }
        catch(Exception e)
        {
            throw new RuntimeException("error creating key");
        }
    }

    public void setRandom(SecureRandom rand)
    {
        random = rand;
    }

    public Enumeration engineAliases()
    {
        Hashtable tab = new Hashtable();
        Enumeration e;
        for(e = certs.keys(); e.hasMoreElements(); tab.put(e.nextElement(), "cert"));
        e = keys.keys();
        do
        {
            if(!e.hasMoreElements())
                break;
            String a = (String)e.nextElement();
            if(tab.get(a) == null)
                tab.put(a, "key");
        } while(true);
        return tab.keys();
    }

    public boolean engineContainsAlias(String alias)
    {
        return certs.get(alias) != null || keys.get(alias) != null;
    }

    public void engineDeleteEntry(String alias)
        throws KeyStoreException
    {
        Key k = (Key)keys.remove(alias);
        Certificate c = (Certificate)certs.remove(alias);
        if(c != null)
            chainCerts.remove(new CertId(c.getPublicKey()));
        if(k != null)
        {
            String id = (String)localIds.remove(alias);
            if(id != null)
                c = (Certificate)keyCerts.remove(id);
            if(c != null)
                chainCerts.remove(new CertId(c.getPublicKey()));
        }
    }

    public Certificate engineGetCertificate(String alias)
    {
        if(alias == null)
            throw new IllegalArgumentException("null alias passed to getCertificate.");
        Certificate c = (Certificate)certs.get(alias);
        if(c == null)
        {
            String id = (String)localIds.get(alias);
            if(id != null)
                c = (Certificate)keyCerts.get(id);
            else
                c = (Certificate)keyCerts.get(alias);
        }
        return c;
    }

    public String engineGetCertificateAlias(Certificate cert)
    {
        Enumeration c = certs.elements();
        Enumeration k = certs.keys();
        while(c.hasMoreElements()) 
        {
            Certificate tc = (Certificate)c.nextElement();
            String ta = (String)k.nextElement();
            if(tc.equals(cert))
                return ta;
        }
        c = keyCerts.elements();
        k = keyCerts.keys();
        while(c.hasMoreElements()) 
        {
            Certificate tc = (Certificate)c.nextElement();
            String ta = (String)k.nextElement();
            if(tc.equals(cert))
                return ta;
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String alias)
    {
        if(alias == null)
            throw new IllegalArgumentException("null alias passed to getCertificateChain.");
        if(!engineIsKeyEntry(alias))
            return null;
        Certificate c = engineGetCertificate(alias);
        if(c != null)
        {
            Vector cs = new Vector();
            while(c != null) 
            {
                X509Certificate x509c = (X509Certificate)c;
                Certificate nextC = null;
                byte bytes[] = x509c.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if(bytes != null)
                    try
                    {
                        ASN1InputStream aIn = new ASN1InputStream(bytes);
                        byte authBytes[] = ((ASN1OctetString)aIn.readObject()).getOctets();
                        aIn = new ASN1InputStream(authBytes);
                        AuthorityKeyIdentifier id = AuthorityKeyIdentifier.getInstance(aIn.readObject());
                        if(id.getKeyIdentifier() != null)
                            nextC = (Certificate)chainCerts.get(new CertId(id.getKeyIdentifier()));
                    }
                    catch(IOException e)
                    {
                        throw new RuntimeException(e.toString());
                    }
                if(nextC == null)
                {
                    Principal i = x509c.getIssuerDN();
                    Principal s = x509c.getSubjectDN();
                    if(!i.equals(s))
                    {
                        Enumeration e = chainCerts.keys();
                        do
                        {
                            if(!e.hasMoreElements())
                                break;
                            X509Certificate crt = (X509Certificate)chainCerts.get(e.nextElement());
                            Principal sub = crt.getSubjectDN();
                            if(!sub.equals(i))
                                continue;
                            try
                            {
                                x509c.verify(crt.getPublicKey());
                                nextC = crt;
                                break;
                            }
                            catch(Exception ex) { }
                        } while(true);
                    }
                }
                cs.addElement(c);
                if(nextC != c)
                    c = nextC;
                else
                    c = null;
            }
            Certificate certChain[] = new Certificate[cs.size()];
            for(int i = 0; i != certChain.length; i++)
                certChain[i] = (Certificate)cs.elementAt(i);

            return certChain;
        } else
        {
            return null;
        }
    }

    public Date engineGetCreationDate(String alias)
    {
        if(alias == null)
            throw new NullPointerException("alias == null");
        if(keys.get(alias) == null && certs.get(alias) == null)
            return null;
        else
            return new Date();
    }

    public Key engineGetKey(String alias, char password[])
        throws NoSuchAlgorithmException, UnrecoverableKeyException
    {
        if(alias == null)
            throw new IllegalArgumentException("null alias passed to getKey.");
        else
            return (Key)keys.get(alias);
    }

    public boolean engineIsCertificateEntry(String alias)
    {
        return certs.get(alias) != null && keys.get(alias) == null;
    }

    public boolean engineIsKeyEntry(String alias)
    {
        return keys.get(alias) != null;
    }

    public void engineSetCertificateEntry(String alias, Certificate cert)
        throws KeyStoreException
    {
        if(keys.get(alias) != null)
        {
            throw new KeyStoreException((new StringBuilder()).append("There is a key entry with the name ").append(alias).append(".").toString());
        } else
        {
            certs.put(alias, cert);
            chainCerts.put(new CertId(cert.getPublicKey()), cert);
            return;
        }
    }

    public void engineSetKeyEntry(String alias, byte key[], Certificate chain[])
        throws KeyStoreException
    {
        throw new RuntimeException("operation not supported");
    }

    public void engineSetKeyEntry(String alias, Key key, char password[], Certificate chain[])
        throws KeyStoreException
    {
        if(!(key instanceof PrivateKey))
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        if((key instanceof PrivateKey) && chain == null)
            throw new KeyStoreException("no certificate chain for private key");
        if(keys.get(alias) != null)
            engineDeleteEntry(alias);
        keys.put(alias, key);
        if(chain != null)
        {
            certs.put(alias, chain[0]);
            for(int i = 0; i != chain.length; i++)
                chainCerts.put(new CertId(chain[i].getPublicKey()), chain[i]);

        }
    }

    public int engineSize()
    {
        Hashtable tab = new Hashtable();
        Enumeration e;
        for(e = certs.keys(); e.hasMoreElements(); tab.put(e.nextElement(), "cert"));
        e = keys.keys();
        do
        {
            if(!e.hasMoreElements())
                break;
            String a = (String)e.nextElement();
            if(tab.get(a) == null)
                tab.put(a, "key");
        } while(true);
        return tab.size();
    }

    protected PrivateKey unwrapKey(AlgorithmIdentifier algId, byte data[], char password[], boolean wrongPKCS12Zero)
        throws IOException
    {
        ASN1ObjectIdentifier algorithm;
        algorithm = algId.getAlgorithm();
        PBES2Parameters alg;
        PBKDF2Params func;
        SecretKeyFactory keyFact;
        javax.crypto.SecretKey k;
        Cipher cipher;
        try
        {
            if(algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds))
            {
                PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algId.getParameters());
                PBEKeySpec pbeSpec = new PBEKeySpec(password);
                SecretKeyFactory keyFact = SecretKeyFactory.getInstance(algorithm.getId(), bcProvider);
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                javax.crypto.SecretKey k = keyFact.generateSecret(pbeSpec);
                ((BCPBEKey)k).setTryWrongPKCS12Zero(wrongPKCS12Zero);
                Cipher cipher = Cipher.getInstance(algorithm.getId(), bcProvider);
                cipher.init(4, k, defParams);
                return (PrivateKey)cipher.unwrap(data, "", 2);
            }
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("exception unwrapping private key - ").append(e.toString()).toString());
        }
        if(algorithm.equals(PKCSObjectIdentifiers.id_PBES2))
        {
            alg = PBES2Parameters.getInstance(algId.getParameters());
            func = PBKDF2Params.getInstance(alg.getKeyDerivationFunc().getParameters());
            keyFact = SecretKeyFactory.getInstance(alg.getKeyDerivationFunc().getAlgorithm().getId(), bcProvider);
            k = keyFact.generateSecret(new PBEKeySpec(password, func.getSalt(), func.getIterationCount().intValue(), SecretKeyUtil.getKeySize(alg.getEncryptionScheme().getAlgorithm())));
            cipher = Cipher.getInstance(alg.getEncryptionScheme().getAlgorithm().getId(), bcProvider);
            cipher.init(4, k, new IvParameterSpec(ASN1OctetString.getInstance(alg.getEncryptionScheme().getParameters()).getOctets()));
            return (PrivateKey)cipher.unwrap(data, "", 2);
        }
        throw new IOException((new StringBuilder()).append("exception unwrapping private key - cannot recognise: ").append(algorithm).toString());
    }

    protected byte[] wrapKey(String algorithm, Key key, PKCS12PBEParams pbeParams, char password[])
        throws IOException
    {
        PBEKeySpec pbeSpec = new PBEKeySpec(password);
        byte out[];
        try
        {
            SecretKeyFactory keyFact = SecretKeyFactory.getInstance(algorithm, bcProvider);
            PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
            Cipher cipher = Cipher.getInstance(algorithm, bcProvider);
            cipher.init(3, keyFact.generateSecret(pbeSpec), defParams);
            out = cipher.wrap(key);
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("exception encrypting data - ").append(e.toString()).toString());
        }
        return out;
    }

    protected byte[] cryptData(boolean forEncryption, AlgorithmIdentifier algId, char password[], boolean wrongPKCS12Zero, byte data[])
        throws IOException
    {
        String algorithm = algId.getAlgorithm().getId();
        PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algId.getParameters());
        PBEKeySpec pbeSpec = new PBEKeySpec(password);
        try
        {
            SecretKeyFactory keyFact = SecretKeyFactory.getInstance(algorithm, bcProvider);
            PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
            BCPBEKey key = (BCPBEKey)keyFact.generateSecret(pbeSpec);
            key.setTryWrongPKCS12Zero(wrongPKCS12Zero);
            Cipher cipher = Cipher.getInstance(algorithm, bcProvider);
            int mode = forEncryption ? 1 : 2;
            cipher.init(mode, key, defParams);
            return cipher.doFinal(data);
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("exception decrypting data - ").append(e.toString()).toString());
        }
    }

    public void engineLoad(InputStream stream, char password[])
        throws IOException
    {
        if(stream == null)
            return;
        if(password == null)
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        BufferedInputStream bufIn = new BufferedInputStream(stream);
        bufIn.mark(10);
        int head = bufIn.read();
        if(head != 48)
            throw new IOException("stream does not represent a PKCS12 key store");
        bufIn.reset();
        ASN1InputStream bIn = new ASN1InputStream(bufIn);
        ASN1Sequence obj = (ASN1Sequence)bIn.readObject();
        Pfx bag = Pfx.getInstance(obj);
        ContentInfo info = bag.getAuthSafe();
        Vector chain = new Vector();
        boolean unmarkedKey = false;
        boolean wrongPKCS12Zero = false;
        if(bag.getMacData() != null)
        {
            MacData mData = bag.getMacData();
            DigestInfo dInfo = mData.getMac();
            AlgorithmIdentifier algId = dInfo.getAlgorithmId();
            byte salt[] = mData.getSalt();
            int itCount = mData.getIterationCount().intValue();
            byte data[] = ((ASN1OctetString)info.getContent()).getOctets();
            try
            {
                byte res[] = calculatePbeMac(algId.getAlgorithm(), salt, itCount, password, false, data);
                byte dig[] = dInfo.getDigest();
                if(!Arrays.constantTimeAreEqual(res, dig))
                {
                    if(password.length > 0)
                        throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                    res = calculatePbeMac(algId.getAlgorithm(), salt, itCount, password, true, data);
                    if(!Arrays.constantTimeAreEqual(res, dig))
                        throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                    wrongPKCS12Zero = true;
                }
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new IOException((new StringBuilder()).append("error constructing MAC: ").append(e.toString()).toString());
            }
        }
        keys = new IgnoresCaseHashtable();
        localIds = new Hashtable();
        if(info.getContentType().equals(data))
        {
            bIn = new ASN1InputStream(((ASN1OctetString)info.getContent()).getOctets());
            AuthenticatedSafe authSafe = AuthenticatedSafe.getInstance(bIn.readObject());
            ContentInfo c[] = authSafe.getContentInfo();
label0:
            for(int i = 0; i != c.length; i++)
            {
                if(c[i].getContentType().equals(data))
                {
                    ASN1InputStream dIn = new ASN1InputStream(((ASN1OctetString)c[i].getContent()).getOctets());
                    ASN1Sequence seq = (ASN1Sequence)dIn.readObject();
                    int j = 0;
                    do
                    {
                        if(j == seq.size())
                            continue label0;
                        SafeBag b = SafeBag.getInstance(seq.getObjectAt(j));
                        if(b.getBagId().equals(pkcs8ShroudedKeyBag))
                        {
                            EncryptedPrivateKeyInfo eIn = EncryptedPrivateKeyInfo.getInstance(b.getBagValue());
                            PrivateKey privKey = unwrapKey(eIn.getEncryptionAlgorithm(), eIn.getEncryptedData(), password, wrongPKCS12Zero);
                            PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier)privKey;
                            String alias = null;
                            ASN1OctetString localId = null;
                            if(b.getBagAttributes() != null)
                            {
                                Enumeration e = b.getBagAttributes().getObjects();
                                do
                                {
                                    if(!e.hasMoreElements())
                                        break;
                                    ASN1Sequence sq = (ASN1Sequence)e.nextElement();
                                    ASN1ObjectIdentifier aOid = (ASN1ObjectIdentifier)sq.getObjectAt(0);
                                    ASN1Set attrSet = (ASN1Set)sq.getObjectAt(1);
                                    ASN1Primitive attr = null;
                                    if(attrSet.size() > 0)
                                    {
                                        attr = (ASN1Primitive)attrSet.getObjectAt(0);
                                        ASN1Encodable existing = bagAttr.getBagAttribute(aOid);
                                        if(existing != null)
                                        {
                                            if(!existing.toASN1Primitive().equals(attr))
                                                throw new IOException("attempt to add existing attribute with different value");
                                        } else
                                        {
                                            bagAttr.setBagAttribute(aOid, attr);
                                        }
                                    }
                                    if(aOid.equals(pkcs_9_at_friendlyName))
                                    {
                                        alias = ((DERBMPString)attr).getString();
                                        keys.put(alias, privKey);
                                    } else
                                    if(aOid.equals(pkcs_9_at_localKeyId))
                                        localId = (ASN1OctetString)attr;
                                } while(true);
                            }
                            if(localId != null)
                            {
                                String name = new String(Hex.encode(localId.getOctets()));
                                if(alias == null)
                                    keys.put(name, privKey);
                                else
                                    localIds.put(alias, name);
                            } else
                            {
                                unmarkedKey = true;
                                keys.put("unmarked", privKey);
                            }
                        } else
                        if(b.getBagId().equals(certBag))
                        {
                            chain.addElement(b);
                        } else
                        {
                            System.out.println((new StringBuilder()).append("extra in data ").append(b.getBagId()).toString());
                            System.out.println(ASN1Dump.dumpAsString(b));
                        }
                        j++;
                    } while(true);
                }
                if(c[i].getContentType().equals(encryptedData))
                {
                    EncryptedData d = EncryptedData.getInstance(c[i].getContent());
                    byte octets[] = cryptData(false, d.getEncryptionAlgorithm(), password, wrongPKCS12Zero, d.getContent().getOctets());
                    ASN1Sequence seq = (ASN1Sequence)ASN1Primitive.fromByteArray(octets);
                    int j = 0;
                    do
                    {
                        if(j == seq.size())
                            continue label0;
                        SafeBag b = SafeBag.getInstance(seq.getObjectAt(j));
                        if(b.getBagId().equals(certBag))
                            chain.addElement(b);
                        else
                        if(b.getBagId().equals(pkcs8ShroudedKeyBag))
                        {
                            EncryptedPrivateKeyInfo eIn = EncryptedPrivateKeyInfo.getInstance(b.getBagValue());
                            PrivateKey privKey = unwrapKey(eIn.getEncryptionAlgorithm(), eIn.getEncryptedData(), password, wrongPKCS12Zero);
                            PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier)privKey;
                            String alias = null;
                            ASN1OctetString localId = null;
                            Enumeration e = b.getBagAttributes().getObjects();
                            do
                            {
                                if(!e.hasMoreElements())
                                    break;
                                ASN1Sequence sq = (ASN1Sequence)e.nextElement();
                                ASN1ObjectIdentifier aOid = (ASN1ObjectIdentifier)sq.getObjectAt(0);
                                ASN1Set attrSet = (ASN1Set)sq.getObjectAt(1);
                                ASN1Primitive attr = null;
                                if(attrSet.size() > 0)
                                {
                                    attr = (ASN1Primitive)attrSet.getObjectAt(0);
                                    ASN1Encodable existing = bagAttr.getBagAttribute(aOid);
                                    if(existing != null)
                                    {
                                        if(!existing.toASN1Primitive().equals(attr))
                                            throw new IOException("attempt to add existing attribute with different value");
                                    } else
                                    {
                                        bagAttr.setBagAttribute(aOid, attr);
                                    }
                                }
                                if(aOid.equals(pkcs_9_at_friendlyName))
                                {
                                    alias = ((DERBMPString)attr).getString();
                                    keys.put(alias, privKey);
                                } else
                                if(aOid.equals(pkcs_9_at_localKeyId))
                                    localId = (ASN1OctetString)attr;
                            } while(true);
                            String name = new String(Hex.encode(localId.getOctets()));
                            if(alias == null)
                                keys.put(name, privKey);
                            else
                                localIds.put(alias, name);
                        } else
                        if(b.getBagId().equals(keyBag))
                        {
                            PrivateKeyInfo kInfo = PrivateKeyInfo.getInstance(b.getBagValue());
                            PrivateKey privKey = BouncyCastleProvider.getPrivateKey(kInfo);
                            PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier)privKey;
                            String alias = null;
                            ASN1OctetString localId = null;
                            Enumeration e = b.getBagAttributes().getObjects();
                            do
                            {
                                if(!e.hasMoreElements())
                                    break;
                                ASN1Sequence sq = (ASN1Sequence)e.nextElement();
                                ASN1ObjectIdentifier aOid = (ASN1ObjectIdentifier)sq.getObjectAt(0);
                                ASN1Set attrSet = (ASN1Set)sq.getObjectAt(1);
                                ASN1Primitive attr = null;
                                if(attrSet.size() > 0)
                                {
                                    attr = (ASN1Primitive)attrSet.getObjectAt(0);
                                    ASN1Encodable existing = bagAttr.getBagAttribute(aOid);
                                    if(existing != null)
                                    {
                                        if(!existing.toASN1Primitive().equals(attr))
                                            throw new IOException("attempt to add existing attribute with different value");
                                    } else
                                    {
                                        bagAttr.setBagAttribute(aOid, attr);
                                    }
                                }
                                if(aOid.equals(pkcs_9_at_friendlyName))
                                {
                                    alias = ((DERBMPString)attr).getString();
                                    keys.put(alias, privKey);
                                } else
                                if(aOid.equals(pkcs_9_at_localKeyId))
                                    localId = (ASN1OctetString)attr;
                            } while(true);
                            String name = new String(Hex.encode(localId.getOctets()));
                            if(alias == null)
                                keys.put(name, privKey);
                            else
                                localIds.put(alias, name);
                        } else
                        {
                            System.out.println((new StringBuilder()).append("extra in encryptedData ").append(b.getBagId()).toString());
                            System.out.println(ASN1Dump.dumpAsString(b));
                        }
                        j++;
                    } while(true);
                }
                System.out.println((new StringBuilder()).append("extra ").append(c[i].getContentType().getId()).toString());
                System.out.println((new StringBuilder()).append("extra ").append(ASN1Dump.dumpAsString(c[i].getContent())).toString());
            }

        }
        certs = new IgnoresCaseHashtable();
        chainCerts = new Hashtable();
        keyCerts = new Hashtable();
        for(int i = 0; i != chain.size(); i++)
        {
            SafeBag b = (SafeBag)chain.elementAt(i);
            CertBag cb = CertBag.getInstance(b.getBagValue());
            if(!cb.getCertId().equals(x509Certificate))
                throw new RuntimeException((new StringBuilder()).append("Unsupported certificate type: ").append(cb.getCertId()).toString());
            Certificate cert;
            try
            {
                ByteArrayInputStream cIn = new ByteArrayInputStream(((ASN1OctetString)cb.getCertValue()).getOctets());
                cert = certFact.generateCertificate(cIn);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.toString());
            }
            ASN1OctetString localId = null;
            String alias = null;
            if(b.getBagAttributes() != null)
            {
                Enumeration e = b.getBagAttributes().getObjects();
                do
                {
                    if(!e.hasMoreElements())
                        break;
                    ASN1Sequence sq = (ASN1Sequence)e.nextElement();
                    ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)sq.getObjectAt(0);
                    ASN1Primitive attr = (ASN1Primitive)((ASN1Set)sq.getObjectAt(1)).getObjectAt(0);
                    PKCS12BagAttributeCarrier bagAttr = null;
                    if(cert instanceof PKCS12BagAttributeCarrier)
                    {
                        bagAttr = (PKCS12BagAttributeCarrier)cert;
                        ASN1Encodable existing = bagAttr.getBagAttribute(oid);
                        if(existing != null)
                        {
                            if(!existing.toASN1Primitive().equals(attr))
                                throw new IOException("attempt to add existing attribute with different value");
                        } else
                        {
                            bagAttr.setBagAttribute(oid, attr);
                        }
                    }
                    if(oid.equals(pkcs_9_at_friendlyName))
                        alias = ((DERBMPString)attr).getString();
                    else
                    if(oid.equals(pkcs_9_at_localKeyId))
                        localId = (ASN1OctetString)attr;
                } while(true);
            }
            chainCerts.put(new CertId(cert.getPublicKey()), cert);
            if(unmarkedKey)
            {
                if(keyCerts.isEmpty())
                {
                    String name = new String(Hex.encode(createSubjectKeyId(cert.getPublicKey()).getKeyIdentifier()));
                    keyCerts.put(name, cert);
                    keys.put(name, keys.remove("unmarked"));
                }
                continue;
            }
            if(localId != null)
            {
                String name = new String(Hex.encode(localId.getOctets()));
                keyCerts.put(name, cert);
            }
            if(alias != null)
                certs.put(alias, cert);
        }

    }

    public void engineStore(java.security.KeyStore.LoadStoreParameter param)
        throws IOException, NoSuchAlgorithmException, CertificateException
    {
        if(param == null)
            throw new IllegalArgumentException("'param' arg cannot be null");
        if(!(param instanceof PKCS12StoreParameter) && !(param instanceof JDKPKCS12StoreParameter))
            throw new IllegalArgumentException((new StringBuilder()).append("No support for 'param' of type ").append(param.getClass().getName()).toString());
        PKCS12StoreParameter bcParam;
        if(param instanceof PKCS12StoreParameter)
            bcParam = (PKCS12StoreParameter)param;
        else
            bcParam = new PKCS12StoreParameter(((JDKPKCS12StoreParameter)param).getOutputStream(), param.getProtectionParameter(), ((JDKPKCS12StoreParameter)param).isUseDEREncoding());
        java.security.KeyStore.ProtectionParameter protParam = param.getProtectionParameter();
        char password[];
        if(protParam == null)
            password = null;
        else
        if(protParam instanceof java.security.KeyStore.PasswordProtection)
            password = ((java.security.KeyStore.PasswordProtection)protParam).getPassword();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("No support for protection parameter of type ").append(protParam.getClass().getName()).toString());
        doStore(bcParam.getOutputStream(), password, bcParam.isForDEREncoding());
    }

    public void engineStore(OutputStream stream, char password[])
        throws IOException
    {
        doStore(stream, password, false);
    }

    private void doStore(OutputStream stream, char password[], boolean useDEREncoding)
        throws IOException
    {
        BEROctetString keyString;
        ASN1EncodableVector certSeq;
        AlgorithmIdentifier cAlgId;
        Hashtable doneCerts;
        Enumeration cs;
        Certificate cert;
        boolean cAttrSet;
        if(password == null)
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        ASN1EncodableVector keyS = new ASN1EncodableVector();
        SafeBag kBag;
        for(Enumeration ks = keys.keys(); ks.hasMoreElements(); keyS.add(kBag))
        {
            byte kSalt[] = new byte[20];
            random.nextBytes(kSalt);
            String name = (String)ks.nextElement();
            PrivateKey privKey = (PrivateKey)keys.get(name);
            PKCS12PBEParams kParams = new PKCS12PBEParams(kSalt, 1024);
            byte kBytes[] = wrapKey(keyAlgorithm.getId(), privKey, kParams, password);
            AlgorithmIdentifier kAlgId = new AlgorithmIdentifier(keyAlgorithm, kParams.toASN1Primitive());
            EncryptedPrivateKeyInfo kInfo = new EncryptedPrivateKeyInfo(kAlgId, kBytes);
            boolean attrSet = false;
            ASN1EncodableVector kName = new ASN1EncodableVector();
            if(privKey instanceof PKCS12BagAttributeCarrier)
            {
                PKCS12BagAttributeCarrier bagAttrs = (PKCS12BagAttributeCarrier)privKey;
                DERBMPString nm = (DERBMPString)bagAttrs.getBagAttribute(pkcs_9_at_friendlyName);
                if(nm == null || !nm.getString().equals(name))
                    bagAttrs.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(name));
                if(bagAttrs.getBagAttribute(pkcs_9_at_localKeyId) == null)
                {
                    Certificate ct = engineGetCertificate(name);
                    bagAttrs.setBagAttribute(pkcs_9_at_localKeyId, createSubjectKeyId(ct.getPublicKey()));
                }
                ASN1EncodableVector kSeq;
                for(Enumeration e = bagAttrs.getBagAttributeKeys(); e.hasMoreElements(); kName.add(new DERSequence(kSeq)))
                {
                    ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                    kSeq = new ASN1EncodableVector();
                    kSeq.add(oid);
                    kSeq.add(new DERSet(bagAttrs.getBagAttribute(oid)));
                    attrSet = true;
                }

            }
            if(!attrSet)
            {
                ASN1EncodableVector kSeq = new ASN1EncodableVector();
                Certificate ct = engineGetCertificate(name);
                kSeq.add(pkcs_9_at_localKeyId);
                kSeq.add(new DERSet(createSubjectKeyId(ct.getPublicKey())));
                kName.add(new DERSequence(kSeq));
                kSeq = new ASN1EncodableVector();
                kSeq.add(pkcs_9_at_friendlyName);
                kSeq.add(new DERSet(new DERBMPString(name)));
                kName.add(new DERSequence(kSeq));
            }
            kBag = new SafeBag(pkcs8ShroudedKeyBag, kInfo.toASN1Primitive(), new DERSet(kName));
        }

        byte keySEncoded[] = (new DERSequence(keyS)).getEncoded("DER");
        keyString = new BEROctetString(keySEncoded);
        byte cSalt[] = new byte[20];
        random.nextBytes(cSalt);
        certSeq = new ASN1EncodableVector();
        PKCS12PBEParams cParams = new PKCS12PBEParams(cSalt, 1024);
        cAlgId = new AlgorithmIdentifier(certAlgorithm, cParams.toASN1Primitive());
        doneCerts = new Hashtable();
        for(cs = keys.keys(); cs.hasMoreElements();)
            try
            {
                String name = (String)cs.nextElement();
                cert = engineGetCertificate(name);
                cAttrSet = false;
                CertBag cBag = new CertBag(x509Certificate, new DEROctetString(cert.getEncoded()));
                ASN1EncodableVector fName = new ASN1EncodableVector();
                if(cert instanceof PKCS12BagAttributeCarrier)
                {
                    PKCS12BagAttributeCarrier bagAttrs = (PKCS12BagAttributeCarrier)cert;
                    DERBMPString nm = (DERBMPString)bagAttrs.getBagAttribute(pkcs_9_at_friendlyName);
                    if(nm == null || !nm.getString().equals(name))
                        bagAttrs.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(name));
                    if(bagAttrs.getBagAttribute(pkcs_9_at_localKeyId) == null)
                        bagAttrs.setBagAttribute(pkcs_9_at_localKeyId, createSubjectKeyId(cert.getPublicKey()));
                    for(Enumeration e = bagAttrs.getBagAttributeKeys(); e.hasMoreElements();)
                    {
                        ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                        ASN1EncodableVector fSeq = new ASN1EncodableVector();
                        fSeq.add(oid);
                        fSeq.add(new DERSet(bagAttrs.getBagAttribute(oid)));
                        fName.add(new DERSequence(fSeq));
                        cAttrSet = true;
                    }

                }
                if(!cAttrSet)
                {
                    ASN1EncodableVector fSeq = new ASN1EncodableVector();
                    fSeq.add(pkcs_9_at_localKeyId);
                    fSeq.add(new DERSet(createSubjectKeyId(cert.getPublicKey())));
                    fName.add(new DERSequence(fSeq));
                    fSeq = new ASN1EncodableVector();
                    fSeq.add(pkcs_9_at_friendlyName);
                    fSeq.add(new DERSet(new DERBMPString(name)));
                    fName.add(new DERSequence(fSeq));
                }
                SafeBag sBag = new SafeBag(certBag, cBag.toASN1Primitive(), new DERSet(fName));
                certSeq.add(sBag);
                doneCerts.put(cert, cert);
            }
            catch(CertificateEncodingException e)
            {
                throw new IOException((new StringBuilder()).append("Error encoding certificate: ").append(e.toString()).toString());
            }

        cs = certs.keys();
_L2:
        if(!cs.hasMoreElements())
            break; /* Loop/switch isn't completed */
        String certId;
        certId = (String)cs.nextElement();
        cert = (Certificate)certs.get(certId);
        cAttrSet = false;
        if(keys.get(certId) == null)
            try
            {
                CertBag cBag = new CertBag(x509Certificate, new DEROctetString(cert.getEncoded()));
                ASN1EncodableVector fName = new ASN1EncodableVector();
                if(cert instanceof PKCS12BagAttributeCarrier)
                {
                    PKCS12BagAttributeCarrier bagAttrs = (PKCS12BagAttributeCarrier)cert;
                    DERBMPString nm = (DERBMPString)bagAttrs.getBagAttribute(pkcs_9_at_friendlyName);
                    if(nm == null || !nm.getString().equals(certId))
                        bagAttrs.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(certId));
                    Enumeration e = bagAttrs.getBagAttributeKeys();
                    do
                    {
                        if(!e.hasMoreElements())
                            break;
                        ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                        if(!oid.equals(PKCSObjectIdentifiers.pkcs_9_at_localKeyId))
                        {
                            ASN1EncodableVector fSeq = new ASN1EncodableVector();
                            fSeq.add(oid);
                            fSeq.add(new DERSet(bagAttrs.getBagAttribute(oid)));
                            fName.add(new DERSequence(fSeq));
                            cAttrSet = true;
                        }
                    } while(true);
                }
                if(!cAttrSet)
                {
                    ASN1EncodableVector fSeq = new ASN1EncodableVector();
                    fSeq.add(pkcs_9_at_friendlyName);
                    fSeq.add(new DERSet(new DERBMPString(certId)));
                    fName.add(new DERSequence(fSeq));
                }
                SafeBag sBag = new SafeBag(certBag, cBag.toASN1Primitive(), new DERSet(fName));
                certSeq.add(sBag);
                doneCerts.put(cert, cert);
            }
            catch(CertificateEncodingException e)
            {
                throw new IOException((new StringBuilder()).append("Error encoding certificate: ").append(e.toString()).toString());
            }
        if(true) goto _L2; else goto _L1
_L1:
        cs = chainCerts.keys();
_L4:
        if(!cs.hasMoreElements())
            break; /* Loop/switch isn't completed */
        e = (CertId)cs.nextElement();
        cert = (Certificate)chainCerts.get(e);
        if(doneCerts.get(cert) == null)
            try
            {
                CertBag cBag = new CertBag(x509Certificate, new DEROctetString(cert.getEncoded()));
                ASN1EncodableVector fName = new ASN1EncodableVector();
                if(cert instanceof PKCS12BagAttributeCarrier)
                {
                    PKCS12BagAttributeCarrier bagAttrs = (PKCS12BagAttributeCarrier)cert;
                    Enumeration e = bagAttrs.getBagAttributeKeys();
                    do
                    {
                        if(!e.hasMoreElements())
                            break;
                        ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                        if(!oid.equals(PKCSObjectIdentifiers.pkcs_9_at_localKeyId))
                        {
                            ASN1EncodableVector fSeq = new ASN1EncodableVector();
                            fSeq.add(oid);
                            fSeq.add(new DERSet(bagAttrs.getBagAttribute(oid)));
                            fName.add(new DERSequence(fSeq));
                        }
                    } while(true);
                }
                SafeBag sBag = new SafeBag(certBag, cBag.toASN1Primitive(), new DERSet(fName));
                certSeq.add(sBag);
            }
            catch(CertificateEncodingException e)
            {
                throw new IOException((new StringBuilder()).append("Error encoding certificate: ").append(e.toString()).toString());
            }
        if(true) goto _L4; else goto _L3
_L3:
        byte certSeqEncoded[] = (new DERSequence(certSeq)).getEncoded("DER");
        byte certBytes[] = cryptData(true, cAlgId, password, false, certSeqEncoded);
        EncryptedData cInfo = new EncryptedData(data, cAlgId, new BEROctetString(certBytes));
        ContentInfo info[] = {
            new ContentInfo(data, keyString), new ContentInfo(encryptedData, cInfo.toASN1Primitive())
        };
        AuthenticatedSafe auth = new AuthenticatedSafe(info);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DEROutputStream asn1Out;
        if(useDEREncoding)
            asn1Out = new DEROutputStream(bOut);
        else
            asn1Out = new BEROutputStream(bOut);
        asn1Out.writeObject(auth);
        byte pkg[] = bOut.toByteArray();
        ContentInfo mainInfo = new ContentInfo(data, new BEROctetString(pkg));
        byte mSalt[] = new byte[20];
        int itCount = 1024;
        random.nextBytes(mSalt);
        byte data[] = ((ASN1OctetString)mainInfo.getContent()).getOctets();
        MacData mData;
        try
        {
            byte res[] = calculatePbeMac(id_SHA1, mSalt, itCount, password, false, data);
            AlgorithmIdentifier algId = new AlgorithmIdentifier(id_SHA1, DERNull.INSTANCE);
            DigestInfo dInfo = new DigestInfo(algId, res);
            mData = new MacData(dInfo, mSalt, itCount);
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("error constructing MAC: ").append(e.toString()).toString());
        }
        Pfx pfx = new Pfx(mainInfo, mData);
        if(useDEREncoding)
            asn1Out = new DEROutputStream(stream);
        else
            asn1Out = new BEROutputStream(stream);
        asn1Out.writeObject(pfx);
        return;
    }

    private static byte[] calculatePbeMac(ASN1ObjectIdentifier oid, byte salt[], int itCount, char password[], boolean wrongPkcs12Zero, byte data[])
        throws Exception
    {
        SecretKeyFactory keyFact = SecretKeyFactory.getInstance(oid.getId(), bcProvider);
        PBEParameterSpec defParams = new PBEParameterSpec(salt, itCount);
        PBEKeySpec pbeSpec = new PBEKeySpec(password);
        BCPBEKey key = (BCPBEKey)keyFact.generateSecret(pbeSpec);
        key.setTryWrongPKCS12Zero(wrongPkcs12Zero);
        Mac mac = Mac.getInstance(oid.getId(), bcProvider);
        mac.init(key, defParams);
        mac.update(data);
        return mac.doFinal();
    }

    private static final int SALT_SIZE = 20;
    private static final int MIN_ITERATIONS = 1024;
    private static final Provider bcProvider = new BouncyCastleProvider();
    private IgnoresCaseHashtable keys;
    private Hashtable localIds;
    private IgnoresCaseHashtable certs;
    private Hashtable chainCerts;
    private Hashtable keyCerts;
    static final int NULL = 0;
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int SECRET = 3;
    static final int SEALED = 4;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    protected SecureRandom random;
    private CertificateFactory certFact;
    private ASN1ObjectIdentifier keyAlgorithm;
    private ASN1ObjectIdentifier certAlgorithm;



}
