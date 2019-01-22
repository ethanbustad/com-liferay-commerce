/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.media.web.internal.portlet;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.media.web.internal.configuration.CommerceMediaDefaultImageConfiguration;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.settings.*;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN,
		"mvc.command.name=editCommerceMediaDefaultImage"
	},
	service = MVCActionCommand.class
)
public class EditCommerceMediaDefaultImageMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String fileEntryId = ParamUtil.getString(actionRequest, "fileEntryId");

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				_portal.getScopeGroupId(actionRequest),
				CommerceMediaDefaultImageConfiguration.class.getName()));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		modifiableSettings.setValue("defaultFileEntryId", fileEntryId);

		modifiableSettings.store();

		sendRedirect(
			actionRequest, actionResponse,
			ParamUtil.getString(actionRequest, "redirect"));
	}

	@Reference
	Portal _portal;

	@Reference
	SettingsFactory _settingsFactory;

}