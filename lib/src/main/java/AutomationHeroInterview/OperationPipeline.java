package AutomationHeroInterview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationPipeline {

	private int id;
	private boolean isUpStreamExecuted = false;
	public  List<OperationPipeline> upstreamDependencies;

	OperationPipeline(int id) {
		this.id = id;
		upstreamDependencies = new ArrayList<>();
	}

	public int getId() {
		return id;
	}
	
	OperationPipeline(){
		
	}

	public void exectute() {
		if (upstreamDependencies.isEmpty() == true) {
			isUpStreamExecuted = true;
		}
		boolean isAllUpStreamExecuted = upstreamDependencies.stream()
				.allMatch(operation -> operation.isUpStreamExecuted == true);
		if (isAllUpStreamExecuted == true) {
			isUpStreamExecuted = true;
		}
	}

	public List<OperationPipeline> getParents() {
		return upstreamDependencies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Operation : {" + "id : " + id + ", upstreamDependencies : " + upstreamDependencies + '}';
	}

	public static void main(String arg[]) {

	}

	public Set<OperationPipeline> sortOperations(Set<OperationPipeline> operations) {
		Set<OperationPipeline> pipeline = new LinkedHashSet<>();
		OperationPipeline parent = operations.stream().filter(op -> op.getParents().isEmpty()).findFirst().orElse(null);
		findDescendant(operations, pipeline, parent);
		return pipeline;
	}

	public void findDescendant(Set<OperationPipeline> operations, Set<OperationPipeline> pipeline,
			OperationPipeline operation) {
		if (pipeline.contains(operation))
			return;
		pipeline.add(operation);
		List<OperationPipeline> descendant = operations.stream().filter(ops -> ops.getParents().contains(operation))
				.collect(Collectors.toList());

		descendant.stream().forEach(des -> {
			findDescendant(operations, pipeline, des);
		});
	}

}
