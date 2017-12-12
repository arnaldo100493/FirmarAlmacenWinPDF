// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsUtils.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.util.*;
import co.org.bouncy.util.io.Streams;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            SignatureAndHashAlgorithm, TlsFatalAlert, CombinedHash, TlsDSSSigner, 
//            TlsECDSASigner, TlsRSASigner, ProtocolVersion, SecurityParameters, 
//            TlsProtocol, TlsContext, Certificate, TlsSigner

public class TlsUtils
{

    public TlsUtils()
    {
    }

    public static boolean isValidUint8(short i)
    {
        return (i & 0xff) == i;
    }

    public static boolean isValidUint16(int i)
    {
        return (i & 0xffff) == i;
    }

    public static boolean isValidUint24(int i)
    {
        return (i & 0xffffff) == i;
    }

    public static boolean isValidUint32(long i)
    {
        return (i & 0xffffffffL) == i;
    }

    public static boolean isValidUint48(long i)
    {
        return (i & 0xffffffffffffL) == i;
    }

    public static boolean isValidUint64(long i)
    {
        return true;
    }

    public static void writeUint8(short i, OutputStream output)
        throws IOException
    {
        output.write(i);
    }

    public static void writeUint8(short i, byte buf[], int offset)
    {
        buf[offset] = (byte)i;
    }

    public static void writeUint16(int i, OutputStream output)
        throws IOException
    {
        output.write(i >> 8);
        output.write(i);
    }

    public static void writeUint16(int i, byte buf[], int offset)
    {
        buf[offset] = (byte)(i >> 8);
        buf[offset + 1] = (byte)i;
    }

    public static void writeUint24(int i, OutputStream output)
        throws IOException
    {
        output.write(i >> 16);
        output.write(i >> 8);
        output.write(i);
    }

    public static void writeUint24(int i, byte buf[], int offset)
    {
        buf[offset] = (byte)(i >> 16);
        buf[offset + 1] = (byte)(i >> 8);
        buf[offset + 2] = (byte)i;
    }

    public static void writeUint32(long i, OutputStream output)
        throws IOException
    {
        output.write((int)(i >> 24));
        output.write((int)(i >> 16));
        output.write((int)(i >> 8));
        output.write((int)i);
    }

    public static void writeUint32(long i, byte buf[], int offset)
    {
        buf[offset] = (byte)(int)(i >> 24);
        buf[offset + 1] = (byte)(int)(i >> 16);
        buf[offset + 2] = (byte)(int)(i >> 8);
        buf[offset + 3] = (byte)(int)i;
    }

    public static void writeUint48(long i, byte buf[], int offset)
    {
        buf[offset] = (byte)(int)(i >> 40);
        buf[offset + 1] = (byte)(int)(i >> 32);
        buf[offset + 2] = (byte)(int)(i >> 24);
        buf[offset + 3] = (byte)(int)(i >> 16);
        buf[offset + 4] = (byte)(int)(i >> 8);
        buf[offset + 5] = (byte)(int)i;
    }

    public static void writeUint64(long i, OutputStream output)
        throws IOException
    {
        output.write((int)(i >> 56));
        output.write((int)(i >> 48));
        output.write((int)(i >> 40));
        output.write((int)(i >> 32));
        output.write((int)(i >> 24));
        output.write((int)(i >> 16));
        output.write((int)(i >> 8));
        output.write((int)i);
    }

    public static void writeUint64(long i, byte buf[], int offset)
    {
        buf[offset] = (byte)(int)(i >> 56);
        buf[offset + 1] = (byte)(int)(i >> 48);
        buf[offset + 2] = (byte)(int)(i >> 40);
        buf[offset + 3] = (byte)(int)(i >> 32);
        buf[offset + 4] = (byte)(int)(i >> 24);
        buf[offset + 5] = (byte)(int)(i >> 16);
        buf[offset + 6] = (byte)(int)(i >> 8);
        buf[offset + 7] = (byte)(int)i;
    }

    public static void writeOpaque8(byte buf[], OutputStream output)
        throws IOException
    {
        writeUint8((short)buf.length, output);
        output.write(buf);
    }

    public static void writeOpaque16(byte buf[], OutputStream output)
        throws IOException
    {
        writeUint16(buf.length, output);
        output.write(buf);
    }

    public static void writeOpaque24(byte buf[], OutputStream output)
        throws IOException
    {
        writeUint24(buf.length, output);
        output.write(buf);
    }

    public static void writeUint8Array(short uints[], OutputStream output)
        throws IOException
    {
        for(int i = 0; i < uints.length; i++)
            writeUint8(uints[i], output);

    }

    public static void writeUint16Array(int uints[], OutputStream output)
        throws IOException
    {
        for(int i = 0; i < uints.length; i++)
            writeUint16(uints[i], output);

    }

    public static short readUint8(InputStream input)
        throws IOException
    {
        int i = input.read();
        if(i < 0)
            throw new EOFException();
        else
            return (short)i;
    }

    public static short readUint8(byte buf[], int offset)
    {
        return (short)buf[offset];
    }

    public static int readUint16(InputStream input)
        throws IOException
    {
        int i1 = input.read();
        int i2 = input.read();
        if(i2 < 0)
            throw new EOFException();
        else
            return i1 << 8 | i2;
    }

    public static int readUint16(byte buf[], int offset)
    {
        int n = (buf[offset] & 0xff) << 8;
        n |= buf[++offset] & 0xff;
        return n;
    }

    public static int readUint24(InputStream input)
        throws IOException
    {
        int i1 = input.read();
        int i2 = input.read();
        int i3 = input.read();
        if(i3 < 0)
            throw new EOFException();
        else
            return i1 << 16 | i2 << 8 | i3;
    }

    public static int readUint24(byte buf[], int offset)
    {
        int n = (buf[offset] & 0xff) << 16;
        n |= (buf[++offset] & 0xff) << 8;
        n |= buf[++offset] & 0xff;
        return n;
    }

    public static long readUint32(InputStream input)
        throws IOException
    {
        int i1 = input.read();
        int i2 = input.read();
        int i3 = input.read();
        int i4 = input.read();
        if(i4 < 0)
            throw new EOFException();
        else
            return (long)i1 << 24 | (long)i2 << 16 | (long)i3 << 8 | (long)i4;
    }

    public static long readUint48(InputStream input)
        throws IOException
    {
        int i1 = input.read();
        int i2 = input.read();
        int i3 = input.read();
        int i4 = input.read();
        int i5 = input.read();
        int i6 = input.read();
        if(i6 < 0)
            throw new EOFException();
        else
            return (long)i1 << 40 | (long)i2 << 32 | (long)i3 << 24 | (long)i4 << 16 | (long)i5 << 8 | (long)i6;
    }

    public static long readUint48(byte buf[], int offset)
    {
        int hi = readUint24(buf, offset);
        int lo = readUint24(buf, offset + 3);
        return ((long)hi & 0xffffffffL) << 24 | (long)lo & 0xffffffffL;
    }

    public static byte[] readFully(int length, InputStream input)
        throws IOException
    {
        if(length < 1)
            return EMPTY_BYTES;
        byte buf[] = new byte[length];
        if(length != Streams.readFully(input, buf))
            throw new EOFException();
        else
            return buf;
    }

    public static void readFully(byte buf[], InputStream input)
        throws IOException
    {
        int length = buf.length;
        if(length > 0 && length != Streams.readFully(input, buf))
            throw new EOFException();
        else
            return;
    }

    public static byte[] readOpaque8(InputStream input)
        throws IOException
    {
        short length = readUint8(input);
        return readFully(length, input);
    }

    public static byte[] readOpaque16(InputStream input)
        throws IOException
    {
        int length = readUint16(input);
        return readFully(length, input);
    }

    public static byte[] readOpaque24(InputStream input)
        throws IOException
    {
        int length = readUint24(input);
        return readFully(length, input);
    }

    public static short[] readUint8Array(int count, InputStream input)
        throws IOException
    {
        short uints[] = new short[count];
        for(int i = 0; i < count; i++)
            uints[i] = readUint8(input);

        return uints;
    }

    public static int[] readUint16Array(int count, InputStream input)
        throws IOException
    {
        int uints[] = new int[count];
        for(int i = 0; i < count; i++)
            uints[i] = readUint16(input);

        return uints;
    }

    public static ProtocolVersion readVersion(byte buf[], int offset)
        throws IOException
    {
        return ProtocolVersion.get(buf[offset] & 0xff, buf[offset + 1] & 0xff);
    }

    public static ProtocolVersion readVersion(InputStream input)
        throws IOException
    {
        int i1 = input.read();
        int i2 = input.read();
        if(i2 < 0)
            throw new EOFException();
        else
            return ProtocolVersion.get(i1, i2);
    }

    public static int readVersionRaw(InputStream input)
        throws IOException
    {
        int i1 = input.read();
        int i2 = input.read();
        if(i2 < 0)
            throw new EOFException();
        else
            return i1 << 8 | i2;
    }

    public static void writeGMTUnixTime(byte buf[], int offset)
    {
        int t = (int)(System.currentTimeMillis() / 1000L);
        buf[offset] = (byte)(t >> 24);
        buf[offset + 1] = (byte)(t >> 16);
        buf[offset + 2] = (byte)(t >> 8);
        buf[offset + 3] = (byte)t;
    }

    public static void writeVersion(ProtocolVersion version, OutputStream output)
        throws IOException
    {
        output.write(version.getMajorVersion());
        output.write(version.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion version, byte buf[], int offset)
        throws IOException
    {
        buf[offset] = (byte)version.getMajorVersion();
        buf[offset + 1] = (byte)version.getMinorVersion();
    }

    public static Vector getDefaultDSSSignatureAlgorithms()
    {
        return vectorOfOne(new SignatureAndHashAlgorithm((short)2, (short)2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms()
    {
        return vectorOfOne(new SignatureAndHashAlgorithm((short)2, (short)3));
    }

    public static Vector getDefaultRSASignatureAlgorithms()
    {
        return vectorOfOne(new SignatureAndHashAlgorithm((short)2, (short)1));
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion clientVersion)
    {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(clientVersion.getEquivalentTLSVersion());
    }

    public static void addSignatureAlgorithmsExtension(Hashtable extensions, Vector supportedSignatureAlgorithms)
        throws IOException
    {
        extensions.put(EXT_signature_algorithms, createSignatureAlgorithmsExtension(supportedSignatureAlgorithms));
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable extensions)
        throws IOException
    {
        if(extensions == null)
            return null;
        byte extensionValue[] = (byte[])(byte[])extensions.get(EXT_signature_algorithms);
        if(extensionValue == null)
            return null;
        else
            return readSignatureAlgorithmsExtension(extensionValue);
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector supportedSignatureAlgorithms)
        throws IOException
    {
        if(supportedSignatureAlgorithms == null || supportedSignatureAlgorithms.size() < 1 || supportedSignatureAlgorithms.size() >= 32768)
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        writeUint16(2 * supportedSignatureAlgorithms.size(), buf);
        for(int i = 0; i < supportedSignatureAlgorithms.size(); i++)
        {
            SignatureAndHashAlgorithm entry = (SignatureAndHashAlgorithm)supportedSignatureAlgorithms.elementAt(i);
            entry.encode(buf);
        }

        return buf.toByteArray();
    }

    public static Vector readSignatureAlgorithmsExtension(byte extensionValue[])
        throws IOException
    {
        if(extensionValue == null)
            throw new IllegalArgumentException("'extensionValue' cannot be null");
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionValue);
        int length = readUint16(buf);
        if(length < 2 || (length & 1) != 0)
            throw new TlsFatalAlert((short)50);
        int count = length / 2;
        Vector result = new Vector(count);
        for(int i = 0; i < count; i++)
        {
            SignatureAndHashAlgorithm entry = SignatureAndHashAlgorithm.parse(buf);
            result.addElement(entry);
        }

        TlsProtocol.assertEmpty(buf);
        return result;
    }

    public static byte[] PRF(TlsContext context, byte secret[], String asciiLabel, byte seed[], int size)
    {
        ProtocolVersion version = context.getServerVersion();
        if(version.isSSL())
            throw new IllegalStateException("No PRF available for SSLv3 session");
        byte label[] = Strings.toByteArray(asciiLabel);
        byte labelSeed[] = concat(label, seed);
        int prfAlgorithm = context.getSecurityParameters().getPrfAlgorithm();
        if(prfAlgorithm == 0)
        {
            if(!ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(version.getEquivalentTLSVersion()))
                return PRF_legacy(secret, label, labelSeed, size);
            prfAlgorithm = 1;
        }
        Digest prfDigest = createPRFHash(prfAlgorithm);
        byte buf[] = new byte[size];
        hmac_hash(prfDigest, secret, labelSeed, buf);
        return buf;
    }

    static byte[] PRF_legacy(byte secret[], byte label[], byte labelSeed[], int size)
    {
        int s_half = (secret.length + 1) / 2;
        byte s1[] = new byte[s_half];
        byte s2[] = new byte[s_half];
        System.arraycopy(secret, 0, s1, 0, s_half);
        System.arraycopy(secret, secret.length - s_half, s2, 0, s_half);
        byte b1[] = new byte[size];
        byte b2[] = new byte[size];
        hmac_hash(new MD5Digest(), s1, labelSeed, b1);
        hmac_hash(new SHA1Digest(), s2, labelSeed, b2);
        for(int i = 0; i < size; i++)
            b1[i] ^= b2[i];

        return b1;
    }

    static byte[] concat(byte a[], byte b[])
    {
        byte c[] = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    static void hmac_hash(Digest digest, byte secret[], byte seed[], byte out[])
    {
        HMac mac = new HMac(digest);
        KeyParameter param = new KeyParameter(secret);
        byte a[] = seed;
        int size = digest.getDigestSize();
        int iterations = ((out.length + size) - 1) / size;
        byte buf[] = new byte[mac.getMacSize()];
        byte buf2[] = new byte[mac.getMacSize()];
        for(int i = 0; i < iterations; i++)
        {
            mac.init(param);
            mac.update(a, 0, a.length);
            mac.doFinal(buf, 0);
            a = buf;
            mac.init(param);
            mac.update(a, 0, a.length);
            mac.update(seed, 0, seed.length);
            mac.doFinal(buf2, 0);
            System.arraycopy(buf2, 0, out, size * i, Math.min(size, out.length - size * i));
        }

    }

    static void validateKeyUsage(Certificate c, int keyUsageBits)
        throws IOException
    {
        Extensions exts = c.getTBSCertificate().getExtensions();
        if(exts != null)
        {
            KeyUsage ku = KeyUsage.fromExtensions(exts);
            if(ku != null)
            {
                int bits = ku.getBytes()[0] & 0xff;
                if((bits & keyUsageBits) != keyUsageBits)
                    throw new TlsFatalAlert((short)46);
            }
        }
    }

    static byte[] calculateKeyBlock(TlsContext context, int size)
    {
        SecurityParameters securityParameters = context.getSecurityParameters();
        byte master_secret[] = securityParameters.getMasterSecret();
        byte seed[] = concat(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        if(context.getServerVersion().isSSL())
            return calculateKeyBlock_SSL(master_secret, seed, size);
        else
            return PRF(context, master_secret, "key expansion", seed, size);
    }

    static byte[] calculateKeyBlock_SSL(byte master_secret[], byte random[], int size)
    {
        Digest md5 = new MD5Digest();
        Digest sha1 = new SHA1Digest();
        int md5Size = md5.getDigestSize();
        byte shatmp[] = new byte[sha1.getDigestSize()];
        byte tmp[] = new byte[size + md5Size];
        int i = 0;
        for(int pos = 0; pos < size;)
        {
            byte ssl3Const[] = SSL3_CONST[i];
            sha1.update(ssl3Const, 0, ssl3Const.length);
            sha1.update(master_secret, 0, master_secret.length);
            sha1.update(random, 0, random.length);
            sha1.doFinal(shatmp, 0);
            md5.update(master_secret, 0, master_secret.length);
            md5.update(shatmp, 0, shatmp.length);
            md5.doFinal(tmp, pos);
            pos += md5Size;
            i++;
        }

        byte rval[] = new byte[size];
        System.arraycopy(tmp, 0, rval, 0, size);
        return rval;
    }

    static byte[] calculateMasterSecret(TlsContext context, byte pre_master_secret[])
    {
        SecurityParameters securityParameters = context.getSecurityParameters();
        byte seed[] = concat(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        if(context.getServerVersion().isSSL())
            return calculateMasterSecret_SSL(pre_master_secret, seed);
        else
            return PRF(context, pre_master_secret, "master secret", seed, 48);
    }

    static byte[] calculateMasterSecret_SSL(byte pre_master_secret[], byte random[])
    {
        Digest md5 = new MD5Digest();
        Digest sha1 = new SHA1Digest();
        int md5Size = md5.getDigestSize();
        byte shatmp[] = new byte[sha1.getDigestSize()];
        byte rval[] = new byte[md5Size * 3];
        int pos = 0;
        for(int i = 0; i < 3; i++)
        {
            byte ssl3Const[] = SSL3_CONST[i];
            sha1.update(ssl3Const, 0, ssl3Const.length);
            sha1.update(pre_master_secret, 0, pre_master_secret.length);
            sha1.update(random, 0, random.length);
            sha1.doFinal(shatmp, 0);
            md5.update(pre_master_secret, 0, pre_master_secret.length);
            md5.update(shatmp, 0, shatmp.length);
            md5.doFinal(rval, pos);
            pos += md5Size;
        }

        return rval;
    }

    static byte[] calculateVerifyData(TlsContext context, String asciiLabel, byte handshakeHash[])
    {
        if(context.getServerVersion().isSSL())
        {
            return handshakeHash;
        } else
        {
            SecurityParameters securityParameters = context.getSecurityParameters();
            byte master_secret[] = securityParameters.getMasterSecret();
            int verify_data_length = securityParameters.getVerifyDataLength();
            return PRF(context, master_secret, asciiLabel, handshakeHash, verify_data_length);
        }
    }

    public static final Digest createHash(int hashAlgorithm)
    {
        switch(hashAlgorithm)
        {
        case 1: // '\001'
            return new MD5Digest();

        case 2: // '\002'
            return new SHA1Digest();

        case 3: // '\003'
            return new SHA224Digest();

        case 4: // '\004'
            return new SHA256Digest();

        case 5: // '\005'
            return new SHA384Digest();

        case 6: // '\006'
            return new SHA512Digest();
        }
        throw new IllegalArgumentException("unknown HashAlgorithm");
    }

    public static final Digest cloneHash(int hashAlgorithm, Digest hash)
    {
        switch(hashAlgorithm)
        {
        case 1: // '\001'
            return new MD5Digest((MD5Digest)hash);

        case 2: // '\002'
            return new SHA1Digest((SHA1Digest)hash);

        case 3: // '\003'
            return new SHA224Digest((SHA224Digest)hash);

        case 4: // '\004'
            return new SHA256Digest((SHA256Digest)hash);

        case 5: // '\005'
            return new SHA384Digest((SHA384Digest)hash);

        case 6: // '\006'
            return new SHA512Digest((SHA512Digest)hash);
        }
        throw new IllegalArgumentException("unknown HashAlgorithm");
    }

    public static final Digest createPRFHash(int prfAlgorithm)
    {
        switch(prfAlgorithm)
        {
        case 0: // '\0'
            return new CombinedHash();
        }
        return createHash(getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
    }

    public static final Digest clonePRFHash(int prfAlgorithm, Digest hash)
    {
        switch(prfAlgorithm)
        {
        case 0: // '\0'
            return new CombinedHash((CombinedHash)hash);
        }
        return cloneHash(getHashAlgorithmForPRFAlgorithm(prfAlgorithm), hash);
    }

    public static final short getHashAlgorithmForPRFAlgorithm(int prfAlgorithm)
    {
        switch(prfAlgorithm)
        {
        case 0: // '\0'
            throw new IllegalArgumentException("legacy PRF not a valid algorithm");

        case 1: // '\001'
            return 4;

        case 2: // '\002'
            return 5;
        }
        throw new IllegalArgumentException("unknown PRFAlgorithm");
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(int hashAlgorithm)
    {
        switch(hashAlgorithm)
        {
        case 1: // '\001'
            return PKCSObjectIdentifiers.md5;

        case 2: // '\002'
            return X509ObjectIdentifiers.id_SHA1;

        case 3: // '\003'
            return NISTObjectIdentifiers.id_sha224;

        case 4: // '\004'
            return NISTObjectIdentifiers.id_sha256;

        case 5: // '\005'
            return NISTObjectIdentifiers.id_sha384;

        case 6: // '\006'
            return NISTObjectIdentifiers.id_sha512;
        }
        throw new IllegalArgumentException("unknown HashAlgorithm");
    }

    static short getClientCertificateType(co.org.bouncy.crypto.tls.Certificate clientCertificate, co.org.bouncy.crypto.tls.Certificate serverCertificate)
        throws IOException
    {
        Certificate x509Cert;
        SubjectPublicKeyInfo keyInfo;
        if(clientCertificate.isEmpty())
            return -1;
        x509Cert = clientCertificate.getCertificateAt(0);
        keyInfo = x509Cert.getSubjectPublicKeyInfo();
        AsymmetricKeyParameter publicKey;
        publicKey = PublicKeyFactory.createKey(keyInfo);
        if(publicKey.isPrivate())
            throw new TlsFatalAlert((short)80);
        if(publicKey instanceof RSAKeyParameters)
        {
            validateKeyUsage(x509Cert, 128);
            return 1;
        }
        if(publicKey instanceof DSAPublicKeyParameters)
        {
            validateKeyUsage(x509Cert, 128);
            return 2;
        }
        try
        {
            if(publicKey instanceof ECPublicKeyParameters)
            {
                validateKeyUsage(x509Cert, 128);
                return 64;
            }
        }
        catch(Exception e) { }
        throw new TlsFatalAlert((short)43);
    }

    public static boolean hasSigningCapability(short clientCertificateType)
    {
        switch(clientCertificateType)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 64: // '@'
            return true;
        }
        return false;
    }

    public static TlsSigner createTlsSigner(short clientCertificateType)
    {
        switch(clientCertificateType)
        {
        case 2: // '\002'
            return new TlsDSSSigner();

        case 64: // '@'
            return new TlsECDSASigner();

        case 1: // '\001'
            return new TlsRSASigner();
        }
        throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
    }

    private static byte[][] genConst()
    {
        int n = 10;
        byte arr[][] = new byte[n][];
        for(int i = 0; i < n; i++)
        {
            byte b[] = new byte[i + 1];
            Arrays.fill(b, (byte)(65 + i));
            arr[i] = b;
        }

        return arr;
    }

    private static Vector vectorOfOne(Object obj)
    {
        Vector v = new Vector(1);
        v.addElement(obj);
        return v;
    }

    public static byte EMPTY_BYTES[] = new byte[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);
    static final byte SSL_CLIENT[] = {
        67, 76, 78, 84
    };
    static final byte SSL_SERVER[] = {
        83, 82, 86, 82
    };
    static final byte SSL3_CONST[][] = genConst();

}
