import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class FarmerImpl extends UnicastRemoteObject implements Farmer{

	private Worker[] workers;

	public FarmerImpl(String[] args)
		throws java.rmi.RemoteException, Exception {
		super();
		int i;
		workers = new Worker[args.length - 1];
		for (i = 1; i < args.length; i++) {
			try {
				workers[i-1] = (Worker) java.rmi.Naming.lookup(args[i]);
			}
			catch (Exception e) {
				throw e;
			}
		}
	}

	@Override
	public Object compute(Task t, Object params, FarmerBO bo) throws RemoteException {
		WorkerThread[] threads = new WorkerThread[workers.length];
		Vector results = new Vector();
		Vector subtasks;
		subtasks = bo.splitParams(params, workers.length);
		int i;
		for (i = 0; i < workers.length; i++) {
			threads[i] = new WorkerThread(workers[i], t, subtasks.get(i), results);
			threads[i].start();
		}

		synchronized (results) {
			while (results.size() < workers.length) {
				try {
					results.wait();
				}
				catch (Exception e) {

				}
			}
		}

		return bo.combineResults(results);
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Podaj uchwyt farmera i co najmniej jeden uchwyt workera.");
			return;
		}
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		try {
			FarmerImpl instance = new FarmerImpl(args);
			java.rmi.Naming.rebind(args[0], instance);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Farmer gotowy.");
	}
}
