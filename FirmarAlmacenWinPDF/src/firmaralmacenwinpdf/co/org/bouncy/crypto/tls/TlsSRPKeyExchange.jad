// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsSRPKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.agreement.srp.SRP6Client;
import co.org.bouncy.crypto.agreement.srp.SRP6Util;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.io.SignerInputStream;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.util.BigIntegers;
import java.io.*;
import java.math.BigInteger;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsKeyExchange, TlsRSASigner, TlsDSSSigner, TlsFatalAlert, 
//            Certificate, SecurityParameters, TlsSigner, TlsUtils, 
//            TlsContext, CertificateRequest, TlsCredentials

public class TlsSRPKeyExchange extends AbstractTlsKeyExchange
{

    public TlsSRPKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, byte identity[], byte password[])
    {
        super(keyExchange, supportedSignatureAlgorithms);
        serverPublicKey = null;
        s = null;
        B = null;
        srpClient = new SRP6Client();
        switch(keyExchange)
        {
        case 21: // '\025'
            tlsSigner = null;
            break;

        case 23: // '\027'
            tlsSigner = new TlsRSASigner();
            break;

        case 22: // '\026'
            tlsSigner = new TlsDSSSigner();
            break;

        default:
            throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.keyExchange = keyExchange;
        this.identity = identity;
        this.password = password;
    }

    public void init(TlsContext context)
    {
        super.init(context);
        if(tlsSigner != null)
            tlsSigner.init(context);
    }

    public void skipServerCredentials()
        throws IOException
    {
        if(tlsSigner != null)
            throw new TlsFatalAlert((short)10);
        else
            return;
    }

    public void processServerCertificate(co.org.bouncy.crypto.tls.Certificate serverCertificate)
        throws IOException
    {
        if(tlsSigner == null)
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
        if(!tlsSigner.isValidPublicKey(serverPublicKey))
        {
            throw new TlsFatalAlert((short)46);
        } else
        {
            TlsUtils.validateKeyUsage(x509Cert, 128);
            super.processServerCertificate(serverCertificate);
            return;
        }
    }

    public boolean requiresServerKeyExchange()
    {
        return true;
    }

    public void processServerKeyExchange(InputStream input)
        throws IOException
    {
        SecurityParameters securityParameters = context.getSecurityParameters();
        InputStream sigIn = input;
        Signer signer = null;
        if(tlsSigner != null)
        {
            signer = initVerifyer(tlsSigner, securityParameters);
            sigIn = new SignerInputStream(input, signer);
        }
        byte NBytes[] = TlsUtils.readOpaque16(sigIn);
        byte gBytes[] = TlsUtils.readOpaque16(sigIn);
        byte sBytes[] = TlsUtils.readOpaque8(sigIn);
        byte BBytes[] = TlsUtils.readOpaque16(sigIn);
        if(signer != null)
        {
            byte sigByte[] = TlsUtils.readOpaque16(input);
            if(!signer.verifySignature(sigByte))
                throw new TlsFatalAlert((short)51);
        }
        BigInteger N = new BigInteger(1, NBytes);
        BigInteger g = new BigInteger(1, gBytes);
        s = sBytes;
        try
        {
            B = SRP6Util.validatePublicValue(N, new BigInteger(1, BBytes));
        }
        catch(CryptoException e)
        {
            throw new TlsFatalAlert((short)47);
        }
        srpClient.init(N, g, new SHA1Digest(), context.getSecureRandom());
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
        byte keData[] = BigIntegers.asUnsignedByteArray(srpClient.generateClientCredentials(s, identity, password));
        TlsUtils.writeOpaque16(keData, output);
    }

    public byte[] generatePremasterSecret()
        throws IOException
    {
        try
        {
            return BigIntegers.asUnsignedByteArray(srpClient.calculateSecret(B));
        }
        catch(CryptoException e)
        {
            throw new TlsFatalAlert((short)47);
        }
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SecurityParameters securityParameters)
    {
        Signer signer = tlsSigner.createVerifyer(serverPublicKey);
        signer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signer;
    }

    protected TlsSigner tlsSigner;
    protected byte identity[];
    protected byte password[];
    protected AsymmetricKeyParameter serverPublicKey;
    protected byte s[];
    protected BigInteger B;
    protected SRP6Client srpClient;
}
