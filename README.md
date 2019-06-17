# Apruve

Innovation in the world of B2B Payments

## Issues

Please use [Github issues](https://github.com/apruve/apruve-java/issues) to request features or report bugs.

## Installation

### Maven

Add this dependency to your `pom.xml`:

    <dependency>
	    <groupId>com.apruve</groupId>
	    <artifactId>apruve-java</artifactId>
	    <version>1.0.0</version>
    </dependency>

**NOTE**: the `<version>XXX</version>` can be out of date in this README.

## Usage

### Setup
Before using the rest of the Apruve Java client, create an instance of ApruveClient.  This class handles all marshaling/unmarshaling
of request/response payloads and communications with the Apruve servers.  The ApruveClient constructor requires two parameters: an API
key, and an instance of ApruveEnvironment.  You may find your API key in the Technical section of your store's Apruve dashboard.

An ApruveEnvironment instance is used to specify the location of the Apruve servers.  There are three ways to get an ApruveEnvironment instance:
* The class defines a couple of convenience instances (`ApruveEnvironment.TEST` and `ApruveEnvironment.PROD`) that correspond to Apruve's test (https://test.apruve.com) and production (https://app.apruve.com) servers.  Most of the time you will be able to use one of these.
```
	ApruveClient client = new ApruveClient.("myAPIKey", ApruveEnvironment.TEST);
```	
* If you need to specify a non-standard URL for contacting the Apruve servers, you may construct an ApruveEnvironment specifying a URL scheme, host, and port:
```
	ApruveEnvironment env = new ApruveEnvironment("http", "localhost", 8080);
	ApruveClient client = new ApruveClient("myAPIKey", env);
```	
* The default constructor will retrieve values for scheme, host, and port from the environment variables `APRUVE_SCHEME`, `APRUVE_HOST`, and `APRUVE_PORT` (values default to `http`, `localhost`, and `8080` respectively if the variables are not set).
```	
	ApruveEnvironment env = new ApruveEnvironment();
	ApruveClient client = new ApruveClient("myAPIKey", env);
```
### Doing Stuff
Each model class defines methods that implement calls to the different RESTful APIs defined for that model.  The basic pattern is that 
actions that require a request body (creates, updates, etc.) are implemented as instance methods and use the state of the object
to generate the request body via JAXB.  Actions that do not require a request body are implemented as static methods.  The first argument
to each of these methods is the ApruveClient instance you created earlier.

Creating a new object:

	Invoice invoice = new Invoice("anOrderId");
	invoice.setAmountCents(10000);
	invoice.setTaxCents(1000);
	invoice.setShippingCents(1500);
	invoice.create(client);

Retrieving and updating an existing object:

	Invoice invoice = Invoice.get(client, "myInvoiceId");
	invoice.setMerchantInvoiceId("myInternalId");
	invoice.update(client);

API call return values are wrapped in an ApruveResponse<> object.  If the call was successful, call getResponseObject()
on this wrapper to get the API-specific response for that call (generally an instance of the API's model class).  If the call failed, the getErrorResponse() method will contain the failure
reason.

	ApruveResponse<Order> response = Order.finalize("anOrderId");
	if (response.success()) {
		System.out.println("The new status is: " + response.getResponseObject.getStatus());
	} else {
		System.err.println("The update failed: " + response.getErrorResponse());
	}

If you are implementing an online checkout, the Order class can be used to generate the JSON and secure hash strings that are needed to
initialize the Apruve Javascript library.  Instantiate the class, add line items, subscriptions, or other attributes, and call the toJson() and/or toSecureHash() methods:

	Order order = new Order("myMerchantId", "myAPIKey");
	order.setAmountCents(15000);
	order.setShippingCents(2500);
	order.setShopperId("theShopperId");
	OrderItem item = new OrderItem("itemTitle");
	item.setQuantity(1);
	item.setPriceTotalCents(12500);
	order.addOrderItem(item);
	String json = order.toJson();
	String hash = order.toSecureHash();

## Testing

    $ mvn test

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Write your code **and tests**
4. Ensure all [tests](#testing) still pass
5. Commit your changes (`git commit -am 'Add some feature'`)
6. Push to the branch (`git push origin my-new-feature`)
7. Create new pull request

