package src;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Calculator{
	
	public Server() {}

	public float add(float a, float b) throws RemoteException {
		return a+b;
	}
	public float sub(float a, float b) throws RemoteException {
		return a-b;
	}
	public float mul(float a, float b) throws RemoteException {
		return a*b;
	}
	public float div(float a, float b) throws RemoteException {
		if(b != 0)
			return a/b;
		else
			return 0;
	}
	public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            //Calculator stub = new CalculatorImpl();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}