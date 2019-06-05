public interface Farmer extends java.rmi.Remote {
	public Object compute(Task t, Object params, FarmerBO bo)
		throws java.rmi.RemoteException;
}
