package com.revolut.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.cache.Cache;

import com.revolut.model.Account;
import com.revolut.request.CreateAccountRequest;
import com.revolut.request.DepositRequest;
import com.revolut.request.TransferRequest;
import com.revolut.request.WithdrawRequest;
import com.revolut.service.AccountService;
import com.revolut.service.UrlExtractor;

@Path("/v0.1/revolut")
public class SitesResource {
	
    
    @GET
    @Path("/createAccount")
    @Produces(MediaType.APPLICATION_JSON)
    @Cache(maxAge=1800, mustRevalidate = false, noStore = false, proxyRevalidate = false, sMaxAge = 1800)
    public Response createAccount(@Context UriInfo uriInfo) throws Exception{
	
    	Account account = null;
    	CreateAccountRequest createAccountRequest = UrlExtractor.exractCreateAccountRequest(uriInfo);
    	try {
    		account  = AccountService.getInstance().createAccount(createAccountRequest);
             
		} catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
    
    	return Response.ok(account.toString()).type(MediaType.APPLICATION_JSON).build();

       
    }
    
    @GET
    @Path("/deposit")
    @Produces(MediaType.APPLICATION_JSON)
    @Cache(maxAge=1800, mustRevalidate = false, noStore = false, proxyRevalidate = false, sMaxAge = 1800)
    public Response deposit(@Context UriInfo uriInfo) throws Throwable{
    	
    	DepositRequest depositRequest = UrlExtractor.exractDepositRequest(uriInfo);

    	try {
    		Account account = AccountService.getInstance().deposit(depositRequest);
            return Response.ok(account.toString()).type(MediaType.APPLICATION_JSON).build();


		} catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    	
    }
      
    @GET
    @Path("/withdraw")
    @Produces(MediaType.APPLICATION_JSON)
    @Cache(maxAge=1800, mustRevalidate = false, noStore = false, proxyRevalidate = false, sMaxAge = 1800)
    public Response withdraw(@Context UriInfo uriInfo) throws Throwable{
    	
    	
    	WithdrawRequest withdrawRequest = UrlExtractor.exractWithdrawRequest(uriInfo);

    	try {
    		Account account = AccountService.getInstance().withdraw(withdrawRequest);
            return Response.ok(account.toString()).type(MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    }
    
    @GET
    @Path("/transfer")
    @Produces(MediaType.APPLICATION_JSON)
    @Cache(maxAge=1800, mustRevalidate = false, noStore = false, proxyRevalidate = false, sMaxAge = 1800)
    public Response transfer(@Context UriInfo uriInfo) throws Throwable{
    	
    	TransferRequest transferRequest = UrlExtractor.exractTransferRequest(uriInfo);

    	try {
    		List<Account> account = AccountService.getInstance().transfer(transferRequest);
            return Response.ok(account.toString()).type(MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    	
    }

}
