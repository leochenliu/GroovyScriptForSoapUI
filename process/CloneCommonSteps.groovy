log.info '---------------------------------------------------'

def tc = testRunner.getTestCase()
def ts = tc.getTestSuite()
def pj = ts.getProject()
def ws = pj.getWorkspace()

def tpj = ws.getProjectByName('Weather_newEndpoint')
def tts = tpj.getTestSuiteByName('Weather TestSuite')
def ttc = tts.getTestCaseByName('Weather TestCase')

def mvsets = ['Properties','Login','setSessionId','Create','Delete','Get','Set','neg_Create','Logout']
mvsets.each { item -> 
//closure
	if (!ttc.getTestStepByName(item)) {
		ttc.cloneStep(tc.getTestStepByName(item), item)
	}
}

def dts = ttc.getTestStepByName('Create')
log.info dts.getClass().getName()


log.info '---------------------------------------------------'
