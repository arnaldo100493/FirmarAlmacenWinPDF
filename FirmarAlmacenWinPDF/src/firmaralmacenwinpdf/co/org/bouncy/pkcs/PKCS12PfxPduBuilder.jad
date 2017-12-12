// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12PfxPduBuilder.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.cms.*;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCSIOException, PKCSException, MacDataGenerator, PKCS12PfxPdu, 
//            PKCS12MacCalculatorBuilder, PKCS12SafeBag

public class PKCS12PfxPduBuilder
{

    public PKCS12PfxPduBuilder()
    {
        dataVector = new ASN1EncodableVector();
    }

    public PKCS12PfxPduBuilder addData(PKCS12SafeBag data)
        throws IOException
    {
        dataVector.add(new ContentInfo(PKCSObjectIdentifiers.data, new DEROctetString((new DLSequence(data.toASN1Structure())).getEncoded())));
        return this;
    }

    public PKCS12PfxPduBuilder addEncryptedData(OutputEncryptor dataEncryptor, PKCS12SafeBag data)
        throws IOException
    {
        return addEncryptedData(dataEncryptor, ((ASN1Sequence) (new DERSequence(data.toASN1Structure()))));
    }

    public PKCS12PfxPduBuilder addEncryptedData(OutputEncryptor dataEncryptor, PKCS12SafeBag data[])
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != data.length; i++)
            v.add(data[i].toASN1Structure());

        return addEncryptedData(dataEncryptor, ((ASN1Sequence) (new DLSequence(v))));
    }

    private PKCS12PfxPduBuilder addEncryptedData(OutputEncryptor dataEncryptor, ASN1Sequence data)
        throws IOException
    {
        CMSEncryptedDataGenerator envGen = new CMSEncryptedDataGenerator();
        try
        {
            dataVector.add(envGen.generate(new CMSProcessableByteArray(data.getEncoded()), dataEncryptor).toASN1Structure());
        }
        catch(CMSException e)
        {
            throw new PKCSIOException(e.getMessage(), e.getCause());
        }
        return this;
    }

    public PKCS12PfxPdu build(PKCS12MacCalculatorBuilder macCalcBuilder, char password[])
        throws PKCSException
    {
        AuthenticatedSafe auth = AuthenticatedSafe.getInstance(new DLSequence(dataVector));
        byte encAuth[];
        try
        {
            encAuth = auth.getEncoded();
        }
        catch(IOException e)
        {
            throw new PKCSException((new StringBuilder()).append("unable to encode AuthenticatedSafe: ").append(e.getMessage()).toString(), e);
        }
        ContentInfo mainInfo = new ContentInfo(PKCSObjectIdentifiers.data, new DEROctetString(encAuth));
        MacData mData = null;
        if(macCalcBuilder != null)
        {
            MacDataGenerator mdGen = new MacDataGenerator(macCalcBuilder);
            mData = mdGen.build(password, encAuth);
        }
        Pfx pfx = new Pfx(mainInfo, mData);
        return new PKCS12PfxPdu(pfx);
    }

    private ASN1EncodableVector dataVector;
}
