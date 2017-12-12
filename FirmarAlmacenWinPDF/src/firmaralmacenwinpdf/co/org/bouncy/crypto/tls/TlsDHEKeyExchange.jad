// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsDHEKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.generators.DHKeyPairGenerator;
import co.org.bouncy.crypto.io.SignerInputStream;
import co.org.bouncy.crypto.params.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsDHKeyExchange, TlsSignerCredentials, TlsFatalAlert, CombinedHash, 
//            SecurityParameters, TlsCredentials, TlsContext, TlsDHUtils, 
//            TlsUtils, TlsSigner

public class TlsDHEKeyExchange extends TlsDHKeyExchange
{

    public TlsDHEKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, DHParameters dhParameters)
    {
        super(keyExchange, supportedSignatureAlgorithms, dhParameters);
        serverCredentials = null;
    }

    public void processServerCredentials(TlsCredentials serverCredentials)
        throws IOException
    {
        if(!(serverCredentials instanceof TlsSignerCredentials))
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            processServerCertificate(serverCredentials.getCertificate());
            this.serverCredentials = (TlsSignerCredentials)serverCredentials;
            return;
        }
    }

    public byte[] generateServerKeyExchange()
        throws IOException
    {
        if(dhParameters == null)
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            DHKeyPairGenerator kpg = new DHKeyPairGenerator();
            kpg.init(new DHKeyGenerationParameters(context.getSecureRandom(), dhParameters));
            AsymmetricCipherKeyPair kp = kpg.generateKeyPair();
            BigInteger Ys = ((DHPublicKeyParameters)kp.getPublic()).getY();
            TlsDHUtils.writeDHParameter(dhParameters.getP(), buf);
            TlsDHUtils.writeDHParameter(dhParameters.getG(), buf);
            TlsDHUtils.writeDHParameter(Ys, buf);
            byte digestInput[] = buf.toByteArray();
            Digest d = new CombinedHash();
            SecurityParameters securityParameters = context.getSecurityParameters();
            d.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
            d.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
            d.update(digestInput, 0, digestInput.length);
            byte hash[] = new byte[d.getDigestSize()];
            d.doFinal(hash, 0);
            byte sigBytes[] = serverCredentials.generateCertificateSignature(hash);
            TlsUtils.writeOpaque16(sigBytes, buf);
            return buf.toByteArray();
        }
    }

    public void processServerKeyExchange(InputStream input)
        throws IOException
    {
        SecurityParameters securityParameters = context.getSecurityParameters();
        Signer signer = initVerifyer(tlsSigner, securityParameters);
        InputStream sigIn = new SignerInputStream(input, signer);
        BigInteger p = TlsDHUtils.readDHParameter(sigIn);
        BigInteger g = TlsDHUtils.readDHParameter(sigIn);
        BigInteger Ys = TlsDHUtils.readDHParameter(sigIn);
        byte sigBytes[] = TlsUtils.readOpaque16(input);
        if(!signer.verifySignature(sigBytes))
        {
            throw new TlsFatalAlert((short)51);
        } else
        {
            dhAgreeServerPublicKey = validateDHPublicKey(new DHPublicKeyParameters(Ys, new DHParameters(p, g)));
            return;
        }
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SecurityParameters securityParameters)
    {
        Signer signer = tlsSigner.createVerifyer(serverPublicKey);
        signer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signer;
    }

    protected TlsSignerCredentials serverCredentials;
}
