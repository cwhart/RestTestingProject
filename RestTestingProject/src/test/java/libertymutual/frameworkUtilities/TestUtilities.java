package libertymutual.frameworkUtilities;

import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.ws.client.WebServiceClient;
import libertymutual.EndpointConfig;
import org.testng.annotations.Parameters;

@Parameters("designer")
public class TestUtilities extends TestNGCitrusTestRunner {

	public String determineEnvironmentVariable(WebServiceClient GetAutomobileRateClient, WebServiceClient IRatingServiceLocalClient) {
		if (!GetAutomobileRateClient.getEndpointConfiguration().getDefaultUri().equals(IRatingServiceLocalClient.getEndpointConfiguration().getDefaultUri())) {
			return EndpointConfig.ENVIRONMENT_NON_PROD;
		} else {
			return EndpointConfig.ENVIRONMENT_LOCAL;
		}
	}

	public boolean testsAreRunningLocally(WebServiceClient GetAutomobileRateClient, WebServiceClient IRatingServiceLocalClient) {
		if (!GetAutomobileRateClient.getEndpointConfiguration().getDefaultUri().equals(IRatingServiceLocalClient.getEndpointConfiguration().getDefaultUri())) {
			return false;
		} else {
			return true;
		}
	}
}
