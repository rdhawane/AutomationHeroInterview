package AutomationHeroInterview;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class OperationPipelineTest {
	
	@Test
	public void testOperationSort() {
		OperationPipeline op1=new OperationPipeline(1);
		
		//op1.exectute();
		OperationPipeline op2=new OperationPipeline(2);
		op2.upstreamDependencies.add(op1);
		//op2.exectute();
		OperationPipeline op3=new OperationPipeline(3);
		op3.upstreamDependencies.add(op2);
		
		//op3.exectute();
		OperationPipeline op4=new OperationPipeline(4);
		op4.upstreamDependencies.add(op3);
		//op4.exectute();
		OperationPipeline op5=new OperationPipeline(5);
		op5.upstreamDependencies.add(op4);
		//op5.exectute();
		OperationPipeline op6=new OperationPipeline(6);
		op6.upstreamDependencies.add(op3);
		op6.upstreamDependencies.add(op4);
		
		Set<OperationPipeline> operationList = new HashSet();
		operationList.add(op6);
		operationList.add(op4);
		operationList.add(op2);
		operationList.add(op5);
		operationList.add(op3);
		operationList.add(op1);
		
		new OperationPipeline().sortOperations(operationList);
	}
}


