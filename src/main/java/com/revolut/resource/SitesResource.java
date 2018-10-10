/**
 * Copyright (C) 2018 Peter Nagy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * ======================================================================
 *
 * @author Peter Nagy - https://peternagy.ie
 * @since March 2018
 * @version 0.1
 * @description SitesResource - the endpoint handling sites data requests
 * @package ie.peternagy.alexa.top.api.resources.sites.resource
 */
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
