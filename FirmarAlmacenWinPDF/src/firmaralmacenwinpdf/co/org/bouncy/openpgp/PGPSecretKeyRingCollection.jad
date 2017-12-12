// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSecretKeyRingCollection.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGOutputStream;
import co.org.bouncy.util.Strings;
import java.io.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPObjectFactory, PGPSecretKeyRing, PGPException, PGPPublicKey, 
//            PGPSecretKey

public class PGPSecretKeyRingCollection
{

    private PGPSecretKeyRingCollection(Map secretRings, List order)
    {
        this.secretRings = new HashMap();
        this.order = new ArrayList();
        this.secretRings = secretRings;
        this.order = order;
    }

    public PGPSecretKeyRingCollection(byte encoding[])
        throws IOException, PGPException
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))));
    }

    public PGPSecretKeyRingCollection(InputStream in)
        throws IOException, PGPException
    {
        secretRings = new HashMap();
        order = new ArrayList();
        PGPObjectFactory pgpFact = new PGPObjectFactory(in);
        Object obj;
        while((obj = pgpFact.nextObject()) != null) 
        {
            if(!(obj instanceof PGPSecretKeyRing))
                throw new PGPException((new StringBuilder()).append(obj.getClass().getName()).append(" found where PGPSecretKeyRing expected").toString());
            PGPSecretKeyRing pgpSecret = (PGPSecretKeyRing)obj;
            Long key = new Long(pgpSecret.getPublicKey().getKeyID());
            secretRings.put(key, pgpSecret);
            order.add(key);
        }
    }

    public PGPSecretKeyRingCollection(Collection collection)
        throws IOException, PGPException
    {
        secretRings = new HashMap();
        order = new ArrayList();
        Long key;
        for(Iterator it = collection.iterator(); it.hasNext(); order.add(key))
        {
            PGPSecretKeyRing pgpSecret = (PGPSecretKeyRing)it.next();
            key = new Long(pgpSecret.getPublicKey().getKeyID());
            secretRings.put(key, pgpSecret);
        }

    }

    public int size()
    {
        return order.size();
    }

    public Iterator getKeyRings()
    {
        return secretRings.values().iterator();
    }

    public Iterator getKeyRings(String userID)
        throws PGPException
    {
        return getKeyRings(userID, false, false);
    }

    public Iterator getKeyRings(String userID, boolean matchPartial)
        throws PGPException
    {
        return getKeyRings(userID, matchPartial, false);
    }

    public Iterator getKeyRings(String userID, boolean matchPartial, boolean ignoreCase)
        throws PGPException
    {
        Iterator it = getKeyRings();
        List rings = new ArrayList();
        if(ignoreCase)
            userID = Strings.toLowerCase(userID);
        while(it.hasNext()) 
        {
            PGPSecretKeyRing secRing = (PGPSecretKeyRing)it.next();
            Iterator uIt = secRing.getSecretKey().getUserIDs();
            while(uIt.hasNext()) 
            {
                String next = (String)uIt.next();
                if(ignoreCase)
                    next = Strings.toLowerCase(next);
                if(matchPartial)
                {
                    if(next.indexOf(userID) > -1)
                        rings.add(secRing);
                } else
                if(next.equals(userID))
                    rings.add(secRing);
            }
        }
        return rings.iterator();
    }

    public PGPSecretKey getSecretKey(long keyID)
        throws PGPException
    {
        for(Iterator it = getKeyRings(); it.hasNext();)
        {
            PGPSecretKeyRing secRing = (PGPSecretKeyRing)it.next();
            PGPSecretKey sec = secRing.getSecretKey(keyID);
            if(sec != null)
                return sec;
        }

        return null;
    }

    public PGPSecretKeyRing getSecretKeyRing(long keyID)
        throws PGPException
    {
        Long id = new Long(keyID);
        if(secretRings.containsKey(id))
            return (PGPSecretKeyRing)secretRings.get(id);
        for(Iterator it = getKeyRings(); it.hasNext();)
        {
            PGPSecretKeyRing secretRing = (PGPSecretKeyRing)it.next();
            PGPSecretKey secret = secretRing.getSecretKey(keyID);
            if(secret != null)
                return secretRing;
        }

        return null;
    }

    public boolean contains(long keyID)
        throws PGPException
    {
        return getSecretKey(keyID) != null;
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encode(bOut);
        return bOut.toByteArray();
    }

    public void encode(OutputStream outStream)
        throws IOException
    {
        BCPGOutputStream out;
        if(outStream instanceof BCPGOutputStream)
            out = (BCPGOutputStream)outStream;
        else
            out = new BCPGOutputStream(outStream);
        PGPSecretKeyRing sr;
        for(Iterator it = order.iterator(); it.hasNext(); sr.encode(out))
            sr = (PGPSecretKeyRing)secretRings.get(it.next());

    }

    public static PGPSecretKeyRingCollection addSecretKeyRing(PGPSecretKeyRingCollection ringCollection, PGPSecretKeyRing secretKeyRing)
    {
        Long key = new Long(secretKeyRing.getPublicKey().getKeyID());
        if(ringCollection.secretRings.containsKey(key))
        {
            throw new IllegalArgumentException("Collection already contains a key with a keyID for the passed in ring.");
        } else
        {
            Map newSecretRings = new HashMap(ringCollection.secretRings);
            List newOrder = new ArrayList(ringCollection.order);
            newSecretRings.put(key, secretKeyRing);
            newOrder.add(key);
            return new PGPSecretKeyRingCollection(newSecretRings, newOrder);
        }
    }

    public static PGPSecretKeyRingCollection removeSecretKeyRing(PGPSecretKeyRingCollection ringCollection, PGPSecretKeyRing secretKeyRing)
    {
        Long key = new Long(secretKeyRing.getPublicKey().getKeyID());
        if(!ringCollection.secretRings.containsKey(key))
            throw new IllegalArgumentException("Collection does not contain a key with a keyID for the passed in ring.");
        Map newSecretRings = new HashMap(ringCollection.secretRings);
        List newOrder = new ArrayList(ringCollection.order);
        newSecretRings.remove(key);
        int i = 0;
        do
        {
            if(i >= newOrder.size())
                break;
            Long r = (Long)newOrder.get(i);
            if(r.longValue() == key.longValue())
            {
                newOrder.remove(i);
                break;
            }
            i++;
        } while(true);
        return new PGPSecretKeyRingCollection(newSecretRings, newOrder);
    }

    private Map secretRings;
    private List order;
}
