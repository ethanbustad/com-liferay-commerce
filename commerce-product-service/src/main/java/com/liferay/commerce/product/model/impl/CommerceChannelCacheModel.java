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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CommerceChannel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceChannel in entity cache.
 *
 * @author Marco Leo
 * @see CommerceChannel
 * @generated
 */
@ProviderType
public class CommerceChannelCacheModel implements CacheModel<CommerceChannel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceChannelCacheModel)) {
			return false;
		}

		CommerceChannelCacheModel commerceChannelCacheModel = (CommerceChannelCacheModel)obj;

		if (commerceChannelId == commerceChannelCacheModel.commerceChannelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceChannelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{commerceChannelId=");
		sb.append(commerceChannelId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", commerceCatalogId=");
		sb.append(commerceCatalogId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", active=");
		sb.append(active);
		sb.append(", consumerType=");
		sb.append(consumerType);
		sb.append(", consumerTypeSettings=");
		sb.append(consumerTypeSettings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceChannel toEntityModel() {
		CommerceChannelImpl commerceChannelImpl = new CommerceChannelImpl();

		commerceChannelImpl.setCommerceChannelId(commerceChannelId);
		commerceChannelImpl.setCompanyId(companyId);
		commerceChannelImpl.setUserId(userId);

		if (userName == null) {
			commerceChannelImpl.setUserName("");
		}
		else {
			commerceChannelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceChannelImpl.setCreateDate(null);
		}
		else {
			commerceChannelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceChannelImpl.setModifiedDate(null);
		}
		else {
			commerceChannelImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceChannelImpl.setCommerceCatalogId(commerceCatalogId);

		if (name == null) {
			commerceChannelImpl.setName("");
		}
		else {
			commerceChannelImpl.setName(name);
		}

		commerceChannelImpl.setActive(active);

		if (consumerType == null) {
			commerceChannelImpl.setConsumerType("");
		}
		else {
			commerceChannelImpl.setConsumerType(consumerType);
		}

		if (consumerTypeSettings == null) {
			commerceChannelImpl.setConsumerTypeSettings("");
		}
		else {
			commerceChannelImpl.setConsumerTypeSettings(consumerTypeSettings);
		}

		commerceChannelImpl.resetOriginalValues();

		return commerceChannelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceChannelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceCatalogId = objectInput.readLong();
		name = objectInput.readUTF();

		active = objectInput.readBoolean();
		consumerType = objectInput.readUTF();
		consumerTypeSettings = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(commerceChannelId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(commerceCatalogId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(active);

		if (consumerType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(consumerType);
		}

		if (consumerTypeSettings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(consumerTypeSettings);
		}
	}

	public long commerceChannelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceCatalogId;
	public String name;
	public boolean active;
	public String consumerType;
	public String consumerTypeSettings;
}