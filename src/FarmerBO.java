public interface FarmerBO extends java.io.Serializable {
	public java.util.Vector splitParams(Object params, int howManySubTasks);
	public Object combineResults(java.util.Vector partialResults);
}
