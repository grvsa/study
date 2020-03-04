package ru.grvsa.spring1lesson6.launcher;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.EnumSet;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8180);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());
        webapp.addFilter(new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain")), "/*", EnumSet.allOf(DispatcherType.class));

        server.setHandler(webapp);
        server.start();
        server.join();
    }
}
