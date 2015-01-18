package fr.vidal.webservices.productservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2014-10-24T14:32:31.402+02:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "ProductService", 
                  wsdlLocation = "http://localhost:8011/merlin-service/services/ProductService?wsdl",
                  targetNamespace = "urn:Vidal") 
public class ProductService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:Vidal", "ProductService");
    public final static QName ProductServiceHttpPort = new QName("urn:Vidal", "ProductServiceHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8011/merlin-service/services/ProductService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ProductService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8011/merlin-service/services/ProductService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ProductService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProductService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProductService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ProductService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ProductService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ProductService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ProductService
     */
    @WebEndpoint(name = "ProductServiceHttpPort")
    public ProductService getProductServiceHttpPort() {
        return super.getPort(ProductServiceHttpPort, ProductService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProductService
     */
    @WebEndpoint(name = "ProductServiceHttpPort")
    public ProductService getProductServiceHttpPort(WebServiceFeature... features) {
        return super.getPort(ProductServiceHttpPort, ProductService.class, features);
    }

}
