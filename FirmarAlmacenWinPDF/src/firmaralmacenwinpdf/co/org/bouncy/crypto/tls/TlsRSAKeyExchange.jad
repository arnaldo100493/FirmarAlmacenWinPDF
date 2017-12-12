// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsRSAKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.util.io.Streams;
import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsKeyExchange, TlsFatalAlert, TlsEncryptionCredentials, TlsSignerCredentials, 
//            Certificate, ProtocolVersion, TlsCredentials, TlsUtils, 
//            CertificateRequest, TlsRSAUtils, TlsContext

public class TlsRSAKeyExchange extends AbstractTlsKeyExchange
{

    public TlsRSAKeyExchange(Vector supportedSignatureAlgorithms)
    {
        super(1, supportedSignatureAlgorithms);
        serverPublicKey = null;
        rsaServerPublicKey = null;
        serverCredentials = null;
    }

    public void skipServerCredentials()
        throws IOException
    {
        throw new TlsFatalAlert((short)10);
    }

    public void processServerCredentials(TlsCredentials serverCredentials)
        throws IOException
    {
        if(!(serverCredentials instanceof TlsEncryptionCredentials))
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            processServerCertificate(serverCredentials.getCertificate());
            this.serverCredentials = (TlsEncryptionCredentials)serverCredentials;
            return;
        }
    }

    public void processServerCertificate(co.org.bouncy.crypto.tls.Certificate serverCertificate)
        throws IOException
    {
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

    public void validateCertificateRequest(CertificateRequest certificateRequest)
        throws IOException
    {
        short types[] = certificateRequest.getCertificateTypes();
        int i = 0;
        do
        {
            if(i >= types.length)
                break;
            switch(types[i])
            {
            default:
                throw new TlsFatalAlert((short)47);

            case 1: // '\001'
            case 2: // '\002'
            case 64: // '@'
                i++;
                break;
            }
        } while(true);
    }

    public void processClientCredentials(TlsCredentials clientCredentials)
        throws IOException
    {
        if(!(clientCredentials instanceof TlsSignerCredentials))
            throw new TlsFatalAlert((short)80);
        else
            return;
    }

    public void generateClientKeyExchange(OutputStream output)
        throws IOException
    {
        premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(context, rsaServerPublicKey, output);
    }

    public void processClientKeyExchange(InputStream input)
        throws IOException
    {
        byte encryptedPreMasterSecret[];
        if(context.getServerVersion().isSSL())
            encryptedPreMasterSecret = Streams.readAll(input);
        else
            encryptedPreMasterSecret = TlsUtils.readOpaque16(input);
        ProtocolVersion clientVersion = context.getClientVersion();
        boolean versionNumberCheckDisabled = false;
        byte R[] = new byte[48];
        context.getSecureRandom().nextBytes(R);
        byte M[] = TlsUtils.EMPTY_BYTES;
        try
        {
            M = serverCredentials.decryptPreMasterSecret(encryptedPreMasterSecret);
        }
        catch(Exception e) { }
        if(M.length != 48)
        {
            TlsUtils.writeVersion(clientVersion, R, 0);
            premasterSecret = R;
        } else
        {
            if(!versionNumberCheckDisabled || !clientVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv10))
                TlsUtils.writeVersion(clientVersion, M, 0);
            premasterSecret = M;
        }
    }

    public byte[] generatePremasterSecret()
        throws IOException
    {
        if(premasterSecret == null)
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            byte tmp[] = premasterSecret;
            premasterSecret = null;
            return tmp;
        }
    }

    protected RSAKeyParameters validateRSAPublicKey(RSAKeyParameters key)
        throws IOException
    {
        if(!key.getExponent().isProbablePrime(2))
            throw new TlsFatalAlert((short)47);
        else
            return key;
    }

    protected AsymmetricKeyParameter serverPublicKey;
    protected RSAKeyParameters rsaServerPublicKey;
    protected TlsEncryptionCredentials serverCredentials;
    protected byte premasterSecret[];
}
