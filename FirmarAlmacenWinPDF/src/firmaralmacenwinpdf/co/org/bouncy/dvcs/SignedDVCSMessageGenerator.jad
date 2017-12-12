// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignedDVCSMessageGenerator.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.cms.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSException, DVCSMessage

public class SignedDVCSMessageGenerator
{

    public SignedDVCSMessageGenerator(CMSSignedDataGenerator signedDataGen)
    {
        this.signedDataGen = signedDataGen;
    }

    public CMSSignedData build(DVCSMessage message)
        throws DVCSException
    {
        try
        {
            byte encapsulatedData[] = message.getContent().toASN1Primitive().getEncoded("DER");
            return signedDataGen.generate(new CMSProcessableByteArray(message.getContentType(), encapsulatedData), true);
        }
        catch(CMSException e)
        {
            throw new DVCSException("Could not sign DVCS request", e);
        }
        catch(IOException e)
        {
            throw new DVCSException("Could not encode DVCS request", e);
        }
    }

    private final CMSSignedDataGenerator signedDataGen;
}
