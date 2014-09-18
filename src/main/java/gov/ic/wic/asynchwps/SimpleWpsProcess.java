package gov.ic.wic.asynchwps;

import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;


@DescribeProcess(title = "Simple", description = "Gotta start somewhere")
public class SimpleWpsProcess {
    
    @DescribeResult(name="result")
    public String execute(@DescribeParameter(name = "id") String id) throws Exception {
    	return "hello world";
    }
}