// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIXNameConstraintValidator.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.GeneralSubtree;
import co.org.bouncy.util.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.jce.provider:
//            PKIXNameConstraintValidatorException

public class PKIXNameConstraintValidator
{

    public PKIXNameConstraintValidator()
    {
        excludedSubtreesDN = new HashSet();
        excludedSubtreesDNS = new HashSet();
        excludedSubtreesEmail = new HashSet();
        excludedSubtreesURI = new HashSet();
        excludedSubtreesIP = new HashSet();
    }

    private static boolean withinDNSubtree(ASN1Sequence dns, ASN1Sequence subtree)
    {
        if(subtree.size() < 1)
            return false;
        if(subtree.size() > dns.size())
            return false;
        for(int j = subtree.size() - 1; j >= 0; j--)
            if(!subtree.getObjectAt(j).equals(dns.getObjectAt(j)))
                return false;

        return true;
    }

    public void checkPermittedDN(ASN1Sequence dns)
        throws PKIXNameConstraintValidatorException
    {
        checkPermittedDN(permittedSubtreesDN, dns);
    }

    public void checkExcludedDN(ASN1Sequence dns)
        throws PKIXNameConstraintValidatorException
    {
        checkExcludedDN(excludedSubtreesDN, dns);
    }

    private void checkPermittedDN(Set permitted, ASN1Sequence dns)
        throws PKIXNameConstraintValidatorException
    {
        if(permitted == null)
            return;
        if(permitted.isEmpty() && dns.size() == 0)
            return;
        for(Iterator it = permitted.iterator(); it.hasNext();)
        {
            ASN1Sequence subtree = (ASN1Sequence)it.next();
            if(withinDNSubtree(dns, subtree))
                return;
        }

        throw new PKIXNameConstraintValidatorException("Subject distinguished name is not from a permitted subtree");
    }

    private void checkExcludedDN(Set excluded, ASN1Sequence dns)
        throws PKIXNameConstraintValidatorException
    {
        if(excluded.isEmpty())
            return;
        for(Iterator it = excluded.iterator(); it.hasNext();)
        {
            ASN1Sequence subtree = (ASN1Sequence)it.next();
            if(withinDNSubtree(dns, subtree))
                throw new PKIXNameConstraintValidatorException("Subject distinguished name is from an excluded subtree");
        }

    }

    private Set intersectDN(Set permitted, Set dns)
    {
        Set intersect = new HashSet();
        for(Iterator it = dns.iterator(); it.hasNext();)
        {
            ASN1Sequence dn = ASN1Sequence.getInstance(((GeneralSubtree)it.next()).getBase().getName().toASN1Primitive());
            if(permitted == null)
            {
                if(dn != null)
                    intersect.add(dn);
            } else
            {
                Iterator _iter = permitted.iterator();
                while(_iter.hasNext()) 
                {
                    ASN1Sequence subtree = (ASN1Sequence)_iter.next();
                    if(withinDNSubtree(dn, subtree))
                        intersect.add(dn);
                    else
                    if(withinDNSubtree(subtree, dn))
                        intersect.add(subtree);
                }
            }
        }

        return intersect;
    }

    private Set unionDN(Set excluded, ASN1Sequence dn)
    {
        if(excluded.isEmpty())
            if(dn == null)
            {
                return excluded;
            } else
            {
                excluded.add(dn);
                return excluded;
            }
        Set intersect = new HashSet();
        for(Iterator it = excluded.iterator(); it.hasNext();)
        {
            ASN1Sequence subtree = (ASN1Sequence)it.next();
            if(withinDNSubtree(dn, subtree))
                intersect.add(subtree);
            else
            if(withinDNSubtree(subtree, dn))
            {
                intersect.add(dn);
            } else
            {
                intersect.add(subtree);
                intersect.add(dn);
            }
        }

        return intersect;
    }

    private Set intersectEmail(Set permitted, Set emails)
    {
        Set intersect = new HashSet();
        Iterator it = emails.iterator();
        do
        {
            if(!it.hasNext())
                break;
            String email = extractNameAsString(((GeneralSubtree)it.next()).getBase());
            if(permitted == null)
            {
                if(email != null)
                    intersect.add(email);
            } else
            {
                Iterator it2 = permitted.iterator();
                while(it2.hasNext()) 
                {
                    String _permitted = (String)it2.next();
                    intersectEmail(email, _permitted, intersect);
                }
            }
        } while(true);
        return intersect;
    }

    private Set unionEmail(Set excluded, String email)
    {
        if(excluded.isEmpty())
            if(email == null)
            {
                return excluded;
            } else
            {
                excluded.add(email);
                return excluded;
            }
        Set union = new HashSet();
        String _excluded;
        for(Iterator it = excluded.iterator(); it.hasNext(); unionEmail(_excluded, email, union))
            _excluded = (String)it.next();

        return union;
    }

    private Set intersectIP(Set permitted, Set ips)
    {
        Set intersect = new HashSet();
        Iterator it = ips.iterator();
        do
        {
            if(!it.hasNext())
                break;
            byte ip[] = ASN1OctetString.getInstance(((GeneralSubtree)it.next()).getBase().getName()).getOctets();
            if(permitted == null)
            {
                if(ip != null)
                    intersect.add(ip);
            } else
            {
                Iterator it2 = permitted.iterator();
                while(it2.hasNext()) 
                {
                    byte _permitted[] = (byte[])(byte[])it2.next();
                    intersect.addAll(intersectIPRange(_permitted, ip));
                }
            }
        } while(true);
        return intersect;
    }

    private Set unionIP(Set excluded, byte ip[])
    {
        if(excluded.isEmpty())
            if(ip == null)
            {
                return excluded;
            } else
            {
                excluded.add(ip);
                return excluded;
            }
        Set union = new HashSet();
        byte _excluded[];
        for(Iterator it = excluded.iterator(); it.hasNext(); union.addAll(unionIPRange(_excluded, ip)))
            _excluded = (byte[])(byte[])it.next();

        return union;
    }

    private Set unionIPRange(byte ipWithSubmask1[], byte ipWithSubmask2[])
    {
        Set set = new HashSet();
        if(Arrays.areEqual(ipWithSubmask1, ipWithSubmask2))
        {
            set.add(ipWithSubmask1);
        } else
        {
            set.add(ipWithSubmask1);
            set.add(ipWithSubmask2);
        }
        return set;
    }

    private Set intersectIPRange(byte ipWithSubmask1[], byte ipWithSubmask2[])
    {
        if(ipWithSubmask1.length != ipWithSubmask2.length)
            return Collections.EMPTY_SET;
        byte temp[][] = extractIPsAndSubnetMasks(ipWithSubmask1, ipWithSubmask2);
        byte ip1[] = temp[0];
        byte subnetmask1[] = temp[1];
        byte ip2[] = temp[2];
        byte subnetmask2[] = temp[3];
        byte minMax[][] = minMaxIPs(ip1, subnetmask1, ip2, subnetmask2);
        byte max[] = min(minMax[1], minMax[3]);
        byte min[] = max(minMax[0], minMax[2]);
        if(compareTo(min, max) == 1)
        {
            return Collections.EMPTY_SET;
        } else
        {
            byte ip[] = or(minMax[0], minMax[2]);
            byte subnetmask[] = or(subnetmask1, subnetmask2);
            return Collections.singleton(ipWithSubnetMask(ip, subnetmask));
        }
    }

    private byte[] ipWithSubnetMask(byte ip[], byte subnetMask[])
    {
        int ipLength = ip.length;
        byte temp[] = new byte[ipLength * 2];
        System.arraycopy(ip, 0, temp, 0, ipLength);
        System.arraycopy(subnetMask, 0, temp, ipLength, ipLength);
        return temp;
    }

    private byte[][] extractIPsAndSubnetMasks(byte ipWithSubmask1[], byte ipWithSubmask2[])
    {
        int ipLength = ipWithSubmask1.length / 2;
        byte ip1[] = new byte[ipLength];
        byte subnetmask1[] = new byte[ipLength];
        System.arraycopy(ipWithSubmask1, 0, ip1, 0, ipLength);
        System.arraycopy(ipWithSubmask1, ipLength, subnetmask1, 0, ipLength);
        byte ip2[] = new byte[ipLength];
        byte subnetmask2[] = new byte[ipLength];
        System.arraycopy(ipWithSubmask2, 0, ip2, 0, ipLength);
        System.arraycopy(ipWithSubmask2, ipLength, subnetmask2, 0, ipLength);
        return (new byte[][] {
            ip1, subnetmask1, ip2, subnetmask2
        });
    }

    private byte[][] minMaxIPs(byte ip1[], byte subnetmask1[], byte ip2[], byte subnetmask2[])
    {
        int ipLength = ip1.length;
        byte min1[] = new byte[ipLength];
        byte max1[] = new byte[ipLength];
        byte min2[] = new byte[ipLength];
        byte max2[] = new byte[ipLength];
        for(int i = 0; i < ipLength; i++)
        {
            min1[i] = (byte)(ip1[i] & subnetmask1[i]);
            max1[i] = (byte)(ip1[i] & subnetmask1[i] | ~subnetmask1[i]);
            min2[i] = (byte)(ip2[i] & subnetmask2[i]);
            max2[i] = (byte)(ip2[i] & subnetmask2[i] | ~subnetmask2[i]);
        }

        return (new byte[][] {
            min1, max1, min2, max2
        });
    }

    private void checkPermittedEmail(Set permitted, String email)
        throws PKIXNameConstraintValidatorException
    {
        if(permitted == null)
            return;
        for(Iterator it = permitted.iterator(); it.hasNext();)
        {
            String str = (String)it.next();
            if(emailIsConstrained(email, str))
                return;
        }

        if(email.length() == 0 && permitted.size() == 0)
            return;
        else
            throw new PKIXNameConstraintValidatorException("Subject email address is not from a permitted subtree.");
    }

    private void checkExcludedEmail(Set excluded, String email)
        throws PKIXNameConstraintValidatorException
    {
        if(excluded.isEmpty())
            return;
        for(Iterator it = excluded.iterator(); it.hasNext();)
        {
            String str = (String)it.next();
            if(emailIsConstrained(email, str))
                throw new PKIXNameConstraintValidatorException("Email address is from an excluded subtree.");
        }

    }

    private void checkPermittedIP(Set permitted, byte ip[])
        throws PKIXNameConstraintValidatorException
    {
        if(permitted == null)
            return;
        for(Iterator it = permitted.iterator(); it.hasNext();)
        {
            byte ipWithSubnet[] = (byte[])(byte[])it.next();
            if(isIPConstrained(ip, ipWithSubnet))
                return;
        }

        if(ip.length == 0 && permitted.size() == 0)
            return;
        else
            throw new PKIXNameConstraintValidatorException("IP is not from a permitted subtree.");
    }

    private void checkExcludedIP(Set excluded, byte ip[])
        throws PKIXNameConstraintValidatorException
    {
        if(excluded.isEmpty())
            return;
        for(Iterator it = excluded.iterator(); it.hasNext();)
        {
            byte ipWithSubnet[] = (byte[])(byte[])it.next();
            if(isIPConstrained(ip, ipWithSubnet))
                throw new PKIXNameConstraintValidatorException("IP is from an excluded subtree.");
        }

    }

    private boolean isIPConstrained(byte ip[], byte constraint[])
    {
        int ipLength = ip.length;
        if(ipLength != constraint.length / 2)
            return false;
        byte subnetMask[] = new byte[ipLength];
        System.arraycopy(constraint, ipLength, subnetMask, 0, ipLength);
        byte permittedSubnetAddress[] = new byte[ipLength];
        byte ipSubnetAddress[] = new byte[ipLength];
        for(int i = 0; i < ipLength; i++)
        {
            permittedSubnetAddress[i] = (byte)(constraint[i] & subnetMask[i]);
            ipSubnetAddress[i] = (byte)(ip[i] & subnetMask[i]);
        }

        return Arrays.areEqual(permittedSubnetAddress, ipSubnetAddress);
    }

    private boolean emailIsConstrained(String email, String constraint)
    {
        String sub = email.substring(email.indexOf('@') + 1);
        if(constraint.indexOf('@') != -1)
        {
            if(email.equalsIgnoreCase(constraint))
                return true;
        } else
        if(constraint.charAt(0) != '.')
        {
            if(sub.equalsIgnoreCase(constraint))
                return true;
        } else
        if(withinDomain(sub, constraint))
            return true;
        return false;
    }

    private boolean withinDomain(String testDomain, String domain)
    {
        String tempDomain = domain;
        if(tempDomain.startsWith("."))
            tempDomain = tempDomain.substring(1);
        String domainParts[] = Strings.split(tempDomain, '.');
        String testDomainParts[] = Strings.split(testDomain, '.');
        if(testDomainParts.length <= domainParts.length)
            return false;
        int d = testDomainParts.length - domainParts.length;
        for(int i = -1; i < domainParts.length; i++)
        {
            if(i == -1)
            {
                if(testDomainParts[i + d].equals(""))
                    return false;
                continue;
            }
            if(!domainParts[i].equalsIgnoreCase(testDomainParts[i + d]))
                return false;
        }

        return true;
    }

    private void checkPermittedDNS(Set permitted, String dns)
        throws PKIXNameConstraintValidatorException
    {
        if(permitted == null)
            return;
        for(Iterator it = permitted.iterator(); it.hasNext();)
        {
            String str = (String)it.next();
            if(withinDomain(dns, str) || dns.equalsIgnoreCase(str))
                return;
        }

        if(dns.length() == 0 && permitted.size() == 0)
            return;
        else
            throw new PKIXNameConstraintValidatorException("DNS is not from a permitted subtree.");
    }

    private void checkExcludedDNS(Set excluded, String dns)
        throws PKIXNameConstraintValidatorException
    {
        if(excluded.isEmpty())
            return;
        for(Iterator it = excluded.iterator(); it.hasNext();)
        {
            String str = (String)it.next();
            if(withinDomain(dns, str) || dns.equalsIgnoreCase(str))
                throw new PKIXNameConstraintValidatorException("DNS is from an excluded subtree.");
        }

    }

    private void unionEmail(String email1, String email2, Set union)
    {
        if(email1.indexOf('@') != -1)
        {
            String _sub = email1.substring(email1.indexOf('@') + 1);
            if(email2.indexOf('@') != -1)
            {
                if(email1.equalsIgnoreCase(email2))
                {
                    union.add(email1);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(_sub, email2))
                {
                    union.add(email2);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(_sub.equalsIgnoreCase(email2))
            {
                union.add(email2);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email1.startsWith("."))
        {
            if(email2.indexOf('@') != -1)
            {
                String _sub = email2.substring(email1.indexOf('@') + 1);
                if(withinDomain(_sub, email1))
                {
                    union.add(email1);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(email1, email2) || email1.equalsIgnoreCase(email2))
                    union.add(email2);
                else
                if(withinDomain(email2, email1))
                {
                    union.add(email1);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(withinDomain(email2, email1))
            {
                union.add(email1);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email2.indexOf('@') != -1)
        {
            String _sub = email2.substring(email1.indexOf('@') + 1);
            if(_sub.equalsIgnoreCase(email1))
            {
                union.add(email1);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email2.startsWith("."))
        {
            if(withinDomain(email1, email2))
            {
                union.add(email2);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email1.equalsIgnoreCase(email2))
        {
            union.add(email1);
        } else
        {
            union.add(email1);
            union.add(email2);
        }
    }

    private void unionURI(String email1, String email2, Set union)
    {
        if(email1.indexOf('@') != -1)
        {
            String _sub = email1.substring(email1.indexOf('@') + 1);
            if(email2.indexOf('@') != -1)
            {
                if(email1.equalsIgnoreCase(email2))
                {
                    union.add(email1);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(_sub, email2))
                {
                    union.add(email2);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(_sub.equalsIgnoreCase(email2))
            {
                union.add(email2);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email1.startsWith("."))
        {
            if(email2.indexOf('@') != -1)
            {
                String _sub = email2.substring(email1.indexOf('@') + 1);
                if(withinDomain(_sub, email1))
                {
                    union.add(email1);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(email1, email2) || email1.equalsIgnoreCase(email2))
                    union.add(email2);
                else
                if(withinDomain(email2, email1))
                {
                    union.add(email1);
                } else
                {
                    union.add(email1);
                    union.add(email2);
                }
            } else
            if(withinDomain(email2, email1))
            {
                union.add(email1);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email2.indexOf('@') != -1)
        {
            String _sub = email2.substring(email1.indexOf('@') + 1);
            if(_sub.equalsIgnoreCase(email1))
            {
                union.add(email1);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email2.startsWith("."))
        {
            if(withinDomain(email1, email2))
            {
                union.add(email2);
            } else
            {
                union.add(email1);
                union.add(email2);
            }
        } else
        if(email1.equalsIgnoreCase(email2))
        {
            union.add(email1);
        } else
        {
            union.add(email1);
            union.add(email2);
        }
    }

    private Set intersectDNS(Set permitted, Set dnss)
    {
        Set intersect = new HashSet();
        for(Iterator it = dnss.iterator(); it.hasNext();)
        {
            String dns = extractNameAsString(((GeneralSubtree)it.next()).getBase());
            if(permitted == null)
            {
                if(dns != null)
                    intersect.add(dns);
            } else
            {
                Iterator _iter = permitted.iterator();
                while(_iter.hasNext()) 
                {
                    String _permitted = (String)_iter.next();
                    if(withinDomain(_permitted, dns))
                        intersect.add(_permitted);
                    else
                    if(withinDomain(dns, _permitted))
                        intersect.add(dns);
                }
            }
        }

        return intersect;
    }

    protected Set unionDNS(Set excluded, String dns)
    {
        if(excluded.isEmpty())
            if(dns == null)
            {
                return excluded;
            } else
            {
                excluded.add(dns);
                return excluded;
            }
        Set union = new HashSet();
        for(Iterator _iter = excluded.iterator(); _iter.hasNext();)
        {
            String _permitted = (String)_iter.next();
            if(withinDomain(_permitted, dns))
                union.add(dns);
            else
            if(withinDomain(dns, _permitted))
            {
                union.add(_permitted);
            } else
            {
                union.add(_permitted);
                union.add(dns);
            }
        }

        return union;
    }

    private void intersectEmail(String email1, String email2, Set intersect)
    {
        if(email1.indexOf('@') != -1)
        {
            String _sub = email1.substring(email1.indexOf('@') + 1);
            if(email2.indexOf('@') != -1)
            {
                if(email1.equalsIgnoreCase(email2))
                    intersect.add(email1);
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(_sub, email2))
                    intersect.add(email1);
            } else
            if(_sub.equalsIgnoreCase(email2))
                intersect.add(email1);
        } else
        if(email1.startsWith("."))
        {
            if(email2.indexOf('@') != -1)
            {
                String _sub = email2.substring(email1.indexOf('@') + 1);
                if(withinDomain(_sub, email1))
                    intersect.add(email2);
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(email1, email2) || email1.equalsIgnoreCase(email2))
                    intersect.add(email1);
                else
                if(withinDomain(email2, email1))
                    intersect.add(email2);
            } else
            if(withinDomain(email2, email1))
                intersect.add(email2);
        } else
        if(email2.indexOf('@') != -1)
        {
            String _sub = email2.substring(email2.indexOf('@') + 1);
            if(_sub.equalsIgnoreCase(email1))
                intersect.add(email2);
        } else
        if(email2.startsWith("."))
        {
            if(withinDomain(email1, email2))
                intersect.add(email1);
        } else
        if(email1.equalsIgnoreCase(email2))
            intersect.add(email1);
    }

    private void checkExcludedURI(Set excluded, String uri)
        throws PKIXNameConstraintValidatorException
    {
        if(excluded.isEmpty())
            return;
        for(Iterator it = excluded.iterator(); it.hasNext();)
        {
            String str = (String)it.next();
            if(isUriConstrained(uri, str))
                throw new PKIXNameConstraintValidatorException("URI is from an excluded subtree.");
        }

    }

    private Set intersectURI(Set permitted, Set uris)
    {
        Set intersect = new HashSet();
        Iterator it = uris.iterator();
        do
        {
            if(!it.hasNext())
                break;
            String uri = extractNameAsString(((GeneralSubtree)it.next()).getBase());
            if(permitted == null)
            {
                if(uri != null)
                    intersect.add(uri);
            } else
            {
                Iterator _iter = permitted.iterator();
                while(_iter.hasNext()) 
                {
                    String _permitted = (String)_iter.next();
                    intersectURI(_permitted, uri, intersect);
                }
            }
        } while(true);
        return intersect;
    }

    private Set unionURI(Set excluded, String uri)
    {
        if(excluded.isEmpty())
            if(uri == null)
            {
                return excluded;
            } else
            {
                excluded.add(uri);
                return excluded;
            }
        Set union = new HashSet();
        String _excluded;
        for(Iterator _iter = excluded.iterator(); _iter.hasNext(); unionURI(_excluded, uri, union))
            _excluded = (String)_iter.next();

        return union;
    }

    private void intersectURI(String email1, String email2, Set intersect)
    {
        if(email1.indexOf('@') != -1)
        {
            String _sub = email1.substring(email1.indexOf('@') + 1);
            if(email2.indexOf('@') != -1)
            {
                if(email1.equalsIgnoreCase(email2))
                    intersect.add(email1);
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(_sub, email2))
                    intersect.add(email1);
            } else
            if(_sub.equalsIgnoreCase(email2))
                intersect.add(email1);
        } else
        if(email1.startsWith("."))
        {
            if(email2.indexOf('@') != -1)
            {
                String _sub = email2.substring(email1.indexOf('@') + 1);
                if(withinDomain(_sub, email1))
                    intersect.add(email2);
            } else
            if(email2.startsWith("."))
            {
                if(withinDomain(email1, email2) || email1.equalsIgnoreCase(email2))
                    intersect.add(email1);
                else
                if(withinDomain(email2, email1))
                    intersect.add(email2);
            } else
            if(withinDomain(email2, email1))
                intersect.add(email2);
        } else
        if(email2.indexOf('@') != -1)
        {
            String _sub = email2.substring(email2.indexOf('@') + 1);
            if(_sub.equalsIgnoreCase(email1))
                intersect.add(email2);
        } else
        if(email2.startsWith("."))
        {
            if(withinDomain(email1, email2))
                intersect.add(email1);
        } else
        if(email1.equalsIgnoreCase(email2))
            intersect.add(email1);
    }

    private void checkPermittedURI(Set permitted, String uri)
        throws PKIXNameConstraintValidatorException
    {
        if(permitted == null)
            return;
        for(Iterator it = permitted.iterator(); it.hasNext();)
        {
            String str = (String)it.next();
            if(isUriConstrained(uri, str))
                return;
        }

        if(uri.length() == 0 && permitted.size() == 0)
            return;
        else
            throw new PKIXNameConstraintValidatorException("URI is not from a permitted subtree.");
    }

    private boolean isUriConstrained(String uri, String constraint)
    {
        String host = extractHostFromURL(uri);
        if(!constraint.startsWith("."))
        {
            if(host.equalsIgnoreCase(constraint))
                return true;
        } else
        if(withinDomain(host, constraint))
            return true;
        return false;
    }

    private static String extractHostFromURL(String url)
    {
        String sub = url.substring(url.indexOf(':') + 1);
        if(sub.indexOf("//") != -1)
            sub = sub.substring(sub.indexOf("//") + 2);
        if(sub.lastIndexOf(':') != -1)
            sub = sub.substring(0, sub.lastIndexOf(':'));
        sub = sub.substring(sub.indexOf(':') + 1);
        sub = sub.substring(sub.indexOf('@') + 1);
        if(sub.indexOf('/') != -1)
            sub = sub.substring(0, sub.indexOf('/'));
        return sub;
    }

    public void checkPermitted(GeneralName name)
        throws PKIXNameConstraintValidatorException
    {
        switch(name.getTagNo())
        {
        case 1: // '\001'
            checkPermittedEmail(permittedSubtreesEmail, extractNameAsString(name));
            break;

        case 2: // '\002'
            checkPermittedDNS(permittedSubtreesDNS, DERIA5String.getInstance(name.getName()).getString());
            break;

        case 4: // '\004'
            checkPermittedDN(ASN1Sequence.getInstance(name.getName().toASN1Primitive()));
            break;

        case 6: // '\006'
            checkPermittedURI(permittedSubtreesURI, DERIA5String.getInstance(name.getName()).getString());
            break;

        case 7: // '\007'
            byte ip[] = ASN1OctetString.getInstance(name.getName()).getOctets();
            checkPermittedIP(permittedSubtreesIP, ip);
            break;
        }
    }

    public void checkExcluded(GeneralName name)
        throws PKIXNameConstraintValidatorException
    {
        switch(name.getTagNo())
        {
        case 1: // '\001'
            checkExcludedEmail(excludedSubtreesEmail, extractNameAsString(name));
            break;

        case 2: // '\002'
            checkExcludedDNS(excludedSubtreesDNS, DERIA5String.getInstance(name.getName()).getString());
            break;

        case 4: // '\004'
            checkExcludedDN(ASN1Sequence.getInstance(name.getName().toASN1Primitive()));
            break;

        case 6: // '\006'
            checkExcludedURI(excludedSubtreesURI, DERIA5String.getInstance(name.getName()).getString());
            break;

        case 7: // '\007'
            byte ip[] = ASN1OctetString.getInstance(name.getName()).getOctets();
            checkExcludedIP(excludedSubtreesIP, ip);
            break;
        }
    }

    public void intersectPermittedSubtree(GeneralSubtree permitted)
    {
        intersectPermittedSubtree(new GeneralSubtree[] {
            permitted
        });
    }

    public void intersectPermittedSubtree(GeneralSubtree permitted[])
    {
        Map subtreesMap = new HashMap();
        for(int i = 0; i != permitted.length; i++)
        {
            GeneralSubtree subtree = permitted[i];
            Integer tagNo = Integers.valueOf(subtree.getBase().getTagNo());
            if(subtreesMap.get(tagNo) == null)
                subtreesMap.put(tagNo, new HashSet());
            ((Set)subtreesMap.get(tagNo)).add(subtree);
        }

        Iterator it = subtreesMap.entrySet().iterator();
        do
        {
            if(!it.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            switch(((Integer)entry.getKey()).intValue())
            {
            case 1: // '\001'
                permittedSubtreesEmail = intersectEmail(permittedSubtreesEmail, (Set)entry.getValue());
                break;

            case 2: // '\002'
                permittedSubtreesDNS = intersectDNS(permittedSubtreesDNS, (Set)entry.getValue());
                break;

            case 4: // '\004'
                permittedSubtreesDN = intersectDN(permittedSubtreesDN, (Set)entry.getValue());
                break;

            case 6: // '\006'
                permittedSubtreesURI = intersectURI(permittedSubtreesURI, (Set)entry.getValue());
                break;

            case 7: // '\007'
                permittedSubtreesIP = intersectIP(permittedSubtreesIP, (Set)entry.getValue());
                break;
            }
        } while(true);
    }

    private String extractNameAsString(GeneralName name)
    {
        return DERIA5String.getInstance(name.getName()).getString();
    }

    public void intersectEmptyPermittedSubtree(int nameType)
    {
        switch(nameType)
        {
        case 1: // '\001'
            permittedSubtreesEmail = new HashSet();
            break;

        case 2: // '\002'
            permittedSubtreesDNS = new HashSet();
            break;

        case 4: // '\004'
            permittedSubtreesDN = new HashSet();
            break;

        case 6: // '\006'
            permittedSubtreesURI = new HashSet();
            break;

        case 7: // '\007'
            permittedSubtreesIP = new HashSet();
            break;
        }
    }

    public void addExcludedSubtree(GeneralSubtree subtree)
    {
        GeneralName base = subtree.getBase();
        switch(base.getTagNo())
        {
        case 1: // '\001'
            excludedSubtreesEmail = unionEmail(excludedSubtreesEmail, extractNameAsString(base));
            break;

        case 2: // '\002'
            excludedSubtreesDNS = unionDNS(excludedSubtreesDNS, extractNameAsString(base));
            break;

        case 4: // '\004'
            excludedSubtreesDN = unionDN(excludedSubtreesDN, (ASN1Sequence)base.getName().toASN1Primitive());
            break;

        case 6: // '\006'
            excludedSubtreesURI = unionURI(excludedSubtreesURI, extractNameAsString(base));
            break;

        case 7: // '\007'
            excludedSubtreesIP = unionIP(excludedSubtreesIP, ASN1OctetString.getInstance(base.getName()).getOctets());
            break;
        }
    }

    private static byte[] max(byte ip1[], byte ip2[])
    {
        for(int i = 0; i < ip1.length; i++)
            if((ip1[i] & 0xffff) > (ip2[i] & 0xffff))
                return ip1;

        return ip2;
    }

    private static byte[] min(byte ip1[], byte ip2[])
    {
        for(int i = 0; i < ip1.length; i++)
            if((ip1[i] & 0xffff) < (ip2[i] & 0xffff))
                return ip1;

        return ip2;
    }

    private static int compareTo(byte ip1[], byte ip2[])
    {
        if(Arrays.areEqual(ip1, ip2))
            return 0;
        return !Arrays.areEqual(max(ip1, ip2), ip1) ? -1 : 1;
    }

    private static byte[] or(byte ip1[], byte ip2[])
    {
        byte temp[] = new byte[ip1.length];
        for(int i = 0; i < ip1.length; i++)
            temp[i] = (byte)(ip1[i] | ip2[i]);

        return temp;
    }

    public int hashCode()
    {
        return hashCollection(excludedSubtreesDN) + hashCollection(excludedSubtreesDNS) + hashCollection(excludedSubtreesEmail) + hashCollection(excludedSubtreesIP) + hashCollection(excludedSubtreesURI) + hashCollection(permittedSubtreesDN) + hashCollection(permittedSubtreesDNS) + hashCollection(permittedSubtreesEmail) + hashCollection(permittedSubtreesIP) + hashCollection(permittedSubtreesURI);
    }

    private int hashCollection(Collection coll)
    {
        if(coll == null)
            return 0;
        int hash = 0;
        for(Iterator it1 = coll.iterator(); it1.hasNext();)
        {
            Object o = it1.next();
            if(o instanceof byte[])
                hash += Arrays.hashCode((byte[])(byte[])o);
            else
                hash += o.hashCode();
        }

        return hash;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof PKIXNameConstraintValidator))
        {
            return false;
        } else
        {
            PKIXNameConstraintValidator constraintValidator = (PKIXNameConstraintValidator)o;
            return collectionsAreEqual(constraintValidator.excludedSubtreesDN, excludedSubtreesDN) && collectionsAreEqual(constraintValidator.excludedSubtreesDNS, excludedSubtreesDNS) && collectionsAreEqual(constraintValidator.excludedSubtreesEmail, excludedSubtreesEmail) && collectionsAreEqual(constraintValidator.excludedSubtreesIP, excludedSubtreesIP) && collectionsAreEqual(constraintValidator.excludedSubtreesURI, excludedSubtreesURI) && collectionsAreEqual(constraintValidator.permittedSubtreesDN, permittedSubtreesDN) && collectionsAreEqual(constraintValidator.permittedSubtreesDNS, permittedSubtreesDNS) && collectionsAreEqual(constraintValidator.permittedSubtreesEmail, permittedSubtreesEmail) && collectionsAreEqual(constraintValidator.permittedSubtreesIP, permittedSubtreesIP) && collectionsAreEqual(constraintValidator.permittedSubtreesURI, permittedSubtreesURI);
        }
    }

    private boolean collectionsAreEqual(Collection coll1, Collection coll2)
    {
label0:
        {
            if(coll1 == coll2)
                return true;
            if(coll1 == null || coll2 == null)
                return false;
            if(coll1.size() != coll2.size())
                return false;
            Iterator it1 = coll1.iterator();
            boolean found;
label1:
            do
            {
                if(!it1.hasNext())
                    break label0;
                Object a = it1.next();
                Iterator it2 = coll2.iterator();
                found = false;
                Object b;
                do
                {
                    if(!it2.hasNext())
                        continue label1;
                    b = it2.next();
                } while(!equals(a, b));
                found = true;
            } while(found);
            return false;
        }
        return true;
    }

    private boolean equals(Object o1, Object o2)
    {
        if(o1 == o2)
            return true;
        if(o1 == null || o2 == null)
            return false;
        if((o1 instanceof byte[]) && (o2 instanceof byte[]))
            return Arrays.areEqual((byte[])(byte[])o1, (byte[])(byte[])o2);
        else
            return o1.equals(o2);
    }

    private String stringifyIP(byte ip[])
    {
        String temp = "";
        for(int i = 0; i < ip.length / 2; i++)
            temp = (new StringBuilder()).append(temp).append(Integer.toString(ip[i] & 0xff)).append(".").toString();

        temp = temp.substring(0, temp.length() - 1);
        temp = (new StringBuilder()).append(temp).append("/").toString();
        for(int i = ip.length / 2; i < ip.length; i++)
            temp = (new StringBuilder()).append(temp).append(Integer.toString(ip[i] & 0xff)).append(".").toString();

        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    private String stringifyIPCollection(Set ips)
    {
        String temp = "";
        temp = (new StringBuilder()).append(temp).append("[").toString();
        for(Iterator it = ips.iterator(); it.hasNext();)
            temp = (new StringBuilder()).append(temp).append(stringifyIP((byte[])(byte[])it.next())).append(",").toString();

        if(temp.length() > 1)
            temp = temp.substring(0, temp.length() - 1);
        temp = (new StringBuilder()).append(temp).append("]").toString();
        return temp;
    }

    public String toString()
    {
        String temp = "";
        temp = (new StringBuilder()).append(temp).append("permitted:\n").toString();
        if(permittedSubtreesDN != null)
        {
            temp = (new StringBuilder()).append(temp).append("DN:\n").toString();
            temp = (new StringBuilder()).append(temp).append(permittedSubtreesDN.toString()).append("\n").toString();
        }
        if(permittedSubtreesDNS != null)
        {
            temp = (new StringBuilder()).append(temp).append("DNS:\n").toString();
            temp = (new StringBuilder()).append(temp).append(permittedSubtreesDNS.toString()).append("\n").toString();
        }
        if(permittedSubtreesEmail != null)
        {
            temp = (new StringBuilder()).append(temp).append("Email:\n").toString();
            temp = (new StringBuilder()).append(temp).append(permittedSubtreesEmail.toString()).append("\n").toString();
        }
        if(permittedSubtreesURI != null)
        {
            temp = (new StringBuilder()).append(temp).append("URI:\n").toString();
            temp = (new StringBuilder()).append(temp).append(permittedSubtreesURI.toString()).append("\n").toString();
        }
        if(permittedSubtreesIP != null)
        {
            temp = (new StringBuilder()).append(temp).append("IP:\n").toString();
            temp = (new StringBuilder()).append(temp).append(stringifyIPCollection(permittedSubtreesIP)).append("\n").toString();
        }
        temp = (new StringBuilder()).append(temp).append("excluded:\n").toString();
        if(!excludedSubtreesDN.isEmpty())
        {
            temp = (new StringBuilder()).append(temp).append("DN:\n").toString();
            temp = (new StringBuilder()).append(temp).append(excludedSubtreesDN.toString()).append("\n").toString();
        }
        if(!excludedSubtreesDNS.isEmpty())
        {
            temp = (new StringBuilder()).append(temp).append("DNS:\n").toString();
            temp = (new StringBuilder()).append(temp).append(excludedSubtreesDNS.toString()).append("\n").toString();
        }
        if(!excludedSubtreesEmail.isEmpty())
        {
            temp = (new StringBuilder()).append(temp).append("Email:\n").toString();
            temp = (new StringBuilder()).append(temp).append(excludedSubtreesEmail.toString()).append("\n").toString();
        }
        if(!excludedSubtreesURI.isEmpty())
        {
            temp = (new StringBuilder()).append(temp).append("URI:\n").toString();
            temp = (new StringBuilder()).append(temp).append(excludedSubtreesURI.toString()).append("\n").toString();
        }
        if(!excludedSubtreesIP.isEmpty())
        {
            temp = (new StringBuilder()).append(temp).append("IP:\n").toString();
            temp = (new StringBuilder()).append(temp).append(stringifyIPCollection(excludedSubtreesIP)).append("\n").toString();
        }
        return temp;
    }

    private Set excludedSubtreesDN;
    private Set excludedSubtreesDNS;
    private Set excludedSubtreesEmail;
    private Set excludedSubtreesURI;
    private Set excludedSubtreesIP;
    private Set permittedSubtreesDN;
    private Set permittedSubtreesDNS;
    private Set permittedSubtreesEmail;
    private Set permittedSubtreesURI;
    private Set permittedSubtreesIP;
}
