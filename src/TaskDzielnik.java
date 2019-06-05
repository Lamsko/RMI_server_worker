import java.util.Vector;

public class TaskDzielnik  implements Task {
	private static final long serialVersionUID = 1L;

	@Override
	public Object compute(Object args) {
		Vector<Integer> params = (Vector<Integer>) args;

		int numberToCompute = params.get(0).intValue();
		int lowerBoundry = params.get(1).intValue();
		int upperBoundry = params.get(2).intValue();

		int numberOfDividers = 0;
		for (int i = lowerBoundry; i <= upperBoundry; i++) {
			if (numberToCompute % i == 0) {
				numberOfDividers++;
			}
		}

		return Integer.valueOf(numberOfDividers);
	}
}
