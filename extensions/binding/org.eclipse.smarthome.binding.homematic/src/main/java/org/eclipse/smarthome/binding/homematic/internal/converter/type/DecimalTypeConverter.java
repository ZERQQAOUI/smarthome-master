/**
 * Copyright (c) 2014,2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.binding.homematic.internal.converter.type;

import java.math.BigDecimal;

import org.eclipse.smarthome.binding.homematic.internal.converter.ConverterException;
import org.eclipse.smarthome.binding.homematic.internal.model.HmDatapoint;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.types.Type;

/**
 * Converts between a Homematic datapoint value and a openHAB DecimalType.
 *
 * @author Gerhard Riegler - Initial contribution
 */
public class DecimalTypeConverter extends AbstractTypeConverter<DecimalType> {
    @Override
    protected boolean toBindingValidation(HmDatapoint dp, Class<? extends Type> typeClass) {
        return dp.isNumberType() && typeClass.isAssignableFrom(DecimalType.class);
    }

    @Override
    protected Object toBinding(DecimalType type, HmDatapoint dp) throws ConverterException {
        if (dp.isIntegerType()) {
            return type.intValue();
        }
        return round(type.doubleValue()).doubleValue();
    }

    @Override
    protected boolean fromBindingValidation(HmDatapoint dp) {
        return dp.isNumberType() && dp.getValue() instanceof Number;
    }

    @Override
    protected DecimalType fromBinding(HmDatapoint dp) throws ConverterException {
        Number number = ((Number) dp.getValue()).doubleValue();
        if (dp.isIntegerType()) {
            return new DecimalType(new BigDecimal(number.intValue()));
        }
        return new DecimalType(round(number.doubleValue()));
    }

}