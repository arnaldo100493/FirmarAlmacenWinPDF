// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSRequestBuilder.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.dvcs.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cms.CMSSignedDataGenerator;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.dvcs:
//            DVCSException, DVCSRequest

public abstract class DVCSRequestBuilder
{

    protected DVCSRequestBuilder(DVCSRequestInformationBuilder requestInformationBuilder)
    {
        this.requestInformationBuilder = requestInformationBuilder;
    }

    public void setNonce(BigInteger nonce)
    {
        requestInformationBuilder.setNonce(nonce);
    }

    public void setRequester(GeneralName requester)
    {
        requestInformationBuilder.setRequester(requester);
    }

    public void setDVCS(GeneralName dvcs)
    {
        requestInformationBuilder.setDVCS(dvcs);
    }

    public void setDVCS(GeneralNames dvcs)
    {
        requestInformationBuilder.setDVCS(dvcs);
    }

    public void setDataLocations(GeneralName dataLocation)
    {
        requestInformationBuilder.setDataLocations(dataLocation);
    }

    public void setDataLocations(GeneralNames dataLocations)
    {
        requestInformationBuilder.setDataLocations(dataLocations);
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws DVCSException
    {
        try
        {
            extGenerator.addExtension(oid, isCritical, value);
        }
        catch(IOException e)
        {
            throw new DVCSException((new StringBuilder()).append("cannot encode extension: ").append(e.getMessage()).toString(), e);
        }
    }

    protected co.org.bouncy.dvcs.DVCSRequest createDVCRequest(Data data)
        throws DVCSException
    {
        if(!extGenerator.isEmpty())
            requestInformationBuilder.setExtensions(extGenerator.generate());
        DVCSRequest request = new DVCSRequest(requestInformationBuilder.build(), data);
        return new co.org.bouncy.dvcs.DVCSRequest(new ContentInfo(DVCSObjectIdentifiers.id_ct_DVCSRequestData, request));
    }

    private final ExtensionsGenerator extGenerator = new ExtensionsGenerator();
    private final CMSSignedDataGenerator signedDataGen = new CMSSignedDataGenerator();
    protected final DVCSRequestInformationBuilder requestInformationBuilder;
}
