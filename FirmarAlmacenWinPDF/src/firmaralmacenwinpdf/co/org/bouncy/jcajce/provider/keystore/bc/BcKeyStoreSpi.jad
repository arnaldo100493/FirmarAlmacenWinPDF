// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKeyStoreSpi.java

package co.org.bouncy.jcajce.provider.keystore.bc;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.crypto.io.*;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.jce.interfaces.BCKeyStore;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.io.Streams;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class BcKeyStoreSpi extends KeyStoreSpi
    implements BCKeyStore
{
    public static class Version1 extends BcKeyStoreSpi
    {

        public Version1()
        {
            super(1);
        }
    }

    public static class Std extends BcKeyStoreSpi
    {

        public Std()
        {
            super(2);
        }
    }

    public static class BouncyCastleStore extends BcKeyStoreSpi
    {

        public void engineLoad(InputStream stream, char password[])
            throws IOException
        {
            table.clear();
            if(stream == null)
                return;
            DataInputStream dIn = new DataInputStream(stream);
            int version = dIn.readInt();
            if(version != 2 && version != 0 && version != 1)
                throw new IOException("Wrong version of key store.");
            byte salt[] = new byte[dIn.readInt()];
            if(salt.length != 20)
                throw new IOException("Key store corrupted.");
            dIn.readFully(salt);
            int iterationCount = dIn.readInt();
            if(iterationCount < 0 || iterationCount > 4096)
                throw new IOException("Key store corrupted.");
            String cipherAlg;
            if(version == 0)
                cipherAlg = "OldPBEWithSHAAndTwofish-CBC";
            else
                cipherAlg = "PBEWithSHAAndTwofish-CBC";
            Cipher cipher = makePBECipher(cipherAlg, 2, password, salt, iterationCount);
            CipherInputStream cIn = new CipherInputStream(dIn, cipher);
            Digest dig = new SHA1Digest();
            DigestInputStream dgIn = new DigestInputStream(cIn, dig);
            loadStore(dgIn);
            byte hash[] = new byte[dig.getDigestSize()];
            dig.doFinal(hash, 0);
            byte oldHash[] = new byte[dig.getDigestSize()];
            Streams.readFully(cIn, oldHash);
            if(!Arrays.constantTimeAreEqual(hash, oldHash))
            {
                table.clear();
                throw new IOException("KeyStore integrity check failed.");
            } else
            {
                return;
            }
        }

        public void engineStore(OutputStream stream, char password[])
            throws IOException
        {
            DataOutputStream dOut = new DataOutputStream(stream);
            byte salt[] = new byte[20];
            int iterationCount = 1024 + (random.nextInt() & 0x3ff);
            random.nextBytes(salt);
            dOut.writeInt(version);
            dOut.writeInt(salt.length);
            dOut.write(salt);
            dOut.writeInt(iterationCount);
            Cipher cipher = makePBECipher("PBEWithSHAAndTwofish-CBC", 1, password, salt, iterationCount);
            CipherOutputStream cOut = new CipherOutputStream(dOut, cipher);
            DigestOutputStream dgOut = new DigestOutputStream(new SHA1Digest());
            saveStore(new TeeOutputStream(cOut, dgOut));
            byte dig[] = dgOut.getDigest();
            cOut.write(dig);
            cOut.close();
        }

        public BouncyCastleStore()
        {
            super(1);
        }
    }

    private class StoreEntry
    {

        int getType()
        {
            return type;
        }

        String getAlias()
        {
            return alias;
        }

        Object getObject()
        {
            return obj;
        }

        Object getObject(char password[])
            throws NoSuchAlgorithmException, UnrecoverableKeyException
        {
            if((password == null || password.length == 0) && (obj instanceof Key))
                return obj;
            if(type != 4) goto _L2; else goto _L1
_L1:
            ByteArrayInputStream bIn;
            DataInputStream dIn;
            bIn = new ByteArrayInputStream((byte[])(byte[])obj);
            dIn = new DataInputStream(bIn);
            CipherInputStream cIn;
            byte salt[] = new byte[dIn.readInt()];
            dIn.readFully(salt);
            int iterationCount = dIn.readInt();
            Cipher cipher = makePBECipher("PBEWithSHAAnd3-KeyTripleDES-CBC", 2, password, salt, iterationCount);
            cIn = new CipherInputStream(dIn, cipher);
            try
            {
                return decodeKey(new DataInputStream(cIn));
            }
            catch(Exception x)
            {
                bIn = new ByteArrayInputStream((byte[])(byte[])obj);
            }
            dIn = new DataInputStream(bIn);
            byte salt[] = new byte[dIn.readInt()];
            dIn.readFully(salt);
            int iterationCount = dIn.readInt();
            Cipher cipher = makePBECipher("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, password, salt, iterationCount);
            cIn = new CipherInputStream(dIn, cipher);
            Key k = null;
            try
            {
                k = decodeKey(new DataInputStream(cIn));
            }
            catch(Exception y)
            {
                bIn = new ByteArrayInputStream((byte[])(byte[])obj);
                dIn = new DataInputStream(bIn);
                salt = new byte[dIn.readInt()];
                dIn.readFully(salt);
                iterationCount = dIn.readInt();
                cipher = makePBECipher("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, password, salt, iterationCount);
                cIn = new CipherInputStream(dIn, cipher);
                k = decodeKey(new DataInputStream(cIn));
            }
            if(k != null)
            {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                DataOutputStream dOut = new DataOutputStream(bOut);
                dOut.writeInt(salt.length);
                dOut.write(salt);
                dOut.writeInt(iterationCount);
                Cipher out = makePBECipher("PBEWithSHAAnd3-KeyTripleDES-CBC", 1, password, salt, iterationCount);
                CipherOutputStream cOut = new CipherOutputStream(dOut, out);
                dOut = new DataOutputStream(cOut);
                encodeKey(k, dOut);
                dOut.close();
                obj = bOut.toByteArray();
                return k;
            }
            try
            {
                throw new UnrecoverableKeyException("no match");
            }
            catch(Exception e)
            {
                throw new UnrecoverableKeyException("no match");
            }
_L2:
            throw new RuntimeException("forget something!");
        }

        Certificate[] getCertificateChain()
        {
            return certChain;
        }

        Date getDate()
        {
            return date;
        }

        int type;
        String alias;
        Object obj;
        Certificate certChain[];
        Date date;
        final BcKeyStoreSpi this$0;

        StoreEntry(String alias, Certificate obj)
        {
            this$0 = BcKeyStoreSpi.this;
            super();
            date = new Date();
            type = 1;
            this.alias = alias;
            this.obj = obj;
            certChain = null;
        }

        StoreEntry(String alias, byte obj[], Certificate certChain[])
        {
            this$0 = BcKeyStoreSpi.this;
            super();
            date = new Date();
            type = 3;
            this.alias = alias;
            this.obj = obj;
            this.certChain = certChain;
        }

        StoreEntry(String alias, Key key, char password[], Certificate certChain[])
            throws Exception
        {
            this$0 = BcKeyStoreSpi.this;
            super();
            date = new Date();
            type = 4;
            this.alias = alias;
            this.certChain = certChain;
            byte salt[] = new byte[20];
            random.setSeed(System.currentTimeMillis());
            random.nextBytes(salt);
            int iterationCount = 1024 + (random.nextInt() & 0x3ff);
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DataOutputStream dOut = new DataOutputStream(bOut);
            dOut.writeInt(salt.length);
            dOut.write(salt);
            dOut.writeInt(iterationCount);
            Cipher cipher = makePBECipher("PBEWithSHAAnd3-KeyTripleDES-CBC", 1, password, salt, iterationCount);
            CipherOutputStream cOut = new CipherOutputStream(dOut, cipher);
            dOut = new DataOutputStream(cOut);
            encodeKey(key, dOut);
            dOut.close();
            obj = bOut.toByteArray();
        }

        StoreEntry(String alias, Date date, int type, Object obj)
        {
            this$0 = BcKeyStoreSpi.this;
            super();
            this.date = new Date();
            this.alias = alias;
            this.date = date;
            this.type = type;
            this.obj = obj;
        }

        StoreEntry(String alias, Date date, int type, Object obj, Certificate certChain[])
        {
            this$0 = BcKeyStoreSpi.this;
            super();
            this.date = new Date();
            this.alias = alias;
            this.date = date;
            this.type = type;
            this.obj = obj;
            this.certChain = certChain;
        }
    }


    public BcKeyStoreSpi(int version)
    {
        table = new Hashtable();
        random = new SecureRandom();
        this.version = version;
    }

    private void encodeCertificate(Certificate cert, DataOutputStream dOut)
        throws IOException
    {
        try
        {
            byte cEnc[] = cert.getEncoded();
            dOut.writeUTF(cert.getType());
            dOut.writeInt(cEnc.length);
            dOut.write(cEnc);
        }
        catch(CertificateEncodingException ex)
        {
            throw new IOException(ex.toString());
        }
    }

    private Certificate decodeCertificate(DataInputStream dIn)
        throws IOException
    {
        String type = dIn.readUTF();
        byte cEnc[] = new byte[dIn.readInt()];
        dIn.readFully(cEnc);
        try
        {
            CertificateFactory cFact = CertificateFactory.getInstance(type, "BC");
            ByteArrayInputStream bIn = new ByteArrayInputStream(cEnc);
            return cFact.generateCertificate(bIn);
        }
        catch(NoSuchProviderException ex)
        {
            throw new IOException(ex.toString());
        }
        catch(CertificateException ex)
        {
            throw new IOException(ex.toString());
        }
    }

    private void encodeKey(Key key, DataOutputStream dOut)
        throws IOException
    {
        byte enc[] = key.getEncoded();
        if(key instanceof PrivateKey)
            dOut.write(0);
        else
        if(key instanceof PublicKey)
            dOut.write(1);
        else
            dOut.write(2);
        dOut.writeUTF(key.getFormat());
        dOut.writeUTF(key.getAlgorithm());
        dOut.writeInt(enc.length);
        dOut.write(enc);
    }

    private Key decodeKey(DataInputStream dIn)
        throws IOException
    {
        int keyType;
        String algorithm;
        KeySpec spec;
        keyType = dIn.read();
        String format = dIn.readUTF();
        algorithm = dIn.readUTF();
        byte enc[] = new byte[dIn.readInt()];
        dIn.readFully(enc);
        if(format.equals("PKCS#8") || format.equals("PKCS8"))
            spec = new PKCS8EncodedKeySpec(enc);
        else
        if(format.equals("X.509") || format.equals("X509"))
            spec = new X509EncodedKeySpec(enc);
        else
        if(format.equals("RAW"))
            return new SecretKeySpec(enc, algorithm);
        else
            throw new IOException((new StringBuilder()).append("Key format ").append(format).append(" not recognised!").toString());
        keyType;
        JVM INSTR tableswitch 0 2: default 215
    //                   0 176
    //                   1 189
    //                   2 202;
           goto _L1 _L2 _L3 _L4
_L2:
        return KeyFactory.getInstance(algorithm, "BC").generatePrivate(spec);
_L3:
        try
        {
            return KeyFactory.getInstance(algorithm, "BC").generatePublic(spec);
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("Exception creating key: ").append(e.toString()).toString());
        }
_L4:
        return SecretKeyFactory.getInstance(algorithm, "BC").generateSecret(spec);
_L1:
        throw new IOException((new StringBuilder()).append("Key type ").append(keyType).append(" not recognised!").toString());
    }

    protected Cipher makePBECipher(String algorithm, int mode, char password[], byte salt[], int iterationCount)
        throws IOException
    {
        try
        {
            PBEKeySpec pbeSpec = new PBEKeySpec(password);
            SecretKeyFactory keyFact = SecretKeyFactory.getInstance(algorithm, "BC");
            PBEParameterSpec defParams = new PBEParameterSpec(salt, iterationCount);
            Cipher cipher = Cipher.getInstance(algorithm, "BC");
            cipher.init(mode, keyFact.generateSecret(pbeSpec), defParams);
            return cipher;
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("Error initialising store of key store: ").append(e).toString());
        }
    }

    public void setRandom(SecureRandom rand)
    {
        random = rand;
    }

    public Enumeration engineAliases()
    {
        return table.keys();
    }

    public boolean engineContainsAlias(String alias)
    {
        return table.get(alias) != null;
    }

    public void engineDeleteEntry(String alias)
        throws KeyStoreException
    {
        Object entry = table.get(alias);
        if(entry == null)
        {
            return;
        } else
        {
            table.remove(alias);
            return;
        }
    }

    public Certificate engineGetCertificate(String alias)
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        if(entry != null)
        {
            if(entry.getType() == 1)
                return (Certificate)entry.getObject();
            Certificate chain[] = entry.getCertificateChain();
            if(chain != null)
                return chain[0];
        }
        return null;
    }

    public String engineGetCertificateAlias(Certificate cert)
    {
        for(Enumeration e = table.elements(); e.hasMoreElements();)
        {
            StoreEntry entry = (StoreEntry)e.nextElement();
            if(entry.getObject() instanceof Certificate)
            {
                Certificate c = (Certificate)entry.getObject();
                if(c.equals(cert))
                    return entry.getAlias();
            } else
            {
                Certificate chain[] = entry.getCertificateChain();
                if(chain != null && chain[0].equals(cert))
                    return entry.getAlias();
            }
        }

        return null;
    }

    public Certificate[] engineGetCertificateChain(String alias)
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        if(entry != null)
            return entry.getCertificateChain();
        else
            return null;
    }

    public Date engineGetCreationDate(String alias)
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        if(entry != null)
            return entry.getDate();
        else
            return null;
    }

    public Key engineGetKey(String alias, char password[])
        throws NoSuchAlgorithmException, UnrecoverableKeyException
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        if(entry == null || entry.getType() == 1)
            return null;
        else
            return (Key)entry.getObject(password);
    }

    public boolean engineIsCertificateEntry(String alias)
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        return entry != null && entry.getType() == 1;
    }

    public boolean engineIsKeyEntry(String alias)
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        return entry != null && entry.getType() != 1;
    }

    public void engineSetCertificateEntry(String alias, Certificate cert)
        throws KeyStoreException
    {
        StoreEntry entry = (StoreEntry)table.get(alias);
        if(entry != null && entry.getType() != 1)
        {
            throw new KeyStoreException((new StringBuilder()).append("key store already has a key entry with alias ").append(alias).toString());
        } else
        {
            table.put(alias, new StoreEntry(alias, cert));
            return;
        }
    }

    public void engineSetKeyEntry(String alias, byte key[], Certificate chain[])
        throws KeyStoreException
    {
        table.put(alias, new StoreEntry(alias, key, chain));
    }

    public void engineSetKeyEntry(String alias, Key key, char password[], Certificate chain[])
        throws KeyStoreException
    {
        if((key instanceof PrivateKey) && chain == null)
            throw new KeyStoreException("no certificate chain for private key");
        try
        {
            table.put(alias, new StoreEntry(alias, key, password, chain));
        }
        catch(Exception e)
        {
            throw new KeyStoreException(e.toString());
        }
    }

    public int engineSize()
    {
        return table.size();
    }

    protected void loadStore(InputStream in)
        throws IOException
    {
        DataInputStream dIn = new DataInputStream(in);
        for(int type = dIn.read(); type > 0; type = dIn.read())
        {
            String alias = dIn.readUTF();
            Date date = new Date(dIn.readLong());
            int chainLength = dIn.readInt();
            Certificate chain[] = null;
            if(chainLength != 0)
            {
                chain = new Certificate[chainLength];
                for(int i = 0; i != chainLength; i++)
                    chain[i] = decodeCertificate(dIn);

            }
            switch(type)
            {
            case 1: // '\001'
                Certificate cert = decodeCertificate(dIn);
                table.put(alias, new StoreEntry(alias, date, 1, cert));
                break;

            case 2: // '\002'
                Key key = decodeKey(dIn);
                table.put(alias, new StoreEntry(alias, date, 2, key, chain));
                break;

            case 3: // '\003'
            case 4: // '\004'
                byte b[] = new byte[dIn.readInt()];
                dIn.readFully(b);
                table.put(alias, new StoreEntry(alias, date, type, b, chain));
                break;

            default:
                throw new RuntimeException("Unknown object type in store.");
            }
        }

    }

    protected void saveStore(OutputStream out)
        throws IOException
    {
        Enumeration e = table.elements();
        DataOutputStream dOut = new DataOutputStream(out);
        do
        {
            if(!e.hasMoreElements())
                break;
            StoreEntry entry = (StoreEntry)e.nextElement();
            dOut.write(entry.getType());
            dOut.writeUTF(entry.getAlias());
            dOut.writeLong(entry.getDate().getTime());
            Certificate chain[] = entry.getCertificateChain();
            if(chain == null)
            {
                dOut.writeInt(0);
            } else
            {
                dOut.writeInt(chain.length);
                for(int i = 0; i != chain.length; i++)
                    encodeCertificate(chain[i], dOut);

            }
            switch(entry.getType())
            {
            case 1: // '\001'
                encodeCertificate((Certificate)entry.getObject(), dOut);
                break;

            case 2: // '\002'
                encodeKey((Key)entry.getObject(), dOut);
                break;

            case 3: // '\003'
            case 4: // '\004'
                byte b[] = (byte[])(byte[])entry.getObject();
                dOut.writeInt(b.length);
                dOut.write(b);
                break;

            default:
                throw new RuntimeException("Unknown object type in store.");
            }
        } while(true);
        dOut.write(0);
    }

    public void engineLoad(InputStream stream, char password[])
        throws IOException
    {
        table.clear();
        if(stream == null)
            return;
        DataInputStream dIn = new DataInputStream(stream);
        int version = dIn.readInt();
        if(version != 2 && version != 0 && version != 1)
            throw new IOException("Wrong version of key store.");
        int saltLength = dIn.readInt();
        if(saltLength <= 0)
            throw new IOException("Invalid salt detected");
        byte salt[] = new byte[saltLength];
        dIn.readFully(salt);
        int iterationCount = dIn.readInt();
        HMac hMac = new HMac(new SHA1Digest());
        if(password != null && password.length != 0)
        {
            byte passKey[] = PBEParametersGenerator.PKCS12PasswordToBytes(password);
            PBEParametersGenerator pbeGen = new PKCS12ParametersGenerator(new SHA1Digest());
            pbeGen.init(passKey, salt, iterationCount);
            CipherParameters macParams;
            if(version != 2)
                macParams = pbeGen.generateDerivedMacParameters(hMac.getMacSize());
            else
                macParams = pbeGen.generateDerivedMacParameters(hMac.getMacSize() * 8);
            Arrays.fill(passKey, (byte)0);
            hMac.init(macParams);
            MacInputStream mIn = new MacInputStream(dIn, hMac);
            loadStore(mIn);
            byte mac[] = new byte[hMac.getMacSize()];
            hMac.doFinal(mac, 0);
            byte oldMac[] = new byte[hMac.getMacSize()];
            dIn.readFully(oldMac);
            if(!Arrays.constantTimeAreEqual(mac, oldMac))
            {
                table.clear();
                throw new IOException("KeyStore integrity check failed.");
            }
        } else
        {
            loadStore(dIn);
            byte oldMac[] = new byte[hMac.getMacSize()];
            dIn.readFully(oldMac);
        }
    }

    public void engineStore(OutputStream stream, char password[])
        throws IOException
    {
        DataOutputStream dOut = new DataOutputStream(stream);
        byte salt[] = new byte[20];
        int iterationCount = 1024 + (random.nextInt() & 0x3ff);
        random.nextBytes(salt);
        dOut.writeInt(version);
        dOut.writeInt(salt.length);
        dOut.write(salt);
        dOut.writeInt(iterationCount);
        HMac hMac = new HMac(new SHA1Digest());
        MacOutputStream mOut = new MacOutputStream(hMac);
        PBEParametersGenerator pbeGen = new PKCS12ParametersGenerator(new SHA1Digest());
        byte passKey[] = PBEParametersGenerator.PKCS12PasswordToBytes(password);
        pbeGen.init(passKey, salt, iterationCount);
        if(version < 2)
            hMac.init(pbeGen.generateDerivedMacParameters(hMac.getMacSize()));
        else
            hMac.init(pbeGen.generateDerivedMacParameters(hMac.getMacSize() * 8));
        for(int i = 0; i != passKey.length; i++)
            passKey[i] = 0;

        saveStore(new TeeOutputStream(dOut, mOut));
        byte mac[] = new byte[hMac.getMacSize()];
        hMac.doFinal(mac, 0);
        dOut.write(mac);
        dOut.close();
    }

    private static final int STORE_VERSION = 2;
    private static final int STORE_SALT_SIZE = 20;
    private static final String STORE_CIPHER = "PBEWithSHAAndTwofish-CBC";
    private static final int KEY_SALT_SIZE = 20;
    private static final int MIN_ITERATIONS = 1024;
    private static final String KEY_CIPHER = "PBEWithSHAAnd3-KeyTripleDES-CBC";
    static final int NULL = 0;
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int SECRET = 3;
    static final int SEALED = 4;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    protected Hashtable table;
    protected SecureRandom random;
    protected int version;


}
