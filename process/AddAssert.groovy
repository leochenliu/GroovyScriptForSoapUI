def testCase = testRunner.testCase;
def steps = testCase.getTestSteps() 

steps.each { k,v ->
	if(v.getClass().getName() == 'com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep'){
		// add SchemaCompliance if there is no
		if(!v.getAssertions().containsKey('Schema Compliance')){
			v.addAssertion('Schema Compliance')
		}
	}	
}
