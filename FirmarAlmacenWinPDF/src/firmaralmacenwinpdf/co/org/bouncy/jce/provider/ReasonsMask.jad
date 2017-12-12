// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReasonsMask.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.x509.ReasonFlags;

class ReasonsMask
{

    ReasonsMask(ReasonFlags reasons)
    {
        _reasons = reasons.intValue();
    }

    private ReasonsMask(int reasons)
    {
        _reasons = reasons;
    }

    ReasonsMask()
    {
        this(0);
    }

    void addReasons(ReasonsMask mask)
    {
        _reasons = _reasons | mask.getReasons();
    }

    boolean isAllReasons()
    {
        return _reasons == allReasons._reasons;
    }

    ReasonsMask intersect(ReasonsMask mask)
    {
        ReasonsMask _mask = new ReasonsMask();
        _mask.addReasons(new ReasonsMask(_reasons & mask.getReasons()));
        return _mask;
    }

    boolean hasNewReasons(ReasonsMask mask)
    {
        return (_reasons | mask.getReasons() ^ _reasons) != 0;
    }

    int getReasons()
    {
        return _reasons;
    }

    private int _reasons;
    static final ReasonsMask allReasons = new ReasonsMask(33023);

}
