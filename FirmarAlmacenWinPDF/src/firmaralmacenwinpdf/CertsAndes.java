package firmaralmacenwinpdf;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertsAndes {

    public static String Raiz = "-----BEGIN CERTIFICATE-----\nMIIGKTCCBBGgAwIBAgIILROdTnKoohIwDQYJKoZIhvcNAQEFBQAwgaExIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMR8wHQYDVQQDExZST09UIENB\nIEFOREVTIFNDRCBTLkEuMSIwIAYDVQQLExlEaXZpc2lvbiBkZSBjZXJ0aWZpY2Fj\naW9uMRIwEAYDVQQKEwlBbmRlcyBTQ0QxFDASBgNVBAcTC0JvZ290YSBELkMuMQsw\nCQYDVQQGEwJDTzAeFw0xMDA3MDgxNjM2NDlaFw0zNTA3MDkxNjM2NDlaMIGhMSMw\nIQYJKoZIhvcNAQkBFhRpbmZvQGFuZGVzc2NkLmNvbS5jbzEfMB0GA1UEAxMWUk9P\nVCBDQSBBTkRFUyBTQ0QgUy5BLjEiMCAGA1UECxMZRGl2aXNpb24gZGUgY2VydGlm\naWNhY2lvbjESMBAGA1UEChMJQW5kZXMgU0NEMRQwEgYDVQQHEwtCb2dvdGEgRC5D\nLjELMAkGA1UEBhMCQ08wggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQCc\nsvKA1BIq5MzsIQtZsf8QfaI6uzllLZCdnw1jczyTTE+xlygz9+Bv5ynFiWnZThLF\nLMC3DSMVhAPBSYQ3qierOF/U7QE84NM0bHCKVK33MiA6ruu5804HsdVJuLqP6YCb\nS+nH1Ygnw0q2fX/H094wBWb2Jr2oVe+ydDDjy1RYjZHXiZVekwZTb6oY+f2rE25w\nnTNj1/3B4JfYPBbIDz6aRXPyeSBtI/RVKZBjD4NBXd4mCdXCE6/puOdvAbBWMhq7\n9wLCQIgtU21nne6/YaEHISah2S5KTC4P1nS+1nHvxMxdC1cszv7mheP4/nszfAgU\nLEeI6eL+lvBy+vjssT8dv+utofmj76QQdn2MkTb1paZaan4+3a+c1PsjTO7yZ86k\nKDQDxfnYaGF9b7wOgIDvacqJlREpmlvT2DN+4YAp2RLnuK1+ws6dnS+e1t73qbnZ\nQ7lntelAjFtKms3YIpXfs5sWUlPza1ozxEpmkSM4ZeuPKI6WF7YJuEo5s11Cu+Rw\nCtBOIVjYZWMUipxwfZcT5L3vZYWPSkboDlWZGZFX4mu2srzfv2ac4Ij5M8/hTD/n\n5WT2WzWcLId0xJuY9dZT/6XIrNZ6ZEFweEXxM7zHp3SrMfNSJZdG22d28gTmSyVg\nFPCVop+bdW7fMo6rmCdfveih5LkBRbxE6SPUnztVcwIDAQABo2MwYTAdBgNVHQ4E\nFgQU3RGSWqlsBAz+NKJYv+sd+98IsG8wDwYDVR0TAQH/BAUwAwEB/zAfBgNVHSME\nGDAWgBTdEZJaqWwEDP40oli/6x373wiwbzAOBgNVHQ8BAf8EBAMCAYYwDQYJKoZI\nhvcNAQEFBQADggIBAG1azqlmS7JKLaO25wBImp0p4AO+qeWIxz/N22EhKJ5CTkpz\nfWRyNZHB9mSXDFLnBZpaEN8zwfXP98ibZGf+BkGtyA5RU9yiUXysGAOxZcLcZsdX\n0GBb5ilnPijc/srjcvOH0MzTx14RFDv4WNSxl8j6gsIp2cFZsCeRL7vqPbJ7Fqu9\nPLgrD8+Pv5kWCTfTey/BtTUsn7fdEt9CpDfbk89fTaIoVuCSSp4j8PrXtNLPkyTg\nhk9YncYV/OOpgT1zxFIo0nGvn3D1PULKZllhed3i9eJcElRpeMMc3l1mBpp2ReIz\nYcL5ctntgu2239mI0cxLUDtMdTIjjtzSOqEEChjkuLO/w1tZTUrkm/2cz0/faNR5\nkvqtTI8zTAkP3v+V3oIMF/bndcniuFLfKXhWyhX3LtcKy1oLtAnX2aQaPb3sgTbs\nCzi7UnKvzV66RfOkAdBUFDa0kIrytqICL1Hr3yEMjQkAI4VCPeIiYXw8Zr0Pq+b+\nHWH4Q0eLVyNmGlZbb2CORagUIU/Ona3AOXTxAuYiwwuD+dm0m4da7cZ5a/WMNqE3\n5KgRdQAVdbp5jR32LjCBRK/HVWZLxQ687Q+jBcZwHcCkrdzOquix8rwDlBCUndgg\nXZn8q9LVHdWkQHMfD7KIVPIxF/1hV2UK3eVj4odnUcqC8d1TdRwq+dNxrplm\n-----END CERTIFICATE-----";
    public static String ClaseII = "-----BEGIN CERTIFICATE-----\nMIIGvzCCBKegAwIBAgIIbnH7jsUXCcswDQYJKoZIhvcNAQEFBQAwgaExIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMR8wHQYDVQQDExZST09UIENB\nIEFOREVTIFNDRCBTLkEuMSIwIAYDVQQLExlEaXZpc2lvbiBkZSBjZXJ0aWZpY2Fj\naW9uMRIwEAYDVQQKEwlBbmRlcyBTQ0QxFDASBgNVBAcTC0JvZ290YSBELkMuMQsw\nCQYDVQQGEwJDTzAeFw0xMTA4MTIxNDA1MzhaFw0yMTA4MDkxNDA1MzhaMIG0MSMw\nIQYJKoZIhvcNAQkBFhRpbmZvQGFuZGVzc2NkLmNvbS5jbzEjMCEGA1UEAxMaQ0Eg\nQU5ERVMgU0NEIFMuQS4gQ2xhc2UgSUkxMDAuBgNVBAsTJ0RpdmlzaW9uIGRlIGNl\ncnRpZmljYWNpb24gZW50aWRhZCBmaW5hbDETMBEGA1UEChMKQW5kZXMgU0NELjEU\nMBIGA1UEBxMLQm9nb3RhIEQuQy4xCzAJBgNVBAYTAkNPMIICIjANBgkqhkiG9w0B\nAQEFAAOCAg8AMIICCgKCAgEA1TZmBmaPHG4A2lNdXXBElS5SvaxCgIdQkrcFr63J\nZTFZE4l/8oXQE8Uw7v0M4/wklj9efwegO4j2bza3Uyl4Lkn7Meq6SOt542qy5JWB\nwqWHAvn7QUfBbTR/OPAC9l2sWHksvyfy0gr8Dez0BUei9DhEvIJRS4e0xRrQMDkE\n6lmfl9WbVdLI6+pRMn8+t53UKIQwaJcsitKIXIDEk3iE+m+TeVLM/5keuA6ZLU85\no0qoRuxOVnuxF5waI8vDaVFEfM5I93+by/zZI9gc7Wmc0aTClq5UwqUCubnyzW5r\nV85QxLvdIFKYvcCLMr1PkIVrVDlzEKqBoI1lUar/ByobPeFCh18AZ63gQSD0Flsf\ngx4hHiatfFG0Dv6DpDGrHNKEorO6BexR6533am2rPXUf9OcqepAtVl5OBVuHGDBb\nvTaFMnhQTyyiOFUVrKMIbFVqfEXgQ8le5JcgFkWr+2vb9JtO5ZrglkPxaRaIQTza\nN74J8/kGzZUYt5oFebESOfrAlkxrDEW7k6xnRD7SwgZT/deumGZ5bJvdwjj4ZY5d\nDkQEnUiq5EhLEKTMVPD/Lyc0z+sGc4yHhxycNWr7GJszGdBsbNK3EMv17rvcHMYr\nRGfVlljJlJvBwSu0JAhgmm7XhJ5pO3z5FKVo/X5MW/vZPbcpg/j86fjYgNzAmBij\ntEECAwEAAaOB5TCB4jA3BggrBgEFBQcBAQQrMCkwJwYIKwYBBQUHMAGGG2h0dHA6\nLy9vY3NwLmFuZGVzc2NkLmNvbS5jbzAdBgNVHQ4EFgQUqEu09AuntlvUoCiFEJ0E\nEzPEp/cwDwYDVR0TAQH/BAUwAwEB/zAfBgNVHSMEGDAWgBTdEZJaqWwEDP40oli/\n6x373wiwbzBGBgNVHR8EPzA9MDugOaA3hjVodHRwOi8vd3d3LmFuZGVzc2NkLmNv\nbS5jby9pbmNsdWRlcy9nZXRDZXJ0LnBocD9jcmw9MjAOBgNVHQ8BAf8EBAMCAYYw\nDQYJKoZIhvcNAQEFBQADggIBAAApGjPCXHUnYxyCku4dcPStFf/o1hFO8Fn4IXBD\nm7EPiKENzghkDBKYPi/HwagcDmviztKjCt4C2tkUJoWHc/Gi26IiRD+Wmoj0CesJ\npowYjefP/XKNTK+ojgNsx7Q+oHfHQmFx5XM/teFUViu5tfgPPcKoiDFBpuTe8zJ7\nmRzMRy6r3Qgf8TQRQSrDYmL9t/6mfWqjn6Cs7GLyuau8OUt+9GaYSZElHN2VhS+R\n87ETL22QN1NocDKmXaSCb0nODYJVSZBi3nvaiy5FzqywOYMxvKFg9vIeZpKbypxx\n2wm6tI6mQETgLrklPBg5DZoZzTlO5+DXFTDJVu7TKcWHYTxNv0Xtv8n3/PZkAVUI\nAOo4FbbTmmrYJP2ZTEy7lc5Nt/LeSP93WwIbHJJCNLbhbRUw1AbmVPrmJaXjJlyH\n9unT9ggsPndQr0RV+x/Ub8M1SoKO6s3Po41wKCE8tY5+ulTHBGErH47zIfv4mxSa\nzXUFv70+1pM2B5mEIGv4oKAl987gP+vRZBpFzfD3N3upnWZQrFy7RkFiGYgntQd/\n+XaEezKy2JodPu2JpHd8ryaEW4cjnbAP9ADOUqUbIN8JL3tGiigTuxY0ALHHR3NW\ns+/zFBCorgp756/zpXVebKWoFQLNVr4WxZT64+lROopp3tEb2v9jMG5WppBWd7a/\ntfYK\n-----END CERTIFICATE-----";
    public static String ClaseIII = "-----BEGIN CERTIFICATE-----\nMIIGSDCCBDCgAwIBAgIIeGLtKeZu/FowDQYJKoZIhvcNAQEFBQAwgaExIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMR8wHQYDVQQDExZST09UIENB\nIEFOREVTIFNDRCBTLkEuMSIwIAYDVQQLExlEaXZpc2lvbiBkZSBjZXJ0aWZpY2Fj\naW9uMRIwEAYDVQQKEwlBbmRlcyBTQ0QxFDASBgNVBAcTC0JvZ290YSBELkMuMQsw\nCQYDVQQGEwJDTzAeFw0xNTExMTgxNzM0MzBaFw0yNTExMTUxNzM0MzBaMIHAMSMw\nIQYJKoZIhvcNAQkBFhRpbmZvQGFuZGVzc2NkLmNvbS5jbzEkMCIGA1UEAxMbQ0Eg\nQU5ERVMgU0NEIFMuQS4gQ2xhc2UgSUlJMTwwOgYDVQQLEzNEaXZpc2lvbiBkZSBj\nZXJ0aWZpY2FjaW9uIGVudGlkYWQgZmluYWwgc3Vib3JkaW5hZGExEjAQBgNVBAoT\nCUFuZGVzIFNDRDEUMBIGA1UEBxMLQm9nb3RhIEQuQy4xCzAJBgNVBAYTAkNPMIIC\nIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAuY+GBzoE0dnUnRaKoaeNxc9N\nOImMqJo1k+S4YcXk5H71xOh4JeMNTetnBXi8LWBm8gP9BOvF+mu13zaV51y/T0iJ\nFPmThKu9MCoKR1lhfeMH7ygWFI4fRMGwx7E6PgMpvnmFiLJal80k04uTsqguQgLA\n2vWJF52RElUegKZbsxXahk+gAV+/Pwi4jmeX1SIIYahDfjRnBX0Ezs8fl2eDcWcE\nQT0XPD1YiTzrS9O6J4Cza7gvXMuZcgXCFkid115hIBQU5WlwYhY7H/Ny6Eff9NKE\n1ftxD6fmzgFAAEZVtW187YAEUDafEvhDpEH02O1/9EbiScQ9WE1on98xclIgKK81\n8mRMnlloYIXy002Tip2XujmcRV/sETGGOGrKnE6A9fXQRkHMM+3g0auvSfJGESru\nf4F6OBIaGlyFhNuw0CdpzV6nEy6crLuSJ0slnLjNIxQS4ZqCaAmOLYFbw3Ih3ZXe\nCLxUik07xkL5lzKLAH86FPQ18rko39eEOgGMg52xdpkkdjCfI8XaS7GIWs8txPn7\nzOZRQGzMjM1NyE7d0PPSTrbVDJZqOU3qyI7mBk9RIc8WreSFhEJ3A+iHAt8cam91\nh93CuG4AzlDMQqXENInghegeVXNjcJjwl6nt6KJYUWyUlgBrb5laEQRM/B3zlvBC\ne0+9vSg+HPNe1iBfO/0CAwEAAaNjMGEwHQYDVR0OBBYEFI1V5k8i+QX6k3ouDXpK\nZ8feN9zjMA8GA1UdEwEB/wQFMAMBAf8wHwYDVR0jBBgwFoAU3RGSWqlsBAz+NKJY\nv+sd+98IsG8wDgYDVR0PAQH/BAQDAgGGMA0GCSqGSIb3DQEBBQUAA4ICAQA22QFN\nuHj7Vx7Gjp9tSlZ1nzpPH16Oio2+WKtoHCgUBhpynk5Nb13zWw1hxiqqrg8gj586\nGAhIfCz3+a556dXQ74iXhQ1Ek15ZZ5zr4aL48SWeyUFZ0v2xpxayTWf7ox5rJk+N\nCYpILjSk9HdgGyAhntMtlDRixXyljOy7maYMNtiHHtDrzMKvlRHaRIOJ3KGEXeE/\nH4YrULXCFQNcCzLJ+2QCA39eZZUNN71kLWAeX//E0/qFa1j5haIW9jPmU3wtWUpS\nn1iaqtvJapqFi398NjKQ0HoDo5AHdgVgEqQOqSOmQsV7ScRFYJpoY10iI+eOvObf\nFduDi84i7SrOWUyp4u72xaMewdji35YF+IVcP1n9OvHkdaKu2yWKLhpVj5hPiVpZ\nOEEy11K6rD72bKVVw+JSAgldn9QbwaxviSc+rGctCji3axvU7UDW7URGQt/RvFRK\n02P7QBz03FBSmW1OgkftYfqWYR9H4XGGtT/Ah3g2rez6lv8SiP+DNNHsnEKZmuRl\nBFklyNgpX1/IKxcUbAMVUo6JP6SNPhpC8ybnBlQPpFYYGHwhS0LHnP0vtkFqrhmH\nfo08h3mdK26BcV3E9sR2hX50wg7H9Dme92R5w2S6ShXNeTS6a8k5IwJxuMTSFrY6\nFSX57ny03g+XJxvV0J6AL7+oN+wqeqMH0db3bw==\n-----END CERTIFICATE-----";
    public static String ClaseIIIFNA = "-----BEGIN CERTIFICATE-----\nMIIGcDCCBFigAwIBAgIIGA0uioJYxWwwDQYJKoZIhvcNAQELBQAwgcAxIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMSQwIgYDVQQDExtDQSBBTkRF\nUyBTQ0QgUy5BLiBDbGFzZSBJSUkxPDA6BgNVBAsTM0RpdmlzaW9uIGRlIGNlcnRp\nZmljYWNpb24gZW50aWRhZCBmaW5hbCBzdWJvcmRpbmFkYTESMBAGA1UEChMJQW5k\nZXMgU0NEMRQwEgYDVQQHEwtCb2dvdGEgRC5DLjELMAkGA1UEBhMCQ08wHhcNMTUx\nMTE4MTczOTAwWhcNMjAxMTE2MTczOTAwWjCByTEjMCEGCSqGSIb3DQEJARYUaW5m\nb0BhbmRlc3NjZC5jb20uY28xKDAmBgNVBAMTH0NBIEFOREVTIFNDRCBTLkEuIENs\nYXNlIElJSSBGTkExQTA/BgNVBAsTOERpdmlzaW9uIGRlIGNlcnRpZmljYWNpb24g\ncGFyYSBGb25kbyBOYWNpb25hbCBkZWwgQWhvcnJvMRIwEAYDVQQKEwlBbmRlcyBT\nQ0QxFDASBgNVBAcTC0JvZ290YSBELkMuMQswCQYDVQQGEwJDTzCCAiIwDQYJKoZI\nhvcNAQEBBQADggIPADCCAgoCggIBANrm9nqpvLWUsITr2TR5X8ZQJvharXi5ET6x\nQq4ch7ZjWTQM0lA2DXkzdVCHKJ4grKToWfIWqD7m2fx0CnxmVcz36YKUm9ELhft2\nntNnMGr3lvZeKAuxgst1kMrrrre9+pGbAEKjBpzARbBx0lWoT7Da60U18Sdr7D0H\nX9OhJmZ0l/SYxSfyaZ1i4aEvrcTQ40Z7tBCvHbN00QqO5gxqjtyeJP0YcB3ObQbr\n5U+OoQH5bg70sWKI6MN6fiOkMkylrLBX56Xj1SJk13BuUYvVhPS/cuw8hiTRbm3e\nU3s0t2D0Pd8mT6S3Fv2luhW7AZ/uNun+04KEqgPHzRSjahGsTx1Kez6jtCtdu5ep\nSb1zIH9k9m59HCyZ75TrpQSNmDBxxOImipu+rTLOAaOHf1wso/IjnY7gxsilt3MR\nOncvv/WzUcCf55sFBpn/ww6fOBZ3zLu8TEzYV5jeiuRlHZTqB5k2FgUplF2ox5T+\nwcdkzD96vfbvQKezGRZSh4xLGr3eQ8oQ+Fo2FiVGtI7Hv0iy24qkWvlPfUqf9vz+\nNQDTH7gmWX8LCVCCf2zdfPQhBYOo/nMYhPGSU4WhhzCFFWZtjYGhQBieru3diHqO\npuEWSjTpxi5k+naXmyhznXMcn73WF52AOyYblA1QM9EtTo/pzz8MqJxXpUTMGky7\nkJOJNyvdAgMBAAGjYzBhMB0GA1UdDgQWBBSXuqMfcBENPkbj2Jd22urVyFblJDAP\nBgNVHRMBAf8EBTADAQH/MB8GA1UdIwQYMBaAFI1V5k8i+QX6k3ouDXpKZ8feN9zj\nMA4GA1UdDwEB/wQEAwIBhjANBgkqhkiG9w0BAQsFAAOCAgEAOf9FKLKY4L2B5rPw\n/1eAATKSw+47lz3RR4nHdxZpqSm/N5tZQU5Jh9uDFWBkiz+9GQJIRmtThaZwjwQZ\nyNgIdHJvOXZPYjIscWGmAOquKWWHpFmNmrFeBPzfSVt9MvFki1ff43cdMndVN7Zp\nNorvcve6Y97ESgrZSfXwiXeoh2EWkaTvjfwi2PXtf+LYodWUsQTzgtMgDRkeW0od\nqAggY63hTq86+9jQE3tTja1ptevbKqZD30L8NIreaew/LRW9wlxcVreiNC9M47CS\nan6wmZx5SMDlVCLAi9RelUpW/ZnhPNxc69jBMWrQJmrx8BcP6RYg5TmTPJGhVPIA\nSuGVo6DfxTsm1flLzIxELEiiYCJ94T5tgCrlHXPExz4xe35GtlHeWXZoV2MeuF3O\n2sFNRUspi3YzTpSl827LOiPk1A/exhCDsMEqKbIyHVvictsmm5cCBdUWbwTdrdx4\n4WrrWkxC8+jr7fTzltfRDoWYmFfHK7xPHIM7njsO8YdGWlABrxjrLIKmzvaDgHPv\nz7DZE1qVh9D6yE8IdH7vpnFMCPy/srq57SFu/oSSM9QkZmTfcf2oMCJQ6FkuA+5f\noQHnrOIQUmmNzzDNofRyGc59kny15AnTl8/AakgnpkQzT19tBHzv+HDoXIr5n/vN\nwWiNyjhLOKJrZuHe4Ib7VN/QB1s=\n-----END CERTIFICATE-----";
    public static String Test = "-----BEGIN CERTIFICATE-----\nMIIGijCCBHKgAwIBAgIIPtnlxCqeXwowDQYJKoZIhvcNAQELBQAwgcAxIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMSQwIgYDVQQDExtDQSBBTkRF\nUyBTQ0QgUy5BLiBDbGFzZSBJSUkxPDA6BgNVBAsTM0RpdmlzaW9uIGRlIGNlcnRp\nZmljYWNpb24gZW50aWRhZCBmaW5hbCBzdWJvcmRpbmFkYTESMBAGA1UEChMJQW5k\nZXMgU0NEMRQwEgYDVQQHEwtCb2dvdGEgRC5DLjELMAkGA1UEBhMCQ08wHhcNMTYw\nNjEwMjIxNTE2WhcNMjEwNjA5MjIxNTE2WjCBpzEjMCEGCSqGSIb3DQEJARYUaW5m\nb0BhbmRlc3NjZC5jb20uY28xHTAbBgNVBAMTFEF1dG9yaWRhZCBkZSBQcnVlYmFz\nMRwwGgYDVQQLExNEaXZpc2lvbiBkZSBQcnVlYmFzMSAwHgYDVQQKExdDZXJ0aWZp\nY2Fkb3Mgbm8gdmFsaWRvczEUMBIGA1UEBxMLQm9nb3RhIEQuQy4xCzAJBgNVBAYT\nAkNPMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgOesnSd8iiFSnlta\n5ZPv7YXHbGsRL0ffY5QWg/qYeI3S6QGDCsXwTO4RUEllQHkQbmLfAHotu1XsF8Gk\nMDkgki7lyTr7Fo/fQtdh6+Jbqvu29rZbDMm/W5vdTIJFOKkdrNIqyMkpKK4nqGG9\n25+Di2XmNgABsBYL3LLMh21XByFhUlDXmyc57eFf6/q9HknD+lIYkSVjfOq/NNOG\nMk/WKu/eEa4VN5pcgFYKkillV6GYF9+fnQJdj9M162tKlNEmWkg/0+ruv1Q1ULpO\nH0p3nQWtxA6kDqwPIgUhx13LnIqB3TzleuvpjcrqZLlVhg1OZtRpCvuT9XIogYYB\nOLs9fj0E2kZDknjXHZ9npyACBT8eEZzWTdt4JQWD8H9fpt2+R89BMYiuqUoiEhYM\nF+2/U6XLEHigr8MaVacCcjvSvXLlTYnHmb3hMjMtsQgsEvSHYRcYjwTfUEEfRCv+\nAYcpqUMTYxqJRzYMOPyBROnkjAViNN0/Z8WggidoOfdXO1QLu5iYX5jRHxc4s0BG\nfxHDQ5ozCId6suq6AuJdhBPHiNZAkxjA1mrU1tnILwKGMBzlJ89nC70EYBpkSLH6\n1VkTGOl0PTFfcTCF/YQoJtwhxwbe9qBilGwPr3r680lW1ZNAetMHxKCgv1dAJX72\nGRPYuOfPcmODass6TyoA8Ao13nMCAwEAAaOBnjCBmzAdBgNVHQ4EFgQUMZsO1FVt\n7KnR7brYaojVGvRyX3AwDwYDVR0TAQH/BAUwAwEB/zAfBgNVHSMEGDAWgBSNVeZP\nIvkF+pN6Lg16SmfH3jfc4zA4BgNVHR8EMTAvMC2gK6AphidodHRwOi8vY3JsLmFu\nZGVzc2NkLmNvbS5jby9DbGFzZUlJSS5jcmwwDgYDVR0PAQH/BAQDAgGGMA0GCSqG\nSIb3DQEBCwUAA4ICAQBwW6kvHz2HNi3wKwu4J6mlSEeQ6nBa4gcbnCKBep6HGN4l\nvY06M7fMxymC95O+Azu0KqGd/yjQMGTDsK2KY2seB3ED2mgytMURB+BF4u+tl7kj\nGRuswAuJpHiffxmgYyCpfVaJxWzQ/TMJIoOTQwJ6nY+BuMwl8w593wTOA/t409MV\ngFOksHsw9j2e3+DdemdkISTs8eUNlqd97lLqQe1HSLhiUavWCayItqqQf1X/0gVD\nRba8wpwHnlwaMPe2zpVzqUngUkRm/W46yu6kfleg6dq5RxZV0eBMo8ptkMqvEm84\nU8VTpqIXhamI+7c4urwa4qti6WIICXhEdEfuDBN+CtQW08R4WbhAiLXxrcvgVBl1\nYCc74ReT3WVeSak7IPXW/os2e+Hyat1fArK7h5oXggFkM6ElZLmxhNm8pV0dMxak\n4aOc1IXv2ZRtbqOzOmdvJNohJVwttbtylgfD9sbQyKVfPfkXcHERRDxcaJSvB7+0\nlSaM4L8uJ77iH1eGC/4PprLjbFilxFg4I1k2Z3yRxvCHn6/Q0iTPmCE0M6Ese7Yx\noV2EpX5pT75rEAxp+yEsHVgt1kgfB7+nN+wAo39cxdH/B+mC9rTdf6c1wHY99/Rp\nUf2wuww6ZaSi0D7ZFu1Y/PHfiabnTRGMn0kt5Z+3GFJ+pI4KpCRB0VZpymeZ9A==\n-----END CERTIFICATE-----";
    public static String RaizPruebas = "-----BEGIN CERTIFICATE-----\n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\nWFJ1f4jVXZSHyXI=\n-----END CERTIFICATE-----";
    public static String ClaseIIPruebas = "-----BEGIN CERTIFICATE-----\nMIIHkjCCBXqgAwIBAgIIWTaF0trTxdswDQYJKoZIhvcNAQELBQAwgagxIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMSYwJAYDVQQDEx1ST09UIENB\nIEFOREVTIFNDRCBTLkEgUFJVRUJBUzEiMCAGA1UECxMZRGl2aXNpb24gZGUgY2Vy\ndGlmaWNhY2lvbjESMBAGA1UEChMJQW5kZXMgU0NEMRQwEgYDVQQHEwtCb2dvdGEg\nRC5DLjELMAkGA1UEBhMCQ08wHhcNMTExMjI4MTczODIxWhcNMjExMjI1MTczODIx\nWjCBvDEjMCEGCSqGSIb3DQEJARYUaW5mb0BhbmRlc3NjZC5jb20uY28xKzApBgNV\nBAMTIkNBIEFOREVTIFNDRCBTLkEuIENsYXNlIElJIFBydWViYXMxMTAvBgNVBAsT\nKERpdmlzaW9uIGRlIGNlcnRpZmljYWNpb24gZW50aWRhZCBmaW5hbC4xEjAQBgNV\nBAoTCUFuZGVzIFNDRDEUMBIGA1UEBxMLQm9nb3RhIEQuQy4xCzAJBgNVBAYTAkNP\nMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAmJk/Ix3y4CmxPdv/gYT+\nJRjfN0T3IqPx8wTN9gSq9QgmODNyqW4GUL/35ZAPF4BUhqaDUID+bu6UsbZ9CyFZ\nLrH4YoyRMiXvRtb3412DckGgVewVRRGhlieGSENOo6Lf1MqJ3bleWF/KKP5O7fSj\nnpiMJYPPjWhRc68IfQFw92n0JLesDJaYH7gB7uycRHmRv4sFnK2swpsn6L4mEslE\nAaMGXmMTFR/1zQpl+tkAIicIvWeShKttOvXScaD3LlvxS8fP1XGGwL5Od6JKrlLD\nOIwUkzPfi5hHaqi8gW7fAMb33EKgqQDRXFLmOFriro/+KIm0lvrBdx2fCgBFiQV1\nquVSwdSo2e5Ze0TmkzxST5EH54dLt/kv1A73YWT9ZDzn7kKmb2v1yYJ40wvWeL9O\nFT4pp5JtCdkzhm0RTpYoKKnYZHDM775jxeW1iVtaN95LV+FbvZyT41Eb199BDBPp\ndFSzTtWX+ybzjA3Bq/2RXjQRSP1v05qLROaNC3Wdx2dQHpd3H72u+c869NX3IjEt\nSMnzYOI1/ojX0JbaJLhJ9iPOj264onqJ8yMOVbRkSXFg06g94uDdjbjCqR7KKJHg\nS5XKF4xbU4glazFWrG3QRCL7V8IanfGjIXncFayvNOGLv9GsQ9LrYgGtJSsOvt0h\nD/E1WU6RzlPd7wCVMa2Zng8CAwEAAaOCAagwggGkMD0GCCsGAQUFBwEBBDEwLzAt\nBggrBgEFBQcwAYYhaHR0cDovL29jc3BhbmRlc3NjZC5hdmFuY2Uub3JnLmNvMB0G\nA1UdDgQWBBSSNhpmtOrNHwqLbDyDQMgda6QtvDAPBgNVHRMBAf8EBTADAQH/MB8G\nA1UdIwQYMBaAFFlOSySIVCJ/Hx4PpCUhi4l+idlXMIIBAAYDVR0fBIH4MIH1MIHy\noD+gPYY7aHR0cDovL3d3d2FuZGVzc2NkLmF2YW5jZS5vcmcuY28vaW5jbHVkZXMv\nZ2V0Q2VydC5waHA/Y3JsPTKiga6kgaswgagxIzAhBgkqhkiG9w0BCQEWFGluZm9A\nYW5kZXNzY2QuY29tLmNvMSYwJAYDVQQDDB1ST09UIENBIEFOREVTIFNDRCBTLkEg\nUFJVRUJBUzEiMCAGA1UECwwZRGl2aXNpb24gZGUgY2VydGlmaWNhY2lvbjESMBAG\nA1UECgwJQW5kZXMgU0NEMRQwEgYDVQQHDAtCb2dvdGEgRC5DLjELMAkGA1UEBhMC\nQ08wDgYDVR0PAQH/BAQDAgGGMA0GCSqGSIb3DQEBCwUAA4ICAQANhKnVjXu7ais8\ngCAE3dKby84mHsacLsI+an9b/qRxY5/yXzRyNY/f/5rBe00KmgEfb7yOR291rOHD\n+Z18K5ZC0NJYIsyK2Okaff4KZWMoFUFVytQ4twrvoKHaQKk9qjLuYBrCjLqxKAzB\nEm08SdNLUX1smw4XIynafuE3o6hDPM4ae7Eminb5cjJ5D0ezMmjPg15WkkncistM\nFWQPyf5qIWbfY1LudlLOYrcGwLjwD+cePoWW5qzpuILYXLmY0fuMGFSc+MGCc/tj\ni+npxzLtmOcfH37lxjJRYR6Hib/EJ+MvYeO0yQY/HBWub7E67dNkyflGQaGADM+0\nBoTmXO4tEPbagca6SsKU0f8dSbrR5MsFivlD27KwbYKDZN+jfpJogbKNQ5Xi2Huv\nGWFpWVLfophVmBbkhkS93ccrAxDTvM0CrOwEcif7WeixUbXlL47zB1Fe+jh0arTv\n4xTnnt/c0VYrhLYz7BLBkxWkphfY6NAvEW2pJIYwF8klKZmexrCjm9xOY5qBs+mw\n5enI3l1sbWcKWCTeTCMDC11fNId0xnSpulECA7jAv339tehhgf41r/fZVTJ59E6S\nihx/5gn5/ZRc0mXPSgEoAdCgCCyTz3tZ/0u33MG+amhOcFJBVdBl6iRInPXqBUpu\nRKX0QgzQDC1lQUMeBULNYM8EwIypeg==\n-----END CERTIFICATE-----";
    public static String ClaseIIIPruebas = "-----BEGIN CERTIFICATE-----\nMIIFVjCCAz6gAwIBAgIIcnknCj12V48wDQYJKoZIhvcNAQEFBQAwgagxIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMSYwJAYDVQQDEx1ST09UIENB\nIEFOREVTIFNDRCBTLkEgUFJVRUJBUzEiMCAGA1UECxMZRGl2aXNpb24gZGUgY2Vy\ndGlmaWNhY2lvbjESMBAGA1UEChMJQW5kZXMgU0NEMRQwEgYDVQQHEwtCb2dvdGEg\nRC5DLjELMAkGA1UEBhMCQ08wHhcNMTUxMTI0MjE1OTIyWhcNMjUxMTIxMjE1OTIy\nWjCBxzEjMCEGCSqGSIb3DQEJARYUaW5mb0BhbmRlc3NjZC5jb20uY28xLDAqBgNV\nBAMTI0NBIEFOREVTIFNDRCBTLkEuIENsYXNlIElJSSBQcnVlYmFzMTwwOgYDVQQL\nEzNEaXZpc2lvbiBkZSBjZXJ0aWZpY2FjaW9uIGVudGlkYWQgZmluYWwgc3Vib3Jk\naW5hZGExEjAQBgNVBAoTCUFuZGVzIFNDRDETMBEGA1UEBxMKQm9nb3RhIEQuQzEL\nMAkGA1UEBhMCQ08wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCNF43c\ny8hM+uuN3X5O0+s3DV712tVaF8btbsdLSTLEy+OSKO/D/LaI+p6DpTBwT9tqYSG4\nfHX4IwHoQbKP8V4UsymLN3nHYjOshLpvArMRG1/wPl1TyWOlKQnYlSwZ1f5xrTtQ\nB0us1GNQtIIub+MNZa5lzHRA8bFCCMXyvTgWScdxZN64Y3stbVV4vKUUGPTf188w\nLc6ifAR4nM6f3HN4Gq56BPOVxXyOAttOmq7j8Ab9LunSei+2pLqB4mr7lvdkz/NO\nkDAa7j7u6qCdv3j7YcuqAdMCyzvn/qeHEm3YXE5jrILY9OjAr5EaF9maKODVNaON\ntmmUsbQwqtKaGbXxAgMBAAGjYzBhMB0GA1UdDgQWBBSxa/p/qAnJSWMDZ079R62B\nnnixFzAPBgNVHRMBAf8EBTADAQH/MB8GA1UdIwQYMBaAFFlOSySIVCJ/Hx4PpCUh\ni4l+idlXMA4GA1UdDwEB/wQEAwIBhjANBgkqhkiG9w0BAQUFAAOCAgEAVKeJaHoE\nc2wickYKVmdZ/2SbPipN2e2b+1+Rr7RCleQzLwcgx63WcHThwZTueJxPOR5YgWj8\nLlvZON2AIyh45ePggCiZypqc2rra7aVOBxJ+Hnmij+kLIbmKhgLYDn5Zu5/Qn/0c\nKyGm15jUKR5pRgsu8K+/Ya5cDADXN3LaDdKGSJ4YXOeLzB/dUjy8tEY2cbEpVXNx\nXGm6WE23w7uZQwnRqokVffP+4OkhOXeOG1N19FuwBc8q92sRdMmZHT9eCzwn9chT\ni3yLx/ZfvgMpOjWsDHzlpK2zV0fFWBjxaInJGTCRzdoFkQFpXKAxhj2Ck4lJwpOG\nc57fW8oP20nT5r9+atIdxHT5defrXfbNQJtb8bMvznfjd1d575ISkb717xvKeMk+\nlUJ37e3lCWiqCLl5CJA8z/qYfVcsWqUG3wt5V4E+vKp4ApejGmgA+ukUyvgTXcgS\nEwPZObjKTW2gm+Mvv8hOX9Gg4mc0DPwULmmSc6EbKR8XXb18W9YjQE58FReyRlCy\ntKRp0HPXhncLx/gWWPP9IqKgFfjKqPC/JpKzGh5nl2GRSAIUssZnHyRJPr+3zyji\nTCCCpmD/uFSbxsMmuqDZ9aSwLWEM5ArKJZPNL0dY69iTr4bGPyBG2Zz2Dv1qK1EI\n4MjlP5zrcbUFW5QpZ2Epxdio//CuQqcdKhU=\n-----END CERTIFICATE-----";
    public static String ClaseIIIFNAPruebas = "-----BEGIN CERTIFICATE-----\nMIIEfjCCA2agAwIBAgIIM30v6EQeUFwwDQYJKoZIhvcNAQELBQAwgccxIzAhBgkq\nhkiG9w0BCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMSwwKgYDVQQDEyNDQSBBTkRF\nUyBTQ0QgUy5BLiBDbGFzZSBJSUkgUHJ1ZWJhczE8MDoGA1UECxMzRGl2aXNpb24g\nZGUgY2VydGlmaWNhY2lvbiBlbnRpZGFkIGZpbmFsIHN1Ym9yZGluYWRhMRIwEAYD\nVQQKEwlBbmRlcyBTQ0QxEzARBgNVBAcTCkJvZ290YSBELkMxCzAJBgNVBAYTAkNP\nMB4XDTE1MTEyNDIyMDIzMloXDTIwMTEyMjIyMDIzMlowgdAxIzAhBgkqhkiG9w0B\nCQEWFGluZm9AYW5kZXNzY2QuY29tLmNvMTAwLgYDVQQDEydDQSBBTkRFUyBTQ0Qg\nUy5BLiBDbGFzZSBJSUkgRk5BIFBydWViYXMxQTA/BgNVBAsTOERpdmlzaW9uIGRl\nIGNlcnRpZmljYWNpb24gcGFyYSBGb25kbyBOYWNpb25hbCBkZWwgQWhvcnJvMRIw\nEAYDVQQKEwlBbmRlcyBTQ0QxEzARBgNVBAcTCkJvZ290YSBELkMxCzAJBgNVBAYT\nAkNPMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkfUCsCSxTCcC1qz0\nuu0k293y360Ylf9AEVFBizuAIE5lduJs3nY/Vi3Zk19AQQU9JbhjhNLGti2PYtg+\nxXeFs0vFwglHsJUSSTrOEolP/9UDPsrCAq5nAk3RnhknPrlsZXeCrrqT6Gs6/5mW\nR8TE7lA+Wbx02PCZ9a/ktRN9F/WsObzIDd2nWPZFpnGbvYfuNCAiNMXOvddr9i0Y\nvSHepBsUqWG3QWSvEO2joepb36t3rmtg5ApEaJ4f7rAmTFaf/FjZ5omGqUJNtTpR\nKO7HQcvgMeuO9lEVDTlqNJnivrCpnH1xlR8EW/kJsVwMQ0HvRT1qb1Wa28uUVBWb\noE35HwIDAQABo2MwYTAdBgNVHQ4EFgQUEPO9zdQN2HGBHJQ+mSqrOVPMQzcwDwYD\nVR0TAQH/BAUwAwEB/zAfBgNVHSMEGDAWgBSxa/p/qAnJSWMDZ079R62BnnixFzAO\nBgNVHQ8BAf8EBAMCAYYwDQYJKoZIhvcNAQELBQADggEBAE/c3us86QSxDqiWRqMa\nN8NLcoABpsDKg09h2AaQxsLLnxB7mcgktELDiYhfNK2ku19MqyvtB+DMAwKVnVrY\nx/T4e/OXxcLeLD29hps6VnR4/ZmpD+0cUnYFAikYsOQ563cNRHhsnHKrhvZxPBkD\nR9IYPElePt5H48xkumSyHL13EXYYAwNojxfWIsKx90NktDU1qm74RndOFEUEIX6n\nueDyWL2smM2qFWkh1J9FYjEFBYMkSm5+dvkdzD+w5JXP4C/oGAHFoPhqOKJucvhr\nLtZ+tZ8KARJ4QljRIUGIRWMPJoTlGRfstnuAc5hk0MmOr31TnCLzHpmQl7MigQ0s\nwlU=\n-----END CERTIFICATE-----";

    public static String getRaiz() {
        return Raiz;
    }

    public static String getClaseII() {
        return ClaseII;
    }

    public static String getClaseIII() {
        return ClaseIII;
    }

    public static String getClaseIIIFNA() {
        return ClaseIIIFNA;
    }

    public static String getTest() {
        return Test;
    }

    public static String getRaizPruebas() {
        return RaizPruebas;
    }

    public static String getClaseIIPruebas() {
        return ClaseIIPruebas;
    }

    public static String getClaseIIIPruebas() {
        return ClaseIIIPruebas;
    }

    public static String getClaseIIIFNAPruebas() {
        return ClaseIIIFNAPruebas;
    }

    public static X509Certificate[] chainII()
            throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] chain = new X509Certificate[3];
        ByteArrayInputStream pais = new ByteArrayInputStream(getClaseII().getBytes());
        Certificate cert = cf.generateCertificate(pais);
        chain[1] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getRaiz().getBytes());
        cert = cf.generateCertificate(pais);
        chain[2] = ((X509Certificate) cert);
        return chain;
    }

    public static X509Certificate[] chainTest()
            throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] chain = new X509Certificate[4];
        ByteArrayInputStream pais = new ByteArrayInputStream(getTest().getBytes());
        Certificate cert = cf.generateCertificate(pais);
        chain[1] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getClaseIII().getBytes());
        cert = cf.generateCertificate(pais);
        chain[2] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getRaiz().getBytes());
        cert = cf.generateCertificate(pais);
        chain[3] = ((X509Certificate) cert);
        return chain;
    }

    public static X509Certificate[] chainIIIFNA()
            throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] chain = new X509Certificate[4];
        ByteArrayInputStream pais = new ByteArrayInputStream(getClaseIIIFNA().getBytes());
        Certificate cert = cf.generateCertificate(pais);
        chain[1] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getClaseIII().getBytes());
        cert = cf.generateCertificate(pais);
        chain[2] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getRaiz().getBytes());
        cert = cf.generateCertificate(pais);
        chain[3] = ((X509Certificate) cert);
        return chain;
    }

    public static X509Certificate[] chainIIIFNAPruebas()
            throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] chain = new X509Certificate[4];
        ByteArrayInputStream pais = new ByteArrayInputStream(getClaseIIIFNAPruebas().getBytes());
        Certificate cert = cf.generateCertificate(pais);
        chain[1] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getClaseIIIPruebas().getBytes());
        cert = cf.generateCertificate(pais);
        chain[2] = ((X509Certificate) cert);
        pais = new ByteArrayInputStream(getRaizPruebas().getBytes());
        cert = cf.generateCertificate(pais);
        chain[3] = ((X509Certificate) cert);
        return chain;
    }

    public static X509Certificate[] chainIIPruebas()
            throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] chain = new X509Certificate[2];
        ByteArrayInputStream pais = new ByteArrayInputStream(getClaseIIPruebas().getBytes());
        Certificate cert = cf.generateCertificate(pais);
        chain[1] = ((X509Certificate) cert);

        return chain;
    }

    public static String sha1(String input)
            throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xFF) + 256, 16).substring(1));
        }
        return sb.toString();
    }
}
