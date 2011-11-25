//def destProps = 'Weather TestSuite/Weather TestCase/Properties'

def sequenceId;
def sessionId;

def groovyUtils = new com.eviware.soapui.support.GroovyUtils( context )
def testCase = testRunner.testCase;
def testStep = testCase.getTestStepByName( "Login" );

// Get the session id and baseSequenceId from Login response
def responseLabel = testStep.getLabel() + "#Response";
def responseHolder;
try {
	responseHolder = groovyUtils.getXmlHolder( responseLabel );
} catch(Exception e) {
	testRunner.gotoStepByName( "end"  );
	return;
}

def CAI3G_NAMESPACE_URL  = "http://schemas.weather.com/";
responseHolder.namespaces["ns4"] = CAI3G_NAMESPACE_URL;

sessionId = responseHolder.getNodeValue( "//ns4:sessionId" );
if( sessionId == null )
{
	testRunner.gotoStepByName( "end"  );
	return;
}
sequenceId = responseHolder.getNodeValue( "//ns4:baseSequenceId" );

// assign sessionId and sequenceId
testStep = testCase.getTestStepByName( "Properties" );
testStep.setPropertyValue( "SessionId", sessionId );

def pj = testCase.getTestSuite().getProject()
def tts = pj.getTestSuiteByName('WEATHER_OpenInterface_FT')
def ttc = tts.getTestCaseByName('WEATHER')
def ttsp = ttc.getTestStepByName('Properties')

ttsp.setPropertyValue( "SessionId", sessionId );
ttsp.setPropertyValue( "SequenceId", sequenceId );
