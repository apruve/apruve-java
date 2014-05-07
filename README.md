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
	    <version>0.3.3</version>
    </dependency>

**NOTE**: the `<version>XXX</version>` can be out of date in this README.

## Usage

Before using the rest of the Apruve Java client, be sure to call the ApruveClient.init() method to configure the client with 
your API key and the environment you wish to use (TEST or PROD).  If you need to use multiple API keys in the same application
 for some reason, you will need to re-init the client with the appropriate key before each call.  
(Using the client in this way is not thread-safe).

	ApruveClient.init("myAPIKey", ApruveEnvironment.TEST);

During the checkout process, the PaymentRequest class can be used to generate the JSON and secure hash strings that are needed to
initialize apruve.js.  Instantiate the class, add line items, subscriptions, or other attributes, and call the toJson() and/or
toSecureHash() methods:

	PaymentRequest paymentRequest = new PaymentRequest(myMerchantId);
	paymentRequest.setAmountCents(5000);
	LineItem lineItem = new LineItem();
	lineItem.setQuantity(1);
	lineItem.setAmountCents(5000);
	lineItem.setTitle("A widget");
	paymentRequest.addLineItem(lineItem);
	json = paymentRequest.toJson();
	hash = paymentRequest.toSecureHash();

Each model class defines methods that implement calls to the different RESTful APIs defined for that model.  The basic pattern is that 
actions that require a request body (creates, updates, etc.) are implemented as instance methods and use the state of the object
to generate the request body via JAXB.  Actions that do not require a request body are implemented as statics.  For example, 
this will retrieve and update a Subscription:

	Subscription subscription = Subscription.get("abcd1234");
	subscription.setQuantity(5);
	subscription.setPriceEachCents(2500);
	subscription.setAmountCents(12500);
	subscription.update();

API calls (other than get() or getAll()) are wrapped in an ApruveResponse<> object.  If the call was successful, call getResponseObject()
on this wrapper to get the API-specific respose for that call.  If the call failed, the getErrorResponse() method will contain the failure
reason.

	ApruveResponse<PaymentRequestUpdateResponse> response = PaymentRequest.finalize("aPaymentRequestId");
	if (response.success()) {
		System.out.println("The new status is: " + response.getResponseObject.getStatus());
	} else {
		System.err.println("The update failed: " + response.getErrorResponse());
	}

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

