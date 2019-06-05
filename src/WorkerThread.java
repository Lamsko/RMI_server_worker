import java.util.Vector;

public class WorkerThread extends Thread {
	private Worker w;
	private Task t;
	private Object params;
	private Vector results;

	public WorkerThread(Worker aw, Task at, Object p, Vector res) {
		super();
		w = aw;
		t = at;
		params = p;
		results = res;
	}

	public void run() {
		Object o = null;
		try {
			o = w.compute(t, params);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (results) {
			results.add(o);
			results.notify();
		}
	}
}
