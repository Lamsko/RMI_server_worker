import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerImpl extends UnicastRemoteObject implements Worker {

	public WorkerImpl() throws RemoteException {
		super();
	}

	@Override
	public Object compute(Task task, Object params) throws RemoteException {
		return task.compute(params);
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Podaj uchwyt RMI");
			return;
		}
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		try {
			WorkerImpl instance = new WorkerImpl();
			Naming.rebind(args[0], instance);
			System.out.println("Worker gotowy.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
