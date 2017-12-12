// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12PfxPdu.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DigestInfo;
import co.org.bouncy.cert.CertIOException;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.pkcs:
//            MacDataGenerator, PKCSException, PKCS12MacCalculatorBuilderProvider

public class PKCS12PfxPdu
{

    private static Pfx parseBytes(byte pfxEncoding[])
        throws IOException
    {
        try
        {
            return Pfx.getInstance(ASN1Primitive.fromByteArray(pfxEncoding));
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public PKCS12PfxPdu(Pfx pfx)
    {
        this.pfx = pfx;
    }

    public PKCS12PfxPdu(byte pfx[])
        throws IOException
    {
        this(parseBytes(pfx));
    }

    public ContentInfo[] getContentInfos()
    {
        ASN1Sequence seq = ASN1Sequence.getInstance(ASN1OctetString.getInstance(pfx.getAuthSafe().getContent()).getOctets());
        ContentInfo content[] = new ContentInfo[seq.size()];
        for(int i = 0; i != seq.size(); i++)
            content[i] = ContentInfo.getInstance(seq.getObjectAt(i));

        return content;
    }

    public boolean hasMac()
    {
        return pfx.getMacData() != null;
    }

    public AlgorithmIdentifier getMacAlgorithmID()
    {
        MacData md = pfx.getMacData();
        if(md != null)
            return md.getMac().getAlgorithmId();
        else
            return null;
    }

    public boolean isMacValid(PKCS12MacCalculatorBuilderProvider macCalcProviderBuilder, char password[])
        throws PKCSException
    {
        if(hasMac())
        {
            MacData pfxmData = pfx.getMacData();
            MacDataGenerator mdGen = new MacDataGenerator(macCalcProviderBuilder.get(new AlgorithmIdentifier(pfxmData.getMac().getAlgorithmId().getAlgorithm(), new PKCS12PBEParams(pfxmData.getSalt(), pfxmData.getIterationCount().intValue()))));
            try
            {
                MacData mData = mdGen.build(password, ASN1OctetString.getInstance(pfx.getAuthSafe().getContent()).getOctets());
                return Arrays.constantTimeAreEqual(mData.getEncoded(), pfx.getMacData().getEncoded());
            }
            catch(IOException e)
            {
                throw new PKCSException((new StringBuilder()).append("unable to process AuthSafe: ").append(e.getMessage()).toString());
            }
        } else
        {
            throw new IllegalStateException("no MAC present on PFX");
        }
    }

    public Pfx toASN1Structure()
    {
        return pfx;
    }

    public byte[] getEncoded()
        throws IOException
    {
        return toASN1Structure().getEncoded();
    }

    public byte[] getEncoded(String encoding)
        throws IOException
    {
        return toASN1Structure().getEncoded(encoding);
    }

    private Pfx pfx;
}
