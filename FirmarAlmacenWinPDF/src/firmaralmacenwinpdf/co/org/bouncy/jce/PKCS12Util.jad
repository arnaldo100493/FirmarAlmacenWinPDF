// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12Util.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DigestInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PKCS12Util
{

    public PKCS12Util()
    {
    }

    public static byte[] convertToDefiniteLength(byte berPKCS12File[])
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DEROutputStream dOut = new DEROutputStream(bOut);
        Pfx pfx = Pfx.getInstance(berPKCS12File);
        bOut.reset();
        dOut.writeObject(pfx);
        return bOut.toByteArray();
    }

    public static byte[] convertToDefiniteLength(byte berPKCS12File[], char passwd[], String provider)
        throws IOException
    {
        Pfx pfx = Pfx.getInstance(berPKCS12File);
        ContentInfo info = pfx.getAuthSafe();
        ASN1OctetString content = ASN1OctetString.getInstance(info.getContent());
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DEROutputStream dOut = new DEROutputStream(bOut);
        ASN1InputStream contentIn = new ASN1InputStream(content.getOctets());
        ASN1Primitive obj = contentIn.readObject();
        dOut.writeObject(obj);
        info = new ContentInfo(info.getContentType(), new DEROctetString(bOut.toByteArray()));
        MacData mData = pfx.getMacData();
        try
        {
            int itCount = mData.getIterationCount().intValue();
            byte data[] = ASN1OctetString.getInstance(info.getContent()).getOctets();
            byte res[] = calculatePbeMac(mData.getMac().getAlgorithmId().getObjectId(), mData.getSalt(), itCount, passwd, data, provider);
            AlgorithmIdentifier algId = new AlgorithmIdentifier(mData.getMac().getAlgorithmId().getObjectId(), DERNull.INSTANCE);
            DigestInfo dInfo = new DigestInfo(algId, res);
            mData = new MacData(dInfo, mData.getSalt(), itCount);
        }
        catch(Exception e)
        {
            throw new IOException((new StringBuilder()).append("error constructing MAC: ").append(e.toString()).toString());
        }
        pfx = new Pfx(info, mData);
        bOut.reset();
        dOut.writeObject(pfx);
        return bOut.toByteArray();
    }

    private static byte[] calculatePbeMac(DERObjectIdentifier oid, byte salt[], int itCount, char password[], byte data[], String provider)
        throws Exception
    {
        SecretKeyFactory keyFact = SecretKeyFactory.getInstance(oid.getId(), provider);
        PBEParameterSpec defParams = new PBEParameterSpec(salt, itCount);
        PBEKeySpec pbeSpec = new PBEKeySpec(password);
        javax.crypto.SecretKey key = keyFact.generateSecret(pbeSpec);
        Mac mac = Mac.getInstance(oid.getId(), provider);
        mac.init(key, defParams);
        mac.update(data);
        return mac.doFinal();
    }
}
