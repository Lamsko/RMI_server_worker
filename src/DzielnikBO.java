import java.util.Vector;

public class DzielnikBO implements FarmerBO {
	private static final long serialVersionUID = 1L;

	@Override
	public Vector splitParams(Object params, int howManySubTasks) {
		Vector<Vector<Integer>> subTasks = new Vector<Vector<Integer>>();
		int number = ((Integer) params).intValue();
		int range = (number / 2) / howManySubTasks;

		for (int i = 0; i < howManySubTasks; i++) {
			int lowerBoundary = i * range + 2;
			int upperBoundary = (i + 1) * range + 2;
			Vector<Integer> subtask = new Vector<Integer>();
			subtask.add(Integer.valueOf(number));
			subtask.add(Integer.valueOf(lowerBoundary));
			subtask.add(Integer.valueOf(upperBoundary));
			subTasks.add(subtask);
		}

		return subTasks;
	}

	@Override
	public Object combineResults(Vector partialResults) {
		Vector<Integer> dividers = partialResults;
		int dividersCount = 0;

		for (int i = 0; i < dividers.size(); i++) {
			dividersCount += dividers.get(i).intValue();
		}
		return Integer.valueOf(dividersCount);
	}
}
