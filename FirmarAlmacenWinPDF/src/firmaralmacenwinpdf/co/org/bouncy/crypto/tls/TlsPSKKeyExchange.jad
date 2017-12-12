// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsPSKKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import java.io.*;
import java.math.BigInteger;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsKeyExchange, TlsFatalAlert, TlsPSKIdentity, Certificate, 
//            TlsUtils, TlsDHUtils, TlsRSAUtils, TlsContext, 
//            CertificateRequest, TlsCredentials

public class TlsPSKKeyExchange extends AbstractTlsKeyExchange
{

    public TlsPSKKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, TlsPSKIdentity pskIdentity)
    {
        super(keyExchange, supportedSignatureAlgorithms);
        psk_identity_hint = null;
        dhAgreeServerPublicKey = null;
        dhAgreeClientPrivateKey = null;
        serverPublicKey = null;
        rsaServerPublicKey = null;
        switch(keyExchange)
        {
        default:
            throw new IllegalArgumentException("unsupported key exchange algorithm");

        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            this.pskIdentity = pskIdentity;
            break;
        }
    }

    public void skipServerCredentials()
        throws IOException
    {
        if(keyExchange == 15)
            throw new TlsFatalAlert((short)10);
        else
            return;
    }

    public void processServerCertificate(co.org.bouncy.crypto.tls.Certificate serverCertificate)
        throws IOException
    {
        if(keyExchange != 15)
            throw new TlsFatalAlert((short)10);
        if(serverCertificate.isEmpty())
            throw new TlsFatalAlert((short)42);
        Certificate x509Cert = serverCertificate.getCertificateAt(0);
        SubjectPublicKeyInfo keyInfo = x509Cert.getSubjectPublicKeyInfo();
        try
        {
            serverPublicKey = PublicKeyFactory.createKey(keyInfo);
        }
        catch(RuntimeException e)
        {
            throw new TlsFatalAlert((short)43);
        }
        if(serverPublicKey.isPrivate())
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            rsaServerPublicKey = validateRSAPublicKey((RSAKeyParameters)serverPublicKey);
            TlsUtils.validateKeyUsage(x509Cert, 32);
            super.processServerCertificate(serverCertificate);
            return;
        }
    }

    public boolean requiresServerKeyExchange()
    {
        return keyExchange == 14;
    }

    public void processServerKeyExchange(InputStream input)
        throws IOException
    {
        psk_identity_hint = TlsUtils.readOpaque16(input);
        if(keyExchange == 14)
        {
            byte pBytes[] = TlsUtils.readOpaque16(input);
            byte gBytes[] = TlsUtils.readOpaque16(input);
            byte YsBytes[] = TlsUtils.readOpaque16(input);
            BigInteger p = new BigInteger(1, pBytes);
            BigInteger g = new BigInteger(1, gBytes);
            BigInteger Ys = new BigInteger(1, YsBytes);
            dhAgreeServerPublicKey = TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(Ys, new DHParameters(p, g)));
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest)
        throws IOException
    {
        throw new TlsFatalAlert((short)10);
    }

    public void processClientCredentials(TlsCredentials clientCredentials)
        throws IOException
    {
        throw new TlsFatalAlert((short)80);
    }

    public void generateClientKeyExchange(OutputStream output)
        throws IOException
    {
        if(psk_identity_hint == null)
            pskIdentity.skipIdentityHint();
        else
            pskIdentity.notifyIdentityHint(psk_identity_hint);
        byte psk_identity[] = pskIdentity.getPSKIdentity();
        TlsUtils.writeOpaque16(psk_identity, output);
        if(keyExchange == 15)
            premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(context, rsaServerPublicKey, output);
        else
        if(keyExchange == 14)
            dhAgreeClientPrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(context.getSecureRandom(), dhAgreeServerPublicKey.getParameters(), output);
    }

    public byte[] generatePremasterSecret()
        throws IOException
    {
        byte psk[] = pskIdentity.getPSK();
        byte other_secret[] = generateOtherSecret(psk.length);
        ByteArrayOutputStream buf = new ByteArrayOutputStream(4 + other_secret.length + psk.length);
        TlsUtils.writeOpaque16(other_secret, buf);
        TlsUtils.writeOpaque16(psk, buf);
        return buf.toByteArray();
    }

    protected byte[] generateOtherSecret(int pskLength)
    {
        if(keyExchange == 14)
            return TlsDHUtils.calculateDHBasicAgreement(dhAgreeServerPublicKey, dhAgreeClientPrivateKey);
        if(keyExchange == 15)
            return premasterSecret;
        else
            return new byte[pskLength];
    }

    protected RSAKeyParameters validateRSAPublicKey(RSAKeyParameters key)
        throws IOException
    {
        if(!key.getExponent().isProbablePrime(2))
            throw new TlsFatalAlert((short)47);
        else
            return key;
    }

    protected TlsPSKIdentity pskIdentity;
    protected byte psk_identity_hint[];
    protected DHPublicKeyParameters dhAgreeServerPublicKey;
    protected DHPrivateKeyParameters dhAgreeClientPrivateKey;
    protected AsymmetricKeyParameter serverPublicKey;
    protected RSAKeyParameters rsaServerPublicKey;
    protected byte premasterSecret[];
}
