// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2PrivateKey.java

package co.org.bouncy.pqc.asn1;

import co.org.bouncy.asn1.*;
import co.org.bouncy.pqc.math.linearalgebra.*;
import java.math.BigInteger;

public class McElieceCCA2PrivateKey extends ASN1Object
{

    public McElieceCCA2PrivateKey(ASN1ObjectIdentifier oid, int n, int k, GF2mField field, PolynomialGF2mSmallM goppaPoly, Permutation p, GF2Matrix h, 
            PolynomialGF2mSmallM qInv[])
    {
        this.oid = oid;
        this.n = n;
        this.k = k;
        encField = field.getEncoded();
        encGp = goppaPoly.getEncoded();
        encP = p.getEncoded();
        encH = h.getEncoded();
        encqInv = new byte[qInv.length][];
        for(int i = 0; i != qInv.length; i++)
            encqInv[i] = qInv[i].getEncoded();

    }

    private McElieceCCA2PrivateKey(ASN1Sequence seq)
    {
        oid = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        BigInteger bigN = ((ASN1Integer)seq.getObjectAt(1)).getValue();
        n = bigN.intValue();
        BigInteger bigK = ((ASN1Integer)seq.getObjectAt(2)).getValue();
        k = bigK.intValue();
        encField = ((ASN1OctetString)seq.getObjectAt(3)).getOctets();
        encGp = ((ASN1OctetString)seq.getObjectAt(4)).getOctets();
        encP = ((ASN1OctetString)seq.getObjectAt(5)).getOctets();
        encH = ((ASN1OctetString)seq.getObjectAt(6)).getOctets();
        ASN1Sequence asnQInv = (ASN1Sequence)seq.getObjectAt(7);
        encqInv = new byte[asnQInv.size()][];
        for(int i = 0; i < asnQInv.size(); i++)
            encqInv[i] = ((ASN1OctetString)asnQInv.getObjectAt(i)).getOctets();

    }

    public ASN1ObjectIdentifier getOID()
    {
        return oid;
    }

    public int getN()
    {
        return n;
    }

    public int getK()
    {
        return k;
    }

    public GF2mField getField()
    {
        return new GF2mField(encField);
    }

    public PolynomialGF2mSmallM getGoppaPoly()
    {
        return new PolynomialGF2mSmallM(getField(), encGp);
    }

    public Permutation getP()
    {
        return new Permutation(encP);
    }

    public GF2Matrix getH()
    {
        return new GF2Matrix(encH);
    }

    public PolynomialGF2mSmallM[] getQInv()
    {
        PolynomialGF2mSmallM qInv[] = new PolynomialGF2mSmallM[encqInv.length];
        GF2mField field = getField();
        for(int i = 0; i < encqInv.length; i++)
            qInv[i] = new PolynomialGF2mSmallM(field, encqInv[i]);

        return qInv;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(oid);
        v.add(new ASN1Integer(n));
        v.add(new ASN1Integer(k));
        v.add(new DEROctetString(encField));
        v.add(new DEROctetString(encGp));
        v.add(new DEROctetString(encP));
        v.add(new DEROctetString(encH));
        ASN1EncodableVector asnQInv = new ASN1EncodableVector();
        for(int i = 0; i < encqInv.length; i++)
            asnQInv.add(new DEROctetString(encqInv[i]));

        v.add(new DERSequence(asnQInv));
        return new DERSequence(v);
    }

    public static McElieceCCA2PrivateKey getInstance(Object o)
    {
        if(o instanceof McElieceCCA2PrivateKey)
            return (McElieceCCA2PrivateKey)o;
        if(o != null)
            return new McElieceCCA2PrivateKey(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private ASN1ObjectIdentifier oid;
    private int n;
    private int k;
    private byte encField[];
    private byte encGp[];
    private byte encP[];
    private byte encH[];
    private byte encqInv[][];
}
