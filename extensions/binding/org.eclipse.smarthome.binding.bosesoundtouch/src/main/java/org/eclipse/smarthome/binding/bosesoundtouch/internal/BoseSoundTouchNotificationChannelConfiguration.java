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
package org.eclipse.smarthome.binding.bosesoundtouch.internal;

/**
 * Configuration class for soundtouch notification channel
 *
 * @author Ivaylo Ivanov - Initial contribution
 */
public class BoseSoundTouchNotificationChannelConfiguration {

    public static final String MIN_FIRMWARE = "14";
    public static final String MODEL_TYPE = "sm2";

    public static final String NOTIFICATION_VOLUME = "notificationVolume";
    public static final String NOTIFICATION_SERVICE = "notificationService";
    public static final String NOTIFICATION_REASON = "notificationReason";
    public static final String NOTIFICATION_MESSAGE = "notificationMessage";

    public Integer notificationVolume;
    public String notificationService;
    public String notificationReason;
    public String notificationMessage;
    
    public static boolean isSupportedFirmware(String firmware) {
        return firmware != null && firmware.compareTo(MIN_FIRMWARE) > 0;
    }
    
    public static boolean isSupportedHardware(String hardware) {
        return MODEL_TYPE.equals(hardware);
    }
}
