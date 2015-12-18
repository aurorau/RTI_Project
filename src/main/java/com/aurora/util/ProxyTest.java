package com.aurora.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

public class ProxyTest {
    public static void main(String... args)throws IOException {

    }
    
    public static void inetProxy() throws IOException{
        InetSocketAddress proxyAddress = new InetSocketAddress("myproxyaddress", 1234);
        Proxy.Type proxyType = detectProxyType(proxyAddress);
        System.out.println(proxyAddress + " is a " + proxyType + " proxy.");
    }
    
    public static Proxy.Type detectProxyType(InetSocketAddress proxyAddress) throws IOException {
        URL url = new URL("http://www.google.com");
        List<Proxy.Type> proxyTypesToTry = Arrays.asList(Proxy.Type.SOCKS, Proxy.Type.HTTP);

        for (Proxy.Type proxyType : proxyTypesToTry)
        {
            Proxy proxy = new Proxy(proxyType, proxyAddress);

            //Try with SOCKS
            URLConnection connection = null;
            try
            {
                connection = url.openConnection(proxy);

                //Can modify timeouts if default timeout is taking too long
                //connection.setConnectTimeout(1000);
                //connection.setReadTimeout(1000);

                connection.getContent();

                //If we get here we made a successful connection
                return(proxyType);
            }
            catch (SocketException e) //or possibly more generic IOException?
            {
                //Proxy connection failed
            }
        }

        //No proxies worked if we get here
        return(null);
    }
}
