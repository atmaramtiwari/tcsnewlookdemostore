/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.tcs.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import atg.taglib.json.util.JSONException;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.tcs.storefront.controllers.ControllerConstants;



/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/login")
public class LoginPageController extends AbstractLoginPageController
{
	private HttpSessionRequestCache httpSessionRequestCache;
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/my-account";
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}


	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
	{
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
			@RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
		if (!loginError)
		{
			storeReferer(referer, request, response);
		}
		return getDefaultLoginPage(loginError, session, model);
	}

	protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
	{
		if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
				&& StringUtils.contains(referer, request.getServerName()))
		{
			httpSessionRequestCache.saveRequest(request, response);
		}
	}

	//***************************** Social Commerce- ******************************************

	@RequestMapping(value = "/facebookLogin")
	public void doFacebookLogin(@RequestHeader(value = "referer", required = false) final String referer,
			@RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException, ServletException
	{
		StringBuffer callbackURL = null;
		final String additionalPermissions = request.getServletContext().getInitParameter("additionalPermissions");
		try
		{
			final StringBuffer callbackURLbuffer = request.getRequestURL();
			final int index = callbackURLbuffer.lastIndexOf("/");
			callbackURL = callbackURLbuffer.replace(index, callbackURLbuffer.length(), "").append("/callback");
			//callbackURL = URLEncoder.encode(callbackURLbuffer.toString(), "UTF-8");
			final String facebookAppId = request.getServletContext().getInitParameter("facebookAppId");
			final String authURL = "https://graph.facebook.com/oauth/authorize?client_id=" + facebookAppId + "&redirect_uri="
					+ callbackURL + "&scope=" + additionalPermissions;
			LOG.info("authURL: " + authURL);
			response.sendRedirect(authURL);
		}
		catch (final Exception e)
		{
			throw new ServletException(e);
		}

	}

	@RequestMapping(value = "/callback", params = "code", method = RequestMethod.GET)
	public void doFetchFacebookAccessToken(final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException, ServletException, IOException
	{
		String code = null;
		//final String facebookAppId = reqadditionalPermissionsuest.getServletContext().getInitParameter("facebookAppId");
		//final String facebookAppSecret = request.getServletContext().getInitParameter("facebookAppSecret");
		StringBuffer redirectURL = null;
		final String accessURL = null;
		final String accessToken = null;
		//	            final String webContent = null;
		String token = null;
		try
		{
			final StringBuffer redirectURLbuffer = request.getRequestURL();
			final int index = redirectURLbuffer.lastIndexOf("/");
			//final HttpSession httpSession = request.getSession();
			redirectURL = redirectURLbuffer.replace(index, redirectURLbuffer.length(), "").append("/callback");
			//URLEncoder.encode(redirectURLbuffer.toString(), "UTF-8");
			code = request.getParameter("code");
			//System.out.println(code);
			if (null != code)
			{
				LOG.info("Code: " + code);
				token = getFacebookAccessToken(code, request, redirectURL);
				final String finalToken = StringUtils.removeEnd(
						StringUtils.removeEnd(StringUtils.removeStart(token, "access_token="),
								StringUtils.substringAfterLast(token, "&")), "&");
				LOG.info("FInal Token after String Util Action is" + finalToken);

				/*
				 * if (getUserMailAddressFromJsonResponse(finalToken, httpSession) &&
				 * (request.getParameterValues("RegisterUserIfChecked") != null)) { doRegister(referer, userDataFromFB,
				 * bindingResult, model, request, response, redirectModel); System.out.println(userDataFromFB.getEmail()); }
				 */
				//accessURL = "https://graph.facebook.com/oauth/access_token";
				//"?client_id=" + facebookAppId + "&redirect_uri="
				//+ redirectURL + "&client_secret=" + facebookAppSecret + "&code=" + code;
				LOG.info("accessURL: " + accessURL);
				//	            final String text = "https://graph.facebook.com/oauth/access_token?client_id=";
				//webContent = getWebContentFromURL(accessURL);
				//	            webContent = getWebContentFromURL(accessURL, facebookAppId, redirectURL, facebookAppSecret, code);
				//	            accessToken = getAccessTokenFromWebContent(webContent);
				LOG.info("access Token: " + accessToken);
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/error.html");
			}

			if (null != token)
			{
				LOG.info("accessToken: " + accessToken);
				final FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
				final User user = facebookClient.fetchObject("me", User.class);
				//	final FBUser fbUser = new FBUser(user.getEmail());
				//	request.getSession().setAttribute("fbUser", fbUser);
				LOG.info("User object: " + user.toString());
				response.sendRedirect(request.getContextPath() + "/welcome.jsp");
			}

			if (null == token)
			{
				response.sendRedirect(request.getContextPath() + "/error.html");
			}

		}
		catch (final Exception e)
		{
			response.sendRedirect(request.getContextPath() + "/error.html");
			throw new ServletException(e);
		}

	}

	private String getFacebookAccessToken(final String faceCode, final HttpServletRequest request, final StringBuffer redirectUrl)
	{
		String token = null;
		if (faceCode != null && !"".equals(faceCode))
		{
			final String appId = request.getServletContext().getInitParameter("facebookAppId");
			//String redirectUrl = "http://localhost:8080/index.sec";
			final String faceAppSecret = request.getServletContext().getInitParameter("facebookAppSecret");
			final String url = "https://graph.facebook.com/oauth/access_token?client_id=" + appId + "&redirect_uri=" + redirectUrl
					+ "&client_secret=" + faceAppSecret + "&code=" + faceCode;
			try
			{

				final String USER_AGENT = "Mozilla/5.0";
				final HttpClient client = new DefaultHttpClient();
				LOG.info(client);
				final HttpGet urlrequest = new HttpGet(url);
				LOG.info(urlrequest);
				final HttpParams httpParams = client.getParams();

				HttpConnectionParams.setConnectionTimeout(httpParams, 150000);

				HttpConnectionParams.setSoTimeout(httpParams, 150000);
				urlrequest.addHeader("User-Agent", USER_AGENT);
				urlrequest.addHeader("accept", "application/xml");
				final HttpHost proxy = new HttpHost("proxy.tcs.com", 8080);
				ConnRouteParams.setDefaultProxy(httpParams, proxy);
				final HttpResponse response = client.execute(urlrequest);
				LOG.info("Response Code : " + response.getStatusLine().getStatusCode());
				if (response.getStatusLine().getStatusCode() != 200)
				{
					LOG.info(response.getStatusLine());
				}
				final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				final StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null)
				{
					result.append(line);
					LOG.info("result=====================" + result);
					token = result.toString();
				}
			}
			catch (final Exception e)
			{
				// YTODO: handle exception
			}

		}
		return token;
	}

	private boolean getUserMailAddressFromJsonResponse(final String accessToken, final HttpSession httpSession)
			throws JSONException
	{
		//final String email = null;
		final JsonNode emailNode = null;
		//	            HttpClient httpclient = new DefaultHttpClient();

		try
		{
			if (accessToken != null && !"".equals(accessToken))
			{
				final String newUrl = "https://graph.facebook.com/me?access_token=" + accessToken;
				LOG.info("JSON Response URL:" + newUrl);
				final HttpGet httpget = new HttpGet(newUrl);
				LOG.info("Get info from face --> executing request: " + httpget.getURI());
				final String USER_AGENT = "Mozilla/5.0";
				final HttpClient client = new DefaultHttpClient();

				LOG.info(client);
				final HttpGet urlrequest = new HttpGet(newUrl);
				LOG.info(urlrequest);

				final HttpParams httpParams = client.getParams();

				HttpConnectionParams.setConnectionTimeout(httpParams, 150000);

				HttpConnectionParams.setSoTimeout(httpParams, 150000);
				urlrequest.addHeader("User-Agent", USER_AGENT);
				urlrequest.addHeader("accept", "application/xml");
				final HttpHost proxy = new HttpHost("proxy.tcs.com", 8080);

				ConnRouteParams.setDefaultProxy(httpParams, proxy);
				//Handles HTTP Json Response to string object
				final HttpResponse response = client.execute(urlrequest);
				LOG.info("before===============" + response.getStatusLine().getStatusCode());
				final HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					// A Simple JSON Response Read
					final InputStream instream = entity.getContent();
					String result;// = convertStreamToString(instream);
					final BufferedReader br = new BufferedReader(new InputStreamReader(instream));

					// now you have the string representation of the HTML request

					final StringBuilder sb = new StringBuilder();

					String line = null;

					try
					{
						while ((line = br.readLine()) != null)
						{
							sb.append(line + "\n");
						}
					}
					catch (final IOException e)
					{
						e.printStackTrace();
					}
					result = sb.toString();
					LOG.info(" JSON RESPONSE Object: " + result);
					//create ObjectMapper instance
					//final ObjectMapper objectMapper = new ObjectMapper();
					//read JSON like DOM Parser
					//final JsonNode rootNode = objectMapper.readTree(result);
					//	            emailNode = rootNode.path("email");
					/*
					 * userDataFromFB.setFirstName(rootNode.path("first_name").toString());
					 * userDataFromFB.setLastName(rootNode.path("last_name").toString());
					 * userDataFromFB.setEmail(rootNode.path("email").toString()); userDataFromFB.setPwd("password");
					 * userDataFromFB.setTitleCode(rootNode.path("email").toString());
					 */
					LOG.info("Email from JSON String = " + emailNode.toString());
					/*
					 * final Gson json = new Gson(); final String res = json.toJson(response);
					 */
					LOG.info("response==============" + response);
					LOG.info("after===============" + response.getStatusLine().getStatusCode());
				}
				else
				{
					System.err.println("Token for facebook is null");
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return false;

	}


	//***********************************End Social Commerce******************************************
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@RequestHeader(value = "referer", required = false) final String referer, final RegisterForm form,
			final BindingResult bindingResult, final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getRegistrationValidator().validate(form, bindingResult);
		return processRegisterUserRequest(referer, form, bindingResult, model, request, response, redirectModel);
	}
}
