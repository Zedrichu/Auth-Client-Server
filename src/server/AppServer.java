package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppServer {
    private static final int REGISTRY_PORT = 8035;
    private static PrinterServant printer;
    private static TokenManager tokenManager;
    private static Registry registry;

    private AppServer() throws RemoteException {
        super();
        System.out.println("Initializing the RMI objects...");
        printer = new PrinterServant();
        tokenManager = new TokenManager();
    }

    public static void main(String[] args) throws RemoteException {
        System.out.println("Creating RMI registry on port " + REGISTRY_PORT);
        registry = LocateRegistry.createRegistry(REGISTRY_PORT);

        System.out.println("Rebinding TokenProvider and Printer services to RMI route-names.");
        registry.rebind(tokenManager.routeName, tokenManager);
        registry.rebind(printer.routeName, printer);
    }
}
