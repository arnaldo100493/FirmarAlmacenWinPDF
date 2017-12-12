// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedValueBuilder.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.crmf.EncryptedValue;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.Strings;
import java.io.*;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, EncryptedValuePadder

public class EncryptedValueBuilder
{

    public EncryptedValueBuilder(KeyWrapper wrapper, OutputEncryptor encryptor)
    {
        this(wrapper, encryptor, null);
    }

    public EncryptedValueBuilder(KeyWrapper wrapper, OutputEncryptor encryptor, EncryptedValuePadder padder)
    {
        this.wrapper = wrapper;
        this.encryptor = encryptor;
        this.padder = padder;
    }

    public EncryptedValue build(char revocationPassphrase[])
        throws CRMFException
    {
        return encryptData(padData(Strings.toUTF8ByteArray(revocationPassphrase)));
    }

    public EncryptedValue build(X509CertificateHolder holder)
        throws CRMFException
    {
        try
        {
            return encryptData(padData(holder.getEncoded()));
        }
        catch(IOException e)
        {
            throw new CRMFException((new StringBuilder()).append("cannot encode certificate: ").append(e.getMessage()).toString(), e);
        }
    }

    private EncryptedValue encryptData(byte data[])
        throws CRMFException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        OutputStream eOut = encryptor.getOutputStream(bOut);
        try
        {
            eOut.write(data);
            eOut.close();
        }
        catch(IOException e)
        {
            throw new CRMFException((new StringBuilder()).append("cannot process data: ").append(e.getMessage()).toString(), e);
        }
        AlgorithmIdentifier intendedAlg = null;
        AlgorithmIdentifier symmAlg = encryptor.getAlgorithmIdentifier();
        DERBitString encSymmKey;
        try
        {
            wrapper.generateWrappedKey(encryptor.getKey());
            encSymmKey = new DERBitString(wrapper.generateWrappedKey(encryptor.getKey()));
        }
        catch(OperatorException e)
        {
            throw new CRMFException((new StringBuilder()).append("cannot wrap key: ").append(e.getMessage()).toString(), e);
        }
        AlgorithmIdentifier keyAlg = wrapper.getAlgorithmIdentifier();
        co.org.bouncy.asn1.ASN1OctetString valueHint = null;
        DERBitString encValue = new DERBitString(bOut.toByteArray());
        return new EncryptedValue(intendedAlg, symmAlg, encSymmKey, keyAlg, valueHint, encValue);
    }

    private byte[] padData(byte data[])
    {
        if(padder != null)
            return padder.getPaddedData(data);
        else
            return data;
    }

    private KeyWrapper wrapper;
    private OutputEncryptor encryptor;
    private EncryptedValuePadder padder;
}
